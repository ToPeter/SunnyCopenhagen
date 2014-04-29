package dataSource;

import domain.Guest;
import domain.GuestID;
import domain.Reservation;
import domain.Room;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

/**
 *
 * @author Tomoe
 */
public interface DataMapperInterface
{

    Reservation getreservation(int reservationNo);

    ArrayList<Reservation> getreservationDepositNotPaid();

    ArrayList<Room> getRoomAvailable(Date fromDate, Date toDate, String type);

    int[] getPriceList();

    GuestID getGuest(int guestid);

    int getNextReservationNo();

    boolean insertGuest(ArrayList<Guest> guestList) throws SQLException;

    boolean createReservation(Reservation reservation) throws SQLException;

    boolean updateDeposit(ArrayList<Reservation> reservation) throws SQLException;

    String getRoomType(int roomNo);

    public boolean getGuestInfo(String userName, String password);

    public boolean getEmpInfo(String userName, String password);

    public boolean insertGuestID(ArrayList<GuestID> guestListID) throws SQLException;

    public String getEmpLogInName(String userName);

    public String getGuestLogInName(String userName);

    public GuestID searchGuest(String guestno);

    public ArrayList<GuestID> searchGuestByReservationNO(int reservationNO);

    public boolean updateGuestID(ArrayList<GuestID> dirtyGuestID);
}
