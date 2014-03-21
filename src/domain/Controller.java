
package domain;

import dataSource.DBFacade;
import java.util.ArrayList;


public class Controller
{
    private boolean processingGuest;	// Represent state of business transaction
    private Guest currentGuest;      	// Guest in focus
    private final DBFacade facade;   

    public Controller()
    {
        processingGuest = false;
        currentGuest = null;
        facade = DBFacade.getInstance();
    }
    
  
    public Reservation getReservation(int reservationNo)
    {
        // need to see how it works by Tomoe
//        if (processingOrder)
//        {
//            return null;
//        }

//        dbFacade.startProcessOrderBusinessTransaction();
        //      processingOrder = true;
        return facade.getReservation(reservationNo);

    }

    
      public int getNextReservationNo()
    {
        return facade.getNextReservationNo();
    }
      
    public ArrayList<Room> getRoomsAvailable(String fromDate, String endDate, String type)
    {
        // need to see how it works by Tomoe
//        if (processingOrder)
//        {
//            return null;
//        }

//        dbFacade.startProcessOrderBusinessTransaction();
        //      processingOrder = true;
System.out.println("controller"+fromDate);
        return facade.getRoomsAvailable(fromDate, endDate, type);

    }

    public int[] getPriceList()
    {
        int[] priceList = new int[3];

     priceList =  facade.getPriceList();
       return priceList;
    }

    
    
   // ----------------------------- Unit Of Work ----------------------------
    
    
     public Guest getGuest(int reservationNo)
    {
        if (processingGuest)
        {
            return null;
        }

        facade.startProcessGuestBusinessTransaction(); // method in Fascade
        processingGuest = true;
        currentGuest = facade.getGuest(reservationNo);
        return currentGuest;
    }
     
      public void resetGuest()
    {
        processingGuest = false;
        currentGuest = null;
    }
     
        public boolean saveGuest()
    {
        boolean status = false;
        if (processingGuest)
        {
      //== ends ongoing business transaction

            status = facade.commitProcessGuestBusinessTransaction();
            processingGuest = false;
            currentGuest = null;
        }
        return status;
    }
     
         public Guest deleteGuest()
    {
        if (processingGuest)
        {
            facade.startProcessGuestBusinessTransaction();
            facade.registerDeleteGuest(currentGuest);
        }
        return currentGuest;
    }
         
//          public Guest createGuest(String guestNo, int password )
//    {
//        if (processingGuest)
//        {
//            return null;
//        }
//
//        facade.startProcessGuestBusinessTransaction();
//        int newReservationNo = facade.getNextReservationNo();// DB-generated unique ID --< 
//        if (newReservationNo != 0)
//        {
//            processingGuest = true;
//            //- capture current date.Represent as String
////            String dateReceived = (new java.sql.Date(
////                    (new java.util.Date().getTime())).toString());
//        currentGuest = new Guest(newReservationNo, guestNo, employeeNumber, dateReceived, null, 0);
//            facade.registerNewOrder(currentGuest);
//        } else
//        {
//            processingGuest = false;
//            currentGuest = null;
//        }
//        return currentGuest;
//    }
          
          
     
}
