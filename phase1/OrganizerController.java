//import java.io.Serializable;

/**
 * The OrganizerController class, this is used as a Controller for what organizers can do.
 *
 */
public class OrganizerController{

    private EventManager em = new FileReadWriter().readFromEventFile("events.ser");
    private SpeakerManager sm = new FileReadWriter().readFromSpeakerFile("speaker.ser");
    private RoomManager rm = new FileReadWriter().readFromRoomFile("room.ser");


    /**
     * Create an event
     */
    public Event createEvent(String title, String roomID, Speaker speaker, int startTime) {
        em.createEvent(title, roomID, speaker, startTime);
    }

    /**
     * Remove an event from the room
     * @param event Event being removed
     */
    public void removeEvent(Event event) {
        // TODO
    }

    /**
     * Checks if the time slot is taken
     * @param time Integer time (hour) being checked
     * @return true if time slot is taken
     */
    public boolean isTimeslotTaken(int time) {
        // TODO
        return false;
    }
}
