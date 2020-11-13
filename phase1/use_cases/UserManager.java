package use_cases;

import entities.Attendee;
import entities.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    protected List<User> users;

    public boolean validNewName(String name){
        for (User user : users){
            if (user.getUserName().equals(name)){
                return false;
            }
        }
        return true;
    }

    public boolean validNewEmail(String email){
        for (User user : users){
            if (user.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

    public String validLogIn(String account, String password){
        for (User user : users){
            if (user.getEmail().equals(account) && user.getPassword().equals(password)){
                return user.getUserID();
            } else if (user.getEmail().equals(account)){
                return "NULL";
            }
        }
        return "NULL";
    }

    public String getUserType(String account, String password){
        for (User user : users){
            if (user.getEmail().equals(account) && user.getPassword().equals(password)){
                return user.getType();
            } else if (user.getEmail().equals(account)){
                return "NULL";
            }
        }
        return "NULL";
    }


    public List<String> UsersIdsGetter(){
        ArrayList<String> UserIds = new ArrayList<>();
        for (User user : users){
            UserIds.add(user.getUserID());
        }
        return UserIds;
    }
    public boolean addFriend(String currId1, String userId2) {
        List<String> IdList = UsersIdsGetter();
        if (IdList.contains(currId1) && IdList.contains(userId2)) {
            for (User user : users) {
                if (user.getUserID() == currId1) {
                    user.friendListSetter(userId2);
                } else {
                    user.friendListSetter(currId1);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> friendListGetter(String userId) {
        for (User user : users){
            if (user.getUserID() == userId){
                return user.friendListGetter();
            }
        }return null;
    }

    public String getUserName(String userId){
        for(User user: users){
            if(user.getUserID().equals(userId)){
                return user.getUserName();
            }
        }
        return null;
    }

}
