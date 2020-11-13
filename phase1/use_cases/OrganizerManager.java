package use_cases;

import entities.Attendee;
import entities.Organizer;
import entities.Speaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.OrganizerManager class, this is the use case class to manage the Organizers for this conference.
 *
 */

public class OrganizerManager extends UserManager implements Serializable{

    private List<Organizer> organizers;

    /**
     * Creates an use_cases.OrganizerManager
     */
    public OrganizerManager(){
        organizers = new ArrayList<>();
    }


    public void createOrganizer(String userName, String email, String password) {
        Organizer organizer = new Organizer(userName, email, password);
        this.organizers.add(organizer);
        UserManager.users.add(organizer);
    }
}
