package use_cases;

import entities.*;

public class UserFactory {
    private final AttendeeManager attendeeManager = new AttendeeManager();
    private final VIPUserManager vipUserManager = new VIPUserManager();
    private final OrganizerManager organizerManager = new OrganizerManager();
    private final SpeakerManager speakerManager = new SpeakerManager();

    public User createANewUser(String email, String userName, String password, String type){
        switch (type) {
            case "ATTENDEE":
                attendeeManager.createAttendee(userName, email, password);
                break;
            case "VIPUser":
                vipUserManager.createVIPUser(userName, email, password);
                break;
            case "ORGANIZER":
                organizerManager.createOrganizer(userName, email, password);
                break;
            case "SPEAKER":
                speakerManager.createSpeaker(userName, email, password);
                break;
        }
        return null;
    }
}
