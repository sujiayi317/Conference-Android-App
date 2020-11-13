package Presenter;

import java.util.ArrayList;

public class ViewAllEventAttendees {

    public ViewAllEventAttendees(){}

    public StringBuilder printAllEventAttendees(ArrayList<String> ALLEventAttendees){
        StringBuilder returnString = new StringBuilder("There are the attendees of the selected event:\n");
        for (int i =0 ; i < ALLEventAttendees.size();i++){
            returnString.append(i).append(") ").append(ALLEventAttendees.get(i)).append("\n");
        }
        return returnString;
    }
}
