/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Booking;
import domain.Facility;

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
{return facilityMapper.getBookedfac(type, bookingdate, bookingtime, con);}
        
public int getMaxUsers(int facId)
{return facilityMapper.getMaxUsers(facId, con);}

public ArrayList<Facility> getfacilitylist (String type)
{return facilityMapper.getfacilitylist (type,con);}
public int getnextbookingno()
{return facilityMapper.getNextBookingNo(con);}
public int getbookingno(int facId, Date bookingdate, int bookingtime)
{return facilityMapper.getBookingno(facId, bookingdate, bookingtime, con);}
public boolean createFacilityBooking(Facility facility, String guestNo,Date bookingdate, int bookingtime,int inno)
{ return facilityMapper.createFacilityBooking(facility, guestNo,bookingdate, bookingtime, inno, con);}

public int remaingPlace(String type, Date bookingdate, int bookingtime,int facid)
{ return facilityMapper.remaingPlace(type, bookingdate, bookingtime, facid);}

public ArrayList<Facility> getFacArrayForJlist(String type, Date bookingdate, int bookingtime)
{ 
        ArrayList<Facility> result=new ArrayList();
    ArrayList<Facility> facList=getfacilitylist(type);
       for (int i = 0; i < facList.size(); i++)
    {
       Facility tempfac  = facList.get(i);
       int tempFacId=tempfac.getFacID();
       int remaining=remaingPlace(type, bookingdate, bookingtime,tempFacId);
       Facility newfac = new Facility(tempFacId, tempfac.getMinUsers(), tempfac.getMaxUsers(), remaining);
        
        result.add(newfac);
    }
       System.out.println(result.toString());
       return result;
}






}


        
