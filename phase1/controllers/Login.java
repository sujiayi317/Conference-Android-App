package controllers;

import use_cases.*;

import javax.swing.*;

public class Login{
    private static InputManager input;
    private static OutputManager output;
    private static CreateAccount createAccount;
    private static String ID;
    private static String type;

    public Login(){
        input = new InputManager();
        output = new OutputManager();
        createAccount = new CreateAccount();
    }
    public void run(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                    UserManager usermanager) {
        while (true) {
            output.printPrompt("** Welcome to login page of conference sign up center! Please enter a number **\n");
            String CurrentAction = input.getInputString("1. Sign in \n2. Create an account\n");
            if (CurrentAction.equals("1")) {
                if (signIn(usermanager)){
                    break;
                } else {
                    output.printPrompt("Sign in failed, directing back to main page now...\n");
                }
            } else if (CurrentAction.equals("2")) {
                if (createAccount(attendeeManager, organizerManager, speakerManager, usermanager)){
                    output.printPrompt("New account successfully created! Directing back to main page now...\n");
                } else {
                    output.printPrompt("Account creation cancelled, directing back to main page now...\n");
                }
            } else {
                output.printPrompt("Invalid action entered, directing back to main page now...\n");
            }
        }
    }

    public boolean signIn(UserManager userManager){
        while (true) {
            String account = input.getInputString("Please enter your email:\n");
            String password = input.getInputString("Please enter your password:\n");
//            if (attendeeManager.validLogIn(account, password).equals("NULL") &&
//                    speakerManager.validLogIn(account, password).equals("NULL") &&
//                    organizerManager.validLogIn(account, password).equals("NULL")){
//
//            }
            if (!userManager.validLogIn(account, password).equals("NULL")){
                ID = userManager.validLogIn(account, password);
                type = userManager.getUserType(account, password);
                return true;
            }
            return false;
        }
    }

    public boolean createAccount(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                 SpeakerManager speakerManager, UserManager userManager){
        while (true) {
            output.printPrompt("Select what type of account you want to create, or enter 'cancel' to exit.\n");
            String CurrentAction = input.getInputString("1. Attendee account \n2. Organizer account\n");
            if (CurrentAction.equals("1")) {
                return createAccount.CreateNewAccount(attendeeManager, organizerManager, speakerManager, userManager,"ATTENDEE");
            } else if (CurrentAction.equals("2")) {
                return createAccount.CreateNewAccount(attendeeManager, organizerManager, speakerManager, userManager,"ORGANIZER");
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
