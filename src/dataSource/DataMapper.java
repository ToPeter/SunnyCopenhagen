/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Reservation;
import domain.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomoe
 */
public class DataMapper implements DataMapperInterface
{

    private final Connection con;

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

        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getreservation");
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
                System.out.println("Fail in DataMapper - getreservation");
                System.out.println(e.getMessage());
            }
        }
        return reservation;
    }

    @Override
    public ArrayList<Room> getRoomAvailable(String fromDate, String endDate, String type, Connection con)
    {
        ArrayList<Room> roomAvailableList = null;
        Room tempRoom;
        String SQLString = // get roomavailable
                "select R.Roomno from room R "
                + "WHERE Type = '"+type+"' AND r.Roomno NOT IN "
                + "(SELECT roomno FROM Reservation "
                  + "where enddate >= ? "
                  +"AND fromdate <= ?)";
         SimpleDateFormat format = new SimpleDateFormat("dd-mm-yy");
        Date parsedFrom=null;
        Date parsedTo=null;
        try
        {
            parsedFrom = format.parse(fromDate);
            parsedTo = format.parse(endDate);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.sql.Date sqlFromDate = new java.sql.Date(parsedFrom.getTime());
                java.sql.Date sqlToDate = new java.sql.Date(parsedTo.getTime());
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setDate(1, sqlToDate);
            statement.setDate(2, sqlFromDate);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {    
                tempRoom=new Room(rs.getInt(1),type);
                
                roomAvailableList.add(tempRoom);
            }
        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getRoomAvailable");
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
                System.out.println("Fail in DataMapper - getRoomAvailable");
                System.out.println(e.getMessage());
            }

        return roomAvailableList;  
    }

}
}
