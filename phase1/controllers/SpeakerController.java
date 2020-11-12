package controllers;

/**
 * This is the main controller for Speaker.
 */
public class SpeakerController {

    private static InputManager input = new InputManager();

    public void run() {
        //connect to Attendee Presenter - Menu options

        int choice = input.getInputInt("Please choose from the following options:");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    //connect to MyEvent Controller
                    break;
                case 2:
                    //connect to Contacts Controller
                    break;
                case 3:
                    //connect to Announcements Controller
            }
        }
    }
}
