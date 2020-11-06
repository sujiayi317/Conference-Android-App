import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The EventManager class, this is the use case class to manage all events.
 *
 */
public class EventManager implements Serializable{

    private List<Event> events;


    /**
     * Creates an empty room manager
     */
    public EventManager(){
        events = new ArrayList<>();

    }


    /**
     * Creates a new event
     */
    public Event createEvent(String title, String roomID, Speaker speaker, int startTime) {
        Event event = new Event(title, roomID, speaker, startTime);
        events.add(event);
        return event;
    }


    /**
     * Adds an attendee to the eventsMap  (Piazza question @652)
     * "When the user says they want to sign up for an event, they have a username and they know the event name.
     * So the controller method calls the EventManager method with two String parameters: userName and eventName.
     * The the EventManager, looks through the list of Event objects and finds the one with the correct name and
     * calls addUser(userName).
     *
     * Note: This would add a String with the username to a list of Strings inside the Event, not the User object
     * itself. If you want to add the Even to the User, the Controller would send only the String eventName to the
     * UserManager, to store the Event's name in a list of Strings inside the User object."
     */
    public void addAttendeeToEvent(String userName, String eventID) {
        for (Event event: events) {
            if (event.getEventID().equals(eventID)) {
                event.addAttendee(userName);
            }
        }
    }

}
