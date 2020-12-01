package controllers;

import presenters.ViewAllExistingEvents;

public class cancelEvent {
    private static OutputManager output;
    private static InputManager input;

    public cancelEvent() {
        output = new OutputManager();
        input = new InputManager();
    }

    public void toCancelEvent(EventsController eventsController, ViewAllExistingEvents viewAllExistingEvents) {
        if (eventsController.getAllExistingEvents().size() == 0) {
            output.printPrompt("There is no event yet!\nPlease go back to main menu \nto create one if you're an Organizer");
        }
        int check = 0;
        while (check != 1 && eventsController.getAllExistingEvents().size() != 0) {
            viewAllExistingEvents.printAllExistingEvents(eventsController.getAllExistingEvents());
            String eventNum = Integer.toString(input.getInputInt("Please enter the event number to cancel it, \n" +
                    "Or, press enter to" +
                    " go back to main menu\n"));
            if ((!eventNum.equals("666")) && eventsController.getAllExistingEvents().size() > Integer.parseInt(eventNum)
                    && Integer.parseInt(eventNum) >= 0) {
                String eventID = eventsController.getAllExistingEvents().get(Integer.parseInt(eventNum)).getEventID();
                String eventTitle = eventsController.getAllExistingEvents().get(Integer.parseInt(eventNum)).getTitle();
                if (eventsController.cancelEvent(eventID)) {
                    check += 1;
                    output.printPrompt("Event named " + eventTitle + " has been canceled");

                }
            }
        }
    }
}