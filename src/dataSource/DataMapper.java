package dataSource;

import domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tomoe
 */
public class DataMapper implements DataMapperInterface
{

    static boolean testRun = true;  // used to enable test output

    private final Connection con;

    Calendar c = Calendar.getInstance();

    public DataMapper(Connection con)
    {
        this.con = con;

    }

    @Override
    public Reservation getreservation(int reservationNo)
    {

        Reservation reservation = null;
        String SQLString = // get reservation
                "select * "
                + "from reservation "
                + "where reservationNo = ?";
        PreparedStatement statement = null;

        try
        {
            //=== get reservaton

            statement = con.prepareStatement(SQLString);

            statement.setInt(1, reservationNo);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                reservation = new Reservation(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),//fromDate
                        rs.getDate(4),//toDate
                        rs.getDate(5),//bookingDate
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
            }

        }
        catch (SQLException e)
        {
        }
        finally														// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
            }
        }
        return reservation;
    }

    public ArrayList<Reservation> getreservationDepositNotPaid()
    {
        ArrayList<Reservation> depositNotPaidArray = new ArrayList();
        Reservation reservation = null;
        String SQLString = // get reservation
                "select * "
                + "from reservation "
                + "where DepositPaid =? "
                + "order by reservationno ";
        PreparedStatement statement = null;

        try
        {
            //=== get reservaton

            statement = con.prepareStatement(SQLString);

            statement.setInt(1, 0);
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                reservation = new Reservation(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getDate(3),//fromDate
                        rs.getDate(4),//toDate
                        rs.getDate(5),//bookingDate
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));

                depositNotPaidArray.add(reservation);

            }

        }
        catch (SQLException e)
        {
        }
        finally														// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
            }
        }
        return depositNotPaidArray;
    }

    @Override
    public ArrayList<Room> getRoomAvailable(Date fromDate, Date endDate, String type)
    {
        ArrayList<Room> roomAvailableList = new ArrayList();
        Room tempRoom;
        String SQLString = // get roomavailable
                "select * from room"
                + " WHERE Type = ? AND Roomno NOT IN "
                + "(SELECT roomno FROM Reservation "
                + " where fromdate < ? AND endDate >?)order by roomno ";
        PreparedStatement statement = null;

        try
        {
            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
            java.sql.Date sqlToDate = new java.sql.Date(endDate.getTime());
            statement = con.prepareStatement(SQLString);
            statement.setString(1, type);
            statement.setDate(2, sqlToDate);
            statement.setDate(3, sqlFromDate);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                tempRoom = new Room(rs.getInt(1), type);
                roomAvailableList.add(tempRoom);
            }
        }
        catch (SQLException e)
        {
        }
        finally														// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
            }

            return roomAvailableList;
        }

    }

    private ArrayList<Integer> doubleCheckRoomAvailable(Date fromDate, Date endDate)
    {

        ArrayList<Integer> roomAvailableList = new ArrayList();

        String SQLString = // get roomNoavailable
                "select * from room"
                + " WHERE Roomno NOT IN "
                + "(SELECT roomno FROM Reservation "
                + " where fromdate < ? AND endDate >?)order by roomno ";

        PreparedStatement statement = null;

        try
        {

            java.sql.Date sqlFromDate = new java.sql.Date(fromDate.getTime());
            java.sql.Date sqlToDate = new java.sql.Date(endDate.getTime());
            statement = con.prepareStatement(SQLString);
            statement.setDate(1, sqlToDate);
            statement.setDate(2, sqlFromDate);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                int number = rs.getInt(1);

                roomAvailableList.add(number);
            }

        }
        catch (SQLException e)
        {
        }
        finally			// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
            }

            return roomAvailableList;
        }

    }

    public String getRoomType(int roomNo)
    {
        String result = null;
        String SQLString = // get reservation
                "select * "
                + "from room "
                + "where roomNo = ?";
        PreparedStatement statement = null;

        try
        {
            //=== get reservaton

            statement = con.prepareStatement(SQLString);

            statement.setInt(1, roomNo);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                result = rs.getString(2);
            }

        }
        catch (SQLException e)
        {
        }
        finally														// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
            }
        }
        return result;
    }

    @Override

    public int[] getPriceList()
    {
        int[] priceList = new int[3];

        String SQLString = // get PriceList
                "select price from Prices ";

        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            int i = 0;
            while (rs.next())
            {
                priceList[i] = rs.getInt(1);
                i++;
            }
        }
        catch (SQLException e)
        {
        }
        finally														// must close statement
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
            }
            return priceList;
        }
    }

    @Override
    public boolean createReservation(Reservation res) //return false if it is not doublebooked.
    {
        boolean booked = true;
        boolean doublebooked = false;
        int roomNo = res.getRoomNo();
        String lock = "Lock table reservation in exclusive mode";

        try
        {
            PreparedStatement statement = null;
            statement = con.prepareStatement(lock);
            statement.executeUpdate();

        }
        catch (SQLException e)
        {
            return false;
        }

        ArrayList<Integer> availRoomNumbers = doubleCheckRoomAvailable(res.getFromDate(), res.getEndDate());

        if (!availRoomNumbers.contains(roomNo))
        {
            return doublebooked;
        }
        int rowsInserted = 0;
        String SQLString = "insert into reservation values (?,?,?,?,?,?,?,?) ";

        try
        {
            con.setAutoCommit(false);
            PreparedStatement statement = null;

            java.sql.Date sqlFromDate = new java.sql.Date(res.getFromDate().getTime());
            java.sql.Date sqlToDate = new java.sql.Date(res.getEndDate().getTime());
            java.sql.Date sqlToBookingDate = new java.sql.Date(res.getBoookingDate().getTime());

            statement = con.prepareStatement(SQLString);

            statement.setInt(1, res.getRoomNo());
            statement.setInt(2, res.getReservationNo());
            statement.setDate(3, sqlFromDate);
            statement.setDate(4, sqlToDate);
            statement.setDate(5, sqlToBookingDate);
            statement.setInt(6, res.isDepositPaid());
            statement.setInt(7, res.getEmpNo());
            statement.setInt(8, res.getVersion());
            statement.executeUpdate();

            con.commit();
            doublebooked = true;
        }
        catch (SQLException e)
        {
            return false;
        }
        return booked;
    }
    //======  Methods to read from DB =======================================================
// Retrieve a specific order and related order details
// Returns the Order-object

    @Override
    public GuestID getGuest(int id)
    {
        GuestID guestID = null;
        String SQLString1 = // get order
                "select * "
                + "from GUESTID "
                + "where GUESTID = ? ";

        PreparedStatement statement = null;

        try
        {
            //=== get order
            statement = con.prepareStatement(SQLString1);
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                guestID = new GuestID(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7));

            }

        }
        catch (SQLException e)
        {
        }

        return guestID;
    }

    //====== Methods to save to DB =========================================================
    // Insert a list of new orders
    // returns true if all elements were inserted successfully
    @Override
    public boolean insertGuest(ArrayList<Guest> guestList)
    {
        int rowsInserted = 0;
        String SQLString = "insert into guest values (?,?,?,?,?)";

        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);

            for (int i = 0; i < guestList.size(); i++)
            {

                Guest guest = guestList.get(i);

                statement.setInt(1, guest.getReservationNo());
                statement.setString(2, guest.getGuestNo());
                statement.setInt(3, guest.getPassword());
                statement.setString(4, guest.getAgency());
                statement.setInt(5, guest.getId());

                rowsInserted = statement.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException is thrown");
            return false;
        }

        return (rowsInserted == guestList.size());
    }

    @Override
    public boolean updateDeposit(ArrayList<Reservation> updateList)
    {

        int rowsUpdated = 0;

        String SQLString = "UPDATE RESERVATION "
                + "SET depositpaid = ?, ver_no = ? "
                + "where reservationNO= ? and ver_no = ? ";
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);

            for (int i = 0; i < updateList.size(); i++)
            {
                Reservation reservation = updateList.get(i);
                statement.setInt(1, 1);
                statement.setInt(2, reservation.getVersion() + 1); // next version number
                statement.setInt(3, reservation.getReservationNo());
                statement.setInt(4, reservation.getVersion());   // old version number

                int tupleUpdated = statement.executeUpdate();
                if (tupleUpdated == 1)
                {
                    reservation.incrementVersionNumber();                       // increment version in current OrderObject
                }
                rowsUpdated += tupleUpdated;
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (rowsUpdated == updateList.size());    // false if any conflict in version number
    }

//http://www.w3schools.com/sql/sql_autoincrement.asp    
//CREATE SEQUENCE seq_resno 
//MINVALUE 1
//START WITH 1000
//INCREMENT BY 1;
    public int getNextReservationNo()
    {
        int nextReservationNo = 0;
        String SQLString = "select seq_resno.nextval  " + "from dual";
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                nextReservationNo = rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
        }
        return nextReservationNo;
    }

    @Override
    public boolean getGuestInfo(String userName, String password)
    {
        boolean result = false;
        String SQLString = "select guestno from guest where password = ? and guestno = ?";
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, password);
            statement.setString(2, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                result = true;
            }

        }
        catch (SQLException e)
        {
        }

        return result;
    }

    @Override
    public boolean getEmpInfo(String userName, String password)
    {
        boolean result = false;
        String SQLString = "select empid from employee where password = ? and empid = ?";
        PreparedStatement statement = null;

        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, password);
            statement.setString(2, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                result = true;
            }

        }
        catch (SQLException e)
        {
        }

        return result;

    }

    @Override
    public boolean insertGuestID(ArrayList<GuestID> guestListID)
    {
        int rowsInserted = 0;
        try
        {

            String SQLString = "insert into guestid values (?,?,?,?,?,?,?)";

            PreparedStatement statement = null;
            statement = con.prepareStatement(SQLString);

            for (int i = 0; i < guestListID.size(); i++)
            {

                GuestID guestID = guestListID.get(i);

                statement.setInt(1, guestID.getId());
                statement.setString(2, guestID.getGuestFirstName());
                statement.setString(3, guestID.getGuestFamilyName());
                statement.setString(4, guestID.getAddress());
                statement.setString(5, guestID.getCountry());
                statement.setInt(6, guestID.getPhoneNo());
                statement.setString(7, guestID.getEmail());
                rowsInserted = statement.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            System.out.println("SQLException is thrown");
            return false;
        }
        return (rowsInserted == guestListID.size());
    }

    @Override
    public String getEmpLogInName(String userName)

    {
        String name = "";
        String SQLString = "select firstname from employee where empid = ? ";
        PreparedStatement statement = null;

        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                name = rs.getString(1);;
            }
        }
        catch (SQLException e)
        {
        }
        return name;
    }

    @Override
    public String getGuestLogInName(String userName)
    {
        String name = "";
        String SQLString = "select guestfirstname from guestid where guestid = ("
                + "select guestid from guest where guestno = ?) ";
        PreparedStatement statement = null;

        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, userName);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                name = rs.getString(1);
            }
        }
        catch (SQLException e)
        {
        }
        return name;
    }

    @Override
    public GuestID searchGuest(String guestno)
    {
        GuestID guestID = null;
        String SQLString = "select * from guestid where guestid in ("
                + "select guestid from guest where guestno = ?) ";
        PreparedStatement statement = null;

        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, guestno);
            ResultSet rs = statement.executeQuery();

            if (rs.next())
            {
                guestID = new GuestID(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7));

            }
        }
        catch (SQLException e)
        {
        }

        return guestID;
    }

    @Override
    public ArrayList<GuestID> searchGuestByReservationNO(int reservationNO)
    {
        ArrayList<GuestID> guestIDArray = new ArrayList<>();
        GuestID guestID = null;
        String SQLString = "select * from guestid where guestid in ("
                + "select guestid from guest where reservationno = ?) ";
        PreparedStatement statement = null;

        try
        {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, reservationNO);
            ResultSet rs = statement.executeQuery();

            while (rs.next())
            {
                guestID = new GuestID(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(7));
                guestIDArray.add(guestID);
            }
        }
        catch (SQLException e)
        {
        }

        return guestIDArray;
    }

    public boolean updateGuestID(ArrayList<GuestID> dirtyGuestID)
    {
        int rowsInserted = 0;
        try
        {
            String SQLString = "UPDATE GUESTID SET GuestFirstName = ?, guestLastname = ?, address = ?, country = ?, phoneno = ?, email=? where guestid = ?";

            PreparedStatement statement = null;
            statement = con.prepareStatement(SQLString);

            for (int i = 0; i < dirtyGuestID.size(); i++)
            {

                GuestID guestID = dirtyGuestID.get(i);

                statement.setString(1, guestID.getGuestFirstName());
                statement.setString(2, guestID.getGuestFamilyName());
                statement.setString(3, guestID.getAddress());
                statement.setString(4, guestID.getCountry());
                statement.setInt(5, guestID.getPhoneNo());
                statement.setString(6, guestID.getEmail());
                statement.setInt(7, guestID.getId());

                rowsInserted = statement.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DataMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (rowsInserted == dirtyGuestID.size());
    }
}
