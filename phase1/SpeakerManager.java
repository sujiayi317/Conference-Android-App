import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The SpeakerManager class, this is the use case class to manage the Speaker for this conference.
 *
 */
public class SpeakerManager implements Serializable {
    //private List<String> events;
    private List<Speaker> speakers;

    //private Map<String, Speaker> speakerMap;
    //private Map<String, List<String>> eventsMap;

    /**
     * Creates an SpeakerManager with lists of Events for a speaker that is empty
     */
    public SpeakerManager(){
//        events = new ArrayList<>();
//        speakerMap = new HashMap<>();
//        eventsMap = new HashMap<>();
        speakers = new ArrayList<>(1);  // Initial Capacity is 1 in Phase 1
    }

    /**
     * Creates a Speaker and adds it to the map and lists
     */
    public Speaker createSpeaker(String firstName, String lastName, String email, String password) {
        Speaker speaker = new Speaker(firstName, lastName, email, password);
        //speakerMap.put(speaker.getUserName(), speaker);
        speakers.add(speaker);
        return speaker;
    }

//    public void addEventToSpeaker(String event, Speaker speaker) {
//        events = eventsMap.get(speaker.getUserName());
//        events.add(event);
//        eventsMap.put(speaker.getUserName(), events);
//    }

//    public Map<String, List<String>> getEventsMap() {
//        return eventsMap;
//    }

}
