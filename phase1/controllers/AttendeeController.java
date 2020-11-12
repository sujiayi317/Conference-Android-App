package controllers;

import Presenter.*;
import use_cases.AttendeeManager;

import java.util.ArrayList;

/**
 * This is the main controller for Attendee.
 */
public class AttendeeController {
    private static InputManager input;
    private static OutputManager output;
    private final AttendeeMenu attendeeMenu;

    public AttendeeController(){
        input = new InputManager();
        output = new OutputManager();
        this.attendeeMenu = new AttendeeMenu();
    }
    public void run(String userID, EventsController eventsController, ViewAllExistingEvents viewAllExistingEvents,
                    ViewAllAttendeeEvents viewAllAttendeeEvents, ViewEventInfo viewEventInfo, AttendeeManager attendeeManager) {
        //connect to Attendee Presenter - Menu options
        attendeeMenu.printAttendeeMenu(userID);
        int choice = input.getInputInt("Please choose from the following options:");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    // viewAllEvents
                    int check = 0;
                    while (check != 1) {
                        viewAllEvents(viewAllExistingEvents, eventsController);
                        String eventID = input.getInputString("Please choose an event and see the details or press enter");
                        if (!eventID.equals("")) {
                            viewOneEventInfo(eventID, viewEventInfo, eventsController);
                            String decision = input.getInputString("Yes=sign up OR No");
                            if (decision.equals("Yes")) {
                                attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                                        eventsController.getRoomManager());
                                output.printPrompt("you're successfully in"+ eventID);
                                check +=1;
                            }
                        }
                    }
                case 2:
                    // view all attendee events
                    viewAllAttendeeEvents(userID, viewAllAttendeeEvents, eventsController);
                case 3:
                    //View all my friend

                    break;
                case 4:
                    //View all my message
            }
        }
    }

    private void viewAllEvents(ViewAllExistingEvents viewAllExistingEvents, EventsController eventsController){
        output.printPrompt(viewAllExistingEvents.printAllExistingEvents(eventsController.getAllExistingEvents()));
    }

    private void viewAllAttendeeEvents(String userID, ViewAllAttendeeEvents viewAllAttendeeEvents,
                                       EventsController eventsController){
        output.printPrompt(viewAllAttendeeEvents.printAllAttendeeEvents(eventsController.getALLAttendeeEvents(userID)));
    }

    private void viewOneEventInfo(String eventID, ViewEventInfo viewEventInfo, EventsController eventsController){
        ArrayList<String> eventInfoList = eventsController.getEventInfo(eventID);
        output.printPrompt(viewEventInfo.getEventInfo(eventInfoList));
    }

}
