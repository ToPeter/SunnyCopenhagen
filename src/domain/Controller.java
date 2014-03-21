
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

}
