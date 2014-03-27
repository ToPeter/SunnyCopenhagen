package domain;

import MailSender.Mail;
import dataSource.DBFacade;
import java.util.ArrayList;
import java.util.Date;
import javax.mail.MessagingException;

public class Controller
{

    private boolean processingGuest;	// Represent state of business transaction
    private Guest currentGuest;      	// Guest in focus
    private final DBFacade facade;
    private ArrayList<Guest> guests;
    private Mail mailsender;

    public Controller()
    {
        processingGuest = false;
        currentGuest = null;
        facade = DBFacade.getInstance();
        mailsender=new Mail();

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

            currentGuest = new Guest(reservationNo, guestNo, password, guestFirstName, guestFamilyName, address, country, password, email, agency); //THIS LINE WAS FOR TESTING
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

        Reservation reservation = new Reservation(roomNo, reservationNo, fromDate, endDate, boookingDate, depositPaid);
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

    public boolean updateDeposit(int reservationNoSelected)
    {

       return facade.updateDeposit(reservationNoSelected);
    }

    public boolean sendInvoice(String email, Reservation reservation, ArrayList<Guest> guestarray, String roomType, int roomPrice) throws MessagingException
    {
        return mailsender.sendInvoice(email, reservation, guestarray, roomType, roomPrice);
        
    }
            
            }
