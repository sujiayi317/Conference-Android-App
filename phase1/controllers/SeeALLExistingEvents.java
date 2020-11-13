package controllers;

import Presenter.ViewAllExistingEvents;
import Presenter.ViewEventInfo;
import use_cases.AttendeeManager;

import java.util.ArrayList;

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
            output.printPrompt("There is no event yet!\nPlease go back to main menu to create one if you're the Organizer");
        }
        int check = 0;
        while (check != 1 && eventsController.getAllExistingEvents().size() != 0) {
            viewAllEvents(viewAllExistingEvents, eventsController);
            String eventTitle = input.getInputString("Please choose an event_title and see the details OR press enter to back\n");
            if (!eventTitle.equals("")) {
                String eventID = eventsController.getEventManager().changeEventTitleIntoEventID(eventTitle);
                viewOneEventInfo(eventID, viewEventInfo, eventsController);
                String decision = input.getInputString("Yes=sign up OR No\n");
                if (decision.equals("Yes")) {
                    if (attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                            eventsController.getRoomManager())){
                    output.printPrompt("You're successfully in " + eventTitle + "\n");
                    check += 1;}
                }
            }
            else{
                check +=1;
            }
        }
    }
    public static void viewAllEvents(ViewAllExistingEvents viewAllExistingEvents, EventsController eventsController){
        output.printPrompt(viewAllExistingEvents.printAllExistingEvents(eventsController.getAllExistingEvents()));
    }
    public static void viewOneEventInfo(String eventID, ViewEventInfo viewEventInfo, EventsController eventsController){
        ArrayList<String> eventInfoList = eventsController.getEventInfo(eventID);
        output.printPrompt(viewEventInfo.getEventInfo(eventInfoList));
    }
}
