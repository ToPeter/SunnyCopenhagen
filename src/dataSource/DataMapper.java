/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            { if(rs.getInt(6)==0)//if DepositPaid==0
            {paid=false;}
            else 
            {paid=true;}
                reservation = new Reservation(
                        rs.getInt(1),
                        reservationNo,
                        rs.getDate(3),//fromDate
                        rs.getDate(4),//toDate
                        rs.getDate(5),//bookingDate
                        paid);
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
        
}    
    
    
