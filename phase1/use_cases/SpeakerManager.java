package use_cases;

import entities.Organizer;
import entities.Speaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.SpeakerManager class, this is the use case class to manage the entities.Speaker for this conference.
 *
 */
public class SpeakerManager implements Serializable {
    //private List<String> events;
    private List<Speaker> speakers;

    //private Map<String, entities.Speaker> speakerMap;
    //private Map<String, List<String>> eventsMap;

    /**
     * Creates an use_cases.SpeakerManager with lists of Events for a speaker that is empty
     */
    public SpeakerManager(){
//        events = new ArrayList<>();
//        speakerMap = new HashMap<>();
//        eventsMap = new HashMap<>();
        speakers = new ArrayList<>(1);  // Initial Capacity is 1 in Phase 1
    }

    /**
     * Creates a entities.Speaker and adds it to the map and lists
     */
    public Speaker createSpeaker(String userName, String email, String password) {
        Speaker speaker = new Speaker(userName, email, password);
        //speakerMap.put(speaker.getUserName(), speaker);
        speakers.add(speaker);
        return speaker;
    }

    public boolean validNewSpeakerName(String name){
        for (Speaker speaker : speakers){
            if (speaker.getUserName().equals(name)){
                return false;
            }
        }
        return true;
    }

    public boolean validNewSpeakerEmail(String email){
        for (Speaker speaker : speakers){
            if (speaker.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

//    public void addEventToSpeaker(String event, entities.Speaker speaker) {
//        events = eventsMap.get(speaker.getUserName());
//        events.add(event);
//        eventsMap.put(speaker.getUserName(), events);
//    }

//    public Map<String, List<String>> getEventsMap() {
//        return eventsMap;
//    }

}
