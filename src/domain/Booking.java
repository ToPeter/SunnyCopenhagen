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
public class Booking
{
    int bookingId;
    int bookedNumOfUsers;
    int maxUsers;
    String type;
    int facilityId;
    Date bookingdate;
    int bookingtime;

   
    String guestno;
    int inno;
    int waitingpos;
    String instructorName;

    public Booking(String type, int inno, String instructorName)
    {
        this.type = type;
        this.inno = inno;
        this.instructorName = instructorName;
    }
    
    
    public Booking(int bookingId, Date bookingdate, int bookingtime)
    {
        this.bookingId = bookingId;
        this.bookingdate = bookingdate;
        this.bookingtime = bookingtime;
    }
    
    public Booking(int bookingId, int bookedNumOfUsers, int maxUsers, String type, int facilityId, Date bookingdate, int bookingtime)
    {
        this.bookingId = bookingId;
        this.bookedNumOfUsers = bookedNumOfUsers;
        this.maxUsers = maxUsers;
        this.type = type;
        this.facilityId = facilityId;
        this.bookingdate = bookingdate;
        this.bookingtime = bookingtime;
    }

    public Booking(String guestno, int inno, int waitingpos)
    {
        this.guestno = guestno;
        this.inno = inno;
        this.waitingpos = waitingpos;
    }

    public Booking(int bookingId, int facilityId, Date bookingdate, int bookingtime)
    {
        this.bookingId = bookingId;
        this.facilityId = facilityId;
        this.bookingdate = bookingdate;
        this.bookingtime = bookingtime;
    }
    
    public int getBookingId()
    {
        return bookingId;
    }

    public void setBookingId(int bookingId)
    {
        this.bookingId = bookingId;
    }

    public int getBookedNumOfUsers()
    {
        return bookedNumOfUsers;
    }

    public void setBookedNumOfUsers(int bookedNumOfUsers)
    {
        this.bookedNumOfUsers = bookedNumOfUsers;
    }

    public int getMaxUsers()
    {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers)
    {
        this.maxUsers = maxUsers;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getFacilityId()
    {
        return facilityId;
    }

    @Override
    public String toString()
    {
        return "Booking{" + "bookingId=" + bookingId + ", bookedNumOfUsers=" + bookedNumOfUsers + ", maxUsers=" + maxUsers + ", type=" + type + ", facilityId=" + facilityId + ", bookingdate=" + bookingdate + ", bookingtime=" + bookingtime + '}';
    }

    public void setFacilityId(int facilityId)
    {
        this.facilityId = facilityId;
    }

    public Date getBookingdate()
    {
        return bookingdate;
    }

    public void setBookingdate(Date bookingdate)
    {
        this.bookingdate = bookingdate;
    }

    public int getBookingtime()
    {
        return bookingtime;
    }

    public void setBookingtime(int bookingtime)
    {
        this.bookingtime = bookingtime;
    }
     public String getGuestno()
    {
        return guestno;
    }

    public int getInno()
    {
        return inno;
    }

    public int getWaitingpos()
    {
        return waitingpos;
    }

    public Object toStringInstructor()
    {
      return "Booking{" + "bookingId=" + bookingId +  ", facilityId=" + facilityId + ", bookingdate=" + bookingdate + ", bookingtime=" + bookingtime + '}';
  
    }
     
    public Object toStringInstructorAvailable()
    {
      return "Booking{" + "type=" + type +  ", inno=" + inno + ", instructorName=" + instructorName  + '}';
  
    }
}
