package Presenter;

import controllers.EventsController;
import controllers.OutputManager;

import java.util.ArrayList;

public class ViewAllSpeakerEvents {

    public ViewAllSpeakerEvents(){}

    public StringBuilder printAllSpeakerEvents(ArrayList<String> AllSpeakerEvents, EventsController eventsController) {
        if (AllSpeakerEvents.size() == 0) {
            return new StringBuilder("You have not been assigned any events yet.\n");
        }
        StringBuilder returnString = new StringBuilder("These are all the events you will be speaking at:\n");
        for (int i = 0 ; i < AllSpeakerEvents.size() ; i++){
            returnString.append(i).append(") ").append(
                    eventsController.getEventManager().getEventFromID(AllSpeakerEvents.get(i)).getTitle()). append("\n");
        }
        return returnString;
    }
}
