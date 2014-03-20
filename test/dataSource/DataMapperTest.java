/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Reservation;
import java.sql.Connection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tomoe
 */
public class DataMapperTest
{DataMapper dm;
Connection con =DBConnector.getConnection();

    public DataMapperTest()
    {
    }
//    
//    @BeforeClass
//    public static void setUpClass()
//    {
//    }
//    
//    @AfterClass
//    public static void tearDownClass()
//    {
//    }
//    
    @Before
    public void setUp()
    {    dm=new DataMapper(con);
    }
    
    /**
     * Test of getreservation method, of class DataMapper.
     */
    @Test
    public void testGetreservation()
    {
             System.out.println("getreservation");
        int reservationNo = 12345;
        Reservation res = dm.getreservation(reservationNo, con);
        int result = res.getRoomNo();
        assertEquals(26, result);
        // TODO review the generated test code and remove the default call to fail.
        }
    
}
