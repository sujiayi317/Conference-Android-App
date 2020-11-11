/**
 * The Event class, this is the conference scheduled by Organizer for Attendees and Speakers.
 *
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Meeting class, this is used to create meeting objects, and it holds person objects attending the meeting.
 * We can assume the title for each event is unique (Piazza @704)
 *
 */
public class Event implements Serializable {

    private final String eventID;
    private final String title;
    private final String roomID;
    private ArrayList<String> userID;
    private ArrayList<String> speakers;
    private int startTime;
    private int duration = 1;

    public Event(String title, String roomID, Speaker speaker, int startTime) {
        this.eventID = title;
        this.roomID = roomID;
        this.title = title;
        this.userID = new ArrayList<>();
        this.speakers = new ArrayList<>(1);
        speakers.add(speaker.getUserName());
        this.startTime = startTime;
    }

    public Event(String title, String roomID, Speaker speaker, int startTime, int duration) {
        this.eventID = title;
        this.roomID = roomID;
        this.title = title;
        this.userID = new ArrayList<>();
        this.speakers = new ArrayList<>(1);
        speakers.add(speaker.getUserName());
        this.startTime = startTime;
        this.duration = duration;
    }


    /**
     * Get the value of timeSlot
     *
     * @return the value of timeSlot
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Set the value of timeSlot
     *
     * @param startTime new value of timeSlot
     */
//    public void setStartTime(int startTime) {
//        this.startTime = startTime;
//    }

    /**
     * Adds an attendee
     *
     * @param attendeeID String
     * @return boolean true if we add attendee to the list
     */
    public boolean addAttendee(String attendeeID, List<Event> events) {
        if (this.userID.contains(attendeeID)) {
            return false;
        }
        for (Event event : events){
            if(event.getAttendees().contains(attendeeID) && event.getStartTime() == this.getStartTime()){
                return false;
            }
        }
        this.userID.add(attendeeID);
        return true;
    }

    /**
     * Remove an attendee
     *
     * @param attendeeID String
     * @return boolean true if person existed in attendee list
     */
    public boolean removeAttendee(String attendeeID) {
        if (userID.contains(attendeeID)) {
            return userID.remove(attendeeID);
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
    public String getRoomID(){
        return this.roomID;
    }
    /**
     * Returns all speakers
     *
     * @return all speakers
     */
    public String getSpeakers() {
        return speakers.get(0);
    }

    /**
     * Returns all attendeees
     *
     * @return all attendeees
     */
    public ArrayList<String> getAttendees() {
        return userID;
    }

    /**
     * Formats and returns the time slot
     *
     * @return String of the time formated nicely
     */
    public String getFormattedStartTime() {
        int tempTime;
        tempTime = startTime % 12;
        if (startTime == 12) tempTime = 12;
        return String.format("%s:00 %s", tempTime, ((startTime < 9) || (startTime == 12) ? "PM" : "AM"));
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

//    @Override
//    public int compareTo(Event event) {
//        return startTime.compareTo(event.startTime);
//    }
//}
