package controllers;

import use_cases.AttendeeManager;
import use_cases.OrganizerManager;
import use_cases.UserManager;
import use_cases.VIPUserManager;

public class createNewAttendee {
    private static OutputManager output;

    public createNewAttendee(){
        output = new OutputManager();
    }
    public void toCreateNewAttendee(CreateAccount createAccount, AttendeeManager attendeeManager,
                                 OrganizerManager organizerManager, EventsController eventsController,
                                 VIPUserManager vipUserManager, UserManager userManager) {
        if (createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                vipUserManager, userManager, "ATTENDEE")) {
            output.printPrompt("New attendee account successfully created.\n");
        } else {
            output.printPrompt("Create attendee action cancelled.\n");
        }

    }
}
