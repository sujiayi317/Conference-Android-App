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

    public boolean validNewOrganizerEmail(String email){
        for (Organizer organizer : organizers){
            if (organizer.getEmail().equals(email)){
                return false;
            }
        }
        return true;
    }

    public boolean validNewOrganizerName(String name){
        for (Organizer organizer : organizers){
            if (organizer.getUserName().equals(name)){
                return false;
            }
        }
        return true;
    }

    public String validLogIn(String account, String password){
        for (Organizer organizer : organizers){
            if (organizer.getEmail().equals(account) && organizer.getPassword().equals(password)){
                return "VALID";
            } else if (organizer.getEmail().equals(account)){
                return "PASSWORD WRONG";
            }
        }
        return "NULL";
    }

}
