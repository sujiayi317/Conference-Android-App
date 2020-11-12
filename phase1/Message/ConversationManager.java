package Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * The Message.Conversation Manager class, is a use case class to manage conversations.
 */
public class ConversationManager implements Serializable{

    private HashMap<HashSet<String>, Conversation> Conversations;

    /**
     * The method to create a new conversation between different users.
     * @param userId1
     * @param userId2
     */
    public void CreateConversation(String userId1, String userId2){
        Conversation conversation = new Conversation(userId1, userId2);
        HashSet<String> conversationId = conversation.getUserIds();
        Conversations.put(conversationId, conversation);
    };

    /**
     * The method to send a message to another user
     */
    public void SendMessage(String senderId, String receiverId, String text){



    }

    public void Announcement(){

    }


}
