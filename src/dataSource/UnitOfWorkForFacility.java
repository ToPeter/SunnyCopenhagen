package dataSource;

import domain.Booking;
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
    private final ArrayList<Booking> newBookingStatus;
    private final ArrayList<Booking> newBookingInstructor;
    
    String type;

    public UnitOfWorkForFacility(DataMapperForFacility dmf)
    {
        this.dmf = dmf;
        delBooking = new ArrayList<>();
        newBooking = new ArrayList<>();
        newBookingStatus = new ArrayList<>();
        newBookingInstructor = new ArrayList<>();
    }

    public boolean commit(Connection con)
    {
        boolean status = true;  // will be set false if any part of transaction fails    
        try
        {
            //=== system transaction - starts
            con.setAutoCommit(false);
                status = status && dmf.createFacilityBooking(newBooking, newBookingStatus);
                status = status && dmf.updateWaitingPos(delBooking);
                status = status && dmf.saveInstructorBooking(newBookingInstructor);

            if (!status)
            {
                throw new Exception("Process facility Business Transaction aborted");

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

    public void registerDeleteBooking(Booking deleteSql)
    {
        if (!delBooking.contains(deleteSql)) // if not all ready registered in any list

        {
            delBooking.add(deleteSql);
        }

    }

   public void registerNewBooking(Booking bookingSQL2)
    {
        if (!newBooking.contains(bookingSQL2)) // if not all ready registered in any list

        {
            newBooking.add(bookingSQL2);
        }
    }

    public void registerNewBookingStatus(Booking bookingSQL1)
    {
        if (!newBookingStatus.contains(bookingSQL1)) // if not all ready registered in any list

        {
            newBookingStatus.add(bookingSQL1);
        }
    }
   public void registerNewInstructorBooking(Booking booking)
    {
        if (!newBookingInstructor.contains(booking)) // if not all ready registered in any list

        {
            newBookingInstructor.add(booking);
        }
    }

}
