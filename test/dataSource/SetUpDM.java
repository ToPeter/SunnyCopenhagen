/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Reservation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomoe
 */
public class SetUpDM
{

    public static void setUpForTest(Connection con)
    {
        /**
         * run the following script on SQL before testing
         *
         */

        String SQLString1 = "delete from reservation where reservationno = 55555 ";
        String SQLString2 = "update reservation set depositpaid = 0 where reservationno = 1111 ";
        String SQLString3 = "update reservation set Ver_No = 0 where reservationno = 1111 ";
        String SQLString4 = "delete from guestID where guestid = 4321";
        String SQLString5 = "delete from guestID where guestid = 9876";
        String SQLString6 = "delete from guest where reservationno=2222";
        String SQLString7 = "insert into guestid values(9876, 'firstname', 'familyname','address','country',0,'email')";

        Statement statement = null;

        try
        {
            con.setAutoCommit(false);
            //=== get reservaton

            statement = con.createStatement();

            int rows = statement.executeUpdate(SQLString1);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(SQLString2);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(SQLString3);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(SQLString4);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(SQLString5);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(SQLString6);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(SQLString7);
            System.out.println("rows" + rows);
        }
        catch (Exception e)
        {
            System.out.println("Fail in settingup");
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
                System.out.println("Fail in settingup");
                System.out.println(e.getMessage());
            }
            try
            {
                con.commit();
            }
            catch (SQLException ex)
            {
                try
                {
                    con.rollback();
                }
                catch (SQLException ex1)
                {
                    Logger.getLogger(SetUpDM.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(SetUpDM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void setUpForFacilityTest(Connection con)
    {

        String deleteBooking = "delete from bookingstatus where bookingid=1000 ";
        String SQLString2 = "delete from booking where bookingid=1000 ";
        String insertBooking = "insert into booking values (1000,101,'01-04-14',19)";
        String insertBookingStatus = "insert into bookingstatus values(1000,'10000-1',1,0,0) ";
        String insertBookingStatus2 = "insert into bookingstatus values(1000,'10000-2',2,0,0)";

        Statement statement = null;

        try
        {
            con.setAutoCommit(false);
            //=== get reservaton

            statement = con.createStatement();

            int rows = statement.executeUpdate(deleteBooking);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(SQLString2);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(insertBooking);
            System.out.println("rows" + rows);
            rows = statement.executeUpdate(insertBookingStatus);
            System.out.println("rows" + rows);

            rows = statement.executeUpdate(insertBookingStatus2);
            System.out.println("rows" + rows);
        }
        catch (Exception e)
        {
            System.out.println("Fail in settingup");
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
                System.out.println("Fail in settingup");
                System.out.println(e.getMessage());
            }
            try
            {
                con.commit();
            }
            catch (SQLException ex)
            {
                try
                {
                    con.rollback();
                }
                catch (SQLException ex1)
                {
                    Logger.getLogger(SetUpDM.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(SetUpDM.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
