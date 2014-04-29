package domain;

/**
 *
 * @author Tomoe
 */
public class Room
{

    int roomNo;
    String type;

    public Room(int roomNo, String type)
    {
        this.roomNo = roomNo;
        this.type = type;
    }

    public int getRoomNo()
    {
        return roomNo;
    }

    public void setRoomNo(int roomNo)
    {
        this.roomNo = roomNo;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

}
