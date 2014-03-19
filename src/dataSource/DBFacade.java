/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import java.sql.Connection;

/**
 *
 * @author Tomoe
 */
public class DBFacade
{
    
    private DataMapper om;
    private Connection con;

    //== Singleton start
    private static DBFacade instance;

    private DBFacade()
    {
        om = new DataMapper(con);
        con = new DBConnector().getConnection();  // the connection will be released upon program 
        // termination by the garbage collector		  
    }

    public static DBFacade getInstance()
    {
        if (instance == null)
        {
            instance = new DBFacade();
        }
        return instance;
    }
	  //== Singleton end

    
    //== this is gonna be changed to making the reservations / updating and stuff
    //== i kept is so we could see how we did earlier :) - Peter K
    
    
//    public Order getOrder(int ono)
//    {
//        return om.getOrder(ono, con);
//    }
//
//    public boolean saveNewOrder(Order o)
//    {
//        return om.saveNewOrder(o, con);
//    }
//
//    public boolean saveNewOrderDetail(OrderDetail od)
//    {
//        return om.saveNewOrderDetail(od, con);
//    }
//
//    public boolean updateCurrentOrder(Order o)
//    {
//        return om.updateCurrentOrder(o, con);
//    }
//
//    public boolean updateOrderDetail(OrderDetail od)
//    {
//        return om.updateOrderDetail(od, con);
//    }
//
//    public boolean deleteOrderDetail(OrderDetail od)
//    {
//           return om.deleteOrderDetail(od, con);
//    }
//
//    public void deleteOrder(Order o)
//    {
//
//        om.deleteOrder(o, con);
//    }
}
