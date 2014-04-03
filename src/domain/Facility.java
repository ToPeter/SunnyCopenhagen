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
public class Facility
{
    int facID;
    String type;
    int minUsers;
    int maxUsers;
    int roomRemaining;

    public Facility(int facID, int minUsers, int maxUsers, int roomRemaining)
    {
        this.facID = facID;
        this.minUsers = minUsers;
        this.maxUsers = maxUsers;
        this.roomRemaining = roomRemaining;
    }
    
    public Facility(int facID, String type, int minUsers, int maxUsers)
    {
        this.facID = facID;
        this.type = type;
        this.minUsers = minUsers;
        this.maxUsers = maxUsers;
    }

    public int getRoomRemaining()
    {
        return roomRemaining;
    }

    public void setRoomRemaining(int roomRemaining)
    {
        this.roomRemaining = roomRemaining;
    }

    public int getFacID()
    {
        return facID;
    }

    public void setFacID(int facID)
    {
        this.facID = facID;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public int getMinUsers()
    {
        return minUsers;
    }

    public void setMinUsers(int minUsers)
    {
        this.minUsers = minUsers;
    }

    public int getMaxUsers()
    {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers)
    {
        this.maxUsers = maxUsers;
    }

    @Override
    public String toString()
    {
        return  "facID=" + facID + ", minUsers=" + minUsers + ", maxUsers=" + maxUsers +",roomRemaing"+roomRemaining;
    }
    
 
    
    
    
}
