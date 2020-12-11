package unused.presenters;

import unused.controllers.EventsController;
import java.util.ArrayList;

/**
 * The presenter for Attendee's events.
 */
public class ViewAllAttendeeEvents {

    public ViewAllAttendeeEvents() {
    }

    /**
     * The presenter for Attendee's events.
     *
     * @param ALLAttendeeEvents ALLAttendeeEvents
     * @param eventsController eventsController
     * @return StringBuilder all the events that an Attendee is signed up
     */
    public StringBuilder printAllAttendeeEvents(ArrayList<String> ALLAttendeeEvents, EventsController eventsController) {
        if (ALLAttendeeEvents.size() == 0) {
            return new StringBuilder("You have not attended any events yet\nPlease go back to main menu and attend one\n");
        }
        StringBuilder returnString = new StringBuilder("\nThese are all the events that you are signed up:\n");
        for (int i = 0; i < ALLAttendeeEvents.size(); i++) {
            returnString.append(i).append(") ").append(eventsController.getEventManager().getEventFromID(ALLAttendeeEvents.get(i)).getTitle()).append("\n");
        }
        return returnString;
    }
}
