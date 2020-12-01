package entities;

import java.util.ArrayList;
import java.util.List;

public interface Addtendable {
    ArrayList<String> getSpeakers();

    String getTitle();

    String getRoomID();

    String getStartTime();

    String getEventID();

    String getDuration();

    boolean removeAttendee(String userID);

    ArrayList<String> getAttendees();

    boolean addAttendee(String userID, List<Addtendable> addtendableList);

}
