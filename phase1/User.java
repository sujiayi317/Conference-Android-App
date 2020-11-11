import java.io.Serializable;

/**
 * The User class, this is used as a superclass for Attendees, Speakers and Organizers.
 * It is abstract because it does not actually contain users.
 *
 */
public abstract class User implements Serializable {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;

    public User(String firstName, String lastName, String email) {
        this.firstName = setFirstName(firstName);
        this.lastName = setFirstName(lastName);
        this.email = setEmail(email);
        this.userName = getFirstName() + "_" + getLastName() + "_"
                + getUserID().substring(0, 3);
        this.userID = UUID.randomUUID().toString().split("-")[0];
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
    public String getUserID() {
        return this.userID;
    }


    @Override
    public String toString() {
        return String.format("User: %s %s", this.firstName, this.lastName);
    }
}