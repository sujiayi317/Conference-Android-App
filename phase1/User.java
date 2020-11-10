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

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = firstName + " " + lastName;
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
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the value of username
     *
     * @return the value of lastName
     */
    public String getUserName() {
        return userName;
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
     * @return Name format of Person
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.firstName, this.lastName);
    }
}