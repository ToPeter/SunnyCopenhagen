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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        facilitymapper = new DataMapperForFacility(con);
        format = new SimpleDateFormat("dd-MM-yy");
        bookingUpdateList = new ArrayList();
    
     /* excute following script before testing. delete from bookingstatus where
     * bookingid=1000; delete from booking where bookingid=1000; insert into
     * booking values (1000,101,'01-04-14',19); insert into bookingstatus
     * values(1000,'10000-1',1,0,0); insert into bookingstatus
     * values(1000,'10000-2',2,0,0);
     *
     * commit;
     */
    
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

        ArrayList<Booking> result = facilitymapper.getBookedfac(type, bookingdate, bookingtime, con);
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

        int expResultTennis = 4;
        int expResultVB = 10;
        int resultTennis = facilitymapper.getMaxUsers(facIDTennis, con);
        int resultVB = facilitymapper.getMaxUsers(facIDVolleyball, con);
        assertEquals(expResultTennis, resultTennis);
        assertEquals(expResultVB, resultVB);
    }

    /**
     * Test of getfacilitylist method, of class DataMapperForFacility.
     */
    @Test
    public void testGetfacilitylist()
    {   System.out.println("getfacilitylist");
        String type = "badminton";

        ArrayList<Facility> result = facilitymapper.getfacilitylist(type, con);
        //they have 4 badminton facilities facID 201,202,203,204
        assertEquals(4, result.size());
        assertEquals(201, result.get(0).getFacID());

    }

    /**
     * Test of getNextBookingNo method, of class DataMapperForFacility.
     */
    @Test
    public void testGetNextBookingNo()
    {
        System.out.println("getNextBookingNo");
        int currentno = facilitymapper.getNextBookingNo(con);
        int result = facilitymapper.getNextBookingNo(con);
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

        int result = facilitymapper.getBookingno(facId, bookingdate, bookingtime, con);
        int result2 = facilitymapper.getBookingno(facId, bookingdate2, bookingtime2, con);
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
        int bookingtime = 8;
        int facid = 101;

        int expResult = -1;
        int result = facilitymapper.remaingPlace(type, bookingdate, bookingtime, facid);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateWaitingPos method, of class DataMapperForFacility. 
     */
    @Test
    public void testUpdateWaitingPos()
    {//int bookingno, String guestno
        System.out.println("updateWaitingPos");
        bookingUpdateList.add(new Booking(1000, "10000-1"));

        boolean expResult = true;
        boolean result = facilitymapper.updateWaitingPos(bookingUpdateList, con);
        ArrayList<Booking> bookedfac = facilitymapper.getBookingDetails(1000, con);
        int size = bookedfac.size();
        int waitingpos = bookedfac.get(0).getWaitingpos();

        assertEquals(expResult, result);
        assertEquals(1, size);
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
        boolean resultTrue = facilitymapper.fourBookingPerDay(guestnoTrue, date, con);

        boolean resultFalse = facilitymapper.fourBookingPerDay(guestnoFalse, date, con);
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
        ArrayList<Booking> bookingSql1 = new ArrayList();
        bookingSql1.add(new Booking(1001,101,format.parse("02-04-14"),19));
        ArrayList<Booking> bookingSql2 = new ArrayList();
        bookingSql2.add(new Booking(1001,"10000-4",0,0));
        ArrayList<Booking> array=facilitymapper.getBookingDetails(1001, con);
        
        boolean expResult=false;
        
        for (int i = 0; i < array.size(); i++)
        {
            Booking booking = array.get(i);
            String guestno=booking.getGuestno();
            System.out.println("guestno= "+guestno);
            int facID=booking.getFacilityId();
            
            if(guestno.equals("10000-4")&&facID==101)
            {expResult=true;
                System.out.println("true");
            break;
            }
            
            expResult=false;
                        
        }
        boolean result = facilitymapper.createFacilityBooking(bookingSql1, bookingSql2, con);
        assertEquals(expResult, result);
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
    public void testGetWaitingListForJlist()
    {
        System.out.println("getWaitingListForJlist");
//this method is just for showing info. 
        //testing is not needed.    
    }

    /**
     * Test of getBookingList method, of class DataMapperForFacility.
     */
    @Test
    public void testGetBookingList()
    {
        System.out.println("getBookingList");
        String guestno = "10000-2";
        int size=6;
        ArrayList<Booking> result = facilitymapper.getBookingList(guestno, con);
        assertEquals(size, result.size());
    }

    /**
     * Test of getBookingDetails method, of class DataMapperForFacility.
     */
    @Test
    public void testGetBookingDetails()
    {
        System.out.println("getBookingDetails");

    //tested in CreateFacilityBookingmethod.
    }

    /**
     * Test of getTypes method, of class DataMapperForFacility.
     */
    @Test
    public void testGetTypes()
    {
        System.out.println("getTypes");
        boolean contains=false;
        int expResultSize = 9;
        ArrayList<String> result = facilitymapper.getTypes(con);
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
        
        
        
        
        if(result.containsAll(types))
        {contains=true;}
        
        
        assertEquals(expResultSize, result.size());
        assertTrue(contains);}
}
