/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import Mock.DataMapperMock;
import domain.Guest;
import domain.Reservation;
import domain.Room;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomoe
 */
public class DBFacadeTest
{
    DataMapperInterface dmm = new DataMapperMock();
    DBFacade facade = new DBFacade(dmm);
    Map<Integer, Reservation> reservationMap=new HashMap();
    Map<Integer, Room> roomMap=new HashMap(); //res.no
    Map<Integer, ArrayList> guestMap=new HashMap(); //res.no , guestArray
    ArrayList<Guest> guestarray1=new ArrayList();
    ArrayList<Guest> guestarray2=new ArrayList();
    ArrayList<Guest> guestarray3=new ArrayList();
    ArrayList<Guest> guestarray4=new ArrayList();
    ArrayList<Guest> guestarray5=new ArrayList();
    
    ArrayList<Room> roomarray=new ArrayList(); // allrooms
    ArrayList<Room> availableRooms=new ArrayList();
    int[] pricearray;
     
    public DBFacadeTest()
    {   
            
        }
    
    @Before
    public void setUp() throws ParseException
    {DateFormat format = new SimpleDateFormat("dd-MM-yy");
        reservationMap.put(10000, new Reservation (100,10000,format.parse("11-04-14"), format.parse("18-04-14"),format.parse("11-03-14"),false));
        reservationMap.put(10001, new Reservation (101,10001,format.parse("11-04-14"), format.parse("18-04-14"),format.parse("11-03-14"),false));
        reservationMap.put(10002, new Reservation (102,10002,format.parse("11-04-14"), format.parse("18-04-14"),format.parse("11-03-14"),false));
        reservationMap.put(10003, new Reservation (103,10003,format.parse("11-04-14"), format.parse("18-04-14"),format.parse("11-03-14"),false));
        reservationMap.put(10004, new Reservation (104,10004,format.parse("11-04-14"), format.parse("18-04-14"),format.parse("11-03-14"),false));
    
    roomMap.put(10000, new Room (100,"single"));
    roomMap.put(10001, new Room (101,"single"));
    roomMap.put(10002, new Room (102,"double"));
    roomMap.put(10003, new Room (103,"double"));
    roomMap.put(10004, new Room (104,"family"));
    
    
    guestarray1.add(new Guest(10000,"10000-01",123,"Abc","Def","Lundtoftevej","Denmark",01234566,"email10000-01"));
    guestarray2.add(new Guest(10001,"10001-01",345,"Ghi","JKL","Lundtoft","Sweden",01666666,"email10001-01"));
        guestarray3.add(new Guest(10002,"10002-01",678,"lmn","opq","Lund","USA",01234566,"email10002-01"));
        guestarray3.add(new Guest(10002,"10002-02",901,"rst","uvw","Lej","Denmark",012000,"email10002-02"));
        guestarray4.add(new Guest(10003,"10003-01",234,"xyz","abcc","vej","Japan",0000566,"email10003-01"));
        guestarray4.add(new Guest(10003,"10003-02",567,"ddee","fgh","Norwayload","Norge",01234566,"email10003-02"));
        guestarray5.add(new Guest(10004,"10004-01",123,"ijk","lmn","Chinatown","China",01265433,"email10004-01"));
        guestarray5.add(new Guest(10004,"10004-02",123,"opq","rst","Chinatouw","China",01265433,"email10004-02"));
        guestarray5.add(new Guest(10004,"10004-03",123,"uvw","xyz","Chinatown","China",01265433,"email10004-03"));
    
    guestMap.put(10000,guestarray1);
guestMap.put(10001,guestarray2);
guestMap.put(10002,guestarray3);
guestMap.put(10003,guestarray4);
guestMap.put(10004,guestarray5);
    
    roomarray.add(new Room (100,"single"));
    roomarray.add(new Room (101,"single"));
    roomarray.add(new Room (102,"double"));
    roomarray.add(new Room (103,"double"));
    roomarray.add(new Room (104,"family"));
    roomarray.add(new Room (105,"family"));
    
    pricearray=new int[]{60,80,120}; 
    }
    
    /**
     * Test of getInstance method, of class DBFacade.
//     */
//    @Test
//    public void testGetInstance()
//    {
//        System.out.println("getInstance");
//        DBFacade expResult = null;
//        DBFacade result = DBFacade.getInstance();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

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
    public void testGetRoomsAvailable()
    {
        System.out.println("getRoomsAvailable");
        String fromDate = "15-";
        String endDate = "";
        String type = "";
        DBFacade instance = null;
        ArrayList<Room> expResult = null;
        ArrayList<Room> result = instance.getRoomsAvailable(fromDate, endDate, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGuests method, of class DBFacade.
     */
    @Test
    public void testGetGuests()
    {
        System.out.println("getGuests");
        int reservation = 0;
        DBFacade instance = null;
        ArrayList<Guest> expResult = null;
        ArrayList<Guest> result = instance.getGuests(reservation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextReservationNo method, of class DBFacade.
     */
    @Test
    public void testGetNextReservationNo()
    {
        System.out.println("getNextReservationNo");
        DBFacade instance = null;
        int expResult = 0;
        int result = instance.getNextReservationNo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startProcessGuestBusinessTransaction method, of class DBFacade.
     */
    @Test
    public void testStartProcessGuestBusinessTransaction()
    {
        System.out.println("startProcessGuestBusinessTransaction");
        DBFacade instance = null;
        instance.startProcessGuestBusinessTransaction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerNewOrder method, of class DBFacade.
     */
    @Test
    public void testRegisterNewOrder()
    {
        System.out.println("registerNewOrder");
        Guest guest = null;
        DBFacade instance = null;
        instance.registerNewOrder(guest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDirtyOrder method, of class DBFacade.
     */
    @Test
    public void testRegisterDirtyOrder()
    {
        System.out.println("registerDirtyOrder");
        Guest guest = null;
        DBFacade instance = null;
        instance.registerDirtyOrder(guest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerDeleteGuest method, of class DBFacade.
     */
    @Test
    public void testRegisterDeleteGuest()
    {
        System.out.println("registerDeleteGuest");
        Guest currentGuest = null;
        DBFacade instance = null;
        instance.registerDeleteGuest(currentGuest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startProcessOrderBusinessTransaction method, of class DBFacade.
     */
    @Test
    public void testStartProcessOrderBusinessTransaction()
    {
        System.out.println("startProcessOrderBusinessTransaction");
        DBFacade instance = null;
        instance.startProcessOrderBusinessTransaction();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of commitProcessGuestBusinessTransaction method, of class DBFacade.
     */
    @Test
    public void testCommitProcessGuestBusinessTransaction()
    {
        System.out.println("commitProcessGuestBusinessTransaction");
        DBFacade instance = null;
        boolean expResult = false;
        boolean result = instance.commitProcessGuestBusinessTransaction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPriceList method, of class DBFacade.
     */
    @Test
    public void testGetPriceList()
    {
        System.out.println("getPriceList");
        DBFacade instance = null;
        int[] expResult = null;
        int[] result = instance.getPriceList();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
