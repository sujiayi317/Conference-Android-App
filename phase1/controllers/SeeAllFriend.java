package controllers;

import presenters.*;
import use_cases.AttendeeManager;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * This is the class for viewing all friends
 */
public class SeeAllFriend {
    private static OutputManager output;
    private static InputManager input;

    public SeeAllFriend() {
        output = new OutputManager();
        input = new InputManager();
    }

    /**
     *This class can present the friend list of a user, and the user can choose to enter the conversation of a given friend.
     *
     * @param userID userID
     * @param viewFriendList viewFriendList
     * @param attendeeManager attendeeManager
     * @param userManager userManager
     * @param conversationController conversationController
     */
    public void getToSeeAllFriend(String userID, ViewFriendList viewFriendList, AttendeeManager attendeeManager,
                                  UserManager userManager, ConversationController conversationController){
        ArrayList<String> friendList = new ArrayList<>();
        ArrayList<String> friendIdList = new ArrayList<>();
        for(String id:attendeeManager.friendListGetter(userID)){
            friendList.add(userManager.getUserName(id));
            friendIdList.add(id);
        } // get list of friends' usernames

        boolean check3 = false;
        while (!check3) {
            viewAllFriends(friendList, viewFriendList); //output StringBuilder of the Friend list
            int chooseFriend = input.getInputInt("Choose a friend to start the conversation, or" +
                    " \"0\" to quit:\n") - 1;
            if (0 <= chooseFriend && chooseFriend <= friendList.size()-1) {
                String friendId = friendIdList.get(chooseFriend);
                conversationController.enterConversation(friendId);
                check3 = true;
            }else if (chooseFriend == -1){
                check3 = true;
            }else{
                System.out.println("Friend not found.");
            }
        }

    }

    /**
     *
     *
     * @param friends friends
     * @param viewFriendList viewFriendList
     */
    public static void viewAllFriends(ArrayList<String> friends,ViewFriendList viewFriendList){
        output.printPrompt(viewFriendList.getFriendList(friends));
    }
}
