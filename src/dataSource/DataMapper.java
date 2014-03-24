/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomoe
 */
public class DataMapper implements DataMapperInterface
{

    static boolean testRun = true;  // used to enable test output

    private final Connection con;
    private Date parsedFrom;
    private Date parsedTo;
    Calendar c = Calendar.getInstance();

    public DataMapper(Connection con)
    {
        this.con = con;

    }

    @Override
    public Reservation getreservation(int reservationNo, Connection con)
    {
        boolean paid;
        Reservation reservation = null;
        String SQLString = // get reservation
                "select * "
                + "from reservation "
                + "where reservationNo = ?";
        PreparedStatement statement = null;

        try
        {
            //=== get reservaton

            statement = con.prepareStatement(SQLString);

            statement.setInt(1, reservationNo);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                if (rs.getInt(6) == 0)//if DepositPaid==0
                {
                    paid = false;
                }
                else
                {
                    paid = true;
                }
                reservation = new Reservation(
                        rs.getInt(1),
                        reservationNo,
                        rs.getDate(3),//fromDate
                        rs.getDate(4),//toDate
                        rs.getDate(5),//bookingDate
                        rs.getInt(6));
                System.out.println("gotreservation");
            }

        } catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getreservation");
            System.out.println(e.getMessage());
        } finally														// must close statement
        {
            try
            {
                statement.close();
            } catch (SQLException e)
            {
                System.out.println("Fail in DataMapper - getreservation");
                System.out.println(e.getMessage());
            }
        }
        return reservation;
    }

    @Override
    public ArrayList<Room> getRoomAvailable(String fromDate, String endDate, String type, Connection con)
    {
        ArrayList<Room> roomAvailableList = new ArrayList();
        Room tempRoom;
        String SQLString = // get roomavailable
                "select * from room r "
                + "WHERE Type = '" + type + "' AND r.Roomno NOT IN "
                + "(SELECT re.roomno FROM Reservation re "
                + "where roomNo=r.roomNo AND fromdate<? AND roomno in("
                + "select roomno from reservation where endDate >?))order by roomno";

        PreparedStatement statement = null;

        try
        {
            DateFormat format = new SimpleDateFormat("dd-MM-yy");

            parsedFrom = format.parse(fromDate);
            parsedTo = format.parse(endDate);
            c.setTime(parsedFrom);
            System.out.println(parsedFrom);
            java.sql.Date sqlFromDate = new java.sql.Date(parsedFrom.getTime());
            java.sql.Date sqlToDate = new java.sql.Date(parsedTo.getTime());
            System.out.println(sqlFromDate);
            System.out.println(sqlToDate);
            statement = con.prepareStatement(SQLString);
            statement.setDate(1, sqlToDate);
            statement.setDate(2, sqlFromDate);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                System.out.println(rs.getInt(1));

                tempRoom = new Room(rs.getInt(1), type);
                System.out.println("adding");
                roomAvailableList.add(tempRoom);
            }
        } catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getRoomAvailable");
            System.out.println(e.getMessage());
        } finally														// must close statement
        {
            try
            {
                statement.close();
            } catch (SQLException e)
            {
                System.out.println("Fail in DataMapper - getRoomAvailable");
                System.out.println(e.getMessage());
            }

            return roomAvailableList;
        }

    }

    @Override

    public int[] getPriceList(Connection con)
    {
        int[] priceList = new int[3];

        String SQLString = // get PriceList
                "select price from Prices ";

        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            while (rs.next())
            {
                priceList[i] = rs.getInt(1);
                i++;
//                System.out.println(rs.getInt(1));
//
//                tempRoom = new Room(rs.getInt(1), type);
//                System.out.println("adding");
//                roomAvailableList.add(tempRoom);
            }
        } catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getPriceList");
            System.out.println(e.getMessage());
        } finally														// must close statement
        {
            try
            {
                statement.close();
            } catch (SQLException e)
            {
                System.out.println("Fail in DataMapper - getPriceList");
                System.out.println(e.getMessage());
            }
            return priceList;
        }
    }

    @Override
    public void createReservation(Reservation res, Connection con)
    {

        int rowsInserted = 0;
        String SQLString = "insert into reservation values (?,?,?,?,?,?,?) ";

        try
        {
           con.setAutoCommit(false);
//            DateFormat format = new SimpleDateFormat("dd-MM-yy");
            PreparedStatement statement = null;

            java.sql.Date sqlFromDate = new java.sql.Date(res.getFromDate().getTime());
            java.sql.Date sqlToDate = new java.sql.Date(res.getEndDate().getTime());
            java.sql.Date sqlToBookingDate = new java.sql.Date(res.getBoookingDate().getTime());

            statement = con.prepareStatement(SQLString);

            statement.setInt(1, res.getRoomNo());
            statement.setInt(2, res.getReservationNo());
            statement.setDate(3, sqlFromDate);
            statement.setDate(4, sqlToDate);
            statement.setDate(5, sqlToBookingDate);
            statement.setInt(6, res.isDepositPaid());
            statement.setInt(7, 1111);

            statement.executeUpdate();

            System.out.println("printing statement " + rowsInserted);
            con.commit();
        } catch (SQLException e)
        {
            System.out.println("Fail in DataMapper - ERROR IN BOOKING");
            System.out.println(e.getMessage());
        }

    }
    //======  Methods to read from DB =======================================================
// Retrieve a specific order and related order details
// Returns the Order-object

    public ArrayList<Guest> getGuests(int reservationNo, Connection con)
    {
        ArrayList<Guest> guestList = new ArrayList<>();
        Guest guest = null;
        String SQLString1 = // get order
                "select * "
                + "from guest "
                + "where reservationNo = ?";

        PreparedStatement statement = null;

        try
        {
            //=== get order
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, reservationNo);

            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                guest = new Guest(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9));
                guestList.add(guest);
            }

        } catch (Exception e)
        {
            System.out.println("Fail in OrderMapper - getOrder");
            System.out.println(e.getMessage());
        }
        if (testRun)
        {
            System.out.println("Retrieved Order: " + guestList.toString());
        }
        return guestList;
    }

    // Retrieves the next unique order number from DB
    public int getNextGuestNo(Connection con)
    {
        int nextGuestNo = 0;
        String SQLString = "select orderseq.nextval  " + "from dual";
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                nextGuestNo = rs.getInt(1);
            }
        } catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getNextGuestNo");
            System.out.println(e.getMessage());
        }
        return nextGuestNo;
    }

    //====== Methods to save to DB =========================================================
    // Insert a list of new orders
    // returns true if all elements were inserted successfully
    public boolean insertGuest(ArrayList<Guest> guestList, Connection con) throws SQLException
    {

        int rowsInserted = 0;
        String SQLString = "insert into guest values (?,?,?,?,?,?,?,?,?)";

        PreparedStatement statement = null;
        statement = con.prepareStatement(SQLString);

        for (int i = 0; i < guestList.size(); i++)
        {

            Guest guest = guestList.get(i);

            System.out.println("printing guest: " + guest.toString());

            statement.setInt(1, guest.getReservationNo());
            statement.setString(2, guest.getGuestNo());
            statement.setInt(3, guest.getPassword());
            statement.setString(4, guest.getGuestFirstName());
            statement.setString(5, guest.getGuestFamilyName());
            statement.setString(6, guest.getAddress());
            statement.setString(7, guest.getCountry());
            statement.setInt(8, guest.getPhoneNo());
            statement.setString(9, guest.getEmail());

            System.out.println("printing statement " + rowsInserted);
            rowsInserted = statement.executeUpdate();

            System.out.println("inserted row: " + rowsInserted);

        }
        if (testRun)
        {
            System.out.println("insertOrders(): " + (rowsInserted == guestList.size())); // for test
        }

        return (rowsInserted == guestList.size());
    }

    public boolean deleteGuest(ArrayList<Guest> delGuest, Connection con) throws SQLException
    {
        int rowsDeleted = 0;

        String SQLStringDeleteGuest = "delete from guest where guestNo = ?";
        PreparedStatement statementDeleteGuest = con.prepareStatement(SQLStringDeleteGuest);
        for (int i = 0; i < delGuest.size(); i++)
        {
            Guest guest = delGuest.get(i);

            statementDeleteGuest.setString(1, guest.getGuestNo());
            //      statementDeleteDetails.executeUpdate();

            statementDeleteGuest.setString(1, guest.getGuestNo());
            //      statementDeleteOrder.setInt(2, order.getVersionNumber());   // old version number
            if (statementDeleteGuest.executeUpdate() > 0)
            {
                rowsDeleted++;
            }
        }
        return rowsDeleted == delGuest.size();
    }

//http://www.w3schools.com/sql/sql_autoincrement.asp    
//CREATE SEQUENCE seq_resno 
//MINVALUE 1
//START WITH 1000
//INCREMENT BY 1;
    public int getNextReservationNo(Connection conn)
    {
        int nextReservationNo = 0;
        String SQLString = "select seq_resno.nextval  " + "from dual";
        PreparedStatement statement = null;
        try
        {
            statement = conn.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                nextReservationNo = rs.getInt(1);
            }
        } catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getNextReservationNo");
            System.out.println(e.getMessage());
        }
        return nextReservationNo;
    }

}
