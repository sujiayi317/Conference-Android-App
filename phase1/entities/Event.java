package entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The entities.Event class, this is the conference scheduled by entities.Organizer for Attendees and Speakers.
 */
public class Event implements Serializable {

    private final String title;
    private final String eventID;
    private final String roomID;
    private ArrayList<String> userIDs;
    private ArrayList<String> speakers;
    private String startTime;
    private int duration = 1;

    public Event(String title, String roomID, String speakerID, String startTime) {
        this.title = title;
        this.eventID = UUID.randomUUID().toString().split("-")[0];
        this.roomID = roomID;
        this.userIDs = new ArrayList<>();
        this.speakers = new ArrayList<>(1);
        this.speakers.add(speakerID);
        this.startTime = startTime;
    }

    public Event(String title, String roomID, String speakerID, String startTime, int duration) {
        this.title = title;
        this.eventID = title;
        this.roomID = roomID;
        this.userIDs = new ArrayList<>();
        this.speakers = new ArrayList<>(1);
        speakers.add(speakerID);
        this.startTime = startTime;
        this.duration = duration;
    }


    /**
     * Get the value of timeSlot
     *
     * @return the value of timeSlot
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Adds an attendee
     *
     * @param attendeeID String
     * @return boolean true if we add attendee to the list
     */
    public boolean addAttendee(String attendeeID, List<Event> events) {
        if (this.userIDs.contains(attendeeID)) {
            return false;
        }
        for (Event event : events){
            if(event.getAttendees().contains(attendeeID) && event.getStartTime() == this.getStartTime()){
                return false;
            }
        }
        this.userIDs.add(attendeeID);
        return true;
    }

    /**
     * Remove an attendee
     *
     * @param attendeeID String
     * @return boolean true if person existed in attendee list
     */
    public boolean removeAttendee(String attendeeID) {
        if (userIDs.contains(attendeeID)) {
            return userIDs.remove(attendeeID);
        }
        return false;
    }

    /**
     * Adds a speaker
     *
     * @param speakerID String
     */
    public boolean addSpeaker(String speakerID, List<Event> events) {
        for (Event event : events) {
            if (event.speakers.contains(speakerID) && event.getStartTime() == this.getStartTime()) {
                return false;
            }
        }
        this.speakers.add(speakerID);
        return true;
    }

    /**
     * Remove an attendee
     *
     * @param speakerID String
     * @return boolean true if person existed in attendee list
     */
    public boolean removeSpeaker(String speakerID) {
        if (this.speakers.contains(speakerID)) {
            return this.speakers.remove(speakerID);
        }
        return false;
    }

    /**
     * Return the ID String
     *
     * @return the ID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Return the title String
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return the roomID String
     *
     * @return the roomID
     */
    public String getRoomID(){
        return this.roomID;
    }

    /**
     * Returns all speakers for this event
     *
     * @return all speakers
     */
    public String getSpeakers() {
        return speakers.get(0);
    }

    /**
     * Returns all attendeees for this event
     *
     * @return all attendeees
     */
    public ArrayList<String> getAttendees() {
        return userIDs;
    }

    /**
     * Formats and returns the time slot
     *
     * @return String of the time formated nicely
     */
    public String getFormattedStartTime() {
        int HourTime = Integer.parseInt(startTime.substring(8, 10));
        String Ending = String.format("%s", (HourTime >= 12) ? "PM" : "AM");
        return String.format("%s/%s/%s/%s:%s%s", startTime.substring(0, 4), startTime.substring(4, 6),
                startTime.substring(6, 8), startTime.substring(8, 10), startTime.substring(10, 11), Ending);
    }

    @Override
    public String toString() {
        return this.title + " at " + this.getFormattedStartTime();
    }

    /**
     * Returns a formatted string will more data
     *
     * @return a formatted string will more data
     */
    public String fullString() {
        return this.toString() + " in room " + this.roomID + " with speaker: " + this.speakers.get(0);
    }
}

