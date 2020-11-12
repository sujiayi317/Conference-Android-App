package Message;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable {
    private String[] userIds = new String[2]; //Store uerIds of the two users
    private ArrayList<String[]> messages = new ArrayList<String[]>();

    public Conversation(String userId1, String userId2){
        userIds[0] = userId1;
        userIds[1] = userId2;
    }

    public int getMessagesLength(){
        return messages.size();
    }

    public boolean addMessage(String userId, String text){
        String[] message = {userId, text};
        messages.add(message);
        return true;
    }

    public String[] getMessage(int index){
        return messages.get(index);
    }
}
