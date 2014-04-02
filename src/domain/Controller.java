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

    private boolean processingGuest, processingReservation;        // Represent state of business transaction
    private Guest currentGuest;             // Guest in focus
    private Reservation currentReservation; //Reservation in focus
    private final DBFacade facade;
    private ArrayList<Guest> guests;
    private Mail mailsender;
    private final DBFacadeForFacility facadeF;
    public Controller()
    {
        processingGuest = false;
        processingReservation = false;
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
    public ArrayList<Guest> getGuests(int reservationNo)
    {
        if (processingGuest)
        {
            return null;
        }

        facade.startProcessGuestBusinessTransaction(); // method in Fascade
        processingGuest = true;
        guests = facade.getGuests(reservationNo);
        return guests;
    }

    public void resetGuest()
    {
        processingGuest = false;
        currentGuest = null;
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

    public Guest createGuest(int reservationNo, String guestNo, int password, String guestFirstName, String guestFamilyName,
            String address, String country, int phone, String email, String agency)
    {
        if (processingGuest)
        {
            return null;
        }

        facade.startProcessGuestBusinessTransaction();
//        int newReservationNo = facade.getNextReservationNo();// DB-generated unique ID --< 

        if (reservationNo != 0)
        {
            processingGuest = true;

            currentGuest = new Guest(reservationNo, guestNo, password, guestFirstName, guestFamilyName, address, country, phone, email, agency); //THIS LINE WAS FOR TESTING
            facade.registerNewGuest(currentGuest);
            processingGuest = false;
        }
        else
        {

            currentGuest = null;
        }

        return currentGuest;
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

//        facade.startProcessGuestBusinessTransaction();
//
//        boolean result = facade.updateDeposit(currentReservation);
//        boolean test = facade.commitProcessGuestBusinessTransaction();
//        return result;
    }

    public boolean sendInvoice(String email, Reservation reservation, ArrayList<Guest> guestarray, String roomType, int roomPrice) throws MessagingException
    {
        {
            return mailsender.sendInvoice(email, reservation, guestarray, roomType, roomPrice);
        }
    }

    public boolean sendConfirmation(String email, Reservation reservation, ArrayList<Guest> guestarray, String roomType) throws MessagingException
    {
        {
            return mailsender.sendConfirmation(email, reservation, guestarray, roomType);
        }
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
        Boolean result = false;
       try {
           
        facade.startProcessOrderBusinessTransaction(); // create new object for Unit of Work
        result = facade.getGuestInfo(userName, password);
          } 
       
     catch (Exception e)
            {
                e.getMessage();
            }
        
        return result;
    }



    public Boolean checkLogInForEmp(String userName, String password)
    {
        Boolean result = false;
       try {
           
        facade.startProcessOrderBusinessTransaction(); // create new object for Unit of Work
        result = facade.getEmpInfo(userName, password);
          } 
       
     catch (Exception e)
            {
                e.getMessage();
            }
        
        return result;
    }


public int remaingPlace(String type, Date bookingdate, int bookingtime,int facid)
{ ArrayList<Booking> booking = facadeF.getBookedfac(type, bookingdate, bookingtime);
int answer=facadeF.getMaxUsers(facid);    
for (int i = 0; i < booking.size(); i++)
    {
        Booking booking1 = booking.get(i);
        if(booking1.getFacilityId()==facid)
        {answer=booking1.getMaxUsers()-booking1.getBookedNumOfUsers();
        return answer;
        }
    }

return answer;
}
      



public ArrayList<Facility> getfacilitylist (String type)
{return facadeF.getfacilitylist(type);

}
        
public ArrayList<Facility> getFacArrayForJlist(String type, Date bookingdate, int bookingtime)
{ return facadeF.getFacArrayForJlist(type, bookingdate, bookingtime);
    }

   public String getString(Facility fac)
    {
        return "FacilityID: " +  fac.getFacID() + "   Minimum user: " + fac.getMinUsers() + "   Max user: " + fac.getMaxUsers()+"   Room remaining: "+fac.roomRemaining;
    }

        }
