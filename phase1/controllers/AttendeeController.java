package controllers;

import Message.ConversationController;
import Message.ConversationManager;
import Presenter.*;
import use_cases.AttendeeManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the main controller for Attendee.
 */
public class AttendeeController {
    private static InputManager input;
    private static OutputManager output;
    private final AttendeeMenu attendeeMenu;

    public AttendeeController(){
        input = new InputManager();
        output = new OutputManager();
        this.attendeeMenu = new AttendeeMenu();
    }
    public void run(String userID, EventsController eventsController, ViewAllExistingEvents viewAllExistingEvents,
                    ViewAllAttendeeEvents viewAllAttendeeEvents, ViewEventInfo viewEventInfo, AttendeeManager attendeeManager,
                    ViewFriendList viewFriendList, ConversationController conversationController) {
        //connect to Attendee Presenter - Menu options
        attendeeMenu.printAttendeeMenu(userID);
        int choice = input.getInputInt("Please choose from the above options:\n");
        if (choice != 0) {
            switch (choice) {
                case 1:
                    // viewAllEvents
                    int check = 0;
                    while (check != 1) {
                        viewAllEvents(viewAllExistingEvents, eventsController);
                        String eventID = input.getInputString("Please choose an event and see the details or press enter\n");
                        if (!eventID.equals("")) {
                            viewOneEventInfo(eventID, viewEventInfo, eventsController);
                            String decision = input.getInputString("Yes=sign up OR No\n");
                            if (decision.equals("Yes")) {
                                attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                                        eventsController.getRoomManager());
                                output.printPrompt("you're successfully in"+ eventID);
                                check +=1;
                            }
                        }
                    }
                case 2:
                    // view all attendee events
                    viewAllAttendeeEvents(userID, viewAllAttendeeEvents, eventsController);
                case 3:
                    //View all my friend
                    ArrayList<String> friendList = attendeeManager.friendListGetter(userID);
                    boolean check3 = false;
                    while (check3 != true){
                        viewAllFriends(friendList, viewFriendList);
                        int chooseFriend = input.getInputInt("Choose a friend to start the conversation\n");
                        if (0<= chooseFriend && chooseFriend<= friendList.size()){
                            String friendId = friendList.get(chooseFriend);
                            conversationController.enterConversation(friendId);
                        }
                    }
                case 4:
                    //View all my message
            }
        }
    }

    public static void viewAllEvents(ViewAllExistingEvents viewAllExistingEvents, EventsController eventsController){
        output.printPrompt(viewAllExistingEvents.printAllExistingEvents(eventsController.getAllExistingEvents()));
    }

    public static void viewAllAttendeeEvents(String userID, ViewAllAttendeeEvents viewAllAttendeeEvents,
                                       EventsController eventsController){
        output.printPrompt(viewAllAttendeeEvents.printAllAttendeeEvents(eventsController.getALLAttendeeEvents(userID)));
    }

    public static void viewOneEventInfo(String eventID, ViewEventInfo viewEventInfo, EventsController eventsController){
        ArrayList<String> eventInfoList = eventsController.getEventInfo(eventID);
        output.printPrompt(viewEventInfo.getEventInfo(eventInfoList));
    }

    public static void viewAllFriends(ArrayList<String> friends,ViewFriendList viewFriendList){
        output.printPrompt(viewFriendList.getFriendList(friends));
    }

}
