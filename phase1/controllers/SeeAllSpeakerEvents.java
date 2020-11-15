package controllers;

import Presenter.ViewAllSpeakerEvents;

public class SeeAllSpeakerEvents {
    private static OutputManager output;
    public SeeAllSpeakerEvents(){ output = new OutputManager(); }
    public void getToSeeAllSpeakerEvents(ViewAllSpeakerEvents viewAllSpeakerEvents, EventsController eventsController,
                                         String userID) {
        output.printPrompt(viewAllSpeakerEvents.printAllSpeakerEvents(
                eventsController.getAllEventsForTheSpeaker(userID), eventsController));
    }
}
