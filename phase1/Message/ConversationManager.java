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

    /**
     * The method to create a new conversation between different users.
     * Precondition: these doesn't already exist a conversation with the same two users
     *
     * @param userId1 the user id of the first user
     * @param userId2 the user id of the second user
     */
    public void CreateConversation(String userId1, String userId2){
        Conversation newConversation = new Conversation(userId1, userId2);
        HashSet<String> newConversationId = newConversation.getUserIds();
        conversations.put(newConversationId, newConversation);
    };


//    public boolean ExistConversation(String userId1, String userId2){
//        HashSet<String> uncheckedSet = new HashSet<>();
//        uncheckedSet.add(userId1);
//        uncheckedSet.add(userId2);
//        if (conversations.containsKey(uncheckedSet)) {
//            return true;
//        }
//        return false;
//    }


    /**
     * The method to send a message to another user
     */
    public boolean SendMessage(String senderId, String receiverId, String text){
        HashSet<String> userIds = new HashSet<String>();
        userIds.add(senderId);
        userIds.add(receiverId);
        if (!conversations.containsKey(userIds)) {
            CreateConversation(senderId, receiverId);
        }
        return conversations.get(userIds).addMessage(senderId, text);
    }

    /**
     * Get the list of conversations of a certain user
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


    public void Announcement(){

    }


}
