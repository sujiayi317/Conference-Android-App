package controllers;

import Presenter.ViewAllAttendeeEvents;
import Presenter.ViewAllAvailableRoom;
import Presenter.ViewAllExistingEvents;
import com.sun.org.apache.xpath.internal.operations.Bool;
import entities.Organizer;
import entities.Speaker;
import use_cases.*;

/**
 * This is the main controller for Organizer.
 */
public class OrganizerController {

    private static InputManager input = new InputManager();
    private static OutputManager output = new OutputManager();
    private CreateAccount create = new CreateAccount();

    public void run(EventsController eventsController, ViewAllExistingEvents viewAllExistingEvents, ViewAllAvailableRoom viewAllAvailableRoom , ViewAllAttendeeEvents viewAllAttendeeEvents, AttendeeManager attendeeManager,
                    OrganizerManager organizerManager, SpeakerManager speakerManager, String userID) {
        //connect to Attendee Presenter - Menu options

        int choice = input.getInputInt("Please choose from the following options:");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    //connect to CreateAccount Controller to create enw speaker
                    if (create.CreateNewAccount(attendeeManager, organizerManager, speakerManager, "Speaker")){
                        output.printPrompt("New speaker account successfully created.");
                    } else {
                        output.printPrompt("Create speaker action cancelled.");
                    }
                    break;
                case 2:
                    //get all available room info
                    int time = input.getInputInt("Please enter your event time");
                    viewAllAvailableRoom.printAllAvailableRoom(eventsController.getAvailableRoom(time));
                case 3:
                    //create a new event
                    //String title, String roomID, Speaker speaker, int startTime
                    String title = input.getInputString("Please enter your event's title");
                    String roomID = input.getInputString("Please enter your room ID");
                    String speaker = input.getInputString("Please set your speaker");
                    int startTime = input.getInputInt("Please enter your event time");
                    output.printPrompt(eventsController.createEvent(title,roomID,speaker,startTime));
                case 4:
                    // view all events
                    viewAllExistingEvents.printAllExistingEvents(eventsController.getAllExistingEvents());
                case 5:
                    // view all attended events
                    viewAllAttendeeEvents.printAllAttendeeEvents(eventsController.getALLAttendeeEvents(userID));

            }
        }
    }
}
