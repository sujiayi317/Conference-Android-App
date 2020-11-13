package use_cases;

import entities.Event;
import entities.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The use_cases.RoomManager class, this is the use case class to manage all rooms.
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
     * @param roomNum the room to create and add
     */

    public boolean createRoom(String roomNum) {
        for (Room room : this.rooms) {
            if (room.getRoomNum().equals(roomNum)){
                return false;
            }
        }
        Room room = new Room(roomNum);
        this.rooms.add(room);
        return true;
    }

    public String changeIdTONum(String roomID){
        for (Room room: rooms){
            if (room.getRoomID().equals(roomID)){
                return room.getRoomNum();
            }
        }
        return "NULL";
    }
    public String changeNumTOID(String roomNum){
        for (Room room: rooms){
            if (room.getRoomID().equals(roomNum)){
                return room.getRoomID();
            }
        }
        return "NULL";
    }


    /**
     * Adds an event to the specified room
     * @param roomID the room to create and add
     */
    public void addEventToRoom(String event, String roomID) {
        if (!eventsMap.containsKey(roomID)) {
            events = new ArrayList<>();
        } else {
            events = eventsMap.get(roomID);
        }
        events.add(event);
        eventsMap.put(roomID, events);
    }

    public Room getRoomBasedOnItsID(String roomID){
        for (Room room: rooms) {
            if (room.getRoomID().equals(roomID)){
                return room;
            }
        }
        return null;
    }

    public ArrayList<String> getAvailableRoom(int time, EventManager eventManager){
        ArrayList<String> roomList = new ArrayList<>();
        for (Room room : rooms){
            roomList.add(changeIdTONum(room.getRoomID()));
        }
        for (String roomID : eventsMap.keySet()){
            for (String eventID :eventsMap.get(roomID)){
                Event event = eventManager.getEventFromID(eventID);
                if (event.getStartTime() == time){
                    roomList.remove(changeIdTONum(roomID));
                }
            }
        }
        return roomList;
    }
}
