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
    private String guestFirstName;
    private String guestFamilyName;
    private String address;
    private String country;
    private int phoneNo;
    private String email;
  //  private final ArrayList<GuestDetail> guestDetails;

    public Guest(int reservationNo,
            String guestNo,
            int password,
            String guestFirstName,
            String guestFamilyName,
            String address,
            String country,
            int phone,
            String email)
    {
        this.reservationNo = reservationNo;
        this.guestNo = guestNo;
        this.password = password;
        this.guestFirstName = guestFirstName;
        this.guestFamilyName = guestFamilyName;
        this.address = address;
        this.country = country;
        this.phoneNo = phone;
        this.email = email;

        //   this.guestDetails = new ArrayList<>();
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

    public String getGuestFirstName()
    {
        return guestFirstName;
    }

    public void setGuestFirstName(String guestFirstName)
    {
        this.guestFirstName = guestFirstName;
    }

    public String getGuestFamilyName()
    {
        return guestFamilyName;
    }

    public void setGuestFamilyName(String guestFamilyName)
    {
        this.guestFamilyName = guestFamilyName;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public int getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo)
    {
        this.phoneNo = phoneNo;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

     // == Methods:  toString,
    // I dont know what about AddGuest ???
    public String toString()
    {
        return //" reservationNo: " + reservationNo
                "Guest no: " + guestNo + "\n"
                + "Password: " + password + "\n"
                + "First Name: " + guestFirstName + "\n"
                + "Family Name: " + guestFamilyName + "\n"
                + "Address: " + address + "\n"
                + "Country: " + country + "\n"
                + "PhoneNo: " + phoneNo + "\n"
                + "Email: " + email + "\n" + "\n";
    }

    public boolean equals(Guest guest)
    {
        return (reservationNo == guest.getReservationNo()
                && guestNo.equals(guest.getGuestNo())
                && password == guest.getPassword()
                && guestFirstName.equals(guest.getGuestFamilyName())
                && guestFamilyName.equals(guest.getGuestFamilyName())
                && address.equals(guest.getAddress())
                && country.equals(guest.getCountry())
                && phoneNo == guest.getPhoneNo())
                && email.equals(guest.getEmail());
    }

}
