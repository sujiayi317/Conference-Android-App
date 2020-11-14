package Message;

import java.io.*;
import java.util.ArrayList;

public class ReadConversation {

    public ArrayList<Conversation> conversationsList = new ArrayList<>();

    public ArrayList<Conversation> readFile(){
        String filename = SaveConversation.CONVERSATION_PATH;
        try{
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            this.conversationsList = (ArrayList<Conversation>)ois.readObject();
            fis.close();
            bis.close();
            ois.close();

        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return this.conversationsList;
    }

    public ArrayList<Conversation> readConversation(){
        return this.conversationsList;
    }

}
