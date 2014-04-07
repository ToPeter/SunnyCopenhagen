/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dataSource;

import domain.GuestID;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Tomoe
 */
public class DataMapperForMail
{
    public String getGuestInfoForMail(int reservationNo, Connection con)
    {   int guestno=1;
        String guestinfo="";
        String SQLString = // get reservation
//                "    select gi.guestFirstname , gi.guestlastname,g.guestno,g.password from guestID gi, guest g " +
"   where G.Guestid=Gi.Guestid and g.reservationno=?";
   PreparedStatement statement = null;

        try
        {

            statement = con.prepareStatement(SQLString);

            statement.setInt(1, reservationNo);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {//   select gi.guestFirstname , gi.guestlastname,g.guestno,g.password 
                guestinfo+= "<<Guest "+guestno+">>"+"\n"
                        +"Name: " + rs.getString(1)+ " "+rs.getString(2) +"\n"
                        +"Guestno: "+rs.getString(3)+   "\n"
                        +"Password: "+rs.getInt(4)+   "\n";
                                                        }
       
        }
        catch (Exception e)
        {
            System.out.println("Fail in DataMapper - getreservation");
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
                System.out.println("Fail in DataMapper - getreservation");
                System.out.println(e.getMessage());
            }
        }
        return guestinfo;
    }
//
//        public ArrayList<GuestID> getGuestIDMail(int reservationNo, Connection con)
//    {
//
//        ArrayList<GuestID> guestIDarray= null;
//        String SQLString = // get reservation
//                " select gi.guestFirstname , gi.guestlastname,Gi.Email from guestID gi, guest g "
//+" where G.Guestid=Gi.Guestid and g.reservationno=?";
//   PreparedStatement statement = null;
//
//        try
//        {
//
//            statement = con.prepareStatement(SQLString);
//
//            statement.setInt(1, reservationNo);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next())
//            {
//                GuestID tempguestID = new GuestID(
//                        rs.getString(1),//firstname
//                        rs.getString(2),//lastname
//                        rs.getString(3));//mail
//                
//                guestIDarray.add(tempguestID);
//                
//                                        }
//        
//        
//        
//
//        }
//        catch (Exception e)
//        {
//            System.out.println("Fail in DataMapper - getreservation");
//            System.out.println(e.getMessage());
//        }
//        finally														// must close statement
//        {
//            try
//            {
//                statement.close();
//            }
//            catch (SQLException e)
//            {
//                System.out.println("Fail in DataMapper - getreservation");
//                System.out.println(e.getMessage());
//            }
//        }
//        return guestIDarray;
//    }

    
        public String getEmailForMail(int reservationno, Connection con)
    {String email="";
       String SQLString = " select gi.email from GuestID gi, guest g " +
"   where g.guestid=gi.guestid and g.reservationno=?";
       PreparedStatement statement = null;
       
        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, reservationno);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                email=rs.getString(1);
            }
        }
        
        catch (Exception e)
        {
            System.out.println("Fail in DataMapper - LogIn_EMP");
            System.out.println(e.getMessage());
        }
       
        
        return email;
        
    }

   
    public String reservationInfoStringForMail(int reservationno, Connection con)
    {String resinfo="";
    DateFormat format = new SimpleDateFormat("dd-MM-yy");
       String SQLString = "   select r.roomno, r.fromdate, r.endDate, ro.type " +
"  from reservation r, room ro, " +
"  where r.roomno=ro.roomno and r.reservationno=?;";
       PreparedStatement statement = null;
       
        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, reservationno);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next())
            {
                resinfo="++++++Reservation details+++++++ "+"/n"
                        +"From: "+format.format(rs.getDate(2))+"/n"
                        +"To* "+format.format(rs.getDate(3))+"/n"
                        +"Room Type: "+rs.getString(4)+"/n"
                        +"Room no: "+ rs.getString(1)+"/n";
            }
        }
        
        catch (Exception e)
        {
            System.out.println("Fail in DataMapper - LogIn_EMP");
            System.out.println(e.getMessage());
        }
       
        
        return resinfo;
        
    }

    
    //paste to Controller
//        public ArrayList<GuestID> getGuestInfoForMail(int reservationno)
//    {return facade.getGuestInfoForMail(reservationno);}
//    

}



   


    
