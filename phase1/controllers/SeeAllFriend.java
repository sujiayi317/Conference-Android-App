package controllers;

import Message.ConversationController;
import Presenter.*;
import use_cases.AttendeeManager;
import use_cases.UserManager;

import java.util.ArrayList;

public class SeeAllFriend {
    private static OutputManager output;
    private static InputManager input;

    public SeeAllFriend() {
        output = new OutputManager();
        input = new InputManager();
    }

    public void getToSeeAllFriend(String userID, ViewFriendList viewFriendList, AttendeeManager attendeeManager,
                                  UserManager userManager, ConversationController conversationController){
        ArrayList<String> friendList = new ArrayList<>();
        for(String id:attendeeManager.friendListGetter(userID)){
            friendList.add(userManager.getUserName(id));
        } // get list of friends' usernames

        boolean check3 = false;
        while (!check3) {
            viewAllFriends(friendList, viewFriendList); //output StringBuilder of the Friend list
            int chooseFriend = input.getInputInt("Choose a friend to start the conversation, or" +
                    " \"0\" to quit:\n") - 1;
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

    }

    public static void viewAllFriends(ArrayList<String> friends,ViewFriendList viewFriendList){
        output.printPrompt(viewFriendList.getFriendList(friends));
    }
}
