package controllers;

import presenters.*;
//import Presenter.ViewAllAttendeeEvents;
//import Presenter.ViewAllAvailableRoom;
//import Presenter.ViewAllExistingEvents;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import use_cases.*;

import java.util.ArrayList;

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
    private final CreateNewSpeaker createNewSpeaker;
    private final ViewAllAvailableRoom viewAllAvailableRoom;
    private final ViewFriendList viewFriendList;
    private final ViewAllAvailableSpeaker viewAllAvailableSpeaker;
    private final SeeAllFriend seeAllFriend;
    private final SeeAllMessage seeAllMessage;
    private final SendToAllAttendees sendToAllAttendees;
    public OrganizerController(){
        this.organizerMenu = new OrganizerMenu();
        input = new InputManager();
        output = new OutputManager();
        this.create = new CreateAccount();
        createNewSpeaker = new CreateNewSpeaker();
        createANewEvent = new CreateANewEvent();
        createANewRoom = new CreateANewRoom();
        seeAllFriend = new SeeAllFriend();
        seeAllMessage = new SeeAllMessage();
        sendToAllAttendees = new SendToAllAttendees();
        this.viewAllAvailableRoom = new ViewAllAvailableRoom();
        this.viewAllAvailableSpeaker = new ViewAllAvailableSpeaker();
        this.viewFriendList = new ViewFriendList();
    }


    public void run(EventsController eventsController, AttendeeManager attendeeManager,
                    OrganizerManager organizerManager, UserManager userManager,
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
                        //Create a new speaker
                        createNewSpeaker.createNewSpeaker(create, attendeeManager,organizerManager,
                                eventsController,userManager);
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
                        createANewEvent.getToCreateANewEvent(eventsController, viewAllAvailableRoom, viewAllAvailableSpeaker);
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

                }
            }

        }
    }
}
