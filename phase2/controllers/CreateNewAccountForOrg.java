package controllers;

import presenters.ViewAllEventType;
import presenters.ViewAllTypeOfAccount;
import use_cases.AttendeeManager;
import use_cases.OrganizerManager;
import use_cases.UserManager;
import use_cases.VIPUserManager;

import java.util.ArrayList;

public class CreateNewAccountForOrg {
    private static OutputManager output;
    private static InputManager input;
    private final ViewAllTypeOfAccount viewAllTypeOfAccount;

    public CreateNewAccountForOrg(){
        output = new OutputManager();
        input = new InputManager();
        viewAllTypeOfAccount = new ViewAllTypeOfAccount();
    }
    public void toCreateNewAccountForOrg(CreateAccount createAccount, AttendeeManager attendeeManager,
                                         OrganizerManager organizerManager, EventsController eventsController,
                                         VIPUserManager vipUserManager, UserManager userManager){
        viewAllTypeOfAccount.printAllTypeOfAccount();
        String type = input.getInputString("Please Enter the type of account you wanna create");
        ArrayList<String> allType= new ArrayList<>();
        allType.add("ORGANIZER");
        allType.add("ATTENDEE");
        allType.add("SPEAKER");
        allType.add("VIP");
        if (allType.contains(type)){
            switch (type){
                case "ORGANIZER":
                    createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                            vipUserManager, userManager, "ORGANIZER");
                case "ATTENDEE":
                    createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                            vipUserManager, userManager, "ATTENDEE");
                case "SPEAKER":
                    createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                            vipUserManager, userManager, "SPEAKER");
                case "VIP":
                    createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                            vipUserManager, userManager, "VIP");

            }
        }
    }
}
