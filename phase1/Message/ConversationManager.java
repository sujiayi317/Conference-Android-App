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

    private HashMap<HashSet<String>, Conversation> conversations;
    private Conversation currentConversation;

    /**
     * The method to create a new conversation between different users.
     * Precondition: these doesn't already exist a conversation with the same two users
     *
     * @param userId1 the user id of the first user
     * @param userId2 the user id of the second user
     */
    public void createConversation(String userId1, String userId2){
        Conversation newConversation = new Conversation(userId1, userId2);
        HashSet<String> newConversationId = newConversation.getUserIds();
        conversations.put(newConversationId, newConversation);
    };


    public boolean existConversation(HashSet<String> talkersList){
        if (conversations.containsKey(talkersList)) {
            return true;
        }
        return false;
    }

    public void currentConversationSetter(HashSet<String> talkersList){
        this.currentConversation = conversations.get(talkersList);
    }



    /**
     * The method to send a message to another user
     */
    public boolean sendMessage(String senderId, String text){
//        HashSet<String> userIds = new HashSet<String>();
//        userIds.add(senderId);
//        userIds.add(receiverId)；
        return currentConversation.addMessage(senderId, text);
    }

    /**
     * Get the list of conversations of a certain user
     * 待定
     */
    public List<Conversation> getUserConversations(String userId){
        ArrayList<Conversation> UserConversations = new ArrayList<Conversation>();
        for (HashSet<String> key: conversations.keySet()){
            if (key.contains(userId)){
                UserConversations.add(conversations.get(key));
            }
        }
        return UserConversations;
    }


    public void announcement(){

    }


}
