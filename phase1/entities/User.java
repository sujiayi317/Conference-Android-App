package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The entities.User class, this is used as a superclass for Attendees, Speakers and Organizers.
 * It is abstract because it does not actually contain users.
 */
public abstract class User implements Serializable {

    //private String firstName;
    //private String lastName;
    private String userName;
    private String email;
    private String password;
    private String type;
    private ArrayList<String> friendList;

    public User(String email, String userName, String password) {
        //this.firstName = firstName;
        //this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.friendList = new ArrayList<String>();
    }

    public ArrayList<String> friendListGetter() {
        return (ArrayList) friendList.clone();
    }

    public void friendListSetter(String userId) {
        friendList.add(userId);
    }

    /**
     * Get the type of this user
     *
     * @return the type of this user
     */
    public String getType() {
        return type;
    }

    public abstract String getUserID();

    /**
     * Set the type of this user
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the value of userName
     *
     * @return the value of lastName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Set the value of userName
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }


    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Get the userID of this user
     *
     * @return the value of userID
     */

    /**
     * Get the password of this user
     *
     * @return the value of password
     */
    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return String.format("entities.User: %s", this.userName);
    }
}