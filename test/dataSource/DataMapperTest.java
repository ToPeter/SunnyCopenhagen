/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Guest;
import domain.GuestID;
import domain.Reservation;
import domain.Room;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
public class DataMapperTest
{

    static Connection con;
    DataMapperInterface datamapper;
    DateFormat format;

    public DataMapperTest()
    {
    }

    @Before
    public void setUp()
    {
        con = DBConnector.getTestConnection();
        datamapper = new DataMapper(con);
        SetUpDM.setUpForTest(con);
        format = new SimpleDateFormat("dd-MM-yy");

    }

    @After
    public void closeConnection()
    {
        DBConnector.releaseConnection();
    }

    /**
     * Test of getreservation method, of class DataMapper.
     */
    @Test
    public void testGetreservation() throws ParseException
    {
        System.out.println("getreservation");
        int reservationNo = 10;
        int notExistingreservationNO = 999;

        Reservation result = datamapper.getreservation(reservationNo);

        Reservation nullResult= datamapper.getreservation(notExistingreservationNO);

        int roomNo = result.getRoomNo();
        Date fromDate = result.getFromDate();
        Date endDate = result.getEndDate();
        assertEquals(100, roomNo);
        assertEquals(format.parse("14-03-14"), fromDate);
        assertEquals(format.parse("14-03-14"), endDate);
                assertEquals(null, nullResult);

    }

    /**
     * Test of getreservationDepositNotPaid method, of class DataMapper.
     */
    @Test
    public void testGetreservationDepositNotPaid()
    {
        System.out.println("getreservationDepositNotPaid");
        boolean allzero = true;
        ArrayList<Reservation> result = datamapper.getreservationDepositNotPaid();
        for (int i = 0; i < result.size(); i++)
        {
            Reservation reservation = result.get(i);
            if (reservation.isDepositPaid() == 1)//if depositpaid
            {
                allzero = false;
                break;
            }

        }
        assertEquals(true, allzero);
    }

    /**
     * Test of getRoomAvailable method, of class DataMapper.
     */
    @Test
    public void testGetRoomAvailable() throws ParseException
    {
        System.out.println("getRoomAvailable");
        Date fromDate1 = format.parse("12-03-14");
        Date endDate1 = format.parse("16-03-14");
        String type = "double";

        ArrayList<Room> result = datamapper.getRoomAvailable(fromDate1, endDate1, type);

        Date fromDate2 = format.parse("15-03-14");
        Date endDate2 = format.parse("17-03-14");

        ArrayList<Room> result2 = datamapper.getRoomAvailable(fromDate2, endDate2, type);
        Date fromDate3 = format.parse("13-03-14");
        Date endDate3 = format.parse("15-03-14");

        ArrayList<Room> result3 = datamapper.getRoomAvailable(fromDate3, endDate3, type);

        assertEquals(6, result.size());
        assertEquals(6, result2.size());
        assertEquals(10, result3.size());

    }

    /**
     * Test of getRoomType method, of class DataMapper. Single=no1-10 Double =
     * no11-20 Family = no21-30
     */
    @Test
    public void testGetRoomType()
    {
        System.out.println("getRoomType");
        int roomNoSingle1 = 1;
        int roomNoSingle2 = 10;
        int roomNoDouble1 = 11;
        int roomNoDouble2 = 20;
        int roomNoFamily1 = 21;
        int roomNoFamily2 = 30;
        int notExistingRoomno = 31;
        String resultSingle1 = datamapper.getRoomType(roomNoSingle1);
        String resultDouble1 = datamapper.getRoomType(roomNoDouble1);
        String resultFamily1 = datamapper.getRoomType(roomNoFamily1);
        String resultSingle2 = datamapper.getRoomType(roomNoSingle2);
        String resultDouble2 = datamapper.getRoomType(roomNoDouble2);
        String resultFamily2 = datamapper.getRoomType(roomNoFamily2);

        String noresult = datamapper.getRoomType(notExistingRoomno);

        assertEquals("single", resultSingle1);
        assertEquals("double", resultDouble1);
        assertEquals("family", resultFamily1);
        assertEquals("single", resultSingle2);
        assertEquals("double", resultDouble2);
        assertEquals("family", resultFamily2);
        assertEquals(null, noresult);

    }

    /**
     * Test of getPriceList method, of class DataMapper.
     */
    @Test
    public void testGetPriceList()
    {
        System.out.println("getPriceList");
        int[] expResult =
        {
            60, 80, 100
        };
        int[] result = datamapper.getPriceList();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createReservation method, of class DataMapper.
     * @throws java.text.ParseException
     */
    @Test
    public void testCreateReservation() throws ParseException, SQLException
    {
        System.out.println("createReservation");
        Date fromDate1 = format.parse("12-02-14");
        Date endDate1 = format.parse("16-02-14");

        Reservation res = new Reservation(21, 55555, fromDate1, endDate1, fromDate1, 0, 1111, 0);
        boolean expResult = true;
        boolean result = datamapper.createReservation(res);
        assertEquals(expResult, result);

        Reservation res2 = new Reservation(21, 55554, fromDate1, endDate1, fromDate1, 0, 1111, 0);
        boolean expResult2 = false;
        boolean result2 = datamapper.createReservation(res2);//the same date, same room as res.no55555. should fail
        assertEquals(expResult2, result2);

        Reservation res3 = new Reservation(22, 55555, fromDate1, endDate1, fromDate1, 0, 1111, 0);
        boolean expResult3 = false;
        boolean result3 = datamapper.createReservation(res3);//the same reservationno cannot be used
        assertEquals(expResult3, result3);

        Reservation res4 = datamapper.getreservation(55555);
        assertEquals(21, res4.getRoomNo());

    }

    /**
     * Test of getGuest method, of class DataMapper.
     */
    @Test
    public void testGetGuest()
    {
        System.out.println("getGuest");
        int id = 123;
        int notExistingID = 333;

        GuestID result = datamapper.getGuest(id);
        GuestID notExistingGuest = datamapper.getGuest(notExistingID);

        assertEquals("Peter", result.getGuestFirstName());
        assertEquals("denmark", result.getCountry());
        assertNull(notExistingGuest);

    }

    /**
     * Test of insertGuest method, of class DataMapper.
     */
    @Test
    public void testInsertGuest() throws Exception
    {
        System.out.println("insertGuest");
        ArrayList<Guest> guestList = new ArrayList();

        guestList.add(new Guest(2222, "22220-1", 1111, "agency", 1));

        boolean result = datamapper.insertGuest(guestList);
        assertEquals(true, result);

        datamapper.insertGuest(guestList);
    }

    /**
     * Test of updateDeposit method, of class DataMapper.
     */
    @Test
    public void testUpdateDeposit() throws Exception
    {
        System.out.println("updateDeposit");
        ArrayList<Reservation> updateList = new ArrayList<>();
        updateList.add(new Reservation(4, 1111, format.parse("14-01-11"), format.parse("14-01-18"), format.parse("14-01-05"), 0, 1111, 0));
        boolean result = datamapper.updateDeposit(updateList);
        Reservation res = datamapper.getreservation(1111);

        assertEquals(true, result);
        assertEquals(1, res.isDepositPaid());

    }

    /**
     * Test of getNextReservationNo method, of class DataMapper.
     */
    @Test
    public void testGetNextReservationNo()
    {
        System.out.println("getNextReservationNo");

        int currentno = datamapper.getNextReservationNo();
        int result = datamapper.getNextReservationNo();
        assertEquals(currentno + 1, result);

    }

    /**
     * Test of getGuestInfo method, of class DataMapper.
     */
    @Test
    public void testGetGuestInfo()
    {
        System.out.println("getGuestInfo");
        String userName = "10000-1";
        String empName = "1111";
        String emppassword = "1111";
        String wrongpassword = "1231";
        String rightpassword = "1234";

        boolean resultFalse = datamapper.getGuestInfo(userName, wrongpassword);
        boolean resultTrue = datamapper.getGuestInfo(userName, rightpassword);

        boolean guestlogin = datamapper.getGuestInfo(empName, emppassword);

        assertEquals(false, resultFalse);
        assertEquals(true, resultTrue);
        assertEquals(false, guestlogin);
    }

    /**
     * Test of getEmpInfo method, of class DataMapper.
     */
    @Test
    public void testGetEmpInfo()
    {
        System.out.println("getEmpInfo");
        String userName = "1111";
        String guestName = "10000-1";
        String guestpassword = "1234";
        String wrongpassword = "1112";
        String rightpassword = "1111";

        boolean resultFalse = datamapper.getEmpInfo(userName, wrongpassword);
        boolean resultTrue = datamapper.getEmpInfo(userName, rightpassword);

        boolean emplogin = datamapper.getEmpInfo(guestName, guestpassword);

        assertEquals(false, resultFalse);
        assertEquals(true, resultTrue);
        assertEquals(false, emplogin);

    }

    /**
     * Test of updateGuestID method, of class DataMapper.
     */
    @Test
    public void testUpdateGuestID()
    {
        System.out.println("updateGuestID");
        ArrayList<GuestID> dirtyGuestID = new ArrayList();

        dirtyGuestID.add(new GuestID(9876, "changedbytest", "changedbytest", "changedbytest", "changedbytest", 0, "changedbytest"));

        boolean expResult = true;
        boolean result = datamapper.updateGuestID(dirtyGuestID);
        GuestID guest = datamapper.getGuest(9876);
        assertEquals(expResult, result);
        assertEquals("changedbytest", guest.getGuestFamilyName());

        ArrayList<GuestID> notexisitingGuestID = new ArrayList();

        notexisitingGuestID.add(new GuestID(111, "changedbytest", "changedbytest", "changedbytest", "changedbytest", 0, "changedbytest"));
        boolean expResult2 = false;
        boolean result2 = datamapper.updateGuestID(notexisitingGuestID);

        assertEquals(expResult2, result2);
    }

    /**
     * Test of insertGuestID method, of class DataMapper.
     */
    @Test
    public void testInsertGuestID()
    {
        System.out.println("insertGuestID");
        ArrayList<GuestID> guestListID = new ArrayList();
        guestListID.add(new GuestID(4321, "testName", "testLName", "testaddress", "testcountry", 000, "testemail"));
        boolean expResult = true;
        boolean result = datamapper.insertGuestID(guestListID);
        GuestID guestid = datamapper.getGuest(4321);

        assertEquals(expResult, result);
        assertEquals("testName", guestid.getGuestFirstName());

        boolean expResult2 = false;
        boolean result2 = datamapper.insertGuestID(guestListID);
        assertEquals(expResult2, result2);

    }

    /**
     * Test of getEmpLogInName method, of class DataMapper.
     */
    @Test
    public void testGetEmpLogInName()
    {
        System.out.println("getEmpLogInName");
        String userName = "1111";
        String notExistingUserName = "5555";
        String expResult = "Darth";
        String result = datamapper.getEmpLogInName(userName);
        String notExistingUNresult = datamapper.getEmpLogInName(notExistingUserName);
        assertEquals(expResult, result);
        assertEquals("", notExistingUNresult);

    }

    /**
     * Test of getGuestLogInName method, of class DataMapper.
     */
    @Test
    public void testGetGuestLogInName()
    {
        System.out.println("getGuestLogInName");
        String userName = "10000-1";
        String notExistingUserName = "11111-1";

        String expResult = "t";
        String result = datamapper.getGuestLogInName(userName);
        String notExistingUNresult = datamapper.getGuestLogInName(notExistingUserName);
        assertEquals(expResult, result);
        assertEquals("", notExistingUNresult);
    }

    /**
     * Test of searchGuest method, of class DataMapper.
     */
    @Test
    public void testSearchGuest()
    {
        System.out.println("searchGuest");
        String guestno = "10000-1";
        String notExistingGuestno = "11111-1";

        GuestID result = datamapper.searchGuest(guestno);
        GuestID notExisitingGNresult = datamapper.searchGuest(notExistingGuestno);

        assertEquals("t", result.getAddress());
        assertEquals(0, result.getPhoneNo());
        assertNull(notExisitingGNresult);

    }

    /**
     * Test of searchGuestByReservationNO method, of class DataMapper.
     */
    @Test
    public void testSearchGuestByReservationNO()
    {
        System.out.println("searchGuestByReservationNO");
        int reservationNO = 10000;
        int notExistingResNO = 111;
        ArrayList<GuestID> result = datamapper.searchGuestByReservationNO(reservationNO);
        ArrayList<GuestID> notExistingResNoresult = datamapper.searchGuestByReservationNO(notExistingResNO);
        assertEquals(6, result.size());
        assertEquals("t", result.get(0).getGuestFirstName());
        assertEquals(0, notExistingResNoresult.size());

    }

}
