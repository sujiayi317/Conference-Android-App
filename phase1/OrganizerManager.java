import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The OrganizerManager class, this is the use case class to manage the Organizers for this conference.
 *
 */

public class OrganizerManager implements Serializable{

    private List<Organizer> organizers;

    /**
     * Creates an OrganizerManager
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
