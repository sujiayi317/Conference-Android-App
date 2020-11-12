package controllers;

import entities.Attendee;
import use_cases.AttendeeManager;
import use_cases.OrganizerManager;
import use_cases.SpeakerManager;

public class CreateAccount {
    private static InputManager input = new InputManager();
    private static OutputManager output = new OutputManager();

    public void CreateNewAccount(AttendeeManager attendeeManager, OrganizerManager organizerManager,
                              SpeakerManager speakerManager) {
        String email = input.getInputString("Please enter the email for new speaker: (ex. 12345@abc.com)");
        while (true) {
            if (isValidEmail(email, attendeeManager, organizerManager, speakerManager)) {
                break;
            } else {
                email = input.getInputString("Invalid email, please enter another one:");
            }
        }

        String user = input.getInputString("Please enter the user name for new speaker: (must have length of at least 2)");
        while (true) {
            if (isValidUserName(user, attendeeManager, organizerManager, speakerManager)) {
                break;
            } else {
                user = input.getInputString("User name already used, please enter another username:");
            }
        }
        String password = input.getInputString("Please enter a password for " + user + ":");
        while (true) {
            if (password.length() >= 8) {
                break;
            } else {
                output.printPrompt("Password must be at least length 8, please try again:");
            }
        }
        speakerManager.createSpeaker(user, "sample_email", password);
        output.printPrompt("New speaker account successfully created.");
    }

    public boolean isValidEmail(String email, AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                SpeakerManager speakerManager){
        return email.length() >= 6 && email.contains("@") && email.charAt(0) != '@' && email.contains(".") &&
                email.charAt(email.length() - 1) != '.' && email.length() - email.replace(".", "").length() == 1 &&
                email.length() - email.replace("@", "").length() == 1 && email.indexOf('@') < email.indexOf('.') &&
                email.indexOf('@') != email.indexOf('.') - 1 && attendeeManager.validNewAttendeeEmail(email) &&
                organizerManager.validNewOrganizerEmail(email) && speakerManager.validNewSpeakerName(email);
    }

    public boolean isValidUserName(String user, AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                   SpeakerManager speakerManager){
        return user.length() >= 2 && speakerManager.validNewSpeakerEmail(user) &&
                organizerManager.validNewOrganizerName(user) && attendeeManager.validNewAttendeeName(user);
    }


}
