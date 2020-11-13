package use_cases;

import entities.Event;
import entities.Speaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.SpeakerManager class, this is the use case class to manage the entities.Speaker for this conference.
 *
 */
public class SpeakerManager extends UserManager implements Serializable {
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
        speakers = new ArrayList<>();
    }

    /**
     * Creates a entities.Speaker and adds it to the map and lists
     */
    public void createSpeaker(String userName, String email, String password) {
        Speaker speaker = new Speaker(userName, email, password);
        //speakerMap.put(speaker.getUserName(), speaker);
        speakers.add(speaker);
        UserManager.users.add(speaker);
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

    public String validLogIn(String account, String password){
        for (Speaker speaker : speakers){
            if (speaker.getEmail().equals(account) && speaker.getPassword().equals(password)){
                return speaker.getUserID();
            } else if (speaker.getEmail().equals(account)){
                return "NULL";
            }
        }
        return "NULL";
    }

    public ArrayList<String> getAllAvailableSpeaker(int time, EventManager eventManager){
        ArrayList<String> availableSpeaker = new ArrayList<>();
        List<Event> events = eventManager.getAllEvent();
        for (Speaker speaker : speakers){
            availableSpeaker.add(speaker.getUserName());
            for (Event event : events){
                if (event.getSpeakers().equals(speaker.getUserName()) && time == event.getStartTime()){
                    availableSpeaker.remove(speaker.getUserName());
                }
            }
        }
        return availableSpeaker;
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
