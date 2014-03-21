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

/**
 *
 * @author Tomoe
 */
public class DataMapper implements DataMapperInterface
{
    
    static boolean testRun = false;  // used to enable test output

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
                        paid);
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

    int[] getPriceList(Connection con)
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


//======  Methods to read from DB =======================================================
  // Retrieve a specific order and related order details
    // Returns the Order-object
    public Guest getGuest(int reservationNo, Connection con)
    {
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
                guest = new Guest( rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3), // read as SQL date type
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getString(9));
                
            }

         
        } catch (Exception e)
        {
            System.out.println("Fail in OrderMapper - getOrder");
            System.out.println(e.getMessage());
        }
        if (testRun)
        {
            System.out.println("Retrieved Order: " + guest);
        }
        return guest;
    }


}
