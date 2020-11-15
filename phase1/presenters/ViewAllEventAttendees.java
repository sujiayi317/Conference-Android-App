package presenters;

import java.util.ArrayList;

/**
 * The presenter for viewing all event attendees
 */
public class ViewAllEventAttendees {

    public ViewAllEventAttendees(){}

    /**
     * Printing to the screen all event attendees
     * @param ALLEventAttendees ALLEventAttendees
     * @return StringBuilder
     */
    public StringBuilder printAllEventAttendees(ArrayList<String> ALLEventAttendees){
        StringBuilder returnString = new StringBuilder("\nThese are all the attendees in the selected event:\n");
        for (int i =0 ; i < ALLEventAttendees.size();i++){
            returnString.append(i).append(") ").append(ALLEventAttendees.get(i)).append("\n");
        }
        return returnString;
    }
}
