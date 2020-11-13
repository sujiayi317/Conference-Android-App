package controllers;

import Message.ConversationController;
import Presenter.*;
import Presenter.ViewAllAttendeeEvents;
import Presenter.ViewAllAvailableRoom;
import Presenter.ViewAllExistingEvents;
//import com.sun.org.apache.xpath.internal.operations.Bool;
import entities.Organizer;
import entities.Speaker;
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

    public OrganizerController(){
        this.organizerMenu = new OrganizerMenu();
        input = new InputManager();
        output = new OutputManager();
        this.create = new CreateAccount();
    }


    public void run(EventsController eventsController, ViewAllExistingEvents viewAllExistingEvents,
                    ViewAllAvailableRoom viewAllAvailableRoom , ViewAllAttendeeEvents viewAllAttendeeEvents,
                    AttendeeManager attendeeManager, ViewEventInfo viewEventInfo, ViewAllAvailableSpeaker viewAllAvailableSpeaker,
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
                        break;
                    case 1:
                        //connect to CreateAccount Controller to create new speaker
                        if (create.CreateNewAccount(attendeeManager, organizerManager, eventsController.getSpeakerManager(), userManager, "SPEAKER")) {
                            output.printPrompt("New speaker account successfully created.\n");
                        } else {
                            output.printPrompt("Create speaker action cancelled.\n");
                        }
                        break;
                    case 2:
                        //view all existing events
//                        viewAllEvents(viewAllExistingEvents, eventsController);
                        if (eventsController.getAllExistingEvents().size() == 0){
                            output.printPrompt("There is no event yet!");
                        }
                        int check = 0;
                        while (check != 1 && eventsController.getAllExistingEvents().size() != 0) {
                            viewAllEvents(viewAllExistingEvents, eventsController);
                            String eventID = input.getInputString("Please choose an event_title and see the details or press enter\n");
                            if (!eventID.equals("")) {
                                OrganizerController.viewOneEventInfo(eventID, viewEventInfo, eventsController);
                                String decision = input.getInputString("Yes=sign up OR No\n");
                                if (decision.equals("Yes")) {
                                    attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                                            eventsController.getRoomManager());
                                    output.printPrompt("you're successfully in " + eventID + "\n");
                                    check += 1;
                                }
                            }
                        }
                        break;

                    case 3:
                        // view all his/her events
                        viewAllAttendeeEvents(userID, viewAllAttendeeEvents, eventsController);
                        break;

                    case 4:
                        //create a new event
                        //String title, String roomID, Speaker speaker, int startTime
                        String time = "-1";
                        while (Integer.parseInt(time) < 0 || Integer.parseInt(time) > 24) {
                            int startTime = input.getInputInt("Please enter your event time between 0-24\n");
                            if (eventsController.getAvailableRoom(startTime).size() == 0) {
                                output.printPrompt("sorry there is no available room yet, please go to create one first!\n");
                            } else if (eventsController.getAllAvailableSpeaker(startTime).size() == 0) {
                                output.printPrompt("sorry there is no available speaker yet, please go to create one first!\n");
                            } else {
                                getAllAvailableRoomInfo(startTime, viewAllAvailableRoom, eventsController);
                                getAllAvailableSpeaker(startTime, eventsController, viewAllAvailableSpeaker);
                                String title = input.getInputString("Please enter your event's title\n");
                                String roomNUm = input.getInputString("Please enter your room Num\n");
                                String speaker = input.getInputString("Please set your speaker\n");
                                createEvent(title, eventsController.getRoomManager().changeNumTOID(roomNUm), speaker, startTime, eventsController);
                                output.printPrompt("The new Event named "+title+" at "
                                        +roomNUm+"\n"+" taught by "
                                        +speaker+" will start at "+startTime);
                                break;
                            }
                        }
                    case 5:
                        //View all my fiends
                        break;
                    case 6:
                        // view all my message
                        break;

                    case 7:
                        //send to all attendees of an event/events
                        ArrayList<String> receivers = new ArrayList<>();
                        boolean check7 = true;
                        while(check7) {
                            String eventId = input.getInputString("Please enter the event ID one by one, and " +
                                    "enter \"done\" to finish:\n");
                            if(eventId.equals("done")){
                                check7 = false;
                            }else{
                                receivers.addAll(eventsController.getAttendeesFromEvent(eventId));
                            }
                        }
                        String message = input.getInputString("Please enter the message you want to send:\n");
                        conversationController.sendToMultipleUsers(message, receivers);
                        break;

                    case 8:
                        RoomManager roomManager = eventsController.getRoomManager();
                        String roomNum = "-1";
                        while (Integer.parseInt(roomNum)>100 || Integer.parseInt(roomNum)<0)
                        roomNum = input.getInputString("Please enter your room Number between 0-100\n");
                        if (roomManager.createRoom(roomNum)){
                            output.printPrompt("room" +" " +roomNum+ " "+"is created successfully\n");
                        }
                        break;
                    case 9:
                        //add friend
                        break;

                }
            }

        }
    }
    private void getAllAvailableRoomInfo(int time, ViewAllAvailableRoom viewAllAvailableRoom, EventsController eventsController){
        viewAllAvailableRoom.printAllAvailableRoom(eventsController.getAvailableRoom(time));
    }

    private void getAllAvailableSpeaker(int time, EventsController eventsController, ViewAllAvailableSpeaker viewAllAvailableSpeaker){
       output.printPrompt(viewAllAvailableSpeaker.printAllAvailableSpeaker(eventsController.getAllAvailableSpeaker(time)));

    }
    private void createEvent(String title, String roomID,String speaker,int startTime, EventsController eventsController){
        output.printPrompt(eventsController.createEvent(title,roomID,speaker,startTime));
    }
}
