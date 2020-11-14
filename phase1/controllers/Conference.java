package controllers;

import Message.Conversation;
import Message.ConversationController;
import Message.ConversationManager;
import Message.SaveConversation;
import Presenter.*;
import entities.Attendee;
import entities.Organizer;
import entities.User;
import use_cases.*;

import java.io.FileReader;
import java.util.HashSet;
import java.util.InputMismatchException;

/**
 * This is the main controller that will manage all other controllers (parts of the conference).
 */
public class Conference {
    private final UserManager userManager;
    private final EventsController eventsController;
    private final AttendeeManager attendeeManager;
    private final OrganizerManager organizerManager;
    private SaveConversation saveConversation;
//    private final ViewAllAttendeeEvents viewAllAttendeeEvents;
//    private final ViewAllExistingEvents viewAllExistingEvents;
//    private final ViewAllSpeakerEvents viewAllSpeakerEvents;
//    private final ViewEventInfo viewEventInfo;
//    private final ViewFriendList viewFriendList;
//    private final ViewMessageList viewMessageList;
//    private final ViewAllEventAttendees viewAllEventAttendees;

    /**
     * This is where our conference system starts.
     */
    public Conference(EventsController eventsController, AttendeeManager attendeeManager,
                      OrganizerManager organizerManager, UserManager userManager){

        this.userManager = userManager;
        this.attendeeManager = attendeeManager;
        this.organizerManager = organizerManager;
        this.eventsController = eventsController;
        this.saveConversation = new SaveConversation();//待定
//        this.viewAllAttendeeEvents = new ViewAllAttendeeEvents();
//        this.viewAllExistingEvents = new ViewAllExistingEvents();
//        this.viewAllSpeakerEvents = new ViewAllSpeakerEvents();
//        this.viewEventInfo = new ViewEventInfo();
//        this.viewFriendList = new ViewFriendList();
//        this.viewMessageList = new ViewMessageList();
//        this.viewAllEventAttendees = new ViewAllEventAttendees();
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
        while (!new Login().getEXITStatus()) {
            start();
            if (!(new Login().getEXITStatus())){
            iteration();
            }
        }

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
        ConversationController conversationController = new ConversationController(userID);

        switch(userType) {
            case "ATTENDEE":
                new AttendeeController().run(userID, eventsController, attendeeManager,
                        conversationController, userManager);
                break;
            case "SPEAKER":
                new SpeakerController().run(userID, eventsController,
                        conversationController, userManager);
                break;
            case "ORGANIZER":
                new OrganizerController().run(eventsController, attendeeManager,
                        organizerManager, userManager, conversationController, userID);
        }

        for(HashSet<String> key: conversationController.conversationsGetter().keySet()){
            saveConversation.save(conversationController.conversationsGetter().get(key));
        }//save
    }

    private void finish() {
        System.out.print("Program exited.");
    }

}
