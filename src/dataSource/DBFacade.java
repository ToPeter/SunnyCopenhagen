
package dataSource;
import domain.*;


import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBFacade
{

    private DataMapper dm;
    private Connection con;
    private UnitOfWorkForGuest unitOfWork;

    //== Singleton start
    private static DBFacade instance;

    private DBFacade()
    {
        dm = new DataMapper(con);
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

    public Reservation getReservation(int reservationNo)
    {
        return dm.getreservation(reservationNo, con);
    }

    public ArrayList<Room> getRoomsAvailable(String fromDate, String endDate, String type)
    {
        System.out.println("facade" + fromDate);
        return dm.getRoomAvailable(fromDate, endDate, type, con);
    }
    
    
   

    //== this is gonna be changed to making the reservations / updating and stuff
    //== i kept is so we could see how we did earlier :) - Peter K
    
    
      //===== Methods to handle business transactions
	  //===	Should be called upon start of a transaction
    //    Any changes done after last commit will be ignored
    //Peter T 
    
     //======	Methods to retrieve data 
    public Guest getGuest(int reservation)
    {
        return dm.getGuest(reservation, con);
    }
    
    
      public int getNextReservationNo()
    {
        return dm.getNextReservationNo(con);
    }

    
    
    public void startProcessGuestBusinessTransaction()
    {
         unitOfWork = new UnitOfWorkForGuest (dm);
    }
    
    //=====	Methods to register changes	in UnitOfWork  
    public void registerNewOrder(Guest guest)
    {
        if (unitOfWork != null)
        {
            unitOfWork.registerNewGuest(guest);
        }
    }

    public void registerDirtyOrder(Guest guest)
    {
        if (unitOfWork != null)
        {
            unitOfWork.registerDirtyGuest(guest);
        }
    }
    
     public void registerDeleteGuest(Guest currentGuest)
    {
        if (unitOfWork != null)
        {
            unitOfWork.registerDeleteGuest(currentGuest);
        }
    }
    
     	  //===== Methods to handle business transactions
	  //===	Should be called upon start of a transaction
    //    Any changes done after last commit will be ignored
    public void startProcessOrderBusinessTransaction()
    {
        unitOfWork = new UnitOfWorkForGuest(dm);
    }

    //=== Save all changes
    public boolean commitProcessOrderBusinessTransaction()
    {
        boolean status = false;
        if (unitOfWork != null)
        {
            status = unitOfWork.commit(con);
            unitOfWork = null;
        }
        return status;
    }
    

    public int[] getPriceList()
    {
        return dm.getPriceList(con);
      
    }

}
