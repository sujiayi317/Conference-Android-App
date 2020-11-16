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
    private final OutputManager outputManager;

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
        this.viewAllAvailableRoom = new ViewAllAvailableRoom();
        this.viewAllAvailableSpeaker = new ViewAllAvailableSpeaker();
        this.viewFriendList = new ViewFriendList();
        this.outputManager = new OutputManager();
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

                        ArrayList<String> receivers = new ArrayList<>();

                        ArrayList<ArrayList<String>> idAndNames = eventsController.getAllIDAndName();

                        StringBuilder eventNames = new StringBuilder();
                        for (int i = 0; i < idAndNames.get(1).size(); i++){
                            eventNames.append( (i+1) + ". " + idAndNames.get(1).get(i) + "\n");
                        }
                        outputManager.printPrompt(eventNames);

                        boolean check7 = true;
                        while(check7) {
                            int eventChoose = input.getInputInt("Please enter the number one by one to choose" +
                                    " the events, and enter \"0\" to finish:\n");
                            if(eventChoose == 0){
                                check7 = false;
                            }else if(eventChoose == 666 || eventChoose > idAndNames.get(1).size() || eventChoose < 0){
                                outputManager.printPrompt("Please enter another number.");
                            } else{
                                receivers.addAll(eventsController.getAttendeesFromEvent(idAndNames.get(0).
                                        get(eventChoose - 1)));
                            }
                        }
                        String message = input.getInputString("Please enter the message you want to send:\n");
                        conversationController.sendToMultipleUsers(message, receivers);
                        break;

                    case 8:
                        //Create a new room
                        createANewRoom.getToCreateANewRoom(eventsController);
                        break;
                    case 9:
                        //add friend
                        ArrayList<String> userList = userManager.userListGetter();
                        boolean check5 = false;
                        while (!check5){
                            String friendName = input.getInputString("Please enter the User Name to add friend," +
                                    " or \"quit\" to quit:\n");
                            String friendId = userManager.getUserIdFromName(friendName);
                            if (userManager.friendListGetter(userID).contains(friendId)){
                                outputManager.printPrompt("Friend already in your friend list.");
                            }else if(userList.contains(friendId)){
                                userManager.addFriend(userID, friendId);
                                check5 = true;
                            }else if (friendName.equals("quit")){
                                check5 = true;
                            }else{
                                outputManager.printPrompt("Can't find the user.");
                            }
                        }
                        break;

                }
            }

        }
    }
}
