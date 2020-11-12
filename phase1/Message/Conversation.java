package Message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Conversation implements Serializable {
    private HashSet<String> userIds = new HashSet<String>(); //Store uerIds of the two users
    private ArrayList<String[]> messages = new ArrayList<String[]>();


    public Conversation(String userId1, String userId2){
        userIds.add(userId1);
        userIds.add(userId2);
    }

    /**
     * Get the length of the list of messages, i.e., number of messages sent by the two users.
     *
     * @return the length of messages
     */
    public int getMessagesLength(){
        return messages.size();
    }

    /**
     * Add the sender's message to the list of messages.
     *
     * @param userId the user id of the sender of the message
     * @param text the String of text the sender sends
     * @return true iff the message is successfully added to the list of messages
     */
    public boolean addMessage(String userId, String text){
        String[] message = {userId, text};
        messages.add(message);
        return true;
    }

    /**
     * Get the message and User information in the specific index position
     *
     * @param index the index of message we want to get
     * @return the Array at position index in messages
     */
    public String[] getMessage(int index){
        return messages.get(index).clone(); //return a clone of the index's String[] in messages
    }

    public HashSet<String> getUserIds(){
        HashSet<String> cloneSet = new HashSet<String>();
        cloneSet = (HashSet<String>)userIds.clone();
        return cloneSet;
    }
}
