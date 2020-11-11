//import java.io.Serializable;

/**
 * The OrganizerController class, this is used as a Controller for what organizers can do.
 *
 */
/**
 * The OrganizerController class, this is the Controller class for an Organizer.
 *
 */
public class OrganizerController{

    FileReadWriter fileReadWriter = new FileReadWriter();

    private EventManager em = fileReadWriter.readFromEventFile("event.ser");
    private SpeakerManager sm = fileReadWriter.readFromSpeakerFile("speaker.ser");
    private RoomManager rm = fileReadWriter.readFromRoomFile("room.ser");


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