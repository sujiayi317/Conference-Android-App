package presenters;

import java.util.ArrayList;

/**
 * The presenter for viewing all the messages of a conversation
 */
public class ViewMessagesOfAConversation {
    public ViewMessagesOfAConversation(){}

    /**
     *
     * @param listOfStrings listOfStrings
     * @return
     */
    public StringBuilder printMessages(ArrayList<String> listOfStrings){
        StringBuilder returnString = new StringBuilder();
        for(String str: listOfStrings){
            returnString.append(str);
            returnString.append("\n");
        }
        return returnString;
    }
}
