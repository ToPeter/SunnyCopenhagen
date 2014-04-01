/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Booking;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 *
 * @author Tomoe
 */
public class DBFacadeForFacility
{private Connection con;
private DataMapperForFacility facilityMapper;
private static DBFacadeForFacility instance;

    public DBFacadeForFacility()
    {
        con = DBConnector.getConnection();
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
{return facilityMapper.getBookedfac(type, bookingdate, bookingtime, con);}
        
public int getMaxUsers(int facId)
{return facilityMapper.getMaxUsers(facId, con);}

}



        
