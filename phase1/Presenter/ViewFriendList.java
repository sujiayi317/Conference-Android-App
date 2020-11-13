package Presenter;

import java.util.ArrayList;

public class ViewFriendList {
    public ViewFriendList(){}
    public StringBuilder getFriendList(ArrayList<String> friendList){
        StringBuilder returnList = new StringBuilder("Friends:\n");
        for (int i =0 ; i < friendList.size();i++){
            returnList.append(i).append("0").append(friendList.get(i)).append("\n");
        }
        return returnList;
    }
}
