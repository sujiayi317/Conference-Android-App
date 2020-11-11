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
    private ArrayList<String> userName;
    private ArrayList<String> speakers;
    private int startTime;
    private int duration = 1;

    public Event(String title, String roomID, Speaker speaker, int startTime) {
        this.eventID = title;
        this.roomID = roomID;
        this.title = title;
        this.userName = new ArrayList<>();
        this.speakers = new ArrayList<>(1);
        speakers.add(speaker.getUserName());
        this.startTime = startTime;
    }

    public Event(String title, String roomID, Speaker speaker, int startTime, int duration) {
        this.eventID = title;
        this.roomID = roomID;
        this.title = title;
        this.userName = new ArrayList<>();
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
     * @param attendee Attendee object
     * @return boolean true if we add attendee to the list
     */
    public boolean addAttendee(Attendee attendee, List<Event> events) {
        if (this.userName.contains(attendee.getUserName())) {
            return false;
        }
        for (Event event : events){
            if(event.getAttendees().contains(attendee.getUserName()) && event.getStartTime() == this.getStartTime()){
                return false;
            }
        }
        this.userName.add(attendee.getUserName());
        return true;
    }

    /**
     * Remove an attendee
     *
     * @param attendee Attendee object
     * @return boolean true if person existed in attendee list
     */
    public boolean removeAttendee(Attendee attendee) {
        if (userName.contains(attendee.getUserName())) {
            return userName.remove(attendee.getUserName());
        }
        return false;
    }

    /**
     * Adds a speaker
     *
     * @param speaker Speaker object
     */
    public boolean addSpeaker(Speaker speaker, List<Event> events) {
        for (Event event : events) {
            if (event.speakers.contains(speaker.getUserName()) && event.getStartTime() == this.getStartTime()) {
                return false;
            }
        }
        this.speakers.add(speaker.getUserName());
        return true;
    }

    /**
     * Remove an attendee
     *
     * @param speaker Speaker object
     * @return boolean true if person existed in attendee list
     */
    public boolean removeSpeaker(Speaker speaker) {
        if (this.speakers.contains(speaker.getUserName())) {
            return this.speakers.remove(speaker.getUserName());
        }
        return false;
    }

    /**
     * Return the ID
     *
     * @return the ID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Return the title
     *
     * @return the title
     */
    public String getTitle() {
        return title;
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
        return userName;
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
