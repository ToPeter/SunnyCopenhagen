/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Booking;
import domain.Facility;
import domain.Guest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                + "where f.type=t.type and f.id=b.facilityid and b.bookingid=bs.bookingid and t.type=? and bookingdate=? and bookingtime=? "
                + "group by bs.Bookingid,T.Max_Users,t.type,f.id,b.bookingdate,b.bookingtime ";

        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, type);
            statement.setDate(2, sqlbookingdate);
            statement.setInt(3, bookingtime);

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
        System.out.println("bookedlist" + bookedlist.toString());
        return bookedlist;
    }

    public int getMaxUsers(int facID, Connection con)
    {
        PreparedStatement statement = null;
        int maxUsers = 0;
        String SQLString
                = "select max_users from type t, facility f "
                + "where t.type=f.type AND f.id=?";
        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, facID);
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

    public ArrayList<Facility> getfacilitylist(String type, Connection con)
    {
        ArrayList<Facility> facilitylist = new ArrayList();
        Facility facility = null;
        PreparedStatement statement = null;
//        java.sql.Date sqlbookingdate = new java.sql.Date(bookingdate.getTime());

        String SQLString
                = "select f.id, f.type,t.num_users,t.max_Users from facility f, type t "
                + "where f.type=t.type and t.type=?";

        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                facility = new Facility(
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

    public int getNextBookingNo(Connection con)
    {
        int nextBookingNo = 0;
        String SQLString = "select booking_seq.nextval from dual";
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            boolean status = rs.next();
            System.out.println("status " + status);
            if (status)
            {
                nextBookingNo = rs.getInt(1);
            }
        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getNextReservationNo");
            System.out.println(e.getMessage());
        }
        return nextBookingNo;
    }

    public int getBookingno(int facId, Date bookingdate, int bookingtime, Connection con)
    {
        int bookingNo = 0;
        java.sql.Date sqlBookingdate = new java.sql.Date(bookingdate.getTime());
        String SQLString = "select bookingid from booking "
                + "   where facilityid=? and bookingdate=? and bookingtime=?";
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, facId);
            statement.setDate(2, sqlBookingdate);
            statement.setInt(3, bookingtime);

            ResultSet rs = statement.executeQuery();
            boolean result = rs.next();
            if (result)
            {
                bookingNo = rs.getInt(1);
            }
        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getNextReservationNo");
            System.out.println(e.getMessage());
        }
        System.out.println("your booking no is: " + bookingNo);
        return bookingNo;
    }

    public int remaingPlace(String type, Date bookingdate, int bookingtime, int facid)
    {
        ArrayList<Booking> booking = getBookedfac(type, bookingdate, bookingtime, con);
        int answer = getMaxUsers(facid, con);
        for (int i = 0; i < booking.size(); i++)
        {
            Booking booking1 = booking.get(i);
            if (booking1.getFacilityId() == facid)
            {
                System.out.println("bookednumusers" + booking1.getBookedNumOfUsers());
                answer = booking1.getMaxUsers() - booking1.getBookedNumOfUsers();

            }
            
            
        }
        System.out.println("remainingplace answer=" + answer);
        return answer;
    }
    
    // under construction. :)
    public boolean updateWaitingPos(Connection con)
    {return true;}

    public boolean createFacilityBooking(Facility facility, String guestNo, Date bookingdate, int bookingtime, int inno, Connection con)
    {
        boolean bookingnoFound = true;
        java.sql.Date sqlBookingdate = new java.sql.Date(bookingdate.getTime());
        int bookingno = getBookingno(facility.getFacID(), sqlBookingdate, bookingtime, con);
        int waitingpos;
        int rowsInserted = 0;
        int remainroom = remaingPlace(facility.getType(), bookingdate, bookingtime, facility.getFacID());
        System.out.println("remainroom " + remainroom);
        if (remainroom <= 0)
        {
            waitingpos = Math.abs(remainroom - 1);
        }
        else
        {
            waitingpos = 0;
        }

        if (bookingno == 0)
        {
            bookingnoFound = false;
            bookingno = getNextBookingNo(con);
            System.out.println("here");
            System.out.println(bookingnoFound);
        }

        String SQLString = "insert into bookingstatus values (?,?,?,?)";
        String SQLString2 = "insert into booking values(?,?,?,?)";

        try
        {
            con.setAutoCommit(false);
//            DateFormat format = new SimpleDateFormat("dd-MM-yy");
            PreparedStatement statement = null;

            //creating a new booking if bookingno is not found yet
            if (!bookingnoFound)
            {
                statement = con.prepareStatement(SQLString2);

                statement.setInt(1, bookingno);
                statement.setInt(2, facility.getFacID());
                statement.setDate(3, sqlBookingdate);
                statement.setInt(4, bookingtime);

                rowsInserted = statement.executeUpdate();
            }

            statement = con.prepareStatement(SQLString);

            statement.setInt(1, bookingno);
            statement.setString(2, guestNo);//guestno should be got
            statement.setInt(3, waitingpos);
            statement.setInt(4, inno);//inno should be got

            rowsInserted += statement.executeUpdate();

            System.out.println("Booking created rows inserted = " + rowsInserted);
            con.commit();
        }
        catch (SQLException e)
        {
            System.out.println("Fail in DataMapper - ERROR IN BOOKING");
            System.out.println(e.getMessage());
        }
        return rowsInserted == 1;
    }
public ArrayList<Facility> getFacArrayForJlist(String type, Date bookingdate, int bookingtime)
{ 
        ArrayList<Facility> result=new ArrayList();
    ArrayList<Facility> facList=getfacilitylist(type,con);
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


public ArrayList<Guest> getWaitingListForJlist(int facId, Date bookingdate, int bookingtime)
{ ArrayList<Guest> waitingarray= new ArrayList();
        java.sql.Date sqlBookingdate = new java.sql.Date(bookingdate.getTime());
        String SQLString = "select g.guestno, gi.guestfirstname, gi.guestlastname from guest g, guestid gi, booking b, bookingstatus bs " +
"  where gi.guestid=g.guestid and bs.guestno=g.guestno and b.bookingid=bs.bookingid and b.facilityid=? and b.bookingdate=? and b.bookingtime=? and bs.waitingpos>0";
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, facId);
            statement.setDate(2, sqlBookingdate);
            statement.setInt(3, bookingtime);

            ResultSet rs = statement.executeQuery();
            System.out.println("rs"+rs); 
            while (rs.next())
            {Guest tempGuest;
                String guestNo = rs.getString(1);
                String guestFN = rs.getString(2);
                String guestLN= rs.getString(3);
                
                tempGuest=new Guest(guestNo,guestFN,guestLN);
                waitingarray.add(tempGuest);
                System.out.println("waitingarray size ="+waitingarray.size());
            }
        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getNextReservationNo");
            System.out.println(e.getMessage());
        };
        return waitingarray;
}
}