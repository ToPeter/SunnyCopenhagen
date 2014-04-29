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

    private final DataMapperInterface dataMapper;
    private final ArrayList<Guest> newGuest;
    private final ArrayList<GuestID> newGuestID;
    private final ArrayList<GuestID> dirtyGuestID;
    private final ArrayList<Guest> delGuest;
    private final ArrayList<Guest> dirtyGuest;
    private final ArrayList<Reservation> updatedDeposit;
    private final ArrayList<Reservation> dirtyUpdatedDeposit;

    public UnitOfWorkForGuest(DataMapperInterface dataMapper)
    {
        this.dataMapper = dataMapper;
        newGuest = new ArrayList<>(); // will never exceed 1 element
        dirtyGuest = new ArrayList<>(); // will never exceed 1 element
        delGuest = new ArrayList<>();
        dirtyGuestID = new ArrayList<>();
        newGuestID = new ArrayList<>();
        updatedDeposit = new ArrayList<>();
        dirtyUpdatedDeposit = new ArrayList<>();
    }

    //====== Methods to register changes ==========================
    public void registerNewGuest(Guest guest)
    {
        if (!newGuest.contains(guest) && !dirtyGuest.contains(guest))    // if not all ready registered in any list
        {
            newGuest.add(guest);

        }
    }

    public void registerDirtyGuest(Guest guest)
    {
        if (!newGuest.contains(guest) && !dirtyGuest.contains(guest)) // if not all ready registered in any list

        {
            dirtyGuest.add(guest);
        }
    }

    void registerDeleteGuest(Guest guest)
    {
        if (newGuest.contains(guest))
        {
            newGuest.remove(guest);
        }
        if (dirtyGuest.contains(guest))
        {
            dirtyGuest.remove(guest);
        }
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
            status = status && dataMapper.insertGuestID(newGuestID);
            status = status && dataMapper.insertGuest(newGuest);
            status = status && dataMapper.updateDeposit(updatedDeposit);
            status = status && dataMapper.updateGuestID(dirtyGuestID);
            
            if (!status)
            {
                throw new Exception("Process Order Business Transaction aborted");

            }
            //=== system transaction - ends with success
            con.commit();
        }
        catch (Exception e)
        {
            //=== system transaction - ends with roll back
            try
            {
                con.rollback();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            status = false;
        }
        return status;
    }

    void updateDeposit(Reservation currentReservation)
    {

        if (!updatedDeposit.contains(currentReservation) && !dirtyUpdatedDeposit.contains(currentReservation))    // if not all ready registered in any list
        {
            updatedDeposit.add(currentReservation);

        }
    }

    void registerNewGuestID(GuestID guestID)
    {
        if (!newGuestID.contains(guestID) && !dirtyGuestID.contains(guestID))    // if not all ready registered in any list
        {
            newGuestID.add(guestID);
        }

    }

    void updateGuestID(GuestID guestID)
    {
        if (!newGuestID.contains(guestID) && !dirtyGuestID.contains(guestID))
        {
            dirtyGuestID.add(guestID);
        }
    }

}
