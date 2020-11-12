package Presenter;

import controllers.OutputManager;
import entities.Event;

import java.util.List;

public class ViewAllExistingEvents {
    private static OutputManager output;

    public ViewAllExistingEvents(){
        output = new OutputManager();
    }
    public void printAllExistingEvents(List<Event> AllExistingEvents){
        StringBuilder returnString = new StringBuilder("There are all existing events you may attend:");
        for (int i =0 ; i < AllExistingEvents.size();i++){
            returnString.append(i).append(":").append(AllExistingEvents.get(i).getEventID()).append("\n");
        }
        output.printPrompt(returnString);
    }


}
