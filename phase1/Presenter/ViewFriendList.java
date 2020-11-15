package Presenter;

import java.util.ArrayList;

public class ViewFriendList {
    public ViewFriendList(){}

    /**
     * The presenter of to present a friend list.
     * @param friendList A arraylist of friend names.
     * @return A String with all friend names.
     */
    public StringBuilder getFriendList(ArrayList<String> friendList){
        StringBuilder returnList = new StringBuilder("Friends:\n");
        for (int i = 0 ; i < friendList.size();i++){
            returnList.append(i+1).append(":").append(friendList.get(i)).append("\n");
        }
        return returnList;
    }
}
