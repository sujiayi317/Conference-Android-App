package presenters;

import controllers.EventsController;
import java.util.ArrayList;

/**
 * The presenter for VIP-Only events.
 */
public class ViewAllVIPEvents {

    public ViewAllVIPEvents(){}

    /**
     * The presenter for all existing VIP-ONLY events.
     * @param ALLVIPEvents allVIPEvents
     * @param eventsController eventsController
     * @return StringBuilder all the existing VIP-ONLY events
     */
    public StringBuilder printAllVIPEvents(ArrayList<String> ALLVIPEvents, EventsController eventsController) {
        if (ALLVIPEvents.size() == 0) {
            return new StringBuilder("There isn't any VIP-ONLY events yet\n");
        }
        StringBuilder returnString = new StringBuilder("\nThese are all the VIP-ONLY events:\n");
        for (int i = 0; i < ALLVIPEvents.size(); i++) {
            returnString.append(i).append(") ").append(eventsController.getEventManager().getEventFromID(ALLVIPEvents.get(i)).getTitle()).append("\n");
        }
        return returnString;
    }
}
