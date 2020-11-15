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
            String eventNum = Integer.toString(input.getInputInt("Please choose an Event_Num and see the details OR press enter to back\n"));
            if ((!eventNum.equals("")) && eventsController.getAllExistingEvents().size() > Integer.parseInt(eventNum) && Integer.parseInt(eventNum) >= 0) {
                String eventTitle = eventsController.getAllExistingEvents().get(Integer.parseInt(eventNum)).getTitle();
                if (!eventTitle.equals("")) {
                    String eventID = eventsController.getEventManager().changeEventTitleIntoEventID(eventTitle);
                    viewOneEventInfo(eventID, viewEventInfo, eventsController);
                    String decision = input.getInputString("Press: Yes to sign up OR No to back menu\n");
                    if (decision.equals("Yes")) {
                        if (attendeeManager.signUp(eventsController.getEventManager(), userID, eventID,
                                eventsController.getRoomManager())) {
                            output.printPrompt("You're successfully in " + eventTitle + "\n");
                            check += 1;
                        }
                        else if (eventsController.getRoomManager().isFull(eventsController.getEventManager().getEventFromID(eventID).getRoomID())){
                            output.printPrompt("Sorry, the current room is full\n");
                        }
                        else {
                        output.printPrompt("You're already in " + eventTitle + "\n");}
                    }
                }
            }
            else if ((!eventNum.equals("") && (Integer.parseInt(eventNum) >= eventsController.getAllExistingEvents().size() || Integer.parseInt(eventNum) < 0))){
                output.printPrompt("The Event_Num is out of bound please enter the correct one\n");
            }
            else {check +=1;}
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
