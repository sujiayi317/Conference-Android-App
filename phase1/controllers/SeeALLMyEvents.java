package controllers;

import presenters.ViewAllAttendeeEvents;

/**
 * This is the class for viewing all my events
 */
public class SeeALLMyEvents {
    private static OutputManager output;

    /**
     * SeeALLMyEvents ...
     */
    public SeeALLMyEvents() {
        output = new OutputManager();
    }

    /**
     *
     * @param viewAllAttendeeEvents viewAllAttendeeEvents
     * @param eventsController eventsController
     * @param userID userID
     */
    public void getToSeeAllMyEvents(ViewAllAttendeeEvents viewAllAttendeeEvents,
                                    EventsController eventsController, String userID) {
        output.printPrompt(viewAllAttendeeEvents.printAllAttendeeEvents(eventsController.getALLAttendeeEvents(userID), eventsController));
    }
}
