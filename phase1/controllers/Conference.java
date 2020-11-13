package controllers;

import Message.Conversation;
import Message.ConversationController;
import Message.ConversationManager;
import Presenter.*;
import entities.Attendee;
import entities.Organizer;
import entities.User;
import use_cases.*;

import java.io.FileReader;
import java.util.InputMismatchException;

/**
 * This is the main controller that will manage all other controllers (parts of the conference).
 */
public class Conference {
    private final UserManager userManager;
    private final EventsController eventsController;
    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private final ViewAllAvailableRoom viewAllAvailableRoom;
    private final ViewAllAttendeeEvents viewAllAttendeeEvents;
    private final ViewAllExistingEvents viewAllExistingEvents;
    private final ViewEventInfo viewEventInfo;
    private final ViewFriendList viewFriendList;
    private final ViewAllAvailableSpeaker viewAllAvailableSpeaker;
    private ViewMessagesOfAConversation viewMessagesOfAConversation;
    private ConversationController conversationController;
    /**
     * This is where our conference system starts.
     */
    public Conference(EventsController eventsController, AttendeeManager attendeeManager,
                      OrganizerManager organizerManager, UserManager userManager){

        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.viewAllAvailableRoom = new ViewAllAvailableRoom();
        this.viewAllAttendeeEvents = new ViewAllAttendeeEvents();
        this.viewAllExistingEvents = new ViewAllExistingEvents();
        this.viewEventInfo = new ViewEventInfo();
        this.eventsController = eventsController;
        this.userManager = userManager;
        this.viewFriendList = new ViewFriendList();
        this.viewAllAvailableSpeaker = new ViewAllAvailableSpeaker();
        this.viewMessagesOfAConversation = new ViewMessagesOfAConversation();
    }
    public void run(){
        try {
            //Initialize all managers, NO FILE FOUND AT THIS POINT, CREATE NEW MANAGERS
            conferenceSystem();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main program flow-of-control.
     */
    private void conferenceSystem() {
        start();

        iteration();

        finish();
    }

    /**
     * Any part of the login system that should be set up before an User logs into the account.
     */
    private void start() {
        //connect to Gateway: set up database
        //connect to Login Controller - log User in
        new Login().run(attendeeManager, organizerManager, eventsController.getSpeakerManager(), userManager);


    }

    /**
     * Connect to one of the three types of User Controllers.
     */
    private void iteration() {
        String userType = new Login().getUserType();
        String userID = new Login().getUserID();
        this.conversationController = new ConversationController(userID);

        switch(userType) {
            case "ATTENDEE":
                new AttendeeController().run(userID, eventsController, viewAllExistingEvents, viewAllAttendeeEvents,
                        viewEventInfo, attendeeManager, viewFriendList, conversationController,
                        viewMessagesOfAConversation);
                break;
            case "SPEAKER":
                new SpeakerController().run();
                break;
            case "ORGANIZER":
                new OrganizerController().run(eventsController, viewAllExistingEvents, viewAllAvailableRoom,
                        viewAllAttendeeEvents, attendeeManager,viewEventInfo, viewAllAvailableSpeaker,
                        organizerManager, userManager, userID);
        }
    }

    private void finish() {
        System.out.print("You have successfully signed out.");
    }

}
