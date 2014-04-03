
package dataSource;

import domain.*;
import java.sql.*;
import java.util.ArrayList;




/**
 *
 * @author pc
 */
public class UnitOfWorkForGuest
{
    //private final DataMapper dataMapper;
    private final DataMapperInterface dataMapper;
    private final ArrayList<Guest> newGuest;
    private final ArrayList<Guest> delGuest;
    private final ArrayList<Guest> dirtyGuest;
    private final ArrayList<Reservation> updatedDeposit;
    private final ArrayList<Reservation> dirtyUpdatedDeposit;
    
    
   
    public UnitOfWorkForGuest (DataMapperInterface dataMapper)
    {
        this.dataMapper = dataMapper;
        newGuest = new ArrayList<>(); // will never exceed 1 element
        dirtyGuest = new ArrayList<>(); // will never exceed 1 element
        delGuest = new ArrayList<>();
        updatedDeposit = new ArrayList<>();
        dirtyUpdatedDeposit = new ArrayList<>();
    }
    
    //====== Methods to register changes ==========================

    public void registerNewGuest(Guest guest)
    {
        if (!newGuest.contains(guest) &&  !dirtyGuest.contains(guest))    // if not all ready registered in any list
        {
            System.out.println("Adding guets int UOW: "+guest.toString());
            newGuest.add(guest);

           
        }        
    }
    
     public void registerDirtyGuest(Guest guest)
    {
        if (!newGuest.contains(guest) &&  !dirtyGuest.contains(guest)) // if not all ready registered in any list
              
        {
            dirtyGuest.add(guest);
        }
    }
     
     void registerDeleteGuest(Guest guest)
    {
        if (newGuest.contains(guest))
            newGuest.remove(guest);
        if(dirtyGuest.contains(guest))
            dirtyGuest.remove(guest);
           delGuest.add(guest);      
    }
    
      //====== Method to save changes to DB ===============================
    public boolean commit(Connection con)
    {
        boolean status = true;  // will be set false if any part of transaction fails    
        try
        {
            //=== system transaction - starts
            con.setAutoCommit(false);
            status = status && dataMapper.insertGuest(newGuest, con);
            System.out.println("printing status aftter inserGuest "+status);
            status = status && dataMapper.updateDeposit(updatedDeposit, con);
             System.out.println("printing status aftter updateDeposit "+status);
         //  status = status && dataMapper.deleteGuest(delGuest, con);

         //   status = status && dataMapper.updateOrderDetails(updateOrderDetails, dirtyOrders, connection);
        //    status = status && dataMapper.deleteOrderDetails(deleteOrderDetails, dirtyOrders, connection);
       //     status = status && dataMapper.insertOrderDetails(newOrderDetails, connection);

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
                        System.out.println("Status is : "+status);
        return status;
    }

    void updateDeposit(Reservation currentReservation)
    {

     if (!updatedDeposit.contains(currentReservation) &&  !dirtyUpdatedDeposit.contains(currentReservation))    // if not all ready registered in any list
        {
            updatedDeposit.add(currentReservation);

           
        }        
    }

}
