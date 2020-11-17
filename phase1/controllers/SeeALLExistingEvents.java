package controllers;

import presenters.ViewAllExistingEvents;
import presenters.ViewEventInfo;
import use_cases.AttendeeManager;

import java.util.ArrayList;

/**
 * This is the class for viewing all friends
 */
public class SeeALLExistingEvents {
    private static OutputManager output;
    private static InputManager input;

    public SeeALLExistingEvents() {
        output = new OutputManager();
        input = new InputManager();
    }

    /**
     * This class can display all existing events and let the user to choose, view information of a chosen event.
     * @param eventsController eventsController
     * @param attendeeManager attendeeManager
     * @param viewAllExistingEvents viewAllExistingEvents
     * @param userID userID
     * @param viewEventInfo viewEventInfo
     */
    public void getToSeeAllExistingEvents(EventsController eventsController, AttendeeManager attendeeManager,
                                          ViewAllExistingEvents viewAllExistingEvents, String userID, ViewEventInfo viewEventInfo) {
        if (eventsController.getAllExistingEvents().size() == 0) {
            output.printPrompt("There is no event yet!\nPlease go back to main menu \nto create one if you're an Organizer");
        }
        int check = 0;
        while (check != 1 && eventsController.getAllExistingEvents().size() != 0) {
            viewAllEvents(viewAllExistingEvents, eventsController);
            String eventNum = Integer.toString(input.getInputInt("Please enter a number to see the details, \n" +
                    "(e.g, enter 0 to check details of first event)\nOr, press enter to" +
                    " go back to main menu\n"));
            if ((!eventNum.equals("666")) && eventsController.getAllExistingEvents().size() > Integer.parseInt(eventNum)
                    && Integer.parseInt(eventNum) >= 0) {
                String eventTitle = eventsController.getAllExistingEvents().get(Integer.parseInt(eventNum)).getTitle();
                if (!eventTitle.equals("")) {
                    String eventID = eventsController.getEventManager().changeEventTitleIntoEventID(eventTitle);
                    viewOneEventInfo(eventID, viewEventInfo, eventsController);
                    String decision = input.getInputString("Type Yes to sign up OR No to back to menu\n");
                    if (decision.equals("Yes")) {
                        if (attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                                eventsController.getRoomManager())) {
                            output.printPrompt("You're successfully in " + eventTitle + "\n");
                            check += 1;
                        } else if (eventsController.getRoomManager().isFull(eventsController.getEventManager().getEventFromID(eventID).getRoomID())) {
                            output.printPrompt("Sorry, the current room is full\n");
                        } else {
                            output.printPrompt("You're already in " + eventTitle + "\n");
                        }
                    }
                }
            } else if ((!eventNum.equals("666") && (Integer.parseInt(eventNum) >= eventsController.getAllExistingEvents().size() || Integer.parseInt(eventNum) < 0))) {
                output.printPrompt("The Event Number entered is out of bound\n please enter the correct one\n");
            } else {
                check += 1;
            }
        }
    }

    /**
     * viewAllEvents
     *
     * @param viewAllExistingEvents viewAllExistingEvents
     * @param eventsController eventsController
     */
    public static void viewAllEvents(ViewAllExistingEvents viewAllExistingEvents, EventsController eventsController) {
        output.printPrompt(viewAllExistingEvents.printAllExistingEvents(eventsController.getAllExistingEvents()));
    }

    /**
     * view information of One Event
     *
     * @param eventID eventID
     * @param viewEventInfo viewEventInfo
     * @param eventsController eventsController
     */
    public static void viewOneEventInfo(String eventID, ViewEventInfo viewEventInfo, EventsController eventsController) {
        ArrayList<String> eventInfoList = eventsController.getEventInfo(eventID);
        output.printPrompt(viewEventInfo.getEventInfo(eventInfoList));
    }
}
