package unused.controllers;

import unused.presenters.*;
import use_cases.EventManager;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * This is the class for viewing all speaker events
 */
public class SeeAllSpeakerEvents {
    private static OutputManager output;
    private static InputManager input;

    public SeeAllSpeakerEvents() {
        output = new OutputManager();
        input = new InputManager();
    }

    /**
     * Displays all the events of the speaker and actions for the events.
     *
     * @param viewAllSpeakerEvents viewAllSpeakerEvents
     * @param eventsController eventsController
     * @param userID userID
     * @param speakerEventMenu speakerEventMenu
     * @param viewEventInfo viewEventInfo
     * @param conversationController conversationController
     * @param viewAllEventAttendees viewAllEventAttendees
     * @param userManager userManager
     */
    public void getToSeeAllSpeakerEvents(ViewAllSpeakerEvents viewAllSpeakerEvents, EventsController eventsController,
                                         String userID, SpeakerEventMenu speakerEventMenu,
                                         ViewEventInfo viewEventInfo, ConversationController conversationController,
                                         ViewAllEventAttendees viewAllEventAttendees, UserManager userManager) {
        int check = 0;
        while (check != 1) {
            viewAllSpeakerEvents(viewAllSpeakerEvents, eventsController, userID);
            int eventNum = input.getInputInt("Choose an event to view by entering the number of " +
                    "the event OR press enter to go back to the main menu\n");
            if ((eventNum != 666) && (eventsController.getAllEventsForTheSpeaker(userID).size() > eventNum)
                    && eventNum >= 0) {
                String eventID = eventsController.getAllEventsForTheSpeaker(userID).get(eventNum);
                viewSpeakerEventMenu(speakerEventMenu, eventID, viewEventInfo, eventsController);
                int eventAction = input.getInputInt("Please select one of the above actions:\n");
                if (0 <= eventAction && eventAction <= 2) {
                    if (eventAction == 0) {
                        check += 1;
                    } else if (eventAction == 1) {
                        ArrayList<String> receivers = new ArrayList<>(
                                eventsController.getAttendeesFromEvent(eventID));
                        String message = input.getInputString("Please enter the message of " +
                                "the announcement OR type Cancel to go back to events menu:\n");
                        if (message.equals("Cancel") || message.equals("cancel")) {
                            check += 1;
                        }
                        conversationController.sendToMultipleUsers(message, receivers);
                    } else {
                        viewAllEventAttendees(eventID, viewAllEventAttendees, eventsController, userManager);
                        int chooseAttendee = input.getInputInt("Choose an attendee to message OR press Enter to" +
                                " go back to events menu:\n");
                        EventManager eventManager = eventsController.getEventManager();
                        ArrayList<String> attendeeList = eventManager.getAttendeesFromEvent(eventID);
                        if (0 <= chooseAttendee && chooseAttendee <= attendeeList.size()) {
                            String attendeeId = attendeeList.get(chooseAttendee);
                            conversationController.enterConversation(attendeeId);
                        }
                    }
                }
            }
            check += 1;
        }
    }

    /**
     * View all the events of the speaker.
     *
     * @param viewAllSpeakerEvents viewAllSpeakerEvents
     * @param eventsController eventsController
     * @param userID userID
     */
    public static void viewAllSpeakerEvents(ViewAllSpeakerEvents viewAllSpeakerEvents, EventsController eventsController,
                                            String userID) {
        output.printPrompt(viewAllSpeakerEvents.printAllSpeakerEvents(
                eventsController.getAllEventsForTheSpeaker(userID), eventsController));
    }

    /**
     * View the information of an event of the speaker and the options for messaging the event attendees.
     *
     * @param speakerEventMenu speakerEventMenu
     * @param eventID eventID
     * @param viewEventInfo viewEventInfo
     * @param eventsController eventsController
     */
    public static void viewSpeakerEventMenu(SpeakerEventMenu speakerEventMenu, String eventID,
                                            ViewEventInfo viewEventInfo, EventsController eventsController) {
        output.printPrompt(speakerEventMenu.printEventMenu(eventID, viewEventInfo, eventsController));
    }

    /**
     * View all the attendees of an event.
     *
     * @param eventID eventID
     * @param viewAllEventAttendees viewAllEventAttendees
     * @param eventsController eventsController
     * @param userManager userManager
     */
    public static void viewAllEventAttendees(String eventID, ViewAllEventAttendees viewAllEventAttendees,
                                             EventsController eventsController, UserManager userManager) {
        ArrayList<String> Names = new ArrayList<>();
        for (String userID : eventsController.getAttendeesFromEvent(eventID)){
            Names.add(userManager.getUserNameFromID(userID));
        }
        output.printPrompt(viewAllEventAttendees.printAllEventAttendees(Names));
    }
}
