package controllers;

import presenters.*;
import use_cases.*;

/**
 * This is the main controller for Organizer.
 */
public class OrganizerController extends AttendeeController{

    private static InputManager input;
    private static OutputManager output;
    private final CreateAccount create;
    private final OrganizerMenu organizerMenu;
    private final CreateANewEvent createANewEvent;
    private final CreateANewRoom createANewRoom;
    private final ViewAllAvailableRoom viewAllAvailableRoom;
    private final ViewAllEventType viewAllEventType;
    private final ViewFriendList viewFriendList;
    private final ViewAllAvailableSpeaker viewAllAvailableSpeaker;
    private final SeeAllFriend seeAllFriend;
    private final SeeAllMessage seeAllMessage;
    private final SendToAllAttendees sendToAllAttendees;
    private final cancelEvent cancelEvent;
    private final CreateNewAccountForOrg createNewAccountForOrg;
    private final SeeSummaryStats seeSummaryStats;

    public OrganizerController(){
        this.organizerMenu = new OrganizerMenu();
        input = new InputManager();
        output = new OutputManager();
        this.create = new CreateAccount();
        createANewEvent = new CreateANewEvent();
        createANewRoom = new CreateANewRoom();
        seeAllFriend = new SeeAllFriend();
        seeAllMessage = new SeeAllMessage();
        sendToAllAttendees = new SendToAllAttendees();
        cancelEvent = new cancelEvent();
        createNewAccountForOrg = new CreateNewAccountForOrg();
        seeSummaryStats = new SeeSummaryStats();
        this.viewAllAvailableRoom = new ViewAllAvailableRoom();
        this.viewAllAvailableSpeaker = new ViewAllAvailableSpeaker();
        this.viewFriendList = new ViewFriendList();
        this.viewAllEventType = new ViewAllEventType();

    }

    /**
     * This method will run when an organizer login. It will display the function menu for the user to choose. there are
     * in total 9 choices for organizer: sign out, create a new speaker, view all existing events, view all attended events,
     * , add a new event, view all the user's friends, view all the message the user has, send to all attendees of an event/events,
     * and add a new friend.
     * @param userID The user's unique id, which is created when the user creates an account.
     * @param eventsController The controller for events.
     * @param attendeeManager The use case class for attendee, which contains methods like login and sign up.
     * @param organizerManager The use case class for controller.
     * @param conversationController The controller for conversation.
     * @param userManager The use case class for user.
     */
    public void run(EventsController eventsController, AttendeeManager attendeeManager,
                    OrganizerManager organizerManager, VIPUserManager vipUserManager, UserManager userManager,
                    ConversationController conversationController, String userID) {
        //connect to Attendee Presenter - Menu options
        boolean quit = false;
        while (!quit) {
            organizerMenu.printOrganizerMenu(userManager.getUserName(userID));
            int choice = input.getInputInt("Please choose from the above options:\n");
            if (0<=choice && choice<=9) {
                switch (choice) {
                    case 0:
                        quit = true;
                        output.printPrompt("You have signed out.\n");
                        break;
                    case 1:
                        //Create a new account
                        createNewAccountForOrg.toCreateNewAccountForOrg(create, attendeeManager,
                                organizerManager, eventsController, vipUserManager, userManager);
                        break;
                    case 2:
                        // View all events
                        seeALLExistingEvents.getToSeeAllExistingEvents(eventsController, attendeeManager,
                                viewAllExistingEvents, userID, viewEventInfo);
                        break;

                    case 3:
                        //View all my events
                        seeALLMyEvents.getToSeeAllMyEvents(viewAllAttendeeEvents, eventsController, userID);
                        break;

                    case 4:
                        //Add a new Event
                        createANewEvent.getToCreateANewEvent(eventsController, viewAllAvailableRoom, viewAllAvailableSpeaker, viewAllEventType);
                        break;

                    case 5:
                        //View all my fiends
                        seeAllFriend.getToSeeAllFriend(userID,viewFriendList,attendeeManager,userManager,conversationController);
                        break;
                    case 6:
                        seeAllMessage.getToSeeAllMessage(userID, userManager, conversationController, viewMessageList);
                        // view all my message
                        break;

                    case 7:
                        //send to all attendees of an event/events
                        sendToAllAttendees.toSendToAllAttendees(eventsController, conversationController);
                        break;

                    case 8:
                        //Create a new room
                        createANewRoom.getToCreateANewRoom(eventsController);
                        break;
                    case 9:
                        //add friend
                        addFriend.toAddFriend(userManager, userID);
                        break;
                    case 10:
                        //cancel event
                        cancelEvent.toCancelEvent(eventsController, viewAllExistingEvents);
                    case 11:
                        //See all User Stat
                        seeSummaryStats.getToSeeUserStat(eventsController, attendeeManager, organizerManager, vipUserManager);
                    case 12:
                        //See all EventEnrollmentStatistics
                        seeSummaryStats.getToSeeEventEnrollmentStatistics(eventsController);
                    case 13:
                        // getTop5EventInfo
                        seeSummaryStats.getTop5EventInfo(eventsController);




                }
            }

        }
    }
}
