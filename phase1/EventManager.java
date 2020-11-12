import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The EventManager class, this is the use case class to manage all events.
 *
 */
public class EventManager implements Serializable {

    private List<Event> events;
    private RoomManager rooms;


    /**
     * Creates an empty event manager
     */
    public EventManager(RoomManager rooms) {
        events = new ArrayList<>();
        this.rooms = rooms;

    }


    /**
     * Creates a new event
     */
    public boolean createEvent(String title, String roomID, Speaker speaker, int startTime) {
        for (Event event : this.events) {
            if ((event.getSpeakers().contains(speaker.getUserName()) || roomID.equals(event.getRoomID()) && event.getStartTime() == startTime)){
                return false;}
        }
        Event newEvent = new Event(title, roomID, speaker, startTime);
        events.add(newEvent);
        return true;
    }

    /**
     * Return a list of all events.
     */
    public List<Event> getAllEvent() {
        return events;
    }


    /**
     * Adds an attendee to the eventsMap  (Piazza question @652)
     * "When the user says they want to sign up for an event, they have a username and they know the event name.
     * So the controller method calls the EventManager method with two String parameters: userName and eventName.
     * The the EventManager, looks through the list of Event objects and finds the one with the correct name and
     * calls addUser(userName).
     * <p>
     * Note: This would add a String with the username to a list of Strings inside the Event, not the User object
     * itself. If you want to add the Even to the User, the Controller would send only the String eventName to the
     * UserManager, to store the Event's name in a list of Strings inside the User object."
     */
    public boolean addAttendeeToEvent(String userID, String eventID) {
        Event event = getEventFromID(eventID);
        if (event != null) {
            return event.addAttendee(userID, events);
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
    public boolean removeAttendeeFromEvent(String userID, String eventID) {
        Event event = getEventFromID(eventID);
        if (event != null) {
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
