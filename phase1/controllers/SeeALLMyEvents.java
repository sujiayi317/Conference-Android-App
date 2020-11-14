package controllers;

import Presenter.ViewAllAttendeeEvents;

public class SeeALLMyEvents {
    private static OutputManager output;
    public SeeALLMyEvents(){
        output = new OutputManager();
    }
    public void getToSeeAllMyEvents(ViewAllAttendeeEvents viewAllAttendeeEvents,
                                    EventsController eventsController, String userID){
        output.printPrompt(viewAllAttendeeEvents.printAllAttendeeEvents(eventsController.getALLAttendeeEvents(userID), eventsController));
    }
}
