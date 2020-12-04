package controllers;

import entities.Attendee;
import use_cases.AttendeeManager;
import use_cases.OrganizerManager;
import use_cases.SpeakerManager;
import use_cases.VIPUserManager;
import use_cases.UserManager;

public class CreateAccount {
    private static InputManager input = new InputManager();

    /**
     * The method of creating an new account.
     *
     * @param attendeeManager  attendee manager class
     * @param organizerManager organizer manager class
     * @param speakerManager   speaker manager class
     * @param vipUserManager   vipUser manager class
     * @param userManager      user manager class
     * @param type             the type of account the user wants to create.
     * @return true iff the account is successfully created.
     */
    public boolean CreateNewAccount(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                    SpeakerManager speakerManager, VIPUserManager vipUserManager,
                                    UserManager userManager, String type) {
        String email = input.getInputString("Please enter the email for new account: (ex. 12345@abc.com), or " +
                "enter 'cancel' at any point to exit account creation\n");
        if (email.contains(" ")) {
            return false;
        }
        while (true) {
            if (email.equals("cancel")) {
                return false;
            } else if (isValidEmail(email, userManager)) {
                break;
            } else {
                email = input.getInputString("Please enter another one, or enter 'cancel' at any point to exit" +
                        " account creation\n");
            }
        }

        String user = input.getInputString("Please enter a user name for new account: (must have length of at " +
                "least 2 and does NOT contain space), or enter 'cancel' at any point to exit account creation\n");
        if (user.contains(" ")){
            return false;
        }
        while (true) {
            if (user.equals("cancel")) {
                return false;
            } else if (isValidUserName(user, userManager)) {
                break;
            } else {
                user = input.getInputString("Invalid User name or user name already used, please enter another " +
                        "one, or enter 'cancel' at any point to exit account creation\n");
            }
        }
        String password = input.getInputString("Please enter a password for " + user + ":\n");
        if (password.contains(" ")){
            return false;
        }
        while (true) {
            if (password.equals("cancel")) {
                return false;
            } else if (password.length() >= 8) {
                break;
            } else {
                password = input.getInputString("Password must be at least length 8, please try again, or enter " +
                        "'cancel' at any point to exit account creation\n");
            }
        }
        if (type.equals("SPEAKER")) {
            speakerManager.createSpeaker(email, user, password);
        } else if (type.equals("ORGANIZER")) {
            organizerManager.createOrganizer(email, user, password);
        } else if (type.equals("VIPUser")){
            vipUserManager.createVIPUser(email, user, password);
        } else {
            attendeeManager.createAttendee(email, user, password);
        }
        return true;
    }

    /**
     * The method to check if a given email is in valid form, or already existed in the system.
     *
     * @param email       The given email.
     * @param userManager user manager class.
     * @return true iff the email is valid.
     */
    public boolean isValidEmail(String email, UserManager userManager) {
        if (!userManager.validNewEmail(email)) {
            new OutputManager().printPrompt("Email already exists in the system...\n");
            return false;
        } else if (email.length() >= 6 && email.contains("@") && email.charAt(0) != '@' && email.contains(".") &&
                email.charAt(email.length() - 1) != '.' && email.length() - email.replace(".", "").length() == 1 &&
                email.length() - email.replace("@", "").length() == 1 && email.indexOf('@') < email.indexOf('.') &&
                email.indexOf('@') != email.indexOf('.') - 1 && userManager.validNewEmail(email)) {
            return true;
        } else {
            new OutputManager().printPrompt("Invalid Email format...\n");
            return false;
        }
    }

    /**
     * The check if the user name is valid.
     *
     * @param user        the user name.
     * @param userManager user manager class.
     * @return true iff the name is valid.
     */
    public boolean isValidUserName(String user, UserManager userManager) {
        return user.length() >= 2 && userManager.validNewName(user);
    }


}