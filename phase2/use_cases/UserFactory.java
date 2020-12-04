package use_cases;

import entities.*;

public class UserFactory {
    public User createANewUser(String email, String userName, String password, String type){
        switch (type) {
            case "ORGANIZER":
                return new Organizer(email, userName, password);
            case "VIPUser":
                return new VIPUser(email, userName, password);
            case "ATTENDEE":
                return new Attendee(email, userName, password);
            case "SPEAKER":
                return new Speaker(email, userName, password);
        }
        return null;
    }
}
