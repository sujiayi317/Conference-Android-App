package Presenter;

import controllers.OutputManager;

import java.util.ArrayList;

public class ViewAllAttendeeEvents {
    private static OutputManager output = new OutputManager();

    public void printAllAttendeeEvents(ArrayList<String> ALLAttendeeEvents){
        StringBuilder returnString = new StringBuilder("There are all the events that you have attended:");
        for (int i =0 ; i < ALLAttendeeEvents.size();i++){
            returnString.append(i).append(":").append(ALLAttendeeEvents.get(i)).append("\n");
        }
        output.printPrompt(returnString);
    }
}
