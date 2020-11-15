package controllers;

import Message.ConversationController;
import Presenter.*;
import use_cases.EventManager;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * This is the main controller for Speaker.
 */
public class SpeakerController {

    private static InputManager input;
    private static OutputManager output;
    private final SpeakerMenu speakermenu;
    private final ViewAllSpeakerEvents viewAllSpeakerEvents;
    private final ViewEventInfo viewEventInfo;
    private final ViewAllAttendeeEvents viewAllAttendeeEvents;
    private final ViewAllEventAttendees viewAllEventAttendees;
    private final SpeakerEventMenu speakerEventMenu;
    //private final SeeSpeakerEventInfo seeSpeakerEventInfo;
    private final SeeAllSpeakerEvents seeAllSpeakerEvents;



    public SpeakerController(){
        input = new InputManager();
        output = new OutputManager();
        speakermenu = new SpeakerMenu();
        viewAllEventAttendees = new ViewAllEventAttendees();
        viewAllAttendeeEvents = new ViewAllAttendeeEvents();
        viewEventInfo = new ViewEventInfo();
        viewAllSpeakerEvents = new ViewAllSpeakerEvents();
        speakerEventMenu = new SpeakerEventMenu();
        //seeSpeakerEventInfo = new SeeSpeakerEventInfo();
        seeAllSpeakerEvents = new SeeAllSpeakerEvents();
    }
    public void run(String userID, EventsController eventsController, ConversationController conversationController,
                    UserManager userManager) {
        //connect to Speaker Presenter - Menu options
        boolean quit = false;
        while (!quit ) {
            speakermenu.printSpeakerMenu(userManager.getUserName(userID));
            int choice = input.getInputInt("Please choose from the above options:\n");
            if (0<=choice && choice<=1) {
                switch (choice) {
                    case 0:
                        quit = true;
                        output.printPrompt("You have signed out.\n");
                        break;
                    case 1:
                        //connect to MyEvent Controller
                        seeAllSpeakerEvents.getToSeeAllSpeakerEvents(viewAllSpeakerEvents, eventsController, userID,
                                speakerEventMenu, viewEventInfo, conversationController, viewAllEventAttendees);
                        //EventManager eventManager = eventsController.getEventManager();
                        //ArrayList<String> eventList = eventManager.getAllEventForTheSpeaker(userID);
                        //int chooseEvent = input.getInputInt("Choose an event to view:\n");
                        //if (0<= chooseEvent && chooseEvent<=eventList.size()){
                            //String eventId = eventList.get(chooseEvent);
                            //display event info
                            //seeSpeakerEventInfo.getToSeeEventInfo(speakerEventMenu, viewEventInfo, eventsController,
                                    //eventId);
                            //options: make an announcement for the event, message individual attendee
                            //int eventAction = input.getInputInt("Please select one of the above actions:\n");
                            //if (0<=eventAction && eventAction <=2) {
                              //  switch (eventAction) {
                                //    case 0:
                                  //      quit = true;
                                    //    output.printPrompt("You have signed out.\n");
                                      //  break;
                                    //case 1:
                                      //  ArrayList<String> receivers = new ArrayList<>(
                                        //        eventsController.getAttendeesFromEvent(eventId));
                                        //String message = input.getInputString("Please enter the message of " +
                                          //      "the announcement:\n");
                                        //conversationController.sendToMultipleUsers(message, receivers);
                                    //case 2:
                                        //message individual attendee: display list of attendees of the event:
                                        //viewAllEventAttendees(eventId, viewAllAttendeeEvents, eventsController);
                                        //choose an attendee to message
                                        //int chooseAttendee = input.getInputInt("Choose an attendee to message:\n");
                                        //ArrayList<String> attendeeList = eventManager.getAttendeesFromEvent(eventId);
                                        //if (0 <= chooseAttendee && chooseAttendee <= attendeeList.size()) {
                                          //  String attendeeId = attendeeList.get(chooseAttendee);
                                            //conversationController.enterConversation(attendeeId);
                                        //}
                                    //}
                                //}
                            //}
                        break;
                    case 2:
                        //view all my conversations
                        // if no convo then return "you do not have any conversations yet."
                        // otherwise display the convo (use viewmsglist then viewmsgofaconvo and seeallmsg)
                }
            }
        }

    }

}

    //public static void viewAllSpeakerEvents(String userID, ViewAllSpeakerEvents viewAllSpeakerEvents,
      //                                      EventsController eventsController){
        //output.printPrompt(viewAllSpeakerEvents.printAllSpeakerEvents(
          //      eventsController.getAllEventsForTheSpeaker(userID)));
    //}

    //public static void viewSelectedEventInfo(String eventID, ViewEventInfo viewEventInfo,
                                             //EventsController eventsController){
        //ArrayList<String> eventInfoList = eventsController.getEventInfo(eventID);
        //StringBuilder returnString = viewEventInfo.getEventInfo(eventInfoList);
        //returnString.append("\n 1) make an announcement for the event\n 2) message individual attendee\n 0) Quit\n");
        //output.printPrompt(returnString);
    //}

    //public static void viewAllEventAttendees(String eventID, ViewAllEventAttendees viewAllEventAttendees,
      //                                       EventsController eventsController){
        //output.printPrompt(viewAllEventAttendees.printAllEventAttendees(
          //      eventsController.getAttendeesFromEvent(eventID)));

    //}


