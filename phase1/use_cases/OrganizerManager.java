package use_cases;

import entities.Organizer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.OrganizerManager class, this is the use case class to manage the Organizers for this conference.
 *
 */

public class OrganizerManager implements Serializable{

    private List<Organizer> organizers;

    /**
     * Creates an use_cases.OrganizerManager
     */
    public OrganizerManager(){
        organizers = new ArrayList<>();
    }


    public Organizer createOrganizer(String userName, String email, String password) {
        Organizer organizer = new Organizer(userName, email, password);
        organizers.add(organizer);
        return organizer;
    }
}