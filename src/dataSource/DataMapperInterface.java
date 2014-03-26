/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Guest;
import domain.Reservation;
import domain.Room;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

/**
 *
 * @author Tomoe
 */
public interface DataMapperInterface
{

    Reservation getreservation(int reservationNo, Connection con);
    ArrayList<Reservation> getreservationDepositNotPaid(Connection con);    
    ArrayList<Room> getRoomAvailable(Date fromDate, Date toDate, String type, Connection con);
 
    int[] getPriceList(Connection con);

    ArrayList<Guest> getGuests(int reservationNo, Connection con);

    int getNextReservationNo(Connection con);
    int getNextGuestNo(Connection con);
    boolean insertGuest(ArrayList<Guest> guestList, Connection con) throws SQLException;
    boolean deleteGuest(ArrayList<Guest> delGuest, Connection con) throws SQLException;

    public boolean createReservation(Reservation reservation, Connection con)throws SQLException;

    public boolean updateDeposit(int reservationNoSelected, Connection con)throws SQLException;
}
