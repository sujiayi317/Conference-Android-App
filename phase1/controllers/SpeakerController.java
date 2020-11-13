package controllers;

import Presenter.SpeakerMenu;
import Presenter.ViewAllSpeakerEvents;
import Presenter.ViewEventInfo;
import use_cases.EventManager;

import java.util.ArrayList;

/**
 * This is the main controller for Speaker.
 */
public class SpeakerController {

    private static InputManager input;
    private static OutputManager output;
    private final SpeakerMenu speakermenu;


    public SpeakerController(){
        input = new InputManager();
        this.speakermenu = new SpeakerMenu();
    }
    public void run(String userID, EventsController eventsController, ViewAllSpeakerEvents viewAllSpeakerEvents,
                    ViewEventInfo viewEventInfo) {
        //connect to Speaker Presenter - Menu options

        int choice = input.getInputInt("Please choose from the following options:");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    //connect to MyEvent Controller
                    viewAllSpeakerEvents(userID, viewAllSpeakerEvents, eventsController);
                    EventManager eventManager = eventsController.getEventManager();
                    ArrayList<String> eventList = eventManager.getAllEventForTheSpeaker(userID);
                    int chooseEvent = input.getInputInt("Choose an event to view\n");
                    if (0<= chooseEvent && chooseEvent<=eventList.size()){
                        String eventId = eventList.get(chooseEvent);
                        //display event info
                        viewOneEventInfo(eventId, viewEventInfo, eventsController);

                        //options: message all attendees, message individual attendee
                        //message all attendees: (need method)
                        //message individual attendee: display list of attendees of the event:
                        //choose an attendee to message

                    }
                case 2:
                    //connect to Contacts Controller
                    break;
                //case 3:
                    //connect to Announcements Controller
            }
        }
    }

    public static void viewAllSpeakerEvents(String userID, ViewAllSpeakerEvents viewAllSpeakerEvents,
                                            EventsController eventsController){
        output.printPrompt(viewAllSpeakerEvents.printAllSpeakerEvents(
                eventsController.getAllEventsForTheSpeaker(userID)));
    }

    public static void viewOneEventInfo(String eventID, ViewEventInfo viewEventInfo, EventsController eventsController){
        ArrayList<String> eventInfoList = eventsController.getEventInfo(eventID);
        output.printPrompt(viewEventInfo.getEventInfo(eventInfoList));
    }
}
