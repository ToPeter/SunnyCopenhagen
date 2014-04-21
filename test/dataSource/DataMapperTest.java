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
public class DataMapperTest
{

    private Connection con;
    DataMapper datamapper;
    DateFormat format;

    public DataMapperTest()
    {
    }

    @Before
    public void setUp()
    {
        con = DBConnector.getTestConnection();
        datamapper = new DataMapper(con);
        format = new SimpleDateFormat("dd-MM-yy");
        /**
         * run the following script on SQL before testing 
         * delete from reservation where reservationno = 55555; 
         * update reservation set depositpaid = 0 where reservationno = 1111;
         * update reservation set Ver_No = 0 where reservationno = 1111;
         * delete from guestID where guestid = 4321; commit;
         */

    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of getreservation method, of class DataMapper.
     */
    @Test
    public void testGetreservation() throws ParseException
    {
        System.out.println("getreservation");
        int reservationNo = 10;

        Reservation result = datamapper.getreservation(reservationNo, con);
        int roomNo = result.getRoomNo();
        Date fromDate = result.getFromDate();
        Date endDate = result.getEndDate();
        assertEquals(100, roomNo);
        assertEquals(format.parse("14-03-14"), fromDate);
        assertEquals(format.parse("14-03-14"), endDate);
    }

    /**
     * Test of getreservationDepositNotPaid method, of class DataMapper.
     */
    @Test
    public void testGetreservationDepositNotPaid()
    {
        System.out.println("getreservationDepositNotPaid");
        boolean allzero = true;
        ArrayList<Reservation> result = datamapper.getreservationDepositNotPaid(con);
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
        ArrayList<Room> result = datamapper.getRoomAvailable(fromDate1, endDate1, type, con);
        Date fromDate2 = format.parse("15-03-14");
        Date endDate2 = format.parse("17-03-14");

        ArrayList<Room> result2 = datamapper.getRoomAvailable(fromDate2, endDate2, type, con);
        Date fromDate3 = format.parse("13-03-14");
        Date endDate3 = format.parse("15-03-14");

        ArrayList<Room> result3 = datamapper.getRoomAvailable(fromDate3, endDate3, type, con);

        assertEquals(6, result.size());
        assertEquals(6, result2.size());
        assertEquals(10, result3.size());

    }

    /**
     * Test of getRoomType method, of class DataMapper.
     */
    @Test
    public void testGetRoomType()
    {
        System.out.println("getRoomType");
        int roomNoSingle = 1;
        int roomNoDouble = 11;
        int roomNoFamily = 21;
        String resultSingle = datamapper.getRoomType(roomNoSingle, con);
        String resultDouble = datamapper.getRoomType(roomNoDouble, con);
        String resultFamily = datamapper.getRoomType(roomNoFamily, con);

        assertEquals("single", resultSingle);
        assertEquals("double", resultDouble);
        assertEquals("family", resultFamily);
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
        int[] result = datamapper.getPriceList(con);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of createReservation method, of class DataMapper. before testing,
     * run the following script. delete from reservation where
     * reservationno=55555; commit;
     */
    @Test
    public void testCreateReservation() throws ParseException
    {
        System.out.println("createReservation");
        Date fromDate1 = format.parse("12-02-14");
        Date endDate1 = format.parse("16-02-14");

        Reservation res = new Reservation(21, 55555, fromDate1, endDate1, fromDate1, 0, 1111, 0);
        boolean expResult = true;
        boolean result = datamapper.createReservation(res, con);
        assertEquals(expResult, result);
        Reservation res2 = new Reservation(21, 55554, fromDate1, endDate1, fromDate1, 0, 1111, 0);
        boolean expResult2 = false;
        boolean result2 = datamapper.createReservation(res2, con);
        assertEquals(expResult2, result2);
        Reservation res3 = new Reservation(22, 55555, fromDate1, endDate1, fromDate1, 0, 1111, 0);
        boolean expResult3 = false;
        boolean result3 = datamapper.createReservation(res3, con);//throw exception
        assertEquals(expResult3, result3);

    }

    /**
     * Test of getGuest method, of class DataMapper.
     */
    @Test
    public void testGetGuest()
    {
        System.out.println("getGuest");
        int id = 123;

        GuestID result = datamapper.getGuest(id, con);
        assertEquals("Peter", result.getGuestFirstName());
        assertEquals("Rasmusen", result.getGuestFamilyName());
        assertEquals("home", result.getAddress());
        assertEquals("denmark", result.getCountry());
    }

//    /** ARE WE USING THIS????????????
//     * Test of deleteGuest method, of class DataMapper.
//     */
//    @Test
//    public void testDeleteGuest() throws Exception
//    {
//        System.out.println("deleteGuest");
//        ArrayList<Guest> delGuest = null;
//        Connection con = null;
//        DataMapper instance = null;
//        boolean expResult = false;
//        boolean result = instance.deleteGuest(delGuest, con);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
    /**
     * Test of insertGuest method, of class DataMapper.
     */
    @Test
    public void testInsertGuest() throws Exception
    {
        System.out.println("insertGuest");
        ArrayList<Guest> guestList = new ArrayList();
        guestList.add(new Guest(2222, "22220-1", 1111, "agency", 1));

        boolean result = datamapper.insertGuest(guestList, con);
        assertEquals(true, result);
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
        boolean result = datamapper.updateDeposit(updateList, con);
        assertEquals(true, result);
    }

    /**
     * Test of getNextReservationNo method, of class DataMapper.
     */
    @Test
    public void testGetNextReservationNo()
    {
        System.out.println("getNextReservationNo");

        int currentno = datamapper.getNextReservationNo(con);
        int result = datamapper.getNextReservationNo(con);
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
        String wrongpassword = "1231";
        String rightpassword = "1234";

        boolean resultFalse = datamapper.getGuestInfo(userName, wrongpassword, con);
        boolean resultTrue = datamapper.getGuestInfo(userName, rightpassword, con);

        assertEquals(false, resultFalse);
        assertEquals(true, resultTrue);
    }

    /**
     * Test of getEmpInfo method, of class DataMapper.
     */
    @Test
    public void testGetEmpInfo()
    {
        System.out.println("getEmpInfo");
        String userName = "1111";
        String wrongpassword = "1112";
        String rightpassword = "1111";

        boolean resultFalse = datamapper.getEmpInfo(userName, wrongpassword, con);
        boolean resultTrue = datamapper.getEmpInfo(userName, rightpassword, con);

        assertEquals(false, resultFalse);
        assertEquals(true, resultTrue);
    }
//
//    /**ARE WE USING THIS?????????????????????????????????????
//     * Test of getGuestID method, of class DataMapper.
//     */
//    @Test
//    public void testGetGuestID()
//    {
//        System.out.println("getGuestID");
//        int guestID = 555;
//        ArrayList<GuestID> result = datamapper.getGuestID(guestID, con);
//        assertEquals("a", result.get(0).getGuestFirstName());
//        assertEquals("a", result.get(0).getGuestFamilyName());
//       
//    }

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
        boolean result = datamapper.insertGuestID(guestListID, con);
        GuestID guestid = datamapper.getGuest(4321, con);
        assertEquals(expResult, result);
        assertEquals("testName", guestid.getGuestFirstName());
    }

    /**
     * Test of getEmpLogInName method, of class DataMapper.
     */
    @Test
    public void testGetEmpLogInName()
    {
        System.out.println("getEmpLogInName");
        String userName = "1111";
        String expResult = "Darth";
        String result = datamapper.getEmpLogInName(userName, con);
        assertEquals(expResult, result);
    }

    /**
     * Test of getGuestLogInName method, of class DataMapper.
     */
    @Test
    public void testGetGuestLogInName()
    {
        System.out.println("getGuestLogInName");
        String userName = "10000-1";
        String expResult = "t";
        String result = datamapper.getGuestLogInName(userName, con);
        assertEquals(expResult, result);
    }

    /**
     * Test of searchGuest method, of class DataMapper.
     */
    @Test
    public void testSearchGuest()
    {
        System.out.println("searchGuest");
        String guestno = "10000-1";
        GuestID result = datamapper.searchGuest(guestno, con);
        assertEquals("t", result.getAddress());
        assertEquals(0, result.getPhoneNo());
    }

    /**
     * Test of searchGuestByReservationNO method, of class DataMapper.
     */
    @Test
    public void testSearchGuestByReservationNO()
    {
        System.out.println("searchGuestByReservationNO");
        int reservationNO = 10000;
        ArrayList<GuestID> result = datamapper.searchGuestByReservationNO(reservationNO, con);
        assertEquals(6, result.size());
        assertEquals("t", result.get(0).getGuestFirstName());
    }

}
