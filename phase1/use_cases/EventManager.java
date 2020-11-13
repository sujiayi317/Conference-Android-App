package use_cases;

import entities.Event;
import entities.Room;
import entities.Speaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.EventManager class, this is the use case class to manage all events.
 *
 */
public class EventManager implements Serializable {

    private List<Event> events;


    /**
     * Creates an empty event manager
     */
    public EventManager() {
        events = new ArrayList<>();

    }


    /**
     * Creates a new event
     */
    public Event createEvent(String title, String roomID, String speakerID, int startTime) {
        for (Event event : this.events) {
            if ((event.getSpeakers().contains(speakerID) || roomID.equals(event.getRoomID())) && event.getStartTime() == startTime){
                return null;}
        }
        Event newEvent = new Event(title, roomID, speakerID, startTime);
        events.add(newEvent);
        return newEvent;
    }

    /**
     * Return a list of all events.
     */
    public List<Event> getAllEvent() {
        return events;
    }

//    public ArrayList<String> getAllEventID(){
//        ArrayList<String> eventList = new ArrayList<>();
//        for (Event event: events){
//            eventList.add(event.getEventID());
//        }
//        return eventList;
//    }

    /**
     * Adds an attendee to the eventsMap  (Piazza question @652)
     * "When the user says they want to sign up for an event, they have a username and they know the event name.
     * So the controller method calls the use_cases.EventManager method with two String parameters: userName and eventName.
     * The the use_cases.EventManager, looks through the list of entities.Event objects and finds the one with the correct name and
     * calls addUser(userName).
     * <p>
     * Note: This would add a String with the username to a list of Strings inside the entities.Event, not the entities.User object
     * itself. If you want to add the Even to the entities.User, the Controller would send only the String eventName to the
     * UserManager, to store the entities.Event's name in a list of Strings inside the entities.User object."
     */
    public boolean addAttendeeToEvent(String userID, String eventID, RoomManager roomManager) {
        Event event = getEventFromID(eventID);
        if (event != null) {
            Room room = roomManager.getRoomBasedOnItsID(event.getRoomID());
            if (room.getCurrentNum() < room.getCapacity()){
                room.increaseCurrentNum();
                return event.addAttendee(userID, events);}
        }
        return false;
    }

    /**
     * Return a list all Attendees from event with eventID. If event not found, return empty array list.
     * @param eventID String object
     * @return arraylist of attendees' ID from given event, return empty arraylist if event not found.
     */
    public ArrayList<String> getAttendeesFromEvent(String eventID) {
        Event event = getEventFromID(eventID);
        if (event != null){ return event.getAttendees();}
        return new ArrayList<>();
    }

    /**
     * return an event based on its ID
     *
     * @param eventID String
     * @return event if eventID existed in events otherwise return null
     */
    public Event getEventFromID(String eventID) {
        for (Event event : events) {
            if (event.getEventID().equals(eventID)) {
                return event;
            }
        }
        return null;
    }

    /**
     * Return true if we remove the attendee from a event.
     * @param eventID,userID String object,
     * @return boolean
     */
    public boolean removeAttendeeFromEvent(String userID, String eventID, RoomManager roomManager) {
        Event event = getEventFromID(eventID);
        if (event != null) {
            Room room = roomManager.getRoomBasedOnItsID(event.getRoomID());
            room.decreaseCurrentNum();
            return event.removeAttendee(userID);
        }
        return false;
    }
    /**
     * Return ArrayList contains all events that attendee has.
     * @param userID String object,
     * @return ArrayList<String>
     */
    public ArrayList<String> getAllEventForTheAttendee(String userID){
        ArrayList<String> eventList = new ArrayList<>();
        for (Event event: events){
            if (event.getAttendees().contains(userID)){
                eventList.add(event.getEventID());
            }
        }
        return eventList;
    }
    /**
     * Return ArrayList contains all events that attendee has.
     * @param userID String object,
     * @return ArrayList<String>
     */
    public ArrayList<String> getAllEventForTheSpeaker(String userID){
        ArrayList<String> eventList = new ArrayList<>();
        for (Event event: events){
            if (event.getSpeakers().contains(userID)){
                eventList.add(event.getEventID());
            }
        }
        return eventList;
    }

}
