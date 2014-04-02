package dataSource;

import domain.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBFacade
{

    //private DataMapper dm;
    private DataMapperInterface dm;
    private Connection con;
    private UnitOfWorkForGuest unitOfWork;

    //== Singleton start
    private static DBFacade instance;

    public DBFacade()
    {
        con = new DBConnector().getConnection();
        dm = new DataMapper(con);
        //con = new DBConnector().getConnection();  // the connection will be released upon program 
        // termination by the garbage collector		  
    }

    public DBFacade(DataMapperInterface dmi)
    {
        dm = dmi;
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

    public ArrayList<Room> getRoomsAvailable(Date fromDate, Date endDate, String type)
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
    public ArrayList<Guest> getGuests(int reservation)
    {
        return dm.getGuests(reservation, con);
    }

    public String getRoomType(int roomNo)
    {
        return dm.getRoomType(roomNo, con);
    }
    public int getNextReservationNo()
    {
        return dm.getNextReservationNo(con);
    }

    public ArrayList<Reservation> getReservationDepositNotPaid()
    {
        return dm.getreservationDepositNotPaid(con);
    }

    public void startProcessGuestBusinessTransaction()
    {
        unitOfWork = new UnitOfWorkForGuest(dm);
    }

    //=====	Methods to register changes	in UnitOfWork  
    public void registerNewGuest(Guest guest)
    {
        if (unitOfWork != null)
        {
            System.out.println("HI");
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
    public boolean commitProcessGuestBusinessTransaction()
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

    public boolean bookRoom(Reservation reservation)
    {
        try
        {
            return dm.createReservation(reservation, con);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DBFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean updateDeposit(Reservation currentReservation)
    {
        
        unitOfWork.updateDeposit(currentReservation);
        
        return true;
    }

  
       

 

    public boolean getGuestInfo(String userName, String password)
    {
        return dm.getGuestInfo(userName, password,con);
       
    }
    
    

    public String getReservationString(int reservationNo)
    {
        Reservation lookedUpReservation;
        ArrayList<Guest> lookedUpGuestarray;
        String guestsString = "";
        String resultString;

        lookedUpReservation = dm.getreservation(reservationNo, con);
        lookedUpGuestarray = dm.getGuests(reservationNo, con);
        for (int i = 0; i < lookedUpGuestarray.size(); i++)
        {
            int guestNo = i + 1;
            guestsString += "Guest " + guestNo + "\n"
                    + lookedUpGuestarray.get(i).toString();
        }
        resultString = lookedUpReservation + guestsString;

        return resultString;
    }

    
    public boolean getEmpInfo(String userName, String password)
    {
       return dm.getEmpInfo(userName, password,con);
    }

 

}
