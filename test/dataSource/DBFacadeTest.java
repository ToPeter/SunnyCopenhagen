/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import Mock.DataMapperMock;
import domain.Guest;
import domain.GuestID;
import domain.Reservation;
import domain.Room;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
public class DBFacadeTest
{

    Map<Integer, Reservation> reservationMap = new HashMap();
    Map<Integer, Room> roomMap = new HashMap(); //res.no
    Map<Integer, ArrayList> guestMap = new HashMap(); //res.no , guestArray
    ArrayList<Guest> guestarray1 = new ArrayList();
    ArrayList<Guest> guestarray2 = new ArrayList();
    ArrayList<Guest> guestarray3 = new ArrayList();
    ArrayList<Guest> guestarray4 = new ArrayList();
    ArrayList<Guest> guestarray5 = new ArrayList();

    ArrayList<Room> roomarray = new ArrayList(); // allrooms
    ArrayList<Room> availableRooms = new ArrayList();
    int[] pricearray;
    DateFormat format = new SimpleDateFormat("dd-MM-yy");
    DataMapperMock dmm = new DataMapperMock();
    DBFacade facade = new DBFacade(dmm);

    public DBFacadeTest()
    {

    }

    @Before
    public void setUp() throws ParseException
    {

        reservationMap.put(10000, new Reservation(100, 10000, format.parse("11-04-14"), format.parse("18-04-14"), format.parse("11-03-14"), 0, 1111, 0));
        reservationMap.put(10001, new Reservation(101, 10001, format.parse("11-04-14"), format.parse("18-04-14"), format.parse("11-03-14"), 0, 1111, 0));
        reservationMap.put(10002, new Reservation(102, 10002, format.parse("11-04-14"), format.parse("18-04-14"), format.parse("11-03-14"), 0, 1111, 0));
        reservationMap.put(10003, new Reservation(103, 10003, format.parse("11-04-14"), format.parse("18-04-14"), format.parse("11-03-14"), 1, 1111, 0));
        reservationMap.put(10004, new Reservation(104, 10004, format.parse("11-04-14"), format.parse("18-04-14"), format.parse("11-03-14"), 1, 1111, 0));
        dmm.setReservationMap(reservationMap);

        roomMap.put(10000, new Room(100, "single"));
        roomMap.put(10001, new Room(101, "single"));
        roomMap.put(10002, new Room(102, "double"));
        roomMap.put(10003, new Room(103, "double"));
        roomMap.put(10004, new Room(104, "family"));
        dmm.setRoomMap(roomMap);

        guestarray1.add(new Guest(10000, "10000-01", 123, "Abc", "Def", "email10000-01"));
        guestarray2.add(new Guest(10001, "10001-01", 345, "Ghi", "JKL", "email10001-01"));
        guestarray3.add(new Guest(10002, "10002-01", 678, "lmn", "opq", "email10002-01"));
        guestarray3.add(new Guest(10002, "10002-02", 901, "rst", "uvw", "email10002-02"));
        guestarray4.add(new Guest(10003, "10003-01", 234, "xyz", "abcc", "email10003-01"));
        guestarray4.add(new Guest(10003, "10003-02", 567, "ddee", "fgh", "email10003-02"));
        guestarray5.add(new Guest(10004, "10004-01", 123, "ijk", "lmn", "email10004-01"));
        guestarray5.add(new Guest(10004, "10004-02", 123, "opq", "rst", "email10004-02"));
        guestarray5.add(new Guest(10004, "10004-03", 123, "uvw", "xyz", "email10004-03"));
          
        guestMap.put(10000, guestarray1);
        guestMap.put(10001, guestarray2);
        guestMap.put(10002, guestarray3);
        guestMap.put(10003, guestarray4);
        guestMap.put(10004, guestarray5);
        dmm.setGuestMap(guestMap); //res.no , guestArray

        roomarray.add(new Room(100, "single"));
        roomarray.add(new Room(101, "single"));
        roomarray.add(new Room(102, "double"));
        roomarray.add(new Room(103, "double"));
        roomarray.add(new Room(104, "family"));
        roomarray.add(new Room(105, "family"));
        dmm.setRoomarray(roomarray);

        pricearray = new int[]
        {
            60, 80, 120
        };
        dmm.setPricearray(pricearray);

    }

    @After
    public void tearDown() throws Exception
    {
    }

    /**
     * Test of getReservation method, of class DBFacade.
     */
    @Test
    public void testGetReservation()
    {
        System.out.println("getReservation");
        int reservationNo = 10004;
        int expRoomno = 104;
        Reservation result = facade.getReservation(reservationNo);
        assertEquals(expRoomno, result.getRoomNo());
        assertEquals(reservationNo, result.getReservationNo());
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of getRoomsAvailable method, of class DBFacade.
     */
    @Test
    public void testGetRoomsAvailable() throws ParseException
    {
        System.out.println("getRoomsAvailable");
        Date fromDate = format.parse("25-03-26");
        Date endDate = format.parse("25-03-28");
        String type = "single";

        ArrayList<Room> expResult = new ArrayList();
        expResult.add(new Room(105, "family"));
        ArrayList<Room> result = facade.getRoomsAvailable(fromDate, endDate, type);
        int expResultSize = expResult.size();
        int resultSize = result.size();
        int expResultRoomno = expResult.get(0).getRoomNo();
        int resultRoomno = result.get(0).getRoomNo();
        assertEquals(expResultSize, resultSize);
        assertEquals(expResultRoomno, resultRoomno);
    }

    /**
     * Test of getInstance method, of class DBFacade.
     */
    @Test
    public void testGetInstance()
    {
        //not needed
    }

    /**
     * Test of getGuest method, of class DBFacade.
     */
    @Test
    public void testGetGuest()
    {
//        System.out.println("getGuest");
//        int guestId = 0;
//        DBFacade instance = new DBFacade();
//        GuestID expResult = null;
//        GuestID result = instance.getGuest(guestId);
//        assertEquals(expResult, result);
    }

    /**
     * Test of getRoomType method, of class DBFacade.
     */
    @Test
    public void testGetRoomType()
    {
        System.out.println("getRoomType");
        int roomNo = 105;
        String expResult = "family";
        String result = facade.getRoomType(roomNo);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getNextReservationNo method, of class DBFacade.
     */
    @Test
    public void testGetNextReservationNo()
    {
       //this is not testable because res.no is sequence no from DB
    }

    /**
     * Test of getReservationDepositNotPaid method, of class DBFacade.
     */
    @Test
    public void testGetReservationDepositNotPaid()
    {
        System.out.println("getReservationDepositNotPaid");
        int expResult = 3;
        ArrayList<Reservation> resultarray = facade.getReservationDepositNotPaid();
        int result= resultarray.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of startProcessGuestBusinessTransaction method, of class DBFacade.
     */
    @Test
    public void testStartProcessGuestBusinessTransaction()
    {//not testable
        
    }

    /**
     * Test of registerNewGuest method, of class DBFacade.
     */
    @Test
    public void testRegisterNewGuest()
    {
//        System.out.println("registerNewGuest");
//        Guest guest = null;
//        DBFacade instance = new DBFacade();
//        instance.registerNewGuest(guest);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDirtyOrder method, of class DBFacade.
     */
    @Test
    public void testRegisterDirtyOrder()
    {
//        System.out.println("registerDirtyOrder");
//        Guest guest = null;
//        DBFacade instance = new DBFacade();
//        instance.registerDirtyOrder(guest);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDeleteGuest method, of class DBFacade.
     */
    @Test
    public void testRegisterDeleteGuest()
    {
//        System.out.println("registerDeleteGuest");
//        Guest currentGuest = null;
//        DBFacade instance = new DBFacade();
//        instance.registerDeleteGuest(currentGuest);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of startProcessOrderBusinessTransaction method, of class DBFacade.
     */
    @Test
    public void testStartProcessOrderBusinessTransaction()
    {
//        System.out.println("startProcessOrderBusinessTransaction");
//        DBFacade instance = new DBFacade();
//        instance.startProcessOrderBusinessTransaction();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of commitProcessGuestBusinessTransaction method, of class DBFacade.
     */
    @Test
    public void testCommitProcessGuestBusinessTransaction()
    {
//        System.out.println("commitProcessGuestBusinessTransaction");
//        DBFacade instance = new DBFacade();
//        boolean expResult = false;
//        boolean result = instance.commitProcessGuestBusinessTransaction();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getPriceList method, of class DBFacade.
     */
    @Test
    public void testGetPriceList()
    {
        System.out.println("getPriceList");
        
        int[] result = facade.getPriceList();
        int answer= result[0];
        assertEquals(60, answer);
    }

    /**
     * Test of bookRoom method, of class DBFacade.
     */
    @Test
    public void testBookRoom() throws ParseException
    {
        System.out.println("bookRoom");
        Reservation reservation=new Reservation(105, 10005, format.parse("11-04-14"), format.parse("18-04-14"), format.parse("11-03-14"), 1, 1111, 0); 
        boolean expResult = true;
        boolean result = facade.bookRoom(reservation);
        assertEquals(expResult, result);
        
   }

    /**
     * Test of updateDeposit method, of class DBFacade.
     */
    @Test
    public void testUpdateDeposit()
    {
//        System.out.println("updateDeposit");
//        Reservation currentReservation = null;
//        DBFacade instance = new DBFacade();
//        boolean expResult = false;
//        boolean result = instance.updateDeposit(currentReservation);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuestInfo method, of class DBFacade.
     */
    @Test
    public void testGetGuestInfo()
    {
//        System.out.println("getGuestInfo");
//        String userName = "";
//        String password = "";
//        DBFacade instance = new DBFacade();
//        boolean expResult = false;
//        boolean result = instance.getGuestInfo(userName, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getReservationString method, of class DBFacade.
     */
    @Test
    public void testGetReservationString()
    {
//        System.out.println("getReservationString");
//        int reservationNo = 0;
//        DBFacade instance = new DBFacade();
//        String expResult = "";
//        String result = instance.getReservationString(reservationNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmpInfo method, of class DBFacade.
     */
    @Test
    public void testGetEmpInfo()
    {
//        System.out.println("getEmpInfo");
//        String userName = "";
//        String password = "";
//        DBFacade instance = new DBFacade();
//        boolean expResult = false;
//        boolean result = instance.getEmpInfo(userName, password);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of registerNewGuestID method, of class DBFacade.
     */
    @Test
    public void testRegisterNewGuestID()
    {
//        System.out.println("registerNewGuestID");
//        GuestID currentGuestID = null;
//        DBFacade instance = new DBFacade();
//        instance.registerNewGuestID(currentGuestID);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuestsID method, of class DBFacade.
     */
    @Test
    public void testGetGuestsID()
    {
//        System.out.println("getGuestsID");
//        int guestID = 0;
//        DBFacade instance = new DBFacade();
//        ArrayList<GuestID> expResult = null;
//        ArrayList<GuestID> result = instance.getGuestsID(guestID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuestArrayForMail method, of class DBFacade.
     */
    @Test
    public void testGetGuestArrayForMail()
    {
//        System.out.println("getGuestArrayForMail");
//        int reservationNo = 0;
//        DBFacade instance = new DBFacade();
//        ArrayList<Guest> expResult = null;
//        ArrayList<Guest> result = instance.getGuestArrayForMail(reservationNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuestStringForMail method, of class DBFacade.
     */
    @Test
    public void testGetGuestStringForMail()
    {
//        System.out.println("getGuestStringForMail");
//        int reservationNo = 0;
//        DBFacade instance = new DBFacade();
//        String expResult = "";
//        String result = instance.getGuestStringForMail(reservationNo);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmpLogInName method, of class DBFacade.
     */
    @Test
    public void testGetEmpLogInName()
    {
//        System.out.println("getEmpLogInName");
//        String userName = "";
//        DBFacade instance = new DBFacade();
//        String expResult = "";
//        String result = instance.getEmpLogInName(userName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuestLogInName method, of class DBFacade.
     */
    @Test
    public void testGetGuestLogInName()
    {
//        System.out.println("getGuestLogInName");
//        String userName = "";
//        DBFacade instance = new DBFacade();
//        String expResult = "";
//        String result = instance.getGuestLogInName(userName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}

