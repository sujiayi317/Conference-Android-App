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
}
