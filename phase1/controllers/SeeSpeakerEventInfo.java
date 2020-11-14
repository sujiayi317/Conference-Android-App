package controllers;

import Presenter.SpeakerEventMenu;
import Presenter.ViewEventInfo;

import java.util.ArrayList;

public class SeeSpeakerEventInfo {
    private static OutputManager output;
    public SeeSpeakerEventInfo() {output = new OutputManager();}
    public void getToSeeEventInfo(SpeakerEventMenu speakerEventMenu, ViewEventInfo viewEventInfo,
                                  EventsController eventsController, String eventID){
        output.printPrompt(speakerEventMenu.printEventMenu(eventsController.getAllEventsForTheSpeaker(eventID),
                viewEventInfo));
    }

}
