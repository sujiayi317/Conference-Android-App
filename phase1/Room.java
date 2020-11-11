import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Room class, this is used to create room objects, it holds conference meeting objects held in the room.
 */
public class Room implements Serializable{

    private final String roomID;

    /**
     * Constructor  
     * @param roomID unique identification of the room
     */
    public Room(String roomID) {
        this.roomID = roomID;
    }

    /**
     * Constructor  
     * @param roomID unique identification of the room
     */
    public Room(Integer roomID) {
        this(roomID.toString());
    }

    /**
     * Get the value of roomID
     *
     * @return the value of ID
     */
    public String getRoomID() {
        return roomID;
    }


    @Override
    public String toString() {
        return "Room Number " + roomID;

    }
}