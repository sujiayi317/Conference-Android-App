import java.io.Serializable;

/**
 * The User class, this is used as a superclass for Attendees, Speakers and Organizers.
 * It is abstract because it does not actually contain users.
 *
 */
public abstract class User implements Serializable {

    private final String firstName;
    private final String lastName;
    private String email;
    private final String userName;
    private String userID;

    public User(String firstName, String lastName, String email) {
        this.firstName = setFirstName(firstName);
        this.lastName = setFirstName(lastName);
        this.email = setEmail(email);
        this.userName = setUserName(firstName, lastName);
        this.userID = UUID.randomUUID().toString();
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
     * Set the value of firstName
     *
     * @param lastName
     */
    public void setLastNameName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get the value of username
     *
     * @return the value of lastName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Set the value of username
     *
     * @param firstName
     * @param lastName
     */
    public void setUserName(String firstName, String lastName) {
        this.userName = firstName + "_" + lastName;
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
     * This will be for the lists, as the list uses toString to display an Object
     *
     * @return Name format of Person
     */

    /**
     * Get the userID of this user
     *
     * @return the value of userID
     */
    public String getUserName() {
        return this.userID;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}