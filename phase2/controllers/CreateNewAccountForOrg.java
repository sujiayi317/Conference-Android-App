package controllers;

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
                    if(createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                            vipUserManager, userManager, "ORGANIZER")){
                        output.printPrompt("New organizer account successfully created.\n");
                    }
                case "ATTENDEE":
                    if (createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                            vipUserManager, userManager, "ATTENDEE")){
                        output.printPrompt("New attendee account successfully created.\n");
                    }
                case "SPEAKER":
                    if (createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                            vipUserManager, userManager, "SPEAKER")){
                        output.printPrompt("New speaker account successfully created.\n");
                    }
                case "VIP":
                    if (createAccount.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(),
                            vipUserManager, userManager, "VIP")){
                        output.printPrompt("New VIP account successfully created.\n");
                    }

            }
        }
        output.printPrompt("Action is Invalid.\n");
    }
}
