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


    //这个method结束，用户就进入了和这个人的聊天室
    public void enterConversation(String secondUserId){
        HashSet<String> setOfTalkersNow = new HashSet<String>();
        setOfTalkersNow.add(currentUserId);
        setOfTalkersNow.add(secondUserId);

        if (!conversationManager.existConversation(setOfTalkersNow)){
            conversationManager.createConversation(currentUserId, secondUserId);
        }
        conversationManager.currentConversationSetter(setOfTalkersNow);
    }


    public void sendToIndividualUser(String messageContent){
        conversationManager.sendMessage(currentUserId, messageContent);
    }


    public void sendToMultipleUsers(String messageContent, ArrayList<String> listOfUsers){
        for(String userId: listOfUsers){
            enterConversation(userId);
            sendToIndividualUser(messageContent);
        }
    }

}
