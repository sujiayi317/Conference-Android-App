package Message;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;


public class ConversationController {
    private ConversationManager conversationManager;
    private String currentUserId;


    public ConversationController(String currentUserId){
        this.conversationManager = new ConversationManager();
        this.currentUserId = currentUserId;
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


    public ArrayList<String> getMessagesOfOneConversation(String userId){
        enterConversation(userId);

        return
    }
}
