package controllers;

import presenters.ViewAllVIPEvents;

/**
 * This is the class for viewing all existing VIP-ONLY events
 */
public class SeeALLVIPEvents {
    private static OutputManager output;

    /**
     * SeeALLVIPEvents ...
     */
    public SeeALLVIPEvents() {output = new OutputManager();}

    /**
     *
     * @param viewAllVIPEvents viewALLVIPEvents
     * @param eventsController eventController
     */
    public void getToSeeAllVIPEvents(ViewAllVIPEvents viewAllVIPEvents, EventsController eventsController) {
        output.printPrompt(viewAllVIPEvents.printAllVIPEvents(eventsController.getAllVIPEvents(), eventsController));
    }
}
