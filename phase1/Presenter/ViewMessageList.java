package Presenter;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewMessageList {
    public ViewMessageList(){}
    public StringBuilder getMessageList(ArrayList<String[]> messageList){
        StringBuilder returnList = new StringBuilder("Messages:");
        for (int i =0 ; i < messageList.size();i++){
            returnList.append(i).append(messageList.get(i)[2]).append(":").append(messageList.get(i)[3]).append("\n");
        }
        return returnList;
    }
}