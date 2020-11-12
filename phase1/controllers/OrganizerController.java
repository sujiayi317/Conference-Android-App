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
    private CreateAccount create = new CreateAccount();

    public void run(EventManager eventManager, RoomManager roomManager, AttendeeManager attendeeManager,
                    OrganizerManager organizerManager, SpeakerManager speakerManager) {
        //connect to Attendee Presenter - Menu options

        int choice = input.getInputInt("Please choose from the following options:");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    //connect to CreateSpeaker Controller
                    if (create.CreateNewAccount(attendeeManager, organizerManager, speakerManager, "Speaker")){
                        output.printPrompt("New speaker account successfully created.");
                    } else {
                        output.printPrompt("Create speaker action cancelled.");
                    }
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
}
