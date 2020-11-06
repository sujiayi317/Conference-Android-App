import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The RoomManager class, this is the use case class to manage all rooms.
 *
 */
public class RoomManager implements Serializable {

    private List<String> events;

    private final Map<String, List<String>> eventsMap;

    /**
     * Creates an empty room manager
     */
    public RoomManager(){
        events = new ArrayList<>();
        eventsMap = new HashMap<>();
    }

    /**
     * Creates a new room (helper method for addEventToRoom method)
     * @param roomID the room to create and add
     */
    private Room createRoom(String roomID) {
        return new Room(roomID);
    }


    /**
     * Adds an event to the specified room
     * @param roomID the room to create and add
     */
    public void addEventToRoom(String event, String roomID) {
        if (!eventsMap.containsKey(roomID)) {
            Room room = createRoom(roomID);
            events = eventsMap.get(room.getRoomID());
        } else {
            events = eventsMap.get(roomID);
        }
        events.add(event);
        eventsMap.put(roomID, events);
    }
}
