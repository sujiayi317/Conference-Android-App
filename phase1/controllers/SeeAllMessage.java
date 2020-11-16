package controllers;

import presenters.*;
import use_cases.UserManager;

import java.util.ArrayList;

/**
 * This is the class for viewing all messages
 */
public class SeeAllMessage {
    private static OutputManager output;
    private static InputManager input;

    public SeeAllMessage() {
        output = new OutputManager();
        input = new InputManager();
    }

    /**
     * See All Message
     *
     * @param userID userID
     * @param userManager userManager
     * @param conversationController conversationController
     * @param viewMessageList viewMessageList
     */
    public void getToSeeAllMessage(String userID, UserManager userManager, ConversationController conversationController,
                                   ViewMessageList viewMessageList) {
        ArrayList<String[]> messageList = conversationController.getUserAllConversation(userID);

        ArrayList<String[]> userNameWithLastMessage = new ArrayList<>();
        for (String[] s : messageList) {
            String[] msg = {userManager.getUserName(s[2]), s[3], s[0], s[1]};
            userNameWithLastMessage.add(msg);
        }//有问题？
        boolean check4 = false;
        while (!check4) {
            viewMessageList(userNameWithLastMessage, viewMessageList);
            int chooseConversation = input.getInputInt("Choose a message to view full conversation and send new messages, or type 0 to quit\n") - 1;
            if (0 <= chooseConversation && chooseConversation <= messageList.size() - 1) {
                if (messageList.get(chooseConversation)[0].equals(userID)) {
                    conversationController.enterConversation(messageList.get(chooseConversation)[1]);
                } else {
                    conversationController.enterConversation(messageList.get(chooseConversation)[0]);
                }
                check4 = true;
            } else if (chooseConversation == -1) {
                check4 = true;
            }
        }
    }

    /**
     * View the list of all messages
     * @param conversations conversations
     * @param viewMessageList viewMessageList
     */
    public static void viewMessageList(ArrayList<String[]> conversations, ViewMessageList viewMessageList) {
        output.printPrompt(viewMessageList.getMessageList(conversations));
    }
}
