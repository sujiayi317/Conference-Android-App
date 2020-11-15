package Message;

import java.io.*;
import java.util.ArrayList;

public class ReadConversation {

    public ArrayList<Conversation> conversationsList = new ArrayList<>();

    /**
     * Read our ser file and get the conversation entities.
     *
     * @return the list of conversations from our ser file.
     */
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

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            //e.printStackTrace();
        }
        return this.conversationsList;
    }

    /**
     * Read our ser file and return the existing conversation list.
     *
     * @return the existing list of conversations from our ser file.
     */
    public ArrayList<Conversation> readConversation(){
        readFile();
        return this.conversationsList;
    }

}
