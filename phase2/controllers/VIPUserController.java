package controllers;

import presenters.*;
import use_cases.VIPUserManager;
import use_cases.UserManager;

public class VIPUserController {
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
    private final SeeALLVIPEvents seeALLVIPEvents;
    private final ViewAllVIPEvents viewAllVIPEvents;
    /**
     * The constructor of the attendee controller.
     */
    public VIPUserController(){
        input = new InputManager();
        output = new OutputManager();
        seeALLExistingEvents = new SeeALLExistingEvents();
        seeALLMyEvents = new SeeALLMyEvents();
        seeAllFriend = new SeeAllFriend();
        seeAllMessage = new SeeAllMessage();
        seeALLVIPEvents = new SeeALLVIPEvents();
        addFriend = new AddFriend();
        this.attendeeMenu = new AttendeeMenu();
        this.viewAllAttendeeEvents = new ViewAllAttendeeEvents();
        this.viewAllExistingEvents = new ViewAllExistingEvents();
        this.viewEventInfo = new ViewEventInfo();
        this.viewFriendList = new ViewFriendList();
        this.viewMessageList = new ViewMessageList();
        this.viewAllVIPEvents = new ViewAllVIPEvents();

    }

    /**
     * This method will run when an attendee login. It will display the function menu for the user to choose. there are
     * in total 7 choices for VIPUsers: sign out, view all existing events, view all attended events,
     * view all the user's friends, view all the message the user has, view all vip only events.
     * @param userID The user's unique id, which is created when the user creates an account.
     * @param eventsController The controller for events.
     * @param vipUserManager The use case class for attendee, which contains methods like login and sign up.
     * @param conversationController The controller for conversation.
     * @param userManager The use case class for user.
     */
    public void run(String userID, EventsController eventsController,
                    VIPUserManager vipUserManager,
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
                        seeALLExistingEvents.getToSeeAllExistingEvents(eventsController, vipUserManager,
                                viewAllExistingEvents, userID, viewEventInfo);
                        break;
                    case 2:
                        // view all attendee events
                        seeALLMyEvents.getToSeeAllMyEvents(viewAllAttendeeEvents, eventsController, userID);
                        break;
                    case 3:
                        //View all my fiends
                        seeAllFriend.getToSeeAllFriend(userID, viewFriendList, vipUserManager, userManager,
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
                    case 6:
                        //view VIP-only events
                        //todo: modify the presenter to allow for viewing event info and signup
                        seeALLVIPEvents.getToSeeAllVIPEvents(viewAllVIPEvents, eventsController);
                }
            }
        }
    }
}
