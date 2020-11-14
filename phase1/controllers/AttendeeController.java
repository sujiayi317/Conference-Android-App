package controllers;

import Message.Conversation;
import Message.ConversationController;
import Message.ConversationManager;
import Presenter.*;
import use_cases.AttendeeManager;
import use_cases.UserManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the main controller for Attendee.
 */
public class AttendeeController {
    private static InputManager input;
    private static OutputManager output;
    public final SeeALLExistingEvents seeALLExistingEvents;
    public final SeeALLMyEvents seeALLMyEvents;
    private final AttendeeMenu attendeeMenu;
    public final ViewAllAttendeeEvents viewAllAttendeeEvents;
    public final ViewAllExistingEvents viewAllExistingEvents;
    public final ViewMessageList viewMessageList;
    public final ViewEventInfo viewEventInfo;
    private final ViewFriendList viewFriendList;
    private final SeeAllFriend seeAllFriend;
    private final SeeAllMessage seeAllMessage;

    public AttendeeController(){
        input = new InputManager();
        output = new OutputManager();
        seeALLExistingEvents = new SeeALLExistingEvents();
        seeALLMyEvents = new SeeALLMyEvents();
        seeAllFriend = new SeeAllFriend();
        seeAllMessage = new SeeAllMessage();
        this.attendeeMenu = new AttendeeMenu();
        this.viewAllAttendeeEvents = new ViewAllAttendeeEvents();
        this.viewAllExistingEvents = new ViewAllExistingEvents();
        this.viewEventInfo = new ViewEventInfo();
        this.viewFriendList = new ViewFriendList();
        this.viewMessageList = new ViewMessageList();

    }
    public void run(String userID, EventsController eventsController,
                    AttendeeManager attendeeManager,
                    ConversationController conversationController, UserManager userManager) {
        //connect to Attendee Presenter - Menu options
        boolean quit = false;
        while (! quit ) {
            attendeeMenu.printAttendeeMenu(userManager.getUserName(userID));
            int choice = input.getInputInt("Please choose from the above options:\n");
            if (0<=choice && choice<=5) {
                switch (choice) {
                    case 0:
                        quit = true;
                        output.printPrompt("You have signed out.\n");
                        break;
                    case 1:
                        //view all existing events
                        seeALLExistingEvents.getToSeeAllExistingEvents(eventsController, attendeeManager,
                                viewAllExistingEvents, userID, viewEventInfo);
                        break;
                    case 2:
                        // view all attendee events
                        seeALLMyEvents.getToSeeAllMyEvents(viewAllAttendeeEvents, eventsController, userID);
                        break;
                    case 3:
                        //View all my fiends
                        seeAllFriend.getToSeeAllFriend(userID, viewFriendList, attendeeManager, userManager,
                                conversationController);
                        break;
                    case 4:
                        //view all my message
                        seeAllMessage.getToSeeAllMessage(userID, userManager, conversationController, viewMessageList);
                        break;
                    case 5:
                        //add friend
                        ArrayList<String> userList = userManager.userListGetter();
                        boolean check5 = false;
                        while (!check5){
                            String friendId = input.getInputString("Please enter the userId, or 0 to quit:\n");
                            if (userManager.friendListGetter(userID).contains(friendId)){
                                System.out.println("Friend already in your friend list.");
                            }else if(userList.contains(friendId)){
                                userManager.addFriend(userID, friendId);
                                check5 = true;
                            }else if (friendId.equals("0")){
                                check5 = true;
                            }else{
                                System.out.println("Can't find the user.");
                            }
                        }
                        break;
                }
            }
        }
    }

}
