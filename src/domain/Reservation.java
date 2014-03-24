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
    private int depositPaid; //in database it's 0 or 1

    public Reservation(int roomNo, int reservationNo, Date fromDate, Date endDate, Date boookingDate, int depositPaid)
    {
        this.roomNo = roomNo;
        this.reservationNo = reservationNo;
        this.fromDate = fromDate;
        this.endDate = endDate;
        this.boookingDate = boookingDate;
        this.depositPaid = depositPaid;
    }

    public int getRoomNo()
    {
        return roomNo;
    }

    public void setRoomNo(int roomNo)
    {
        this.roomNo = roomNo;
    }

    public int getReservationNo()
    {
        return reservationNo;
    }

    public void setReservationNo(int reservationNo)
    {
        this.reservationNo = reservationNo;
    }

    public Date getFromDate()
    {
        return fromDate;
    }

    public void setFromDate(Date fromDate)
    {
        this.fromDate = fromDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getBoookingDate()
    {
        return boookingDate;
    }

    public void setBoookingDate(Date boookingDate)
    {
        this.boookingDate = boookingDate;
    }

    public int isDepositPaid()
    {
        return depositPaid;
    }

    public void setDepositPaid(int depositPaid)
    {
        this.depositPaid = depositPaid;
    }

    @Override
    public String toString()
    {
        String paid;
        if (depositPaid == 1)
        {
            paid = "Yes";
        }
        else
        {
            paid = "No";
        }

        return "Reservation no" + reservationNo + "/n"
                + "Room no=" + roomNo + "/n"
                + "Check in date=" + fromDate + "/n"
                + "Check out date=" + endDate + "/n"
                + "Booking date=" + boookingDate + "/n"
                + "Deposid paid" + paid + "/n" + "/n";
    }

}
