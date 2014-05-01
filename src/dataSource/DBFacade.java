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

    private DataMapperInterface dm;
    private Connection con;
    private UnitOfWorkForGuest unitOfWork;
    private DataMapperForMail mailmapper;

    //== Singleton start
    private static DBFacade instance;

    public DBFacade()
    {
        con = new DBConnector().getConnection();
        dm = new DataMapper(con);
        mailmapper = new DataMapperForMail(con);

    }

    public DBFacade(DataMapperInterface dmi)
    {
        dm = dmi;
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
        return dm.getreservation(reservationNo);
    }

    public ArrayList<Room> getRoomsAvailable(Date fromDate, Date endDate, String type)
    {
        return dm.getRoomAvailable(fromDate, endDate, type);
    }

    //== this is gonna be changed to making the reservations / updating and stuff
    //== i kept is so we could see how we did earlier :) - Peter K
    //===== Methods to handle business transactions
    //===	Should be called upon start of a transaction
    //    Any changes done after last commit will be ignored
    //Peter T 
    //======	Methods to retrieve data 
    public GuestID getGuest(int guestId)
    {
        return dm.getGuest(guestId);
    }

    public String getRoomType(int roomNo)
    {
        return dm.getRoomType(roomNo);
    }

    public int getNextReservationNo()
    {
        return dm.getNextReservationNo();
    }

    public ArrayList<Reservation> getReservationDepositNotPaid()
    {
        return dm.getreservationDepositNotPaid();
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
        return dm.getPriceList();

    }

    public boolean bookRoom(Reservation reservation)
    {

        return dm.createReservation(reservation);

    }

    public boolean updateDeposit(Reservation currentReservation)
    {

        unitOfWork.updateDeposit(currentReservation);

        return true;
    }

    public boolean getGuestInfo(String userName, String password)
    {
        return dm.getGuestInfo(userName, password);
    }

    public String getReservationString(int reservationNo)
    {
        Reservation lookedUpReservation;
        String guestsString = "";
        String resultString;

        lookedUpReservation = dm.getreservation(reservationNo);
        resultString = lookedUpReservation + guestsString;

        return resultString;
    }

    public boolean getEmpInfo(String userName, String password)
    {
        return dm.getEmpInfo(userName, password);
    }

    public void registerNewGuestID(GuestID currentGuestID)
    {

        if (unitOfWork != null)
        {
            unitOfWork.registerNewGuestID(currentGuestID);
        }

    }

    public ArrayList<Guest> getGuestArrayForMail(int reservationNo)
    {
        return mailmapper.getGuestArrayForMail(reservationNo);
    }

    public String getGuestStringForMail(int reservationNo)
    {
        return mailmapper.getGuestStringForMail(reservationNo);
    }

    public String getEmpLogInName(String userName)
    {
        String name = dm.getEmpLogInName(userName);

        return name;
    }

    public String getGuestLogInName(String userName)
    {
        String name = dm.getGuestLogInName(userName);
        return name;
    }

    public GuestID searchGuest(String guestno)
    {
        return dm.searchGuest(guestno);

    }

    public ArrayList<GuestID> searchGuestByReservationNO(int reservationNO)
    {
        return dm.searchGuestByReservationNO(reservationNO);

    }

    public boolean updateGuestID(GuestID currentGuestID)
    {
        unitOfWork.updateGuestID(currentGuestID);
        return true;
    }
}
