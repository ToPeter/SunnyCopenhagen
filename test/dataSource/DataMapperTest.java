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
    
    public DataMapperTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    /**
     * Test of getreservation method, of class DataMapper.
     */
    @Test
    public void testGetreservation()
    {
        System.out.println("getreservation");
        int reservationNo = 0;
        Connection con = null;
        DataMapper instance = null;
        Reservation expResult = null;
        Reservation result = instance.getreservation(reservationNo, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getreservationDepositNotPaid method, of class DataMapper.
     */
    @Test
    public void testGetreservationDepositNotPaid()
    {
        System.out.println("getreservationDepositNotPaid");
        Connection con = null;
        DataMapper instance = null;
        ArrayList<Reservation> expResult = null;
        ArrayList<Reservation> result = instance.getreservationDepositNotPaid(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoomAvailable method, of class DataMapper.
     */
    @Test
    public void testGetRoomAvailable()
    {
        System.out.println("getRoomAvailable");
        Date fromDate = null;
        Date endDate = null;
        String type = "";
        Connection con = null;
        DataMapper instance = null;
        ArrayList<Room> expResult = null;
        ArrayList<Room> result = instance.getRoomAvailable(fromDate, endDate, type, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoomType method, of class DataMapper.
     */
    @Test
    public void testGetRoomType()
    {
        System.out.println("getRoomType");
        int roomNo = 0;
        Connection con = null;
        DataMapper instance = null;
        String expResult = "";
        String result = instance.getRoomType(roomNo, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPriceList method, of class DataMapper.
     */
    @Test
    public void testGetPriceList()
    {
        System.out.println("getPriceList");
        Connection con = null;
        DataMapper instance = null;
        int[] expResult = null;
        int[] result = instance.getPriceList(con);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReservation method, of class DataMapper.
     */
    @Test
    public void testCreateReservation()
    {
        System.out.println("createReservation");
        Reservation res = null;
        Connection con = null;
        DataMapper instance = null;
        boolean expResult = false;
        boolean result = instance.createReservation(res, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuest method, of class DataMapper.
     */
    @Test
    public void testGetGuest()
    {
        System.out.println("getGuest");
        int id = 0;
        Connection con = null;
        DataMapper instance = null;
        GuestID expResult = null;
        GuestID result = instance.getGuest(id, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertGuest method, of class DataMapper.
     */
    @Test
    public void testInsertGuest() throws Exception
    {
        System.out.println("insertGuest");
        ArrayList<Guest> guestList = null;
        Connection con = null;
        DataMapper instance = null;
        boolean expResult = false;
        boolean result = instance.insertGuest(guestList, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteGuest method, of class DataMapper.
     */
    @Test
    public void testDeleteGuest() throws Exception
    {
        System.out.println("deleteGuest");
        ArrayList<Guest> delGuest = null;
        Connection con = null;
        DataMapper instance = null;
        boolean expResult = false;
        boolean result = instance.deleteGuest(delGuest, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDeposit method, of class DataMapper.
     */
    @Test
    public void testUpdateDeposit() throws Exception
    {
        System.out.println("updateDeposit");
        ArrayList<Reservation> updateList = null;
        Connection con = null;
        DataMapper instance = null;
        boolean expResult = false;
        boolean result = instance.updateDeposit(updateList, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextReservationNo method, of class DataMapper.
     */
    @Test
    public void testGetNextReservationNo()
    {
        System.out.println("getNextReservationNo");
        Connection conn = null;
        DataMapper instance = null;
        int expResult = 0;
        int result = instance.getNextReservationNo(conn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuestInfo method, of class DataMapper.
     */
    @Test
    public void testGetGuestInfo()
    {
        System.out.println("getGuestInfo");
        String userName = "";
        String password = "";
        Connection conn = null;
        DataMapper instance = null;
        boolean expResult = false;
        boolean result = instance.getGuestInfo(userName, password, conn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmpInfo method, of class DataMapper.
     */
    @Test
    public void testGetEmpInfo()
    {
        System.out.println("getEmpInfo");
        String userName = "";
        String password = "";
        Connection conn = null;
        DataMapper instance = null;
        boolean expResult = false;
        boolean result = instance.getEmpInfo(userName, password, conn);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuestID method, of class DataMapper.
     */
    @Test
    public void testGetGuestID()
    {
        System.out.println("getGuestID");
        int guestID = 0;
        Connection con = null;
        DataMapper instance = null;
        ArrayList<GuestID> expResult = null;
        ArrayList<GuestID> result = instance.getGuestID(guestID, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertGuestID method, of class DataMapper.
     */
    @Test
    public void testInsertGuestID()
    {
        System.out.println("insertGuestID");
        ArrayList<GuestID> guestListID = null;
        Connection con = null;
        DataMapper instance = null;
        boolean expResult = false;
        boolean result = instance.insertGuestID(guestListID, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmpLogInName method, of class DataMapper.
     */
    @Test
    public void testGetEmpLogInName()
    {
        System.out.println("getEmpLogInName");
        String userName = "";
        Connection con = null;
        DataMapper instance = null;
        String expResult = "";
        String result = instance.getEmpLogInName(userName, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuestLogInName method, of class DataMapper.
     */
    @Test
    public void testGetGuestLogInName()
    {
        System.out.println("getGuestLogInName");
        String userName = "";
        Connection con = null;
        DataMapper instance = null;
        String expResult = "";
        String result = instance.getGuestLogInName(userName, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchGuest method, of class DataMapper.
     */
    @Test
    public void testSearchGuest()
    {
        System.out.println("searchGuest");
        String guestno = "";
        Connection con = null;
        DataMapper instance = null;
        GuestID expResult = null;
        GuestID result = instance.searchGuest(guestno, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchGuestByReservationNO method, of class DataMapper.
     */
    @Test
    public void testSearchGuestByReservationNO()
    {
        System.out.println("searchGuestByReservationNO");
        int reservationNO = 0;
        Connection con = null;
        DataMapper instance = null;
        ArrayList<GuestID> expResult = null;
        ArrayList<GuestID> result = instance.searchGuestByReservationNO(reservationNO, con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
