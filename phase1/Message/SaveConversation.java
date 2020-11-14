package Message;

//package serialization;

import java.io.*;
import java.util.ArrayList;

public class SaveConversation {

    public static final String CONVERSATION_PATH = "conversation.ser";

    public void save(Conversation conversation){
        File file;
        ReadConversation readData = new ReadConversation();

        file = new File(CONVERSATION_PATH);
        ArrayList<Conversation> itemsToAdd = readData.readConversation();
        if(itemsToAdd == null || itemsToAdd.size() == 0){
            itemsToAdd = new ArrayList<>();
        }
        itemsToAdd.add(conversation);
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(itemsToAdd);
            fileOutputStream.close();
            objectOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
