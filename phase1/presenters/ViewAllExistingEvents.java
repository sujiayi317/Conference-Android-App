package presenters;

import entities.Event;

import java.util.List;

/**
 * The presenter for viewing all existing events
 */
public class ViewAllExistingEvents {

    public ViewAllExistingEvents(){}

    /**
     * Printing to the screen all the existing events
     * @param AllExistingEvents AllExistingEvents
     * @return StringBuilder
     */
    public StringBuilder printAllExistingEvents(List<Event> AllExistingEvents){
        StringBuilder returnString = new StringBuilder("\nThese are all the events you may attend:\n");
        for (int i =0 ; i < AllExistingEvents.size();i++){
            returnString.append(i).append(") ").append(AllExistingEvents.get(i).getTitle()).append("\n");
        }
        return returnString;
    }


}
