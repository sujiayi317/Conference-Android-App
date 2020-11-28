package entities;

import java.util.ArrayList;
import java.util.List;

public interface Addtendable {
    public ArrayList<String> getSpeakers();

    public String getTitle();

    public  String getRoomID();

    public String getStartTime();

    public  String getEventID();

    public boolean removeAttendee(String userID);

    public ArrayList<String> getAttendees();

    public boolean addAttendee(String userID, List<Addtendable> addtendableList);

}
