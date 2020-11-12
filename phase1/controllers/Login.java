package controllers;

import use_cases.*;

import javax.swing.*;

public class Login{
    private static InputManager input = new InputManager();
    private static OutputManager output = new OutputManager();

    public void run(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager) {
        while (true) {
            output.printPrompt("Welcome to main page of conference sign up centre! Please enter a number");
            Integer CurrentAction = input.getInputInt("1. Sign in \n2. Create an account");
            if (CurrentAction == 1) {
                signIn();
            } else if (CurrentAction == 2) {
                createAccount();
            } else {
                output.printPrompt("Invalid action, directing back to ");
            }
        }
    }

    public boolean signIn(){}

    public boolean createAccount(){
        while (true) {
            output.printPrompt("Select what type of account you want to create, or enter 'cancel' to exit.");
            String CurrentAction = input.getInputString("1. Attendee account \n2. Organizer account");
            if (CurrentAction.equals("1")) {
                return signIn();
            } else if (CurrentAction.equals("2")) {
                return createAccount();
            } else if(CurrentAction.equals("cancel")){
                return false;
            } else {
                output.printPrompt("Invalid action, please try again.");
            }
        }
    }

    public String getUserType(){
        return
    }

}
