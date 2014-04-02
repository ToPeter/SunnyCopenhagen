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

public ArrayList<Facility> getfacilitylist (String type)
{return facilityMapper.getfacilitylist (type,con);}


public int remaingPlace(String type, Date bookingdate, int bookingtime,int facid)
{ ArrayList<Booking> booking = getBookedfac(type, bookingdate, bookingtime);
int answer=getMaxUsers(facid);    
for (int i = 0; i < booking.size(); i++)
    {
        Booking booking1 = booking.get(i);
        if(booking1.getFacilityId()==facid)
        {answer=booking1.getMaxUsers()-booking1.getBookedNumOfUsers();
        return answer;
        }
    }

return answer;
}

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


        
