package controllers;

/**
 * This is the main controller for Attendee.
 */
public class AttendeeController {
    private static InputManager input = new InputManager();

    public void run(EventsController eventsController) {
        //connect to Attendee Presenter - Menu options

        int choice = input.getInputInt("Please choose from the following options:");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    eventsController.getAllExistingEvents();
                    break;
                case 2:
                    //connect to MyEvent Controller
                    break;
                case 3:
                    //connect to Contacts Controller
                    break;
                case 4:
                    //connect to Announcements Controller
            }
        }
    }

}
