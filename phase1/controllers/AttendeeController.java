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
    public final ViewEventInfo viewEventInfo;
    private final ViewFriendList viewFriendList;

    public AttendeeController(){
        input = new InputManager();
        output = new OutputManager();
        seeALLExistingEvents = new SeeALLExistingEvents();
        seeALLMyEvents = new SeeALLMyEvents();
        this.attendeeMenu = new AttendeeMenu();
        this.viewAllAttendeeEvents = new ViewAllAttendeeEvents();
        this.viewAllExistingEvents = new ViewAllExistingEvents();
        this.viewEventInfo = new ViewEventInfo();
        this.viewFriendList = new ViewFriendList();

    }
    public void run(String userID, EventsController eventsController,
                    AttendeeManager attendeeManager,
                    ConversationController conversationController, UserManager userManager,
                    ViewMessageList viewMessageList) {
        //connect to Attendee Presenter - Menu options
        boolean quit = false;
        while (! quit ) {
            attendeeMenu.printAttendeeMenu(userManager.getUserName(userID));
            int choice = input.getInputInt("Please choose from the above options:\n");
            if (0<=choice && choice<=5) {
                switch (choice) {
                    case 0:
                        quit = true;
                        break;
                    case 1:
                        // viewAllEvents
//                        int check = 0;
//                        while (check != 1 && eventsController.getAllExistingEvents().size() != 0) {
//                            viewAllEvents(viewAllExistingEvents, eventsController);
//                            String eventID = input.getInputString("Please choose an event and see the details or press enter\n");
//                            if (!eventID.equals("")) {
//                                viewOneEventInfo(eventID, viewEventInfo, eventsController);
//                                String decision = input.getInputString("Yes=sign up OR No\n");
//                                if (decision.equals("Yes")) {
//                                    attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
//                                            eventsController.getRoomManager());
//                                    output.printPrompt("you're successfully in" + eventID);
//                                    check += 1;
//                                }
//                            }
//                        }
                        seeALLExistingEvents.getToSeeAllExistingEvents(eventsController, attendeeManager,
                                viewAllExistingEvents, userID, viewEventInfo);
                        break;
                    case 2:
                        // view all attendee events
                        seeALLMyEvents.getToSeeAllMyEvents(viewAllAttendeeEvents, eventsController, userID);
//                        viewAllAttendeeEvents(userID, viewAllAttendeeEvents, eventsController);
                        break;
                    case 3:
                        //View all my friend
                        ArrayList<String> friendList = new ArrayList<>();
                        for(String id:attendeeManager.friendListGetter(userID)){
                            friendList.add(userManager.getUserName(id));
                        } // get list of friends' usernames

                        boolean check3 = false;
                        while (!check3) {
                            viewAllFriends(friendList, viewFriendList); //output StringBuilder of the Friend list
                            int chooseFriend = input.getInputInt("Choose a friend to start the conversation\n") - 1;
                            if (0 <= chooseFriend && chooseFriend <= friendList.size()-1) {
                                String friendId = friendList.get(chooseFriend);
                                conversationController.enterConversation(friendId);
                                check3 = true;
                            }else if (chooseFriend == -1){
                                check3 = true;
                            }else{
                                System.out.println("Friend not found.");
                            }
                        }
                        break;
                    case 4:
                        ArrayList<String[]> messageList = conversationController.getUserAllConversation(userID);
                        boolean check4 = false;
                        while (!check4){
                            viewMessageList(messageList, viewMessageList);
                            int chooseConversation = input.getInputInt("Choose a message to start the conversation\n");
                            if (0 <= chooseConversation && chooseConversation <= messageList.size()-1){
                                if (messageList.get(chooseConversation)[0].equals(userID)){
                                    conversationController.enterConversation(messageList.get(chooseConversation)[1]);
                                }else{
                                    conversationController.enterConversation(messageList.get(chooseConversation)[0]);
                                }
                                check4 = true;
                            }else if (chooseConversation == 88){
                                check4 =true;
                            }
                        }
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

//    public static void viewAllEvents(ViewAllExistingEvents viewAllExistingEvents, EventsController eventsController){
//        output.printPrompt(viewAllExistingEvents.printAllExistingEvents(eventsController.getAllExistingEvents()));
//    }

//    public static void viewAllAttendeeEvents(String userID, ViewAllAttendeeEvents viewAllAttendeeEvents,
//                                       EventsController eventsController){
//        output.printPrompt(viewAllAttendeeEvents.printAllAttendeeEvents(eventsController.getALLAttendeeEvents(userID)));
//    }

//    public static void viewOneEventInfo(String eventID, ViewEventInfo viewEventInfo, EventsController eventsController){
//        ArrayList<String> eventInfoList = eventsController.getEventInfo(eventID);
//        output.printPrompt(viewEventInfo.getEventInfo(eventInfoList));
//    }

    public static void viewAllFriends(ArrayList<String> friends,ViewFriendList viewFriendList){
        output.printPrompt(viewFriendList.getFriendList(friends));
    }

    public static void viewMessageList(ArrayList<String[]> conversations, ViewMessageList viewMessageList){
        output.printPrompt(viewMessageList.getMessageList(conversations));
    }

}
