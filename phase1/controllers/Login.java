package controllers;

import use_cases.*;

import javax.swing.*;

public class Login{
    private static InputManager input = new InputManager();
    private static OutputManager output = new OutputManager();
    private static CreateAccount createAccount = new CreateAccount();
    private static String ID;
    private static String type;


    public void run(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager) {
        while (true) {
            output.printPrompt("Welcome to main page of conference sign up centre! Please enter a number\n");
            Integer CurrentAction = input.getInputInt("1. Sign in \n2. Create an account");
            if (CurrentAction == 1) {
                if (signIn(attendeeManager, organizerManager, speakerManager)){
                    break;
                };
            } else if (CurrentAction == 2) {
                createAccount(attendeeManager, organizerManager, speakerManager);
            } else {
                output.printPrompt("Invalid action, directing back to ");
            }
        }
    }

    public boolean signIn(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager){
        while (true) {
            String account = input.getInputString("Please enter your email:\n");
            String password = input.getInputString("Please enter your password:\n");
//            if (attendeeManager.validLogIn(account, password).equals("NULL") &&
//                    speakerManager.validLogIn(account, password).equals("NULL") &&
//                    organizerManager.validLogIn(account, password).equals("NULL")){
//
//            }
            if (!attendeeManager.validLogIn(account, password).equals("NULL")){
                ID = attendeeManager.validLogIn(account, password);
                type = "ATTENDEE";
                return true;
            } else if (!organizerManager.validLogIn(account, password).equals("NULL")){
                ID = organizerManager.validLogIn(account, password);
                type = "ORGANIZER";
                return true;
            } else if (!speakerManager.validLogIn(account, password).equals("NULL")){
                ID = speakerManager.validLogIn(account, password);
                type = "SPEAKER";
                return true;
            }
        }
    }

    public boolean createAccount(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager){
        while (true) {
            output.printPrompt("Select what type of account you want to create, or enter 'cancel' to exit.\n");
            String CurrentAction = input.getInputString("1. Attendee account \n2. Organizer account");
            if (CurrentAction.equals("1")) {
                return createAccount.CreateNewAccount(attendeeManager, organizerManager, speakerManager, "ATTENDEE");
            } else if (CurrentAction.equals("2")) {
                return createAccount.CreateNewAccount(attendeeManager, organizerManager, speakerManager, "ORGANIZER");
            } else if(CurrentAction.equals("cancel")){
                return false;
            } else {
                output.printPrompt("Invalid action, please try again.\n");
            }
        }
    }

    public String getUserType(){
        return type;
    }

    public String getUserID(){
        return ID;
    }

}
