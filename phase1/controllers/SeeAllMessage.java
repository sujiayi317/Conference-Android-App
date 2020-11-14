package controllers;

import Message.ConversationController;
import Presenter.*;
import use_cases.AttendeeManager;
import use_cases.UserManager;

import java.util.ArrayList;

public class SeeAllMessage {
    private static OutputManager output;
    private static InputManager input;

    public SeeAllMessage(){
        output = new OutputManager();
        input = new InputManager();
    }

    public void getToSeeAllMessage(String userID, UserManager userManager, ConversationController conversationController,
                              ViewMessageList viewMessageList){
        ArrayList<String[]> messageList = conversationController.getUserAllConversation(userID);

        ArrayList<String[]> userNameWithLastMessage = new ArrayList<>();
        for (String[] s: messageList){
            String[] msg = {userManager.getUserName(s[2]), s[3]};
            userNameWithLastMessage.add(msg);
        }//有问题
        boolean check4 = false;
        while (!check4){
            viewMessageList(userNameWithLastMessage, viewMessageList);
            int chooseConversation = input.getInputInt("Choose a message to start the conversation\n") - 1;
            if (0 <= chooseConversation && chooseConversation <= messageList.size()-1){
                if (messageList.get(chooseConversation)[0].equals(userID)){
                    conversationController.enterConversation(messageList.get(chooseConversation)[1]);
                }else{
                    conversationController.enterConversation(messageList.get(chooseConversation)[0]);
                }
                check4 = true;
            }else if (chooseConversation == -1){
                check4 =true;
            }
        }
    }
    public static void viewMessageList(ArrayList<String[]> conversations, ViewMessageList viewMessageList){
        output.printPrompt(viewMessageList.getMessageList(conversations));
    }
}
