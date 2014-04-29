package dataSource;

import domain.Booking;
import domain.Facility;
import domain.Guest;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Tomoe
 */
public class DBFacadeForFacility
{

    private Connection con;
    private DataMapperForFacility facilityMapper;
    private static DBFacadeForFacility instance;
    private UnitOfWorkForFacility uowFacility;

    public DBFacadeForFacility()
    {
        con = new DBConnector().getConnection();
        facilityMapper = new DataMapperForFacility(con);

    }

    public static DBFacadeForFacility getInstance()
    {
        if (instance == null)
        {
            instance = new DBFacadeForFacility();

        }
        return instance;
    }

    public ArrayList<Booking> getBookedfac(String type, Date bookingdate, int bookingtime)
    {
        return facilityMapper.getBookedfac(type, bookingdate, bookingtime);
    }

    public int getMaxUsers(int facId)
    {
        return facilityMapper.getMaxUsers(facId);
    }

    public ArrayList<Facility> getfacilitylist(String type)
    {
        return facilityMapper.getfacilitylist(type);
    }

    public int getnextbookingno()
    {
        return facilityMapper.getNextBookingNo();
    }

    public int getbookingno(int facId, Date bookingdate, int bookingtime)
    {
        return facilityMapper.getBookingno(facId, bookingdate, bookingtime);
    }

    public int remaingPlace(String type, Date bookingdate, int bookingtime, int facid)
    {
        return facilityMapper.remaingPlace(type, bookingdate, bookingtime, facid);
    }

    public ArrayList<Facility> getFacArrayForJlist(String type, Date bookingdate, int bookingtime)
    {
        return facilityMapper.getFacArrayForJlist(type, bookingdate, bookingtime);
    }

    public ArrayList<Guest> getWaitingList(int facID, Date bookingdate, int bookingtime)
    {
        return facilityMapper.getWaitingListForJlist(facID, bookingdate, bookingtime);
    }

    public void startProcessGuestBusinessTransaction()
    {
        uowFacility = new UnitOfWorkForFacility(facilityMapper);
    }

    public void registerNewGuest(Guest guest)
    {
        if (uowFacility != null)
        {
            //uowFacility.registerNewGuest(guest);
        }
    }

    public ArrayList<String> getFacilityTypes()
    {
        return facilityMapper.getTypes();
    }

    public boolean createNewFacility(int facNum, String type)
    {
        return facilityMapper.createNewFacility(facNum, type);
    }

    public int getFacilityNumber(String type)
    {
        return facilityMapper.getFacilityNumber(type);
    }

    public boolean updateWaitingPos(Booking booking)
    {
        System.out.println("booking: " + booking.getGuestno());

        uowFacility.registerDeleteBooking(booking);

        return true;
    }

    public int getBookingno(int facId, Date bookingdate, int bookingtime)
    {
        int bookingno = facilityMapper.getBookingno(facId, bookingdate, bookingtime);

        return bookingno;
    }

    public ArrayList<Booking> getBookingList(String guestno)
    {
        return facilityMapper.getBookingList(guestno);
    }

    public ArrayList<Booking> getBookingDetails(int bookingid)
    {
        return facilityMapper.getBookingDetails(bookingid);

    }

    public void registerNewBooking(Booking bookingSQL1)
    {
        if (uowFacility != null)
        {
            uowFacility.registerNewBooking(bookingSQL1);
        }

    }

    public void registerNewBookingStatus(Booking bookingSQL2)
    {
        if (uowFacility != null)
        {
            uowFacility.registerNewBookingStatus(bookingSQL2);
        }

    }

    public void registerNewInstructorBooking(Booking booking)
    {
        if (uowFacility != null)
        {
            uowFacility.registerNewInstructorBooking(booking);
        }

    }

    public void registerDeletBooking(Booking deleteSql)
    {
        if (uowFacility != null)
        {
            uowFacility.registerDeleteBooking(deleteSql);
        }

    }

    public boolean commitProcessBookingBusinessTransaction()
    {
        boolean status = false;
        if (uowFacility != null)
        {
            status = uowFacility.commit(con);
            uowFacility = null;

        }
        return status;
    }

    public boolean fourBookingPerDay(String guestno, Date date)
    {
        return facilityMapper.fourBookingPerDay(guestno, date);
    }

    public boolean addInstructor(String name, String type)
    {
        return facilityMapper.addInstructor(name, type);
    }

    public ArrayList<Booking> getFacArrayForBookingInstructorJlist(Date bookingdate, int bookingtime, String username)
    {
        return facilityMapper.getFacArrayForBookingInstructorJlist(bookingdate, bookingtime, username);
    }

    public ArrayList<Booking> getFacArrayForBookingInstructorJlist(String type, Date dd, int hour, String username)
    {
        return facilityMapper.getFacArrayForBookingInstructorJlist(type, dd, hour, username);
    }

    public boolean checkInstructorAlreadyThere(int bookingId, String username)
    {
        return facilityMapper.checkInstructorAlreadyThere(bookingId, username);
    }

    public boolean removeInstructor(int bookingId, String username)
    {
        return facilityMapper.removeInstructorFromBooking(username, bookingId);
    }

    public boolean checkOnlyOneBooking(String type, String guestNo, Date dd, int selectedHour)
    {
        return facilityMapper.checkOnlyOneBooking(guestNo, type, dd, selectedHour);
    }

}
