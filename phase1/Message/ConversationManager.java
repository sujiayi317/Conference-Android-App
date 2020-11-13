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

    public ConversationManager(){
        HashMap<HashSet<String>, Conversation> conversations = new HashMap<>();
        createConversation("386d97fa", "89dfhy32");//test，待删
    }

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


    /**
     * Check if the Conversation between two users has been created.
     *
     * @param talkersList the Hashset of two users.
     * @return true iff the Conversation between two users from talkersList has been created.
     */
    public boolean existConversation(HashSet<String> talkersList){
        return conversations.containsKey(talkersList);
    }


    /**
     * Set the current conversation to be the conversation between the two users of talkersList.
     *
     * @param talkersList the Hashset of two users.
     */
    public void currentConversationSetter(HashSet<String> talkersList){
        this.currentConversation = conversations.get(talkersList);
    }



    /**
     * Send a message to another user
     *
     * @param senderId the id of the user we want to send out message to.
     * @param text the content of message we want to send
     */
    public void sendMessage(String senderId, String text){
        currentConversation.addMessage(senderId, text);
    }


    public ArrayList<String[]> getMessagesOfCurrentConversation(){
        return currentConversation.getMessages();
    }


    /**
     * Get the list of conversations of a certain user
     * 待定
     */
    public ArrayList<String[]> getUserConversations(String userId){
        ArrayList<String[]> UserConversations = new ArrayList<String[]>();
        for (HashSet<String> key: conversations.keySet()){
            if (key.contains(userId)){
                UserConversations.add(conversations.get(key).getLastMessage());
            }
        }
        return UserConversations;
    }

}
