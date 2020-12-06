package use_cases;


import entities.Event;
import entities.Speaker;
import entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.SpeakerManager class, this is the use case class to manage the entities.Speaker for this conference.
 */
public class SpeakerManager extends UserManager implements Serializable {

    private List<Speaker> speakers;

    /**
     * Creates an use_cases.SpeakerManager with lists of Events for a speaker that is empty
     */
    public SpeakerManager() {
        speakers = new ArrayList<>();
    }

    /**
     * Creates a entities.Speaker and adds it to the lists
     *
     * @param userName userName of this speaker
     * @param email email of this speaker
     * @param password password of this speaker
     */
    public void createSpeaker(String userName, String email, String password) {
        Speaker speaker = new Speaker(userName, email, password);
        speakers.add(speaker);
        UserManager.users.add(speaker);
    }

    /**
     * Load a entities.Speaker from file and adds it to the lists
     *
     * @param userName userName of this speaker
     * @param email email of this speaker
     * @param password password of this speaker
     * @param ID user ID of this speaker
     */
    public void loadSpeaker(String userName, String email, String password, String ID) {
        Speaker speaker = new Speaker(userName, email, password, ID);
        speakers.add(speaker);
        UserManager.users.add(speaker);
    }

//    /**
//     * Check email and password, return its userID (or "NULL")
//     *
//     * @param email    email
//     * @param password password
//     * @return userID of the speaker, or "NULL" if not found
//     */
//    public String validLogIn(String email, String password) {
//        for (Speaker speaker : speakers) {
//            if (speaker.getEmail().equals(email) && speaker.getPassword().equals(password)) {
//                return speaker.getUserID();
//            } else if (speaker.getEmail().equals(email)) {
//                return "NULL";
//            }
//        }
//        return "NULL";
//    }

    /**
     * Get an ArrayList<String> of all Available Speakers given a starting time
     * @param time String, start time
     * @param eventManager an EventManager
     * @return ArrayList<String> of all Available Speaker
     */
    public ArrayList<String> getAllAvailableSpeaker(String time, EventManager eventManager, String duration) {
        ArrayList<String> availableSpeaker = new ArrayList<>();
        List<Event> events = eventManager.getAllEvent();
        for (Speaker speaker : speakers) {
            availableSpeaker.add(speaker.getUserName());
            for (Event event : events) {
                for (String currentSpeaker: event.getSpeakers()) {
                    if (currentSpeaker.equals(speaker.getUserName()) && event.timeConflict(time, duration)) {
                        availableSpeaker.remove(speaker.getUserName());
                    }
                }
            }
        }
        return availableSpeaker;
    }

    public ArrayList<StringBuilder> getAllSpeakerInfo(){
        ArrayList<StringBuilder> usersInfo = new ArrayList<>();
        for (int i = 0; i < speakers.size(); i++){
            StringBuilder singleInfo = new StringBuilder();
            User currentUser = speakers.get(i);
            singleInfo.append(i).append(") ").append(currentUser.getUserName()).append(" ").append(currentUser.getType());
            usersInfo.add(singleInfo);
        }
        StringBuilder SummaryInfo = new StringBuilder();
        SummaryInfo.append("Total Number: ").append(speakers.size());
        usersInfo.add(0, SummaryInfo);
        return usersInfo;
    }

//    /**
//     * Get ID of a speaker given his user name
//     * @param name name of the speaker
//     * @return user ID of the speaker, or "NULL" if not found
//     */
//    public String getIdFromName(String name) {
//        for (Speaker speaker : speakers) {
//            if (speaker.getUserName().equals(name)) {
//                return speaker.getUserID();
//            }
//        }
//        return "NULL";
//    }

//    public String getSpeakerNameFromID(String speakerId){
//        for (Speaker speaker : speakers){
//            if (speaker.getUserID().equals(speakerId)){
//                return speaker.getUserName();
//            }
//        }
//        return "NULL";
//    }
}
