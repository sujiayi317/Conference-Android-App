package Presenter;

import controllers.OutputManager;

import java.util.ArrayList;

public class ViewAllSpeakerEvents {

    public ViewAllSpeakerEvents(){}

    public StringBuilder printAllSpeakerEvents(ArrayList<String> AllSpeakerEvents) {
        StringBuilder returnString = new StringBuilder("These are all the events you will be speaking at:\n");
        for (int i = 0 ; i < AllSpeakerEvents.size() ; i++){
            returnString.append(i).append(") :\t").append(AllSpeakerEvents.get(i)). append("\n");
        }
        return returnString;
    }
}
