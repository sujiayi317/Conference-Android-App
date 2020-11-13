package controllers;

import Message.Conversation;
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
                    ViewAllAttendeeEvents viewAllAttendeeEvents, ViewEventInfo viewEventInfo,
                    AttendeeManager attendeeManager, ViewFriendList viewFriendList,
                    ConversationController conversationController, ViewMessagesOfAConversation
                            viewMessagesOfAConversation, ConversationManager conversationManager, ViewMessageList viewMessageList) {
        //connect to Attendee Presenter - Menu options
        boolean quit = false;
        while (! quit ) {
            attendeeMenu.printAttendeeMenu(userID);
            int choice = input.getInputInt("Please choose from the above options:\n");
            if (0<=choice && choice<=5) {
                switch (choice) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        // viewAllEvents
                        int check = 0;
                        while (check != 1 && eventsController.getAllExistingEvents().size() != 0) {
                            viewAllEvents(viewAllExistingEvents, eventsController);
                            String eventID = input.getInputString("Please choose an event and see the details or press enter\n");
                            if (!eventID.equals("")) {
                                viewOneEventInfo(eventID, viewEventInfo, eventsController);
                                String decision = input.getInputString("Yes=sign up OR No\n");
                                if (decision.equals("Yes")) {
                                    attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                                            eventsController.getRoomManager());
                                    output.printPrompt("you're successfully in" + eventID);
                                    check += 1;
                                }
                            }
                        }
                        break;
                    case 2:
                        // view all attendee events
                        viewAllAttendeeEvents(userID, viewAllAttendeeEvents, eventsController);
                        break;
                    case 3:
                        //View all my friend
                        ArrayList<String> friendList = attendeeManager.friendListGetter(userID);
                        boolean check3 = false;
                        while (!check3) {
                            viewAllFriends(friendList, viewFriendList); //output StringBuilder of the Friend list
                            int chooseFriend = input.getInputInt("Choose a friend to start the conversation\n");
                            if (0 <= chooseFriend && chooseFriend <= friendList.size()) {
                                String friendId = friendList.get(chooseFriend);
                                conversationController.enterConversation(friendId);
                                viewMessagesOfAConversation.printMessages
                                        (conversationController.getMessagesOfOneConversation(friendId));
                                check3 = true;
                            }
                        }
                        break;
                    case 4:
                        ArrayList<String[]> messageList = conversationManager.getUserConversations(userID);
                        boolean check4 = false;
                        while (!check4){
                            viewMessageList(messageList, viewMessageList);
                            int chooseConversation = input.getInputInt()
                        }
                        //View all my message
                        break;
                    case 5:
                        //
                        break;
                }
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

    public static void viewMessageList(ArrayList<String[]> conversations, ViewMessageList viewMessageList){
        output.printPrompt(viewMessageList.getMessageList(conversations));
    }

}
