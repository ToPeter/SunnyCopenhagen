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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tomoe
 */
public class DataMapperMock implements dataSource.DataMapperInterface
{Map<Integer, Reservation> reservationMap;
Map<Integer,Room> roomMap; //res.no
Map<Integer,ArrayList> guestMap; //res.no , guestArray

ArrayList<Room> roomarray;
int[] pricearray;

    public DataMapperMock()
    { reservationMap= new HashMap();
    
           }

    @Override
    public Reservation getreservation(int reservationNo, Connection con)
    {
return        reservationMap.get(reservationNo);
 
    
    }

    @Override
    public ArrayList<Room> getRoomAvailable(String fromDate, String toDate, String type, Connection con)
    {
    return roomarray; 
    }

    @Override // need to edit
    public int[] getPriceList(Connection con)
    
   {
       return pricearray;
    }

    @Override // need to edit
    public ArrayList<Guest> getGuests(int reservationNo, Connection con)
    {   return guestMap.get(reservationNo);
       
    }

    @Override
    public int getNextReservationNo(Connection conn)
    {
        return 0; // cannnot be tested without connecting DB
    }
    
}
