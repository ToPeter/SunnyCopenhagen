/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.DBFacade;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Tomoe
 */
public class Controller
{

    private final DBFacade facade;
//private boolean processingOrder;	// Represent state of business transaction (need to see how it works. by Tomoe)

    public Controller()
    {
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

       return priceList;
    }

}
