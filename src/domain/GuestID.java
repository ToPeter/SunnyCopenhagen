/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author pc
 */
public class GuestID

{
    
    private int id;
    private String guestFirstName;
    private String guestFamilyName;
    private String address;
    private String country;
    private int phoneNo;
    private String email;

    
    
    
     public GuestID(int id,
            String guestFirstName,
            String guestFamilyName,
            String address,
            String country,
            int phone,
            String email)
    {
        this.id = id;
        this.guestFirstName = guestFirstName;
        this.guestFamilyName = guestFamilyName;
        this.address = address;
        this.country = country;
        this.phoneNo = phoneNo;
        this.email = email;

        //   this.guestDetails = new ArrayList<>();
    }
     
            public int getId()
    {
        return id;
    }        


    public int setId()
    {
        return id;
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
    
    public String toString()
    {
        return //" reservationNo: " + reservationNo
                  "ID: " + id + "\n"
                + "First Name: " + guestFirstName + "\n"
                + "Family Name: " + guestFamilyName + "\n"
                + "Address: " + address + "\n"
                + "Country: " + country + "\n"
                + "PhoneNo: " + phoneNo + "\n"
                + "Email: " + email + "\n" + "\n";
    }
    
        public boolean equals(GuestID guestID)
    {
        return ( id == guestID.getId()
                && guestFirstName.equals(guestID.getGuestFirstName())
                && guestFamilyName.equals(guestID.getGuestFamilyName())
                && address.equals(guestID.getAddress())
                && country.equals(guestID.getCountry())
                && phoneNo == guestID.getPhoneNo())
                && email.equals(guestID.getEmail());
    }
}
