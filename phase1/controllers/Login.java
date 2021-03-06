package unused.controllers;

import use_cases.*;
import javax.swing.*;

/**
 * This is the class for logging in a user
 */
public class Login{
    private static InputManager input;
    private static OutputManager output;
    private static CreateAccount createAccount;
    private static String ID;
    private static String type;
    private static boolean EXIT = false;

    /**
     * The constructor of login class
     */
    public Login(){
        input = new InputManager();
        output = new OutputManager();
        createAccount = new CreateAccount();
    }

    /**
     * The logic of running the login program
     *
     * @param attendeeManager attendeeManager
     * @param organizerManager organizerManager
     * @param speakerManager speakerManager
     * @param userManager userManager
     */
    public void run(AttendeeManager attendeeManager, OrganizerManager organizerManager, SpeakerManager speakerManager,
                    UserManager userManager) {
        while (true) {
            output.printPrompt("\n*** Welcome to login page of conference sign up center! ***\n*** Please enter '1' " +
                    "or '2' to Login or create account, ***\n***      or enter 'EXIT' to save and exit program." +
                    "      ***\n\n");
            String CurrentAction = input.getInputString("1. Sign in \n2. Create an account\n");
            if (CurrentAction.equals("1")) {
                if (signIn(userManager)){
                    break;
                } else {
                    output.printPrompt("Sign in failed, directing back to main page now...\n");
                }
            } else if (CurrentAction.equals("2")) {
                if (createAccount(attendeeManager, organizerManager, speakerManager, userManager)){
                    output.printPrompt("New account successfully created! Directing back to main page now...\n");
                } else {
                    output.printPrompt("Account creation cancelled, directing back to main page now...\n");
                }
            } else if (CurrentAction.equalsIgnoreCase("EXIT")){
                EXIT = true;
                break;
            } else {
                output.printPrompt("Invalid action entered  :(  directing back to main page now...\n");
            }
        }
    }

    /**
     * Try to sign in an user
     *
     * @param userManager userManager
     * @return true iff the signing in is successful
     */
    public boolean signIn(UserManager userManager){
        while (true) {
            String account = input.getInputString("Please enter your email:\n");
            String password = input.getInputString("Please enter your password:\n");
            if (!userManager.validLogIn(account, password).equals("NULL")){
                ID = userManager.validLogIn(account, password);
                type = userManager.getUserType(account, password);
                return true;
            }
            return false;
        }
    }

    /**
     * Try to create an account
     *
     * @param attendeeManager attendeeManager
     * @param organizerManager organizerManager
     * @param speakerManager speakerManager
     * @param userManager userManager
     * @return true iff the account is successfully created
     */
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

    /**
     * get EXIT Status
     *
     * @return EXIT
     */
    public boolean getEXITStatus(){return EXIT;}

    /**
     * return type of the user
     *
     * @return type of the user
     */
    public String getUserType(){
        return type;
    }

    /**
     * return ID of the user
     *
     * @return ID of the user
     */
    public String getUserID(){
        return ID;
    }

}
