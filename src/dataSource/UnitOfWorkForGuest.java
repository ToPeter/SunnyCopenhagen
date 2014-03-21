
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
    private final DataMapper dataMapper;
    private final ArrayList<Guest> newGuest;
    private final ArrayList<Guest> delGuest;
    private final ArrayList<Guest> dirtyGuest;
    
//    private final ArrayList<OrderDetail> newOrderDetails;
//    private final ArrayList<OrderDetail> updateOrderDetails;
//    private final ArrayList<OrderDetail> deleteOrderDetails;
    
   
    public UnitOfWorkForGuest (DataMapper dataMapper)
    {
        this.dataMapper = dataMapper;
        newGuest = new ArrayList<>(); // will never exceed 1 element
        dirtyGuest = new ArrayList<>(); // will never exceed 1 element
        delGuest = new ArrayList<>();
        
    //    newOrderDetails = new ArrayList<>();
   //     updateOrderDetails = new ArrayList<>();
   //     deleteOrderDetails = new ArrayList<>();
    }
    
    //====== Methods to register changes ==========================

    public void registerNewGuest(Guest guest)
    {
        if (!newGuest.contains(guest) &&  !dirtyGuest.contains(guest))    // if not all ready registered in any list
        {
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
    public boolean commit(Connection connection)
    {
        boolean status = true;  // will be set false if any part of transaction fails    
        try
        {
            //=== system transaction - starts
            connection.setAutoCommit(false);

       //     status = status && dataMapper.insertOrders(newGuest, connection);
       //     status = status && dataMapper.updateOrders(dirtyGuest, connection);
       //     status = status && dataMapper.deleteOrder(delGuest, connection);
            
         //   status = status && dataMapper.updateOrderDetails(updateOrderDetails, dirtyOrders, connection);
        //    status = status && dataMapper.deleteOrderDetails(deleteOrderDetails, dirtyOrders, connection);
       //     status = status && dataMapper.insertOrderDetails(newOrderDetails, connection);

            if (!status)
            {
                throw new Exception("Process Order Business Transaction aborted");
            }
            //=== system transaction - ends with success
            connection.commit();
        } catch (Exception e)
        {
            //=== system transaction - ends with roll back
            try
            {
                connection.rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            status = false;
        }
        return status;
    }
//
//    private void removeOrderDetails(int orderNumber)
//    {
//        Iterator<OrderDetail> iterator = newOrderDetails.iterator();
//        while(iterator.hasNext())
//        {
//            if(iterator.next().getOrderNumber() == orderNumber)
//                iterator.remove();
//        }
//    }
}
