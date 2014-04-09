package domain;

import MailSender.Mail;
import dataSource.DBFacade;
import dataSource.DBFacadeForFacility;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import javax.mail.MessagingException;

public class Controller
{

    private boolean processingGuest, processingReservation, processingBooking;        // Represent state of business transaction
    private Guest currentGuest;             // Guest in focus
    private GuestID currentGuestID;
    private Reservation currentReservation; //Reservation in focus
    private final DBFacade facade;
    private ArrayList<Guest> guests;
    private Mail mailsender;
    private final DBFacadeForFacility facadeF;
    private boolean processingGuestID;
    private ArrayList<GuestID> guestsID, arrayOfGuestID;

    public Controller()
    {
        processingGuest = false;
        processingGuestID = false;
        processingReservation = false;
        processingBooking = false;
        currentGuest = null;
        facade = DBFacade.getInstance();
        facadeF = DBFacadeForFacility.getInstance();
        mailsender = new Mail();
        currentReservation = null;

    }

    public Reservation getReservation(int reservationNo)
    {
        return facade.getReservation(reservationNo);

    }

    public int getNextReservationNo()
    {
        return facade.getNextReservationNo();
    }

    public ArrayList<Room> getRoomsAvailable(Date fromDate, Date endDate, String type)
    {
        System.out.println("controller" + fromDate);
        return facade.getRoomsAvailable(fromDate, endDate, type);

    }

    public int[] getPriceList()
    {
        int[] priceList = new int[3];

        priceList = facade.getPriceList();
        return priceList;
    }

    // ----------------------------- Unit Of Work ----------------------------
    public boolean getGuest(int guestID)
    {
        boolean guestFound = false;

        if (processingGuest)
        {
            return guestFound;
        }

        facade.startProcessGuestBusinessTransaction(); // method in Fascade
        processingGuest = true;

        currentGuestID = facade.getGuest(guestID);
        processingGuest = false;
        if (currentGuestID != null)
        {
            guestFound = true;
        }

        return guestFound;
    }

    public ArrayList<GuestID> getGuestsID(int guestID)
    {
        if (processingGuestID)
        {
            return null;
        }

        facade.startProcessGuestBusinessTransaction(); // method in Fascade
        processingGuestID = true;
        guestsID = facade.getGuestsID(guestID);
        return guestsID;
    }

    public void resetGuest()
    {
        processingGuest = false;
        currentGuest = null;
        currentGuestID = null;
    }

    public boolean saveGuest()
    {
        boolean status = false;
        if (processingGuest)
        {
            //== ends ongoing business transaction

            status = facade.commitProcessGuestBusinessTransaction();
            processingGuest = false;
            currentGuest = null;
        }
        return status;
    }

    public ArrayList<Reservation> getReservationDepositNotPaid()
    {

        return facade.getReservationDepositNotPaid();
    }

    public Guest deleteGuest()
    {
        if (processingGuest)
        {
            facade.startProcessGuestBusinessTransaction();
            facade.registerDeleteGuest(currentGuest);
        }
        return currentGuest;
    }

    public Guest createGuest(int reservationNo, String guestNo, int password, String agency)
    {
        if (processingGuest)
        {
            return null;
        }

//       facade.startProcessGuestBusinessTransaction();
//        int newReservationNo = facade.getNextReservationNo();// DB-generated unique ID --< 
        
        if (reservationNo != 0)
        {
            processingGuest = true;

            currentGuest = new Guest(reservationNo, guestNo, password, agency, currentGuestID.getId());
            facade.registerNewGuest(currentGuest);
            processingGuest = false;
        }
        else
        {

            currentGuest = null;
        }

        return currentGuest;
    }

    public GuestID createGuestID(int guestID, String guestFirstName, String guestLastName, String address, String country, int number, String email)
    {
        if (processingGuest)
        {
            return null;
        }

        //       facade.startProcessGuestBusinessTransaction();
//        int newReservationNo = facade.getNextReservationNo();// DB-generated unique ID --< 
        if (guestID != 0)
        {
            processingGuest = true;

            currentGuestID = new GuestID(guestID, guestFirstName, guestLastName, address, country, number, email); //THIS LINE WAS FOR TESTING
            facade.registerNewGuestID(currentGuestID);

            processingGuest = false;

        }
        else
        {

            currentGuestID = null;
        }

        return currentGuestID;
    }

    public boolean bookRoom(int roomNo, int reservationNo, Date fromDate, Date endDate, Date boookingDate, int depositPaid)
    {

        Reservation reservation = new Reservation(roomNo, reservationNo, fromDate, endDate, boookingDate, depositPaid, 1111, 0);
        facade.bookRoom(reservation);

        boolean result = true;

        System.out.println("RESERVATIONNO: " + reservationNo + "\n-----||-----\nORDER BOOKED: \t\nFrom: " + fromDate.toString() + " \tRoom: " + roomNo + " Booked: " + boookingDate.toString() + "\n\tTo  " + endDate.toString() + "\nDepositpaied: " + depositPaid);

        return result;
    }

    
    public void commit()
    {
        facade.commitProcessGuestBusinessTransaction();
    }

    public String getReservationDetails(int parseInt)
    {
        return facade.getReservationString(parseInt);
    }

    public boolean updateDeposit()
    {
        facade.startProcessGuestBusinessTransaction();
        //facade.registerDirtyReservation(currentReservation);
        boolean result = facade.updateDeposit(currentReservation);
        boolean test = facade.commitProcessGuestBusinessTransaction();
        System.out.println("printing trest in contriller update deposti " + test);
        return (result == test);
    }

    public boolean sendInvoice(ArrayList<Guest> guestarray, Reservation reservation, String roomType, int roomPrice) throws MessagingException
    {

        return mailsender.sendInvoice(guestarray, reservation, roomType, roomPrice);

    }

    public boolean sendConfirmation(ArrayList<Guest> guestarray, Reservation reservation, String roomType) throws MessagingException
    {

        return mailsender.sendConfirmation(guestarray, reservation, roomType);

    }

    public String getRoomType(int roomNo)
    {
        return facade.getRoomType(roomNo);
    }

    public boolean getCurrentReservation(int reservationNoSelected)
    {
        boolean result = false;

        facade.startProcessOrderBusinessTransaction();

        currentReservation = facade.getReservation(reservationNoSelected);
        result = true;
        return result;
    }

    public Boolean checkLogInForGuest(String userName, String password)
    {
        boolean result = false;
        try
        {
            facade.startProcessOrderBusinessTransaction(); // create new object for Unit of Work
            result = facade.getGuestInfo(userName, password);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return result;
    }

    public Boolean checkLogInForEmp(String userName, String password)
    {
        boolean result = false;
        try
        {

            facade.startProcessOrderBusinessTransaction(); // create new object for Unit of Work
            result = facade.getEmpInfo(userName, password);
        } catch (Exception e)
        {
            e.getMessage();
        }

        return result;
    }

    public int remaingPlace(String type, Date bookingdate, int bookingtime, int facid)
    {
        ArrayList<Booking> booking = facadeF.getBookedfac(type, bookingdate, bookingtime);
        int answer = facadeF.getMaxUsers(facid);
        for (int i = 0; i < booking.size(); i++)
        {
            Booking booking1 = booking.get(i);
            if (booking1.getFacilityId() == facid)
            {
                answer = booking1.getMaxUsers() - booking1.getBookedNumOfUsers();
                return answer;
            }
        }

        return answer;
    }

    public ArrayList<Facility> getfacilitylist(String type)
    {
        return facadeF.getfacilitylist(type);

    }

    public ArrayList<Facility> getFacArrayForJlist(String type, Date bookingdate, int bookingtime)
    {
        return facadeF.getFacArrayForJlist(type, bookingdate, bookingtime);
    }

    public String getString(Facility fac)
    {
        return "FacilityID: " + fac.getFacID() + "   Minimum user: " + fac.getMinUsers() + "   Max user: " + fac.getMaxUsers() + "   Room remaining: " + fac.roomRemaining;
    }

    public String getCurrentGuestIDFirstName()
    {
        return currentGuestID.getGuestFirstName();
    }

    public String getCurrentGuestIDLastName()
    {
        return currentGuestID.getGuestFamilyName();
    }

    public String getCurrentGuestIDAdress()
    {
        return currentGuestID.getAddress();
    }

    public String getCurrentGuestIDCountry()
    {
        return currentGuestID.getCountry();
    }

    public String getCurrentGuestIDPhoneNo()
    {
        return "" + currentGuestID.getPhoneNo();
    }

    public String getCurrentGuestIDEmail()
    {
        return currentGuestID.getEmail();
    }

    public void updateGuest(int reservationNo, String guestNo, int password, String text)
    {

    }

    public boolean updateWaitingPos(int bookingno, String guestno)
    {
         facadeF.startProcessGuestBusinessTransaction();
        boolean result;
        Booking booking = new Booking (bookingno, guestno);
       
        
        result = facadeF.updateWaitingPos(booking);
        facadeF.commitProcessBookingBusinessTransaction();
        return result;
    }

    public boolean createFacilityBooking(Facility facility, String type, String guestNo, Date bookingdate, int bookingtime, int inno)
    {
        int waitingpos;
        int remainroom = facadeF.remaingPlace(type, bookingdate, bookingtime, facility.getFacID());

        if (remainroom <= 0)
        {
            waitingpos = Math.abs(remainroom - 1);
        }
        else
        {
            waitingpos = 0;
        }

        facadeF.startProcessGuestBusinessTransaction();
        int bookingno = facadeF.getBookingno(facility.getFacID(), bookingdate, bookingtime);
        
        Booking bookingSQL1 = new Booking(bookingno,facility.getFacID() ,bookingdate,bookingtime);
        facadeF.registerNewBooking(bookingSQL1);
        System.out.println("guestno "+ guestNo);
        Booking bookingSQL2 = new Booking(bookingno,guestNo,waitingpos,inno);
        facadeF.registerNewBookingStatus(bookingSQL2);
        
        return facadeF.commitProcessBookingBusinessTransaction(); // return boolean if commit was or wasNOT succesful
        
      //  return true;
    }
//    }
//     public Guest createGuest(int reservationNo, String guestNo, int password, String agency)
//    {
//        if (processingGuest)
//        {
//            return null;
//        }
//
////       facade.startProcessGuestBusinessTransaction();
////        int newReservationNo = facade.getNextReservationNo();// DB-generated unique ID --< 
//        if (reservationNo != 0)
//        {
//            processingGuest = true;
//
//            currentGuest = new Guest(reservationNo, guestNo, password, agency, currentGuestID.getId());
//            facade.registerNewGuest(currentGuest);
//            processingGuest = false;
//        }
//        else
//        {
//
//            currentGuest = null;
//        }
//
//        return currentGuest;
//    }

    

    public int getBookingno(int facId, Date bookingdate, int bookingtime)
    {
        return facadeF.getBookingno(facId, bookingdate, bookingtime);
    }

    public ArrayList<Guest> getWaitingList(int facID, Date bookingdate, int bookingtime)
    {
        return facadeF.getWaitingList(facID, bookingdate, bookingtime);
    }

    public ArrayList<Guest> getGuestArrayForMail(int reservationNo)
    {
        return facade.getGuestArrayForMail(reservationNo);
    }

    public String getGuestNameLogIn(String userName)
    {
        String name = facade.getGuestLogInName(userName);
        return name;
    }

    public String getEmpNameLogIn(String userName)
    {
        String name = facade.getEmpLogInName(userName);
        return name;
    }

    public ArrayList<Booking> getBookingList(String guestno)
    {
        return facadeF.getBookingList(guestno);
    }

    public String getBookingListString(Booking booking)
    {
        return "Booking ID: " + booking.bookingId + " Booking date: " + booking.bookingdate + " Booking time: " + booking.bookingtime;
    }

    public ArrayList<Booking> getBookingDetails(int bookingid)
    {
        return facadeF.getBookingDetails(bookingid);
    }

    public boolean searchGuestID(String guestno)
    {
        boolean result = false;
        currentGuestID = facade.searchGuest(guestno);
        if (currentGuestID != null)
        {
            result = true;
        }
        return result;
    }

    public int getCurrentGuestIDid()
    {
        return currentGuestID.getId();
    }
    
    public void changeCurrentGuestID(int index)
    {
        if(arrayOfGuestID.size()>1)
        {
            currentGuestID = arrayOfGuestID.get(index);
        }
    }

    public boolean searchGuestByReservationNO(int reservationNO)
    {

    boolean result = false;
        arrayOfGuestID = facade.searchGuestByReservationNO(reservationNO);
        if (arrayOfGuestID != null)
        {
            currentGuestID = arrayOfGuestID.get(0);
            result = true;
        }
        return result;
    }
    public int getSizeOfArrayOfGuestID()
    {
        return arrayOfGuestID.size();
    }
    public ArrayList<String> getNamesInArrayOfGuest()
    {
        ArrayList<String> arrayOfNames = new ArrayList<>();
        for (int i = 0; i < arrayOfGuestID.size(); i++)
        {
            GuestID guestID = arrayOfGuestID.get(i);
            String name = guestID.getGuestFirstName();
            arrayOfNames.add(name);
        }
        return arrayOfNames;
    }
    
    public void commitFac()
    {
        facadeF.commitProcessBookingBusinessTransaction();
    }
}
