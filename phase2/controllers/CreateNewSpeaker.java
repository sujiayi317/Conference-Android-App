package controllers;

import use_cases.AttendeeManager;
import use_cases.OrganizerManager;
import use_cases.UserManager;

/**
 * This is the class for Creating a New speaker
 */
public class CreateNewSpeaker {
    private static OutputManager output;

    /**
     * This class is made for creating new speaker.
     */
    public CreateNewSpeaker(){
        output = new OutputManager();
    }
    public void createNewSpeaker(CreateAccount createAccount, AttendeeManager attendeeManager,
                                 OrganizerManager organizerManager, EventsController eventsController, UserManager userManager) {
        if (createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(), userManager, "SPEAKER")) {
            output.printPrompt("New speaker account successfully created.\n");
        } else {
            output.printPrompt("Create speaker action cancelled.\n");
        }

    }
}
