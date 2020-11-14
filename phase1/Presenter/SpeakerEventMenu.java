package Presenter;

import controllers.EventsController;
import Presenter.ViewEventInfo;

import java.util.ArrayList;

public class SpeakerEventMenu {

    public SpeakerEventMenu(){}

    public StringBuilder printEventMenu(ArrayList<String> AllEvents, ViewEventInfo viewEventInfo) {
        if (AllEvents.size() == 0){
            return new StringBuilder("You are not assigned any events yet\n");
        }
        StringBuilder returnString = viewEventInfo.getEventInfo(AllEvents);
        returnString.append("\n 1) make an announcement for the event\n 2) message individual attendee\n 0) Quit\n");
        return returnString;
    }
}
