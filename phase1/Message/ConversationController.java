package Message;

import Presenter.*;
import use_cases.UserManager;
import controllers.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;


public class ConversationController {
    private static InputManager input;
    private static OutputManager output;
    private final ConversationManager conversationManager;
    private final String currentUserId;
    private final UserManager userManager;
    private final ViewMessagesOfAConversation viewMessagesOfAConversation;

    public ConversationController(String currentUserId){
        input = new InputManager();
        output = new OutputManager();
        this.conversationManager = new ConversationManager();
        this.userManager = new UserManager();
        this.currentUserId = currentUserId;
        this.viewMessagesOfAConversation = new ViewMessagesOfAConversation();
    }



    /**
     * Get into the conversation between current user and the other user.
     *
     * @param secondUserId the User Id of user we want to send message to
     */
    public void enterConversation(String secondUserId){
        HashSet<String> setOfTalkersNow = new HashSet<String>();
        setOfTalkersNow.add(currentUserId);
        setOfTalkersNow.add(secondUserId);

        if (!conversationManager.existConversation(setOfTalkersNow)){
            conversationManager.createConversation(currentUserId, secondUserId);
        }
        conversationManager.currentConversationSetter(setOfTalkersNow);
        boolean keepSending = true;
        while (keepSending){
            ArrayList<String> messageList = getMessagesOfCurrentConversation();
            viewMessagesOfAConversation(messageList, viewMessagesOfAConversation);
            String message = input.getInputString("Enter \"quit\" to quit, other messages to send:\n");
            if (message.equals("quit")){
                keepSending = false;
            }else {
                sendToIndividualUser(message);
            }
        }
    }

    /**
     * Send message to one individual user.
     *
     * @param messageContent the content of message we want to send.
     */
    public void sendToIndividualUser(String messageContent){
        conversationManager.sendMessage(currentUserId, messageContent);
    }


    /**
     * Send message to multiple users.
     *
     * @param messageContent the content of message we want to send.
     * @param listOfUsers the list of users we want to send message to.
     */
    public void sendToMultipleUsers(String messageContent, ArrayList<String> listOfUsers){
        for(String userId: listOfUsers){
            enterConversation(userId);
            sendToIndividualUser(messageContent);
        }
    }



//    public ArrayList<String> getMessagesOfOneConversation(String userId){
//        enterConversation(userId);
//        ArrayList<String> FinalTextsList = new ArrayList<>();
//        for(String[] oneMessage: conversationManager.getMessagesOfCurrentConversation()){
//            String finalText = userManager.getUserName(oneMessage[0]) + oneMessage[1];
//            FinalTextsList.add(finalText);
//        }
//        return FinalTextsList;
//    }

    public ArrayList<String> getMessagesOfCurrentConversation(){
        ArrayList<String> FinalTextsList = new ArrayList<>();
        for(String[] oneMessage: conversationManager.getMessagesOfCurrentConversation()){
            String finalText = userManager.getUserName(oneMessage[0]) + ": " + oneMessage[1];
            FinalTextsList.add(finalText);
        }
        return FinalTextsList;
    }

    public ArrayList<String[]> getUserAllConversation(String userId){
        ArrayList<String[]> userNameWithLastMessage = new ArrayList<>();
        for (String[] s: conversationManager.getUserConversations(userId)){
            String[] msg = {userManager.getUserName(s[0]), s[1]};
            userNameWithLastMessage.add(msg);
        }
        return userNameWithLastMessage;
    }

    public static void viewMessagesOfAConversation(ArrayList<String> messages,
                                                   ViewMessagesOfAConversation viewMessagesOfAConversation){
        output.printPrompt(viewMessagesOfAConversation.printMessages(messages));
    }

}
