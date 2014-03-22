
package domain;

import dataSource.DBFacade;
import java.util.ArrayList;
import java.util.Date;


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
         
    public Guest createGuest(int reservationNo,String guestNo,int password,String guestFirstName,String guestFamilyName,
                 String address,  String country,int phone, String email )
    {
        if (processingGuest)
        {
            return null;
        }

        
        facade.startProcessGuestBusinessTransaction();
        int newReservationNo = facade.getNextReservationNo();// DB-generated unique ID --< 

       
        if (newReservationNo != 0)
        {
            processingGuest = true;
            //- capture current date.Represent as String
//            String dateReceived = (new java.sql.Date(
//                    (new java.util.Date().getTime())).toString());
      //  currentGuest = new Guest(newReservationNo, newReservationNo+"-1", dateReceived, null, 0);
            
    //    currentGuest = new Guest(12345, "123459-1",15698, "Peter", "Lorensen", "Amagerbro","Denmark",50122645,"pelo@cph.sk"); //THIS LINE WAS FOR TESTING
                                                                                                                                // to create a guest!
        
        
        
            facade.registerNewOrder(currentGuest);
        } else
        {
            processingGuest = false;
            currentGuest = null;
        }
        
       
        return currentGuest;
    }
          
         public boolean bookRoom(int roomNo, int reservationNo, Date fromDate, Date endDate, Date boookingDate, boolean depositPaid)
    {
        boolean result = true;

        System.out.println("RESERVATIONNO: " + reservationNo + "\n-----||-----\nORDER BOOKED: \t\nFrom: " + fromDate.toString() + " \tRoom: " + roomNo + " Booked: " + boookingDate.toString() + "\n\tTo  " + endDate.toString() + "\nDepositpaied: " + depositPaid);

        return result;
    }
         
         public void commit()
         {
             facade.commitProcessGuestBusinessTransaction();
         }
     
}
