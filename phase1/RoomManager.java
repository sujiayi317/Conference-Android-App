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

    private List<Room> rooms;

    private List<String> events;

    private final Map<String, List<String>> eventsMap;

    /**
     * Creates an empty room manager
     */
    public RoomManager(){
        rooms = new ArrayList<>();
        events = new ArrayList<>();
        eventsMap = new HashMap<>();
    }

    /**
     * Creates a new room (helper method for addEventToRoom method)
     * @param roomID the room to create and add
     */
    private Room createRoom(String roomID) {
        Room room = new Room(roomID);
        this.rooms.add(room);
        return room;
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
    public Room getRoomBasedOnItsID(String roomID){
        for (Room room: rooms) {
            if (room.getRoomID() == roomID){
                return room;
            }
        }
        return null;
    }
    public ArrayList<String> getAvailableRoom(int time, EventManager eventManager){
        ArrayList<String> roomList = new ArrayList<>();
        for (Room room : rooms){
            roomList.add(room.getRoomID());
        }
        for (String roomID : eventsMap.keySet()){
            for (String eventID :eventsMap.get(roomID)){
                Event event = eventManager.getEventFromID(eventID);
                if (event.getStartTime() == time){
                    roomList.remove(roomID);
                }
            }
        }
        return roomList;
    }
}
