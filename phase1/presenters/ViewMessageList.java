package presenters;

import java.util.ArrayList;

/**
 * The presenter for viewing all the message list
 */
public class ViewMessageList {
    public ViewMessageList() {
    }

    /**
     * The presenter to present all messages of a given user. (Last sentence of different conversations.)
     *
     * @param messageList An array list which contains the last sentence of a conversation, user ids of the conversation
     *                    and who sends the last message.
     * @return a string which presents all information.
     */
    public StringBuilder getMessageList(ArrayList<String[]> messageList) {
        StringBuilder returnList = new StringBuilder("Messages:\n");
        for (int i = 0; i < messageList.size(); i++) {
            returnList.append(i + 1).append(" ").append(messageList.get(i)[0]).append(":").append(messageList.get(i)[1])
                    .append("(").append(messageList.get(i)[2]).append(messageList.get(i)[3]).append(")").append("\n");
        }
        return returnList;
    }
}
