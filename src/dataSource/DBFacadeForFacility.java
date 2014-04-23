/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        return facilityMapper.getBookedfac(type, bookingdate, bookingtime, con);
    }

    public int getMaxUsers(int facId)
    {
        return facilityMapper.getMaxUsers(facId, con);
    }

    public ArrayList<Facility> getfacilitylist(String type)
    {
        return facilityMapper.getfacilitylist(type, con);
    }

    public int getnextbookingno()
    {
        return facilityMapper.getNextBookingNo(con);
    }

    public int getbookingno(int facId, Date bookingdate, int bookingtime)
    {
        return facilityMapper.getBookingno(facId, bookingdate, bookingtime, con);
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
        return facilityMapper.getWaitingListForJlist(facID, bookingdate, bookingtime, con);
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

    public boolean updateWaitingPos(int bookingno, String guestno)
    {
        return facilityMapper.updateWaitingPos(bookingno, guestno, con);
    }

    public int getBookingno(int facId, Date bookingdate, int bookingtime)
    {
        return facilityMapper.getBookingno(facId, bookingdate, bookingtime, con);
    }

    public ArrayList<Booking> getBookingList(String guestno)
    {
        return facilityMapper.getBookingList(guestno, con);
    }

    public ArrayList<Booking> getBookingDetails(int bookingid)
    {
        return facilityMapper.getBookingDetails(bookingid, con);
    }

//    public boolean createInstructorBooking(Facility facility, String type, String guestNo, Date bookingdate, int inno, int bookingtime)
//    {
//        return facilityMapper.createInstructorBooking(facility, type, guestNo, bookingdate, bookingtime, inno, con);
//    }
    
    public boolean createFacilityBooking(Facility facility, String type, String guestNo, Date bookingdate, int bookingtime, int inno)
    {
        return facilityMapper.createFacilityBooking(facility, type, guestNo, bookingdate, bookingtime, inno, con);
    }

    public ArrayList<Booking> getFacArrayForBookingInstructorJlist(Date bookingdate, int bookingtime, String username)
    {
       return facilityMapper.getFacArrayForBookingInstructorJlist(bookingdate, bookingtime,username);
    }

    public ArrayList<Booking> getFacArrayForBookingInstructorJlist(String type, Date dd, int hour, String username)
    {
        return facilityMapper.getFacArrayForBookingInstructorJlist(type,dd, hour,username);
    }

    public void saveInstructorBooking(Booking booking)
    {
        facilityMapper.saveInstructorBooking(booking);
    }

   
    

}
