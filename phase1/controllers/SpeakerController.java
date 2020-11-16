package controllers;

import presenters.*;
import use_cases.UserManager;

/**
 * This is the main controller for Speaker.
 */
public class SpeakerController {

    private static InputManager input;
    private static OutputManager output;
    private final SpeakerMenu speakermenu;
    private final ViewAllSpeakerEvents viewAllSpeakerEvents;
    private final ViewEventInfo viewEventInfo;
    private final ViewAllEventAttendees viewAllEventAttendees;
    private final ViewMessageList viewMessageList;
    private final SpeakerEventMenu speakerEventMenu;
    private final SeeAllSpeakerEvents seeAllSpeakerEvents;
    private final SeeAllMessage seeAllMessage;


    public SpeakerController(){
        input = new InputManager();
        output = new OutputManager();
        speakermenu = new SpeakerMenu();
        viewAllEventAttendees = new ViewAllEventAttendees();
        viewEventInfo = new ViewEventInfo();
        viewAllSpeakerEvents = new ViewAllSpeakerEvents();
        viewMessageList = new ViewMessageList();
        speakerEventMenu = new SpeakerEventMenu();
        seeAllSpeakerEvents = new SeeAllSpeakerEvents();
        seeAllMessage = new SeeAllMessage();
    }

    /**
     * This method will run when a speaker login. It will display the function menu for the user. There are 3 options
     * for speakers in total: sign out, view my events, and view my conversations.
     * @param userID the user's unique id, which is created when the user creates an account.
     * @param eventsController The controller for events.
     * @param conversationController The controller for conservation.
     * @param userManager The use case class for user.
     */
    public void run(String userID, EventsController eventsController, ConversationController conversationController,
                    UserManager userManager) {
        //connect to Speaker Presenter - Menu options
        boolean quit = false;
        while (!quit ) {
            speakermenu.printSpeakerMenu(userManager.getUserName(userID));
            int choice = input.getInputInt("Please choose from the above options:\n");
            if (0<=choice && choice<=2) {
                switch (choice) {
                    case 0:
                        quit = true;
                        output.printPrompt("You have signed out.\n");
                        break;
                    case 1:
                        //view all my events
                        seeAllSpeakerEvents.getToSeeAllSpeakerEvents(viewAllSpeakerEvents, eventsController, userID,
                                speakerEventMenu, viewEventInfo, conversationController, viewAllEventAttendees);
                        break;
                    case 2:
                        //view all my conversations
                        seeAllMessage.getToSeeAllMessage(userID, userManager, conversationController, viewMessageList);
                }
            }
        }

    }

}



