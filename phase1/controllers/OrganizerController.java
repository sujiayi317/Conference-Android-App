package controllers;

/**
 * This is the main controller for Organizer.
 */
public class OrganizerController {

    private static InputManager input = new InputManager();

    public void run() {
        //connect to Attendee Presenter - Menu options

        int choice = input.getInputInt("Please choose from the following options:");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    //connect to CreateSpeaker Controller
                    break;
                case 2:
                    //connect to EnterRoom Controller
                    break;
                case 3:
                    //connect to Schedule Controller
                    break;
                case 4:
                    //connect to Contacts Controller
            }
        }
    }
}
