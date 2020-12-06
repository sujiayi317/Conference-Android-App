package use_cases;

import entities.Event;
import entities.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Event;
import entities.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The use_cases.RoomManager class, this is the use case class to manage all rooms.
 */
public class RoomManager implements Serializable {

    private List<Room> rooms;
    private List<String> events;

    private final Map<String, List<String>> eventsMap;  // Map from roomID to events

    /**
     * Creates an empty room manager
     */
    public RoomManager() {
        rooms = new ArrayList<>();
        events = new ArrayList<>();
        eventsMap = new HashMap<>();
    }

    /**
     * Creates a new room
     *
     * @param roomNum the room to create and add
     * @return true iff successfully creates and adds the room
     */
    public boolean createRoom(String roomNum, int capacity) {
        for (Room room : this.rooms) {
            if (room.getRoomNum().equals(roomNum)) {
                return false;
            }
        }
        Room room = new Room(roomNum, capacity);
        this.rooms.add(room);
        return true;
    }

    /**
     * Creates a new room and add it to rooms list
     *
     * @param roomNum the room to create and add
     * @param roomID the room ID
     */
    public void loadRoom(String roomNum, String roomID, int capacity) {
        Room room = new Room(roomNum, roomID, capacity);
        this.rooms.add(room);
    }

    /**
     * Get an ArrayList<String> of all room numbers
     *
     * @return  ArrayList<String> of all room numbers
     */
    public ArrayList<String> getAllRoomNum() {
        ArrayList<String> roomNumbers = new ArrayList<>();
        for (Room room : this.rooms) {
            roomNumbers.add(room.getRoomNum());
        }
        return roomNumbers;
    }

    /**
     * Get an ArrayList<String> of all room IDs
     *
     * @return  ArrayList<String> of all room IDs
     */
    public ArrayList<String> getAllRoomID() {
        ArrayList<String> roomIDs = new ArrayList<>();
        for (Room room : this.rooms) {
            roomIDs.add(room.getRoomID());
        }
        return roomIDs;
    }

    /**
     * Check if a room with roomID is full
     *
     * @param  roomID roomID
     * @return true iff the room is full
     */
    public boolean isFull(String roomID, String eventID) {
        Room room = getRoomBasedOnItsID(roomID);
        return room.getCurrentNumAssociateWithEvent(eventID) == room.getCapacity();
    }

    /**
     * Input roomID, output its room number
     *
     * @param roomID roomID
     * @return roomNum
     */
    public String changeIdTONum(String roomID) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID)) {
                return room.getRoomNum();
            }
        }
        return "NULL";
    }

    /**
     * Input String room number, output its ID
     *
     * @param roomNum roomNum
     * @return roomID
     */
    public String changeNumTOID(String roomNum) {
        for (Room room : rooms) {
            if (room.getRoomNum().equals(roomNum)) {
                return room.getRoomID();
            }
        }
        return "NULL";
    }

    /**
     * Adds an event to the specified room and update events list and eventsMap
     *
     * @param roomID the room to create and add
     */
    public void addEventToRoom(String event, String roomID) {
        Room room = getRoomBasedOnItsID(roomID);
        if (!eventsMap.containsKey(roomID)) {
            events = new ArrayList<>();
        } else {
            events = eventsMap.get(roomID);
        }
        events.add(event);
        eventsMap.put(roomID, events);
        room.setCurrentNumAssociateWithEvent(event);
    }

    /**
     * Search through the list of rooms, return room given its ID
     *
     * @param roomID roomID
     * @return a Room object
     */
    public Room getRoomBasedOnItsID(String roomID) {
        for (Room room : rooms) {
            if (room.getRoomID().equals(roomID)) {
                return room;
            }
        }
        return null;
    }

    /**
     * Get an ArrayList<String> of room numbers that are available for the given time
     * @param time the starting time
     * @param eventManager an EventManager object
     * @return an ArrayList<String> of room numbers that are available for the given time
     */
    public ArrayList<String> getAvailableRoom(String time, EventManager eventManager, String duration) {
        ArrayList<String> roomList = new ArrayList<>();

        // First step, add all room numbers to the roomList
        for (Room room : rooms) {
            roomList.add(changeIdTONum(room.getRoomID()));
        }

        // Next step, remove unavailable room numbers from the roomList
        for (String roomID : eventsMap.keySet()) {
            // Loop though the list of eventIDs of the current room's events:
            for (String eventID : eventsMap.get(roomID)) {
                // Find the event object with this event ID
                Event event = eventManager.getEventFromID(eventID);

                // if the time conflicts, then the room is not available
                if (event.timeConflict(time, duration)) {
                    roomList.remove(changeIdTONum(roomID));
                }
            }
        }
        return roomList;
    }
    public void resetTheCurrentNumberBasedONEvent(String roomID, String eventID){
        Room room = getRoomBasedOnItsID(roomID);
        room.setCurrentNumAssociateWithEvent(eventID);
    }
//
//
//    public ArrayList<String> getTop5Event(){
//        ArrayList<String> Top5Event = new ArrayList<>();
//        int totalSize = events.size();
//        while (Top5Event.size() < Math.min(5, events.size())) {
//
//        }
//    }


//    public void freeUpTheRoomBasedOnEvent(Addtendable event){
//        for (Room room: rooms){
//            if (room.getRoomID().equals(event.getRoomID())){
//                room.resetTheCurrentNum();
//            }
//        }
//    }
}