/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Guest;
import domain.GuestID;
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

    GuestID getGuest(int guestid, Connection con);

    int getNextReservationNo(Connection con);

    //int getNextGuestNo(Connection con);//commented out because guestno is reservationno-1,2,3.... don't need to connect DB
    boolean insertGuest(ArrayList<Guest> guestList, Connection con) throws SQLException;

    boolean deleteGuest(ArrayList<Guest> delGuest, Connection con) throws SQLException;

    boolean createReservation(Reservation reservation, Connection con) throws SQLException;

    boolean updateDeposit(ArrayList<Reservation> reservation, Connection con) throws SQLException;

    String getRoomType(int roomNo, Connection con);

    public boolean getGuestInfo(String userName, String password, Connection con);

    public boolean getEmpInfo(String userName, String password, Connection con);

    public ArrayList<GuestID> getGuestID (int guestID, Connection con);
    
    public boolean insertGuestID(ArrayList<GuestID> guestListID, Connection con) throws SQLException;

   

    public String getEmpLogInName(String userName, Connection con);

    public String getGuestLogInName(String userName, Connection con);

   
}
