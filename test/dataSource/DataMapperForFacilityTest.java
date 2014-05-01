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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Tomoe
 */
public class DataMapperForFacilityTest
{

    private Connection con;
    DataMapperForFacility facilitymapper;
    DateFormat format;
    ArrayList<Booking> bookingUpdateList;

    public DataMapperForFacilityTest()
    {

    }

    @Before
    public void setUp()
    {
        con = DBConnector.getTestConnection();
        SetUpDM.setUpForFacilityTest(con);
        facilitymapper = new DataMapperForFacility(con);
        format = new SimpleDateFormat("dd-MM-yy");
        bookingUpdateList = new ArrayList();

    }

    @After
    public void closeConnection()
    {
        DBConnector.releaseConnection();
    }

    /**
     * Test of getBookedfac method, of class DataMapperForFacility.
     *
     */
    @Test
    public void testGetBookedfac() throws ParseException
    {
        System.out.println("getBookedfac");
        String type = "tennis";
        Date bookingdate = format.parse("09-04-14");
        int bookingtime = 17;
        int arraySize = 6;
        int bookingId1 = 1094;
        int bookingId2 = 1096;

        ArrayList<Booking> result = facilitymapper.getBookedfac(type, bookingdate, bookingtime);
        assertEquals(arraySize, result.size());
        assertEquals(bookingId1, result.get(0).getBookingId());
        assertEquals(bookingId2, result.get(1).getBookingId());
    }

    /**
     * Test of getMaxUsers method, of class DataMapperForFacility.
     */
    @Test
    public void testGetMaxUsers()
    {
        System.out.println("getMaxUsers");
        int facIDTennis = 101;
        int facIDVolleyball = 301;
        int notExistingfacID = 1000;

        int expResultTennis = 4;
        int expResultVB = 10;
        int resultTennis = facilitymapper.getMaxUsers(facIDTennis);
        int resultVB = facilitymapper.getMaxUsers(facIDVolleyball);
        int resultNotExisiting = facilitymapper.getMaxUsers(notExistingfacID);

        assertEquals(expResultTennis, resultTennis);
        assertEquals(expResultVB, resultVB);
        assertEquals(0, resultNotExisiting);
    }

    /**
     * Test of getfacilitylist method, of class DataMapperForFacility.
     */
    @Test
    public void testGetfacilitylist()
    {
        System.out.println("getfacilitylist");
        String type = "badminton";
        int facNoCount = 0;
        ArrayList<Facility> result = facilitymapper.getfacilitylist(type);

        for (int i = 0; i < result.size(); i++)
        {
            Facility facility = result.get(i);
            int tempfacid = facility.getFacID();

            for (int k = 201; k <= 211; k++)
            {
                if (k == tempfacid)
                {
                    facNoCount++;
                }
            }
        }
        assertEquals(11, result.size());
        assertEquals(11, facNoCount);

    }

    /**
     * Test of getNextBookingNo method, of class DataMapperForFacility.
     */
    @Test
    public void testGetNextBookingNo()
    {
        System.out.println("getNextBookingNo");
        int currentno = facilitymapper.getNextBookingNo();
        int result = facilitymapper.getNextBookingNo();
        assertEquals(currentno + 1, result);
    }

    /**
     * Test of getBookingno method, of class DataMapperForFacility.
     */
    @Test
    public void testGetBookingno() throws ParseException
    {
        System.out.println("getBookingno");
        int facId = 106;
        Date bookingdate = format.parse("09-04-14");
        int bookingtime = 10;

        Date bookingdate2 = format.parse("09-04-15");
        int bookingtime2 = 8;

        int expResult = 1082;

        int result = facilitymapper.getBookingno(facId, bookingdate, bookingtime);
        int result2 = facilitymapper.getBookingno(facId, bookingdate2, bookingtime2);
        assertEquals(expResult, result);
        assertEquals(0, result2);//if there is no bookingno in before hand, it returns 0.

    }

    /**
     * Test of remaingPlace method, of class DataMapperForFacility.
     */
    @Test
    public void testRemaingPlace() throws ParseException
    {
        System.out.println("remaingPlace");
        String type = "tennis";
        Date bookingdate = format.parse("08-04-14");
        Date bookingdate2 = format.parse("06-04-14");
        int bookingtime = 8;
        int bookingtime2 = 20;
        int facid = 101;
        int facid2 = 106;

        int expResult = -1;
        int result = facilitymapper.remaingPlace(type, bookingdate, bookingtime, facid);

        int expResult2 = 4;
        int result2 = facilitymapper.remaingPlace(type, bookingdate2, bookingtime2, facid2);

        assertEquals(expResult, result);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of updateWaitingPos method, of class DataMapperForFacility.
     */
    @Test
    public void testUpdateWaitingPos()
    {
        System.out.println("updateWaitingPos");
        bookingUpdateList.add(new Booking(1000, "10000-1"));
        boolean expResult = true;
        boolean result = facilitymapper.updateWaitingPos(bookingUpdateList);
        ArrayList<Booking> bookedfac = facilitymapper.getBookingDetails(1000);
        int waitingpos = bookedfac.get(0).getWaitingpos();

        assertEquals(expResult, result);
        assertEquals(1, waitingpos);

    }

    /**
     * Test of fourBookingPerDay method, of class DataMapperForFacility.
     */
    @Test
    public void testFourBookingPerDay() throws ParseException
    {
        System.out.println("fourBookingPerDay");
        String guestnoTrue = "10000-1";
        String guestnoFalse = "10000-2";
        Date date = format.parse("01-04-14");

        boolean expResultTrue = true;
        boolean expResultFalse = false;
        boolean resultTrue = facilitymapper.fourBookingPerDay(guestnoTrue, date);

        boolean resultFalse = facilitymapper.fourBookingPerDay(guestnoFalse, date);
        assertEquals(expResultTrue, resultTrue);
        assertEquals(expResultFalse, resultFalse);
    }

    /**
     * Test of createFacilityBooking method, of class DataMapperForFacility.
     */
    @Test
    public void testCreateFacilityBooking() throws ParseException
    {
        System.out.println("createFacilityBooking");
        ArrayList<Booking> newbooking = new ArrayList();
        newbooking.add(new Booking(1000, 102, format.parse("30-04-14"), 20));
        ArrayList<Booking> newBookingStatus = new ArrayList();
        newBookingStatus.add(new Booking(1000, "10000-4", 0, 0));

        boolean result = facilitymapper.createFacilityBooking(newbooking, newBookingStatus);
        assertTrue(result);

        newbooking.clear();
        newBookingStatus.clear();
        newbooking.add(new Booking(1000, 102, format.parse("30-04-14"), 20));
        newBookingStatus.add(new Booking(1000, "10000-4", 0, 0));
        result = facilitymapper.createFacilityBooking(newbooking, newBookingStatus);
        assertFalse(result);

    }

    /**
     * Test of getFacArrayForJlist method, of class DataMapperForFacility.
     */
    @Test
    public void testGetFacArrayForJlist()
    {
        System.out.println("getFacArrayForJlist");
        //this method is just for showing info. 
        //testing is not needed.
    }

    /**
     * Test of getWaitingListForJlist method, of class DataMapperForFacility.
     */
    @Test
    public void testGetWaitingListForJlist() throws ParseException
    {
        System.out.println("getWaitingListForJlist");
        ArrayList<Guest> newbooking = facilitymapper.getWaitingListForJlist(101, format.parse("08-04-14"), 8);
        ArrayList<Guest> newbooking2 = facilitymapper.getWaitingListForJlist(999, format.parse("08-04-14"), 8);

        assertEquals(1, newbooking.size());
        assertEquals(true, newbooking2.isEmpty());

    }

    /**
     * Test of getBookingList method, of class DataMapperForFacility.
     */
    @Test
    public void testGetBookingList()
    {
        System.out.println("getBookingList");
        String guestno = "10000-2";
        String guestno2 = "11111-1";
        int size = 7;
        ArrayList<Booking> result = facilitymapper.getBookingList(guestno);

        ArrayList<Booking> result2 = facilitymapper.getBookingList(guestno2);
        assertEquals(size, result.size());
        assertEquals(true, result2.isEmpty());
    }

    /**
     * Test of getBookingDetails method, of class DataMapperForFacility.
     */
    @Test
    public void testGetBookingDetails()
    {
        System.out.println("getBookingDetails");
        ArrayList<Booking> array = facilitymapper.getBookingDetails(1107);
        ArrayList<Booking> emptyArray = facilitymapper.getBookingDetails(8888);

        assertEquals(1, array.size());
        assertEquals(array.get(0).getGuestno(), "10000-5");
        assertEquals(true, emptyArray.isEmpty());

    }

    /**
     * Test of getInstructorNo method, of class DataMapperForFacility.
     */
    @Test
    public void testGetInstructorNo() throws ParseException
    {
        System.out.println("getInstructorNo");
//        String username = "10404-1";
//        Booking booking = new Booking(1000, 101, format.parse("08-04-14"), 8);
//        int result = facilitymapper.getInstructorNo(booking, username);
//        System.out.println("result " + result);
//        boolean notzero=false;
//        if (result!=0)
//        {notzero=true;}
//        assertTrue(notzero);

    }

    /**
     * Test of getAvailableInstructorList method, of class
     * DataMapperForFacility.
     */
    @Test
    public void testGetFacArrayForBookingInstructorJlist() throws ParseException
    {
        System.out.println("getFacArrayForBookingInstructorJlist");
        String type = "tennis";
        Date dd = format.parse("01-04-14");
        int hour = 8;
        String username = "10000-1";
        ArrayList<Booking> result = facilitymapper.getAvailableInstructorList(type, dd, hour, username);
        assertEquals(23, result.size());
        assertEquals(100005, result.get(0).getInno());
    }

    /**
     * Test of saveInstructorBooking method, of class DataMapperForFacility.
     */
    @Test
    public void testSaveInstructorBooking() throws ParseException
    {
        System.out.println("saveInstructorBooking");
        ArrayList<Booking> booking = new ArrayList();
        booking.add(new Booking(1083, "tennis", 0, format.parse("09-04-14"), 16, "10404-1"));
        boolean result = facilitymapper.saveInstructorBooking(booking);
        assertEquals(true, result);

        booking.clear();
        booking.add(new Booking(1059, "tennis", 0, format.parse("09-04-14"), 8, "10000-3"));

        result = facilitymapper.saveInstructorBooking(booking);
        assertEquals(false, result);

    }

    /**
     * Test of getTypes method, of class DataMapperForFacility.
     */
    @Test
    public void testGetTypes()
    {
        System.out.println("getTypes");
        boolean contains = false;
        int expResultSize = 9;
        ArrayList<String> result = facilitymapper.getTypes();
        ArrayList<String> types = new ArrayList();
        types.add("tennis");
        types.add("volleyball");
        types.add("badminton");
        types.add("biking");
        types.add("golf");
        types.add("fitness");
        types.add("handball");
        types.add("swimming");
        types.add("table tennis");

        if (result.containsAll(types))
        {
            contains = true;
        }

        assertEquals(expResultSize, result.size());
        assertTrue(contains);
    }

    /**
     * Test of checkInstructorAlreadyThere method, of class
     * DataMapperForFacility.
     */
    @Test
    public void testCheckInstructorAlreadyThere()
    {
        System.out.println("checkInstructorAlreadyThere");
// this method is found inside of saveInstructorBooking method
        // already tested in saveInstructorBooking method.

    }

    /**
     * Test of removeInstructorFromBooking method, of class
     * DataMapperForFacility.
     */
    @Test
    public void testRemoveInstructorFromBooking()
    {
        System.out.println("removeInstructorFromBooking");
        String username = "10000-1";
        int bookingid = 1079;

        boolean result = facilitymapper.removeInstructorFromBooking(username, bookingid);
        Booking booking = facilitymapper.getBookingDetails(1079).get(0);
        assertEquals(true, result);
        assertEquals(0, booking.getInno());

    }

    /**
     * Test of createNewFacility method, of class DataMapperForFacility.
     */
    @Test
    public void testCreateNewFacility()
    {
        System.out.println("createNewFacility");
        int facNum = 777;
        String type = "tennis";
        boolean result = facilitymapper.createNewFacility(facNum, type);
        assertEquals(true, result);

        result = facilitymapper.createNewFacility(facNum, type);
        assertEquals(false, result);

    }

    /**
     * Test of addInstructor method, of class DataMapperForFacility.
     */
    @Test
    public void testAddInstructor()
    {
        System.out.println("addInstructor");
        String name = "testIn";
        String type = "tennis";
        boolean result = facilitymapper.addInstructor(name, type);
        assertEquals(true, result);
    }

    /**
     * Test of getFacilityNumber method, of class DataMapperForFacility.
     */
    @Test
    public void testGetFacilityNumber()
    {
        System.out.println("getFacilityNumber");
        String type = "golf";
        int expResult = 904;
        int result = facilitymapper.getFacilityNumber(type);
        assertEquals(expResult, result);

    }

    /**
     * Test of checkOnlyOneBooking method, of class DataMapperForFacility.
     */
    @Test
    public void testCheckOnlyOneBooking() throws ParseException
    {
        System.out.println("checkOnlyOneBooking");
        String guestno = "10000-5";
        String type = "tennis";
        Date dd = format.parse("09-04-14");
        int selectedHour = 8;
        boolean result = facilitymapper.checkOnlyOneBooking(guestno, type, dd, selectedHour);
        assertFalse(result);

        dd = format.parse("30-05-14");
        selectedHour = 20;
        result = facilitymapper.checkOnlyOneBooking(guestno, type, dd, selectedHour);
        assertTrue(result);

        type = "notExistingFac";
        result = facilitymapper.checkOnlyOneBooking(guestno, type, dd, selectedHour);
        assertTrue(result);//no resultset=true

    }
}
