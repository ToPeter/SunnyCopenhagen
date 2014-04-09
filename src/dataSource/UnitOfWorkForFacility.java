/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.Booking;
import domain.Guest;
import domain.Reservation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Tomoe
 */
public class UnitOfWorkForFacility
{    
    private final DataMapperForFacility dmf;
    private final ArrayList<Booking> delBooking; 
    private final ArrayList<Booking> newBooking; 
    
   
    public UnitOfWorkForFacility (DataMapperForFacility dmf)
    {
        this.dmf = dmf;
        delBooking = new ArrayList<>();
        newBooking = new ArrayList<>();
    }

    public boolean commit(Connection con)
    {
        boolean status = true;  // will be set false if any part of transaction fails    
        try
        {
            //=== system transaction - starts
            con.setAutoCommit(false);
            
            status = status && dmf.insertNewBooking(newBooking,con);
            
            status = status && dmf.updateWaitingPos(delBooking,con);

            
            
            

            if (!status)
            {
                throw new Exception("Process Order Business Transaction aborted");
                
            }
            //=== system transaction - ends with success
            con.commit();
        } catch (Exception e)
        {
            //=== system transaction - ends with roll back
            try
            {
                con.rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            status = false;
        }
                        System.out.println("Status in Unit : "+status);
        return status;
    }
    
    
    
     public void registerNewBooking(Booking booking)
    {
        if ( !newBooking.contains(booking)) // if not all ready registered in any list
              
        {
            newBooking.add(booking);
        }
    }
     
        public void registerDeleteBooking(Booking booking)
    {
        if ( !delBooking.contains(booking)) // if not all ready registered in any list
              
        {
            delBooking.add(booking);
        }
    }

    
}
