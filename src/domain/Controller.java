/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import dataSource.DBFacade;

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

}
