package controllers;

import gateway.ReadConversation;
import gateway.SaveConversation;
import use_cases.*;
import gateway.FileReadWriter;

import java.util.HashSet;
import java.util.InputMismatchException;

/**
 * This is the main controller that will manage all other controllers (parts of the conference).
 */
public class Conference {
//    private final UserManager userManager;
//    private final EventsController eventsController;
//    private final AttendeeManager attendeeManager;
//    private final OrganizerManager organizerManager;
    private UserManager userManager;
    private EventsController eventsController;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private SaveConversation saveConversation;
    private FileReadWriter fileReadWriter = new FileReadWriter();
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
//    public Conference(EventsController eventsController, AttendeeManager attendeeManager,
//                      OrganizerManager organizerManager, UserManager userManager){
//
////        this.userManager = userManager;
////        this.attendeeManager = attendeeManager;
////        this.organizerManager = organizerManager;
////        this.eventsController = eventsController;
////        this.saveConversation = new SaveConversation();//待定
////        this.viewAllAttendeeEvents = new ViewAllAttendeeEvents();
////        this.viewAllExistingEvents = new ViewAllExistingEvents();
////        this.viewAllSpeakerEvents = new ViewAllSpeakerEvents();
////        this.viewEventInfo = new ViewEventInfo();
////        this.viewFriendList = new ViewFriendList();
////        this.viewMessageList = new ViewMessageList();
////        this.viewAllEventAttendees = new ViewAllEventAttendees();
//    }

    public Conference(){
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
        fileReadWriter.reset();
        fileReadWriter.connectReaders();
        this.userManager = fileReadWriter.GetUserManager();
        this.attendeeManager = fileReadWriter.GetAttendeeManager();
        this.organizerManager = fileReadWriter.GetOrganizerManager();
        this.eventsController = fileReadWriter.GetEventsController();
        this.saveConversation = new SaveConversation();//待定
        Login newLogin = new Login();
        newLogin.run(attendeeManager, organizerManager, eventsController.getSpeakerManager(), userManager);
        fileReadWriter.connectWriters();

        //connect to Login Controller - log User in
        //new Login().run(attendeeManager, organizerManager, eventsController.getSpeakerManager(), userManager);
    }

    /**
     * Connect to one of the three types of User Controllers.
     */
    private void iteration() {
        //connect to Login Controller - log User in
        String userType = new Login().getUserType();
        String userID = new Login().getUserID();

        //load conversations of this user
        ConversationController conversationController = new ConversationController(userID);
        ReadConversation readConversation = new ReadConversation();
        readConversation.loadConversations(conversationController);
//        for (Conversation conversation: readConversation.readFile()){
//            conversationController.addConversation(conversationController.getUserIds(conversation), conversation);
//        };//load


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
        fileReadWriter.connectWriters();
    }

    private void finish() {
        System.out.print("Progress saved...");
    }

}
