/**
 * The Event class, this is the conference scheduled by Organizer for Attendees and Speakers.
 *
 */
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The Meeting class, this is used to create meeting objects, and it holds person objects attending the meeting.
 * We can assume the title for each event is unique (Piazza @704)
 *
 */
public class Event implements Serializable, Comparable<Event> {

    private final String eventID;
    private final String title;
    private final String roomID;
    private ArrayList<String> attendees;
    private ArrayList<String> speakers;
    private int startTime;
    private int duration;

    public Event(String title, String roomID, Speaker speaker, int startTime) {
        this.eventID = title;
        this.roomID = roomID;
        this.title = title;
        this.attendees = new ArrayList<>();
        this.speakers = new ArrayList<>(1);
        speakers.add(speaker.getUserName());
        this.startTime = startTime;
    }

    public Event(String title, String roomID, Speaker speaker, int startTime, int duration) {
        this.eventID = title;
        this.roomID = roomID;
        this.title = title;
        this.attendees = new ArrayList<>();
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
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Adds an attendee
     * @param attendee Attendee object
     */
    public void addAttendee(String attendee) {
        attendees.add(attendee);
    }

    /**
     * Remove an attendee
     * @param attendee Attendee object
     * @return boolean true if person existed in attendee list
     */
    public boolean removeAttendee(Attendee attendee) {
        return attendees.remove(attendee.getUserName());
    }

    /**
     * Adds a speaker
     * @param speaker Speaker object
     */
    public void addSpeaker(Speaker speaker) {
        speakers.add(speaker.getUserName());
    }

    /**
     * Remove an attendee
     * @param speaker Speaker object
     * @return boolean true if person existed in attendee list
     */
    public boolean removeSpeaker(Speaker speaker) {
        return speakers.remove(speaker.getUserName());
    }

    /**
     * Return the ID
     * @return the ID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Return the title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns all speakers
     * @return all speakers
     */
    public String getSpeakers() {
        return speakers.get(0);
    }

    /**
     * Returns all attendeees
     * @return all attendeees
     */
    public ArrayList<String> getAttendees() {
        return attendees;
    }

    /**
     * Formats and returns the time slot
     * @return String of the time formated nicely
     */
    public String getFormattedStartTime(){
        int tempTime;
        tempTime = startTime % 12;
        if (startTime == 12) tempTime = 12;
        return String.format("%s:00 %s", tempTime, ((startTime < 9) || (startTime == 12) ? "PM": "AM"));
    }

    @Override
    public String toString() {
        return this.title+" at "+this.getFormattedStartTime();
    }

    /**
     * Returns a formatted string will more data
     * @return a formatted string will more data
     */
    public String fullString() {
        return this.toString()+" in room " + this.roomID + " with speaker: " + this.speakers.get(0);
    }

    @Override
    public int compareTo(Event event) {
        return startTime.compareTo(event.startTime);
    }
}
