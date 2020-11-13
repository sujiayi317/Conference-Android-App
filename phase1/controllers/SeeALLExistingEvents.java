package controllers;

import Presenter.ViewAllExistingEvents;
import Presenter.ViewEventInfo;
import use_cases.AttendeeManager;

public class SeeALLExistingEvents {
    private static OutputManager output;
    private static InputManager input;
    public SeeALLExistingEvents(){
        output = new OutputManager();
        input = new InputManager();
    }
    public void getToSeeAllExistingEvents(EventsController eventsController, AttendeeManager attendeeManager,
                                          ViewAllExistingEvents viewAllExistingEvents, String userID, ViewEventInfo viewEventInfo){
        if (eventsController.getAllExistingEvents().size() == 0){
            output.printPrompt("There is no event yet!");
        }
        int check = 0;
        while (check != 1 && eventsController.getAllExistingEvents().size() != 0) {
            viewAllEvents(viewAllExistingEvents, eventsController);
            String eventID = input.getInputString("Please choose an event_title and see the details or press enter\n");
            if (!eventID.equals("")) {
                OrganizerController.viewOneEventInfo(eventID, viewEventInfo, eventsController);
                String decision = input.getInputString("Yes=sign up OR No\n");
                if (decision.equals("Yes")) {
                    attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                            eventsController.getRoomManager());
                    output.printPrompt("you're successfully in " + eventID + "\n");
                    check += 1;
                }
            }
        }
    }
    public static void viewAllEvents(ViewAllExistingEvents viewAllExistingEvents, EventsController eventsController){
        output.printPrompt(viewAllExistingEvents.printAllExistingEvents(eventsController.getAllExistingEvents()));
    }
}
