/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mock;

import domain.Guest;
import domain.Reservation;
import domain.Room;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
    int[] pricearray;

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
    public ArrayList<Room> getRoomAvailable(String fromDate, String toDate, String type, Connection con)
    {
        availableRooms = roomarray;

        for (Map.Entry<Integer, Room> entry : roomMap.entrySet())
        {//roommap=already reserved rooms
            
            Room room = entry.getValue();
            for (int i = 0; i < roomarray.size(); i++)
            {
                Room tempRoom = roomarray.get(i);
                if (room == tempRoom)
                {
                    availableRooms.remove(tempRoom);
                }
            }

        }
        return availableRooms;

    }

    @Override // need to edit
    public int[] getPriceList(Connection con)

    {
        return pricearray;
    }

    @Override
    public ArrayList<Guest> getGuests(int reservationNo, Connection con)
    {
        return guestMap.get(reservationNo);

    }

   
    @Override
    public int getNextReservationNo(Connection conn)
    {
        return 0; // cannnot be tested without connecting DB
    }

    @Override
    public int getNextGuestNo(Connection con)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertGuest(ArrayList<Guest> guestList, Connection con) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteGuest(ArrayList<Guest> delGuest, Connection con) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
