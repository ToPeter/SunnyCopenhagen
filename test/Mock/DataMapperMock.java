/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mock;

import domain.Guest;
import domain.GuestID;
import domain.Reservation;
import domain.Room;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tomoe
 */
public class DataMapperMock implements dataSource.DataMapperInterface
{

    Map<Integer, Reservation> reservationMap;
    Map<Integer, Room> roomMap; //res.no
    Map<Integer, ArrayList> guestMap; //res.no , guestArray
    ArrayList<Room> roomarray; // allrooms
    ArrayList<Room> availableRooms;
    int[] pricearray ={60,80,120};

    public DataMapperMock()
    {
        reservationMap = new HashMap();
        roomMap = new HashMap();
        guestMap = new HashMap();
        roomarray = new ArrayList();

        availableRooms = new ArrayList();
    }

    @Override
    public Reservation getreservation(int reservationNo, Connection con)
    {
        return reservationMap.get(reservationNo);

    }

    @Override
    public ArrayList<Room> getRoomAvailable(Date fromDate, Date toDate, String type, Connection con)
    {
        availableRooms = roomarray;

        for (Map.Entry<Integer, Room> entry : roomMap.entrySet())
        {//roommap=already reserved rooms
            
            Room room = entry.getValue();
            for (int i = 0; i < roomarray.size(); i++)
            {
                Room tempRoom = roomarray.get(i);
                if (room.getRoomNo() == tempRoom.getRoomNo())
                {
                    availableRooms.remove(tempRoom);
                    System.out.println("availableroomremoving");
                }
            }

        }
        return availableRooms;

    }

    @Override 
    public int[] getPriceList(Connection con)

    {
        return pricearray;
    }

   
   
    @Override
    public int getNextReservationNo(Connection conn)
    {
        return 0; // cannnot be tested without connecting DB
    }

 
    @Override
    public boolean insertGuest(ArrayList<Guest> guestList, Connection con) throws SQLException
    {
        int size= guestMap.size();
            Guest guest = guestList.get(0);
         guestMap.put(guest.getReservationNo(),guestList);
        
         return size==(guestMap.size()-1);
                        
    }

    @Override
    public boolean deleteGuest(ArrayList<Guest> delGuest, Connection con) throws SQLException
    {
        //we dont need to delete guest?
    return true;
    }

    @Override
    public ArrayList<Reservation> getreservationDepositNotPaid(Connection con)
    { 
       ArrayList<Reservation> array=new ArrayList();
       
        for (Map.Entry<Integer, Reservation> entry : reservationMap.entrySet())
        {
            Reservation reservation = entry.getValue();
            if(reservation.isDepositPaid()==0)
            {array.add(reservation);
            }             
        }
        return array;
   
    }


    @Override
    public boolean createReservation(Reservation reservation, Connection con) throws SQLException
    {
        int size=reservationMap.size();
        reservationMap.put(30000, reservation);
        
        return size==reservationMap.size()-1;

    }

    @Override
    public GuestID getGuest(int guestid, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateDeposit(ArrayList<Reservation> reservation, Connection con) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRoomType(int roomNo, Connection con)
    {
        for (int i = 0; i < roomarray.size(); i++)
        {
            Room room = roomarray.get(i);
            if(room.getRoomNo()==roomNo)
            {return room.getType();}
        }
        return "";
        
    }

    @Override
    public boolean getGuestInfo(String userName, String password, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getEmpInfo(String userName, String password, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GuestID> getGuestID(int guestID, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertGuestID(ArrayList<GuestID> guestListID, Connection con) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmpLogInName(String userName, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getGuestLogInName(String userName, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public GuestID searchGuest(String guestno, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<GuestID> searchGuestByReservationNO(int reservationNO, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    


    
    
    
    
    
    /////getter and setter
    public Map<Integer, Reservation> getReservationMap()
    {
        return reservationMap;
    }

    public void setReservationMap(Map<Integer, Reservation> reservationMap)
    {
        this.reservationMap = reservationMap;
    }

    public Map<Integer, Room> getRoomMap()
    {
        return roomMap;
    }

    public void setRoomMap(Map<Integer, Room> roomMap)
    {
        this.roomMap = roomMap;
    }

    public Map<Integer, ArrayList> getGuestMap()
    {
        return guestMap;
    }

    public void setGuestMap(Map<Integer, ArrayList> guestMap)
    {
        this.guestMap = guestMap;
    }

    public ArrayList<Room> getRoomarray()
    {
        return roomarray;
    }

    public void setRoomarray(ArrayList<Room> roomarray)
    {
        this.roomarray = roomarray;
    }

    public ArrayList<Room> getAvailableRooms()
    {
        return availableRooms;
    }

    public void setAvailableRooms(ArrayList<Room> availableRooms)
    {
        this.availableRooms = availableRooms;
    }

    public int[] getPricearray()
    {
        return pricearray;
    }

    public void setPricearray(int[] pricearray)
    {
        this.pricearray = pricearray;
    }

    @Override
    public boolean updateGuestID(ArrayList<GuestID> dirtyGuestID, Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}