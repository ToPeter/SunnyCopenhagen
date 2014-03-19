/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author Tomoe
 */
public class Reservation
{

    private int roomNo;
    private int reservationNo;
    private Date fromDate;
    private Date endDate;
    private Date boookingDate;
    private boolean depositPaid; //in database it's 0 or 1

}
