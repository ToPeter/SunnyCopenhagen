/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Tomoe
 */
public class Guest
{

    private int reservationNo;
    private String guestNo;
    private int password;
    private String agency;
    private int id;

  //  private final ArrayList<GuestDetail> guestDetails;

    public Guest(int reservationNo,
            String guestNo,
            int password,
            String agency,
            int id)
    {
        this.reservationNo = reservationNo;
        this.guestNo = guestNo;
        this.password = password;
        this.agency = agency;
        this.id = id;

        //   this.guestDetails = new ArrayList<>();
    }

    public String getAgency()
    {
        return agency;
    }

    public void setAgency(String agency)
    {
        this.agency = agency;
    }
    
       public int getId()
    {
        return id;
    }        


    public int setId()
    {
        return id;
    }

    //== accessors [Getters and Setters]
    public int getReservationNo()
    {
        return reservationNo;
    }

    public void setReservationNo(int reservationNo)
    {
        this.reservationNo = reservationNo;
    }

    public void setGuestNo(String guestNo)
    {
        this.guestNo = guestNo;
    }

    public String getGuestNo()
    {
        return guestNo;
    }

    public void setPassword(int password)
    {
        this.password = password;
    }

    public int getPassword()
    {
        return password;
    }

   

     // == Methods:  toString,
    // I dont know what about AddGuest ???
    public String toString()
    {
        return // " ReservationNo: " + reservationNo + "\n" // was comm. out
                 "Guest no: " + guestNo + "\n"
                + "Password: " + password + "\n"
                + "Travel agency: " + agency + "\n"
                + "ID: " + id ;
    }

    public boolean equals(Guest guest)
    {
        return (reservationNo == guest.getReservationNo()
                && guestNo.equals(guest.getGuestNo())
                && password == guest.getPassword()
                && agency.equals(guest.getAgency())
                && id == guest.getId());
    }

}
