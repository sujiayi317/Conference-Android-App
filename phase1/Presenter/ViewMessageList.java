package Presenter;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewMessageList {
    public ViewMessageList(){}
    public StringBuilder getMessageList(ArrayList<String[]> messageList){
        StringBuilder returnList = new StringBuilder("Messages:");
        for (int i =0 ; i < messageList.size();i++){
            returnList.append(i).append(messageList.get(i)[0]).append(":").append(messageList.get(i)[1]).append("\n");
        }
        return returnList;
    }
}
