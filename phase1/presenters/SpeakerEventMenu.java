package presenters;

import controllers.EventsController;

import java.util.ArrayList;

/**
 * The presenter for SpeakerEvent's menu.
 */
public class SpeakerEventMenu {

    public SpeakerEventMenu(){}

    /**
     * Return a StringBuilder for printing the event menu
     *
     * @param eventID eventID
     * @param viewEventInfo viewEventInfo
     * @param eventsController eventsController
     * @return a StringBuilder for printing the event menu
     */
    public StringBuilder printEventMenu(String eventID, ViewEventInfo viewEventInfo, EventsController eventsController) {
        ArrayList<String> eventInfoList = eventsController.getEventInfo(eventID);
        StringBuilder returnString = viewEventInfo.getEventInfo(eventInfoList);
        returnString.append("\n 1) make an announcement for the event\n 2) message individual attendee\n 0) Quit\n");
        return returnString;
    }
}
