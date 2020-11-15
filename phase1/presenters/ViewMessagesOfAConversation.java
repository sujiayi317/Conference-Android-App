package presenters;

import java.util.ArrayList;

public class ViewMessagesOfAConversation {
    public ViewMessagesOfAConversation(){}

    public StringBuilder printMessages(ArrayList<String> listOfStrings){
        StringBuilder returnString = new StringBuilder();
        for(String str: listOfStrings){
            returnString.append(str);
            returnString.append("\n");
        }
        return returnString;
    }
}
