package use_cases;

import entities.Attendee;
import entities.Speaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.AttendeeManager class, this is the use case class to manage the Attendees for this conference.
 *
 */
public class AttendeeManager extends UserManager implements Serializable {
    //private List<String> events;
    private List<Attendee> attendees;

    //private Map<String, entities.Attendee> attendeeMap;
    //private Map<String, List<String>> eventsMap;

    /**
     * Creates an use_cases.AttendeeManager with lists of Events for an attendee that is empty
     */
    public AttendeeManager(){
        //events = new ArrayList<>();
        //attendeeMap = new HashMap<>();
        //eventsMap = new HashMap<>();
        attendees = new ArrayList<>();
    }

    /**
     * Creates An attendee and adds it to the map and lists
     */
    public Attendee createAttendee(String userName, String email, String password) {
        Attendee attendee = new Attendee(userName, email, password);
        //attendeeMap.put(attendee.getUserName(), attendee);
        attendees.add(attendee);
        return attendee;
    }

    public List<Attendee> getAttendees() {
        return attendees;
    }


//    /**
//     * Sign an entities.Attendee up for an event.
//     * @param userID,eventID user id of user and event id of event
//     * @return true iff signed up successfully
//     */
    public boolean signUp(EventManager eventManager, String userID, String eventID, RoomManager roomManager) {
        return eventManager.addAttendeeToEvent(userID, eventID, roomManager);
    }

    public boolean cancel(EventManager eventManager, String userID, String eventID, RoomManager roomManager) {
        return eventManager.removeAttendeeFromEvent(userID, eventID, roomManager);
    }

}