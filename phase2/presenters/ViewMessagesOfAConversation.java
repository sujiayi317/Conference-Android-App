package presenters;

import java.util.ArrayList;

/**
 * The presenter for viewing all the messages of a conversation
 */
public class ViewMessagesOfAConversation {
    public ViewMessagesOfAConversation(){}

    /**
     * This class take in an arraylist of strings and turn them into a single string which shows the menu.
     * @param listOfStrings listOfStrings
     * @return A StringBuilder contain the messages of a conversation.
     */
    public StringBuilder printMessages(ArrayList<String> listOfStrings, String userName){
        StringBuilder returnString = new StringBuilder("Here is your Conversation with " + userName +"\n");
        for(String str: listOfStrings){
            returnString.append(str);
            returnString.append("\n");
        }
        return returnString;
    }
}
