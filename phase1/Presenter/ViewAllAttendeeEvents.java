package Presenter;

import controllers.OutputManager;

import java.util.ArrayList;

public class ViewAllAttendeeEvents {

    public ViewAllAttendeeEvents(){}

    public StringBuilder printAllAttendeeEvents(ArrayList<String> ALLAttendeeEvents){
        StringBuilder returnString = new StringBuilder("There are all the events that you have attended:");
        for (int i =0 ; i < ALLAttendeeEvents.size();i++){
            returnString.append(i).append(":").append(ALLAttendeeEvents.get(i)).append("\n");
        }
        return returnString;
    }
}
