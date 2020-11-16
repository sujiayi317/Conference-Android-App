package controllers;

import use_cases.UserManager;

import java.util.ArrayList;

public class AddFriend {
    private static OutputManager output;
    private static InputManager input;

    public AddFriend(){
        output = new OutputManager();
        input = new InputManager();
    }
    public void toAddFriend(UserManager userManager, String userID){
        ArrayList<String> userList = userManager.userListGetter();
        boolean check5 = false;
        while (!check5){
            String friendName = input.getInputString("Please enter the User Name to add friend," +
                    " or \"quit\" to quit:\n");
            String friendId = userManager.getUserIdFromName(friendName);
            if (userManager.friendListGetter(userID).contains(friendId)){
                output.printPrompt("Friend already in your friend list.");
            }else if(userList.contains(friendId)){
                userManager.addFriend(userID, friendId);
                check5 = true;
            }else if (friendName.equals("quit")){
                check5 = true;
            }else{
                output.printPrompt("Can't find the user.");
            }
        }
    }
}
