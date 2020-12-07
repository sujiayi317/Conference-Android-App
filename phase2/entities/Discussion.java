package entities;

import com.example.a207_demo.eventSystem.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Discussion is a type of event
 */
public class Discussion extends Event {

    /**
     * Discussion
     *
     * @param title       title
     * @param roomID      roomID
     * @param startTime   startTime
     * @param speakers    speakers
     * @param duration    duration
     * @param restriction restriction
     */
    public Discussion(String title, String roomID, String startTime, String duration,
                      String restriction, int capacity, ArrayList<String> speakers) {
        super(title, roomID, startTime, duration, restriction, capacity);
        setType("DISCUSSION");
        setSpeakerUserIDs(speakers);
    }

    /**
     * Discussion
     * @param title title
     * @paramt eventID eventID
     * @param roomID roomID
     * @param speakerID speakerID
     * @param startTime startTime
     * @param duration duration
     * @param restriction restriction
     */
    public Discussion (String title, String eventID, String roomID, String startTime,
                       String duration, String restriction, int capacity, ArrayList<String> speakerID){
        super(title, eventID, roomID, startTime, duration, restriction, capacity);
        setType("DISCUSSION");
        setSpeakerUserIDs(speakerID);
    }


    /**
     * speakersToString
     *
     * @return StringBuilder
     */
    public StringBuilder speakersToString() {
        StringBuilder totalString = new StringBuilder();
        for (String s : getSpeakers()) {
            totalString.append(s);
        }
        return totalString;
    }

    /**
     * toFullString
     *
     * @return String
     */
    @Override
    public String toFullString() {
        return this.toString() + " in room " + this.getRoomID() + " with speaker: " + this.speakersToString();
    }
}
