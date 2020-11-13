package controllers;

import Presenter.*;
import Presenter.ViewAllAttendeeEvents;
import Presenter.ViewAllAvailableRoom;
import Presenter.ViewAllExistingEvents;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import entities.Organizer;
import entities.Speaker;
import use_cases.*;

/**
 * This is the main controller for Organizer.
 */
public class OrganizerController extends AttendeeController{

    private static InputManager input;
    private static OutputManager output;
    private final CreateAccount create;
    private final OrganizerMenu organizerMenu;

    public OrganizerController(){
        this.organizerMenu = new OrganizerMenu();
        input = new InputManager();
        output = new OutputManager();
        this.create = new CreateAccount();
    }


    public void run(EventsController eventsController, ViewAllExistingEvents viewAllExistingEvents,
                    ViewAllAvailableRoom viewAllAvailableRoom , ViewAllAttendeeEvents viewAllAttendeeEvents,
                    AttendeeManager attendeeManager,ViewEventInfo viewEventInfo,
                    OrganizerManager organizerManager, SpeakerManager speakerManager, String userID) {
        //connect to Attendee Presenter - Menu options
        boolean quit = false;
        while (!quit) {
            organizerMenu.printOrganizerMenu(userID);
            int choice = input.getInputInt("Please choose from the above options:\n");
            if (0<=choice && choice<=7) {
                switch (choice) {
                    case 0:
                        quit = true;
                    case 1:
                        //connect to CreateAccount Controller to create enw speaker
                        if (create.CreateNewAccount(attendeeManager, organizerManager, speakerManager, "Speaker")) {
                            output.printPrompt("New speaker account successfully created.");
                        } else {
                            output.printPrompt("Create speaker action cancelled.");
                        }
                    case 2:
                        //view all existing events
                        viewAllEvents(viewAllExistingEvents, eventsController);
                        int check = 0;
                        while (check != 1) {
                            viewAllEvents(viewAllExistingEvents, eventsController);
                            String eventID = input.getInputString("Please choose an event and see the details or press enter\n");
                            if (!eventID.equals("")) {
                                OrganizerController.viewOneEventInfo(eventID, viewEventInfo, eventsController);
                                String decision = input.getInputString("Yes=sign up OR No\n");
                                if (decision.equals("Yes")) {
                                    attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                                            eventsController.getRoomManager());
                                    output.printPrompt("you're successfully in" + eventID);
                                    check += 1;
                                }
                            }
                        }

                    case 3:
                        // view all his/her events
                        viewAllAttendeeEvents(userID, viewAllAttendeeEvents, eventsController);

                    case 4:
                        //create a new event
                        //String title, String roomID, Speaker speaker, int startTime
                        String title = input.getInputString("Please enter your event's title\n");
                        String roomID = input.getInputString("Please enter your room ID\n");
                        String speaker = input.getInputString("Please set your speaker\n");
                        int startTime = input.getInputInt("Please enter your event time\n");
                        createEvent(title, roomID, speaker, startTime, eventsController);
                    case 5:
                        // view all events
                        AttendeeController.viewAllEvents(viewAllExistingEvents, eventsController);
                    case 6:
                        // view all attended events
                        viewAllAttendeeEvents.printAllAttendeeEvents(eventsController.getALLAttendeeEvents(userID));

                    case 7:
                        int time = input.getInputInt("Please enter your event time between 0-24\n");
                        getAllAvailableRoomInfo(time, viewAllAvailableRoom, eventsController);
                }
            }
        }
    }
    private void getAllAvailableRoomInfo(int time, ViewAllAvailableRoom viewAllAvailableRoom, EventsController eventsController){
        viewAllAvailableRoom.printAllAvailableRoom(eventsController.getAvailableRoom(time));
    }
    private void createEvent(String title, String roomID,String speaker,int startTime, EventsController eventsController){
        output.printPrompt(eventsController.createEvent(title,roomID,speaker,startTime));
    }
}
