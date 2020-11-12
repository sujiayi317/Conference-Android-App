package controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import entities.Organizer;
import entities.Speaker;
import use_cases.*;

/**
 * This is the main controller for Organizer.
 */
public class OrganizerController {

    private static InputManager input = new InputManager();
    private static OutputManager output = new OutputManager();

    public void run(EventManager eventManager, RoomManager roomManager, AttendeeManager attendeeManager,
                    OrganizerManager organizerManager, SpeakerManager speakerManager) {
        //connect to Attendee Presenter - Menu options

        int choice = input.getInputInt("Please choose from the following options:");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    //connect to CreateSpeaker Controller
                    CreateSpeaker(speakerManager);
                    break;
                case 2:
                    //connect to EnterRoom Controller
                    break;
                case 3:
                    //connect to Schedule Controller
                    break;
                case 4:
                    //connect to Contacts Controller
            }
        }
    }

    public void CreateSpeaker(SpeakerManager speakerManager) {
        String user = input.getInputString("Please enter the user name for new speaker:");
        while (true) {
            if (speakerManager.validSpeakerName(user)) {
                break;
            } else {
                user = input.getInputString("User name already used, please enter another username:");
            }
        }
        String password = input.getInputString("Please enter a password for " + user + ":");
        while (true) {
            if (password.length() >= 8) {
                break;
            } else {
                output.printPrompt("Password must be at least length 8, please try again:");
            }
        }
        speakerManager.createSpeaker(user, "sample_email", password);
    }
}
