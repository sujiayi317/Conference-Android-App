import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The AttendeeManager class, this is the use case class to manage the Attendees for this conference.
 *
 */
public class AttendeeManager implements Serializable {
    private List<String> events;
    private List<Attendee> attendees;

    private Map<String, Attendee> attendeeMap;
    private Map<String, List<String>> eventsMap;

    /**
     * Creates an AttendeeManager with lists of Events for an attendee that is empty
     */
    public AttendeeManager(){
        events = new ArrayList<>();
        attendeeMap = new HashMap<>();
        eventsMap = new HashMap<>();
        attendees = new ArrayList<>();
    }

    /**
     * Creates An attendee and adds it to the map and lists
     */
    public Attendee createAttendee(String firstName, String lastName, String email) {
        Attendee attendee = new Attendee(firstName, lastName, email);
        attendeeMap.put(attendee.getUserName(), attendee);
        attendees.add(attendee);
        return attendee;
    }

    public void addEventToAttendee(String event, Attendee attendee) {
        events = eventsMap.get(attendee.getUserName());
        events.add(event);
        eventsMap.put(attendee.getUserName(), events);
    }


    /**
     * View all the events
     * @return list of events
     */
    public List<Event> viewAllEvents() {
        return EventManager.getAllEvents();
    }


    /**
     * Sign an Attendee up for an event.
     * @param event
     * @return true iff signed up successfully
     */
    public boolean signUp(Attendee attendee, Event event) {
        if(event.isFull()) return false;

        //update event to attendee
        addEventToAttendee(event, attendee);

        //update attendee to event
        EventManager eventManager = new EventManager();
        eventManager.addAttendeeToEvent(attendee, event);
        eventManager.updateCapacity(event, 1);

        //update room
        RoomManager roomManager = new RoomManager();
        roomManager.updateCapacity(event, 1);

        return true;
    }

    public boolean cancel(Attendee attendee, Event event) {
        if(!event.hasAttendee(attendee)) return false;
        //update event to attendee
        removeEventFromAttendee(event, attendee);

        //update attendee to event
        EventManager eventManager = new EventManager();
        eventManager.removeAttendeeFromEvent(attendee, event);
        eventManager.updateCapacity(event, -1);

        //update Room
        RoomManager roomManager = new RoomManager();
        roomManager.updateCapacity(event, -1);

        return true;
    }
}