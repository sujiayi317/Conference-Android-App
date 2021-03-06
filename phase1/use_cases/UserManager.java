package use_cases;

import entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.UserManager class, this is the use case class to manage the entities.User for this conference.
 */
public class UserManager {
    public static List<User> users = new ArrayList<>();

    /**
     * Check if the given String is a valid new user Name
     * @param name String name to be checked
     * @return true iff the name is a valid new user Name
     */
    public boolean validNewName(String name) {
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public void reset(){
        users = new ArrayList<>();
    }

    /**
     * Check if the given String is a valid new email
     * @param email String email to be checked
     * @return true iff the email is a valid new email
     */
    public boolean validNewEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the String user ID of a user given his account and password, or "NULL" if not found
     *
     * @param account String email of the user
     * @param password String password of the user
     * @return the String user ID of this user given his account and password, or "NULL"
     */
    public String validLogIn(String account, String password) {
        for (User user : users) {
            if (user.getEmail().equals(account) && user.getPassword().equals(password)) {
                return user.getUserID();
            }
        }
        return "NULL";
    }

    /**
     * Return the String user type of a user given his account and password, or "NULL" if not found
     *
     * @param account String email of the user
     * @param password String password of the user
     * @return the String user type of this user given his account and password, or "NULL"
     */
    public String getUserType(String account, String password) {
        for (User user : users) {
            if (user.getEmail().equals(account) && user.getPassword().equals(password)) {
                return user.getType();
            } else if (user.getEmail().equals(account)) {
                return "NULL";
            }
        }
        return "NULL";
    }

    /**
     * Return the String user type of a user given his userID, or "NULL" if not found
     *
     * @param userID String userID the user
     * @return the String type of this user given his userID, or "NULL"
     */
    public String getUserType(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user.getType();
            }
        }
        return "NULL";
    }

    /**
     * Return the String user email of a user given his userID, or "NULL" if not found
     *
     * @param userID String userID the user
     * @return the String email of this user given his userID, or "NULL"
     */
    public String getUserEmail(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user.getEmail();
            }
        }
        return "NULL";
    }

    /**
     * Return the String user password of a user given his userID, or "NULL" if not found
     *
     * @param userID String userID the user
     * @return the String password of this user given his userID, or "NULL"
     */
    public String getUserPassword(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user.getPassword();
            }
        }
        return "NULL";
    }

    /**
     * Return List<String> of user IDs
     *
     * @return List<String> of user IDs
     */
    public List<String> UsersIdsGetter() {
        ArrayList<String> UserIds = new ArrayList<>();
        for (User user : users) {
            UserIds.add(user.getUserID());
        }
        return UserIds;
    }

    /**
     * Add another user's userID to the current user's friend list.
     *
     * @param currId1 current user ID
     * @param userId2 another user ID
     * @return true iff another user is successfully added to the current user's friendList
     */
    public boolean addFriend(String currId1, String userId2) {
        List<String> idList = UsersIdsGetter();
        if (idList.contains(currId1) && idList.contains(userId2)) {
            for (User user : users) {
                if (user.getUserID().equals(currId1)) {
                    if (!user.friendListGetter().contains(userId2)) {
                        user.friendListSetter(userId2);
                    }
                } else if (user.getUserID().equals(userId2)) {
                    if (!user.friendListGetter().contains(currId1)) {
                        user.friendListSetter(currId1);
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Get the friend list of the user with user ID
     * @param userID String user ID
     * @return the friend list of the user with user ID or null if this user does not exist
     */
    public ArrayList<String> friendListGetter(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user.friendListGetter();
            }
        }
        return null;
    }

    /**
     * Return an ArrayList of all userIDs
     * @return an ArrayList of all userIDs
     */
    public ArrayList<String> userListGetter() {
        ArrayList<String> idList = new ArrayList<String>();
        for (User user : users) {
            idList.add(user.getUserID());
        }
        return idList;

    }

    /**
     * Given a user ID, return the corresponding user name
     * @param userId user ID
     * @return the corresponding user name
     */
    public String getUserName(String userId) {
        for (User user : users) {
            if (user.getUserID().equals(userId)) {
                return user.getUserName();
            }
        }
        return null;
    }

    /**
     * Given a user name, return the corresponding user id
     * @param userName the user Name
     * @return the corresponding user id
     */
    public String getUserIdFromName(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user.getUserID();
            }
        }
        return null;
    }

    /**
     * Given a user ID, return the corresponding user name
     * @param userID the user Name
     * @return the corresponding user name
     */
    public String getUserNameFromID(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user.getUserName();
            }
        }
        return null;
    }

    public ArrayList<StringBuilder> getAllUsersInfo(){
        ArrayList<StringBuilder> usersInfo = new ArrayList<>();
        for (int i = 0; i < users.size(); i++){
            StringBuilder singleInfo = new StringBuilder();
            User currentUser = users.get(i);
            singleInfo.append(i).append(") ").append(currentUser.getUserName()).append(" ").append(currentUser.getType());
            usersInfo.add(singleInfo);
        }
        StringBuilder SummaryInfo = new StringBuilder();
        SummaryInfo.append("Total Number: ").append(users.size());
        usersInfo.add(0, SummaryInfo);
        return usersInfo;
    }
}
