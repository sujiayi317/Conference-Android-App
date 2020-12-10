package unused.controllers;

import unused.presenters.*;
import use_cases.AttendeeManager;
import use_cases.UserManager;

/**
 * This is the main controller for Attendee.
 */
public class AttendeeController {
    private static InputManager input;
    private static OutputManager output;
    public final SeeALLExistingEvents seeALLExistingEvents;
    public final SeeALLMyEvents seeALLMyEvents;
    private final AttendeeMenu attendeeMenu;
    public final ViewAllAttendeeEvents viewAllAttendeeEvents;
    public final ViewAllExistingEvents viewAllExistingEvents;
    public final ViewMessageList viewMessageList;
    public final ViewEventInfo viewEventInfo;
    public final AddFriend addFriend;
    private final ViewFriendList viewFriendList;
    private final SeeAllFriend seeAllFriend;
    private final SeeAllMessage seeAllMessage;

    /**
     * The constructor of the attendee controller.
     */
    public AttendeeController(){
        input = new InputManager();
        output = new OutputManager();
        seeALLExistingEvents = new SeeALLExistingEvents();
        seeALLMyEvents = new SeeALLMyEvents();
        seeAllFriend = new SeeAllFriend();
        seeAllMessage = new SeeAllMessage();
        addFriend = new AddFriend();
        this.attendeeMenu = new AttendeeMenu();
        this.viewAllAttendeeEvents = new ViewAllAttendeeEvents();
        this.viewAllExistingEvents = new ViewAllExistingEvents();
        this.viewEventInfo = new ViewEventInfo();
        this.viewFriendList = new ViewFriendList();
        this.viewMessageList = new ViewMessageList();
    }

    /**
     * This method will run when an attendee login. It will display the function menu for the user to choose. there are
     * in total 6 choices for attendees: sign out, view all existing events, view all attended events, view all the user's
     * friends, view all the message the user has.
     * @param userID The user's unique id, which is created when the user creates an account.
     * @param eventsController The controller for events.
     * @param attendeeManager The use case class for attendee, which contains methods like login and sign up.
     * @param conversationController The controller for conversation.
     * @param userManager The use case class for user.
     */
    public void run(String userID, EventsController eventsController,
                    AttendeeManager attendeeManager,
                    ConversationController conversationController, UserManager userManager) {
        //connect to Attendee Presenter - Menu options
        boolean quit = false;
        while (! quit ) {
            attendeeMenu.printAttendeeMenu(userManager.getUserName(userID));
            int choice = input.getInputInt("Please choose from the above options:\n");
            if (0<=choice && choice<=5) {
                switch (choice) {
                    case 0:
                        quit = true;
                        output.printPrompt("You have signed out.\n");
                        break;
                    case 1:
                        //view all existing events
                        seeALLExistingEvents.getToSeeAllExistingEvents(eventsController, attendeeManager,
                                viewAllExistingEvents, userID, viewEventInfo);
                        break;
                    case 2:
                        // view all attendee events
                        seeALLMyEvents.getToSeeAllMyEvents(viewAllAttendeeEvents, eventsController, userID);
                        break;
                    case 3:
                        //View all my fiends
                        seeAllFriend.getToSeeAllFriend(userID, viewFriendList, attendeeManager, userManager,
                                conversationController);
                        break;
                    case 4:
                        //view all my message
                        seeAllMessage.getToSeeAllMessage(userID, userManager, conversationController, viewMessageList);
                        break;
                    case 5:
                        //add friend
                        addFriend.toAddFriend(userManager, userID);
                        break;
                }
            }
        }
    }

}
