package use_cases;

import com.example.a207_demo.entities.*;
import entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.OrganizerManager class, this is the use case class to manage the Organizers for this conference.
 */

public class OrganizerManager extends UserManager implements Serializable {

    private List<Organizer> organizers;

    /**
     * Creates an use_cases.OrganizerManager
     */
    public OrganizerManager() {
        organizers = new ArrayList<>();
    }

    /**
     * Reset the organizers: no user exists
     */
    public void reset() {
        organizers = new ArrayList<>();
    }


    /**
     * Create an organizer account and store it in the organizers and users lists
     *
     * @param userName userName of this organizer
     * @param email    email of this organizer
     * @param password password of this organizer
     */
    public void createOrganizer(String userName, String email, String password) {
        Organizer organizer = new Organizer(userName, email, password);
        this.organizers.add(organizer);
        super.addUser(organizer);
    }

    /**
     * Create an organizer account (with parameter: ID) and store it in the organizers and users lists
     *
     * @param userName userName of this organizer
     * @param email    email of this organizer
     * @param password password of this organizer
     * @param ID       user ID of this organizer
     */
    public void loadOrganizer(String userName, String email, String password, String ID) {
        Organizer organizer = new Organizer(userName, email, password, ID);
        this.organizers.add(organizer);
        super.addUser(organizer);
    }

    public ArrayList<StringBuilder> getAllOrganizerInfo(){
        ArrayList<StringBuilder> usersInfo = new ArrayList<>();
        for (int i = 0; i < organizers.size(); i++){
            StringBuilder singleInfo = new StringBuilder();
            User currentUser = organizers.get(i);
            singleInfo.append(i).append(") ").append(currentUser.getUserName()).append(" ").append(currentUser.getType());
            usersInfo.add(singleInfo);
        }
        StringBuilder SummaryInfo = new StringBuilder();
        SummaryInfo.append("Total Number: ").append(organizers.size());
        usersInfo.add(0, SummaryInfo);
        return usersInfo;
    }


}
