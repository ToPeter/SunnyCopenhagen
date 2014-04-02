/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Booking;
import domain.Facility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 *
 * @author Tomoe
 */
public class DataMapperForFacility
{

    private final Connection con;
    private Date parsedFrom;
    private Date parsedTo;
    Calendar c = Calendar.getInstance();
    LinkedHashMap<Date, Integer> oneweekmap;

    public DataMapperForFacility(Connection con)
    {
        this.con = con;
    }

    public ArrayList<Booking> getBookedfac(String type, Date bookingdate, int bookingtime, Connection con)
    {
        ArrayList<Booking> bookedlist = new ArrayList();
        Booking booking = null;
        PreparedStatement statement = null;
        java.sql.Date sqlbookingdate = new java.sql.Date(bookingdate.getTime());

        String SQLString
                = "select  bs.bookingid, count(bs.bookingid), T.Max_Users, t.type, f.id, b.bookingdate, b.bookingtime "
                + "from bookingstatus bs, type t, facility f, booking b "
                + "where f.type=t.type and f.id=b.facilityid and b.bookingid=bs.bookingid and t.type='" + type + "' and bookingdate='" + sqlbookingdate + "' and bookingtime='" + bookingtime + "' "
                + "group by bs.Bookingid,T.Max_Users,t.type,f.id,b.bookingdate,b.bookingtime ";

        try
        {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                booking = new Booking(
                        rs.getInt(1),
                        rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getDate(6), rs.getInt(7));

                bookedlist.add(booking);
            }

        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapperForFacility - gotfacarray");
            System.out.println(e.getMessage());
        }
        finally														// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                System.out.println("Fail in DataMapperForFacility - gotfacarray");
                System.out.println(e.getMessage());
            }
        }
        return bookedlist;
    }

    public int getMaxUsers(int facID, Connection con)
    {
        PreparedStatement statement = null;
        int maxUsers = 0;
        String SQLString
                = "select max_users from type t, facility f "
                + "where t.type=f.type AND f.id='" + facID + "'";
        try
        {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                maxUsers = rs.getInt(1);
            }

        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapperForFacility - getallfac");
            System.out.println(e.getMessage());
        }
        finally														// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                System.out.println("Fail in DataMapperForFacility - getallfac");
                System.out.println(e.getMessage());
            }
        }
        return maxUsers;
    }

    public ArrayList<Facility> getfacilitylist (String type,Connection con)
    {
        ArrayList<Facility> facilitylist = new ArrayList();
        Facility facility = null;
        PreparedStatement statement = null;
//        java.sql.Date sqlbookingdate = new java.sql.Date(bookingdate.getTime());

        String SQLString
                = "select f.id, f.type,t.num_users,t.max_Users from facility f, type t " +
"where f.type=t.type and t.type='"+type+"'";
        
        try
        {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                facility= new Facility(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3), 
                        rs.getInt(4));

                facilitylist.add(facility);
            }

        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapperForFacility - gotfacarray");
            System.out.println(e.getMessage());
        }
        finally														// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                System.out.println("Fail in DataMapperForFacility - gotfacarray");
                System.out.println(e.getMessage());
            }
        }
        return facilitylist;
    
    
    }
}
