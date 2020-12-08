package com.example.a207_demo.use_cases;

import com.example.a207_demo.entities.*;
import com.example.a207_demo.eventSystem.Event;

import java.io.Serializable;
import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.UserManager class, this is the use case class to manage the entities.User for this conference.
 */
public class UserManager implements Serializable {
    public static List<User> users = new ArrayList<>();

    /**
     * Reset the users: no user exists
     */
    public void reset() {
        users = new ArrayList<>();
    }

    /**
     * Add another user's userID to the current user's friend list.
     *
     * @param currId1 current user ID
     * @param userId2 another user ID
     * @return true iff another user is successfully added to the current user's friendList
     */
    public boolean addFriend(String currId1, String userId2) {
        List<String> idList = getUserIDs();
        if (idList.contains(currId1) && idList.contains(userId2)) {
            for (User user : users) {
                if (user.getUserID().equals(currId1)) {
                    if (!user.getFriendList().contains(userId2)) {
                        user.setFriendList(userId2);
                    }
                }if (user.getUserID().equals(userId2)) {
                    if (!user.getFriendList().contains(currId1)) {
                        user.setFriendList(currId1);
                    }
                }
            }
            return true;
        }
        return false;
    }

    protected boolean setAnnouncement(String userID, String eventTitle, String announcement){
        for(User user : users){
            if(user.getUserID().equals(userID)){
                user.addAnnouncement("From event '" + eventTitle + "': " + announcement);
                return true;
            }
        }
        return false;
    }

    protected boolean setAnnouncement(ArrayList<String> userIDs, String eventTitle, String announcement){
        for(String userID : userIDs){
            boolean hasUser = false;
            for(User user : users){
                if(user.getUserID().equals(userID)){
                    user.addAnnouncement("From '" + eventTitle + "': " + announcement);
                    hasUser = true;
                    break;
                }
            }
            if(!hasUser){
                return false;
            }
        }

        return true;
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
     * Return the String user type of a user given his account and password, or "NULL" if not found
     *
     * @param account  String email of the user
     * @param password String password of the user
     * @return the String user type of this user given his account and password, or "NULL"
     */
    public String getUserType(String account, String password) {
        for (User user : users) {
            if (user.getEmail().equals(account) && user.getPassword().equals(password)) {
                return user.getType();
            }
        }
        return "NULL";
    }

    /**
     * Return List<String> of user IDs
     *
     * @return List<String> of user IDs
     */
    public List<String> getUserIDs() {
        ArrayList<String> UserIds = new ArrayList<>();
        for (User user : users) {
            UserIds.add(user.getUserID());
        }
        return UserIds;
    }

    /**
     * Given a user ID, return the corresponding user name
     *
     * @param userId user ID
     * @return the corresponding user name
     */
    public String getUserNameFromID(String userId) {
        for (User user : users) {
            if (user.getUserID().equals(userId)) {
                return user.getUserName();
            }
        }
        return null;
    }

    /**
     * Given a user ID, return the corresponding user name
     *
     * @param userIDs the user IDS
     * @return the corresponding user names
     */
    public ArrayList<String> getUserNamesFromID(List<String> userIDs) {
        ArrayList<String> names = new ArrayList<>();
        for(String userID : userIDs){
            names.add(getUserNameFromID(userID));
        }
        return names;
    }


    /**
     * Given a user name, return the corresponding user id
     *
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

    public ArrayList<String> getUserIdsFromName(List<String> userNames){
        ArrayList<String> ids = new ArrayList<>();
        for(String userName : userNames){
            ids.add(getUserIdFromName(userName));
        }
        return ids;
    }

    /**
     * Get the friend list of the user with user ID
     *
     * @param userID String user ID
     * @return the friend list of the user with user ID or null if this user does not exist
     */
    public ArrayList<String> friendListGetter(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user.getFriendList();
            }
        }
        return null;
    }

    /**
     * Get the announcement list of a user
     * @param userID ID of the user
     * @return a list of announcements
     */
    public ArrayList<String> getAnnouncements(String userID){
        for (User user : users){
            if(user.getUserID().equals(userID)){
                return user.getAnnouncements();
            }
        }
        return null;
    }

    /**
     * Check if the given String is a valid new email
     *
     * @param email String email to be checked
     * @return true iff the email is a valid new email
     */
    public boolean validEmail(String email) {
        return validNewEmail(email) && emailFormat(email);
    }

    private boolean validNewEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    private boolean emailFormat(String email) {
        return email.length() >= 6 && email.contains("@") && email.charAt(0) != '@' && email.contains(".") &&
                email.charAt(email.length() - 1) != '.' && email.length() - email.replace(".", "").length() == 1 &&
                email.length() - email.replace("@", "").length() == 1 && email.indexOf('@') < email.indexOf('.') &&
                email.indexOf('@') != email.indexOf('.') - 1;
    }

    /**
     * Check if the given String is a valid new user Name
     *
     * @param name String name to be checked
     * @return true iff the name is a valid new user Name
     */
    public boolean validName(String name) {
        return validNewName(name) && name.length() >= 2;
    }

    private boolean validNewName(String name) {
        for (User user : users) {
            if (user.getUserName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the String user ID of a user given his account and password, or "NULL" if not found
     *
     * @param account  String email of the user
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
     * Generate the formatted user's information.
     *
     * @param userID the id of an user.
     * @return a string of formatted event's information.
     */
    public String generateFormattedUserInfo(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                String info = user.getType() + " " + user.getUserName() + " " + user.getEmail()
                        + " " + user.getPassword() + " " + userID;
                if(user.getAnnouncements() != null){
                    info += " &" + user.getAnnouncements() +"&";
                }
                return info;
            }
        }
        return "NULL";
    }

    /**
     * Generate the formatted user's information.
     *
     *
     * @return a string of formatted event's information.
     */
    public  ArrayList<ArrayList<String>> generateFormattedFriendInfo(String userID) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        for (String friendID: friendListGetter(userID)){
            ArrayList<String> info = new ArrayList<>();

            info.add(friendID);
            info.add(getUserNameFromID(friendID));
            result.add(info);
        }
        return result;
    }

    public ArrayList<ArrayList<String>> generateIDNameInfo(ArrayList<String> userIDs){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(String userID : userIDs){
            ArrayList<String> IDNamePair = new ArrayList<>();
            IDNamePair.add(userID);
            IDNamePair.add(getUserNameFromID(userID));
            result.add(IDNamePair);
        }
        return result;
    }

    public ArrayList<ArrayList<String>> generateAccountInfo(){
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        for(User user : users){
            ArrayList<String> info = new ArrayList<>();
            info.add(user.getUserName());
            info.add(user.getType());
            info.add(user.getEmail());
            info.add(user.getUserID());
            result.add(info);
        }
        return result;
    }
}
