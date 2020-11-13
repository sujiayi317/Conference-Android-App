package Presenter;

import entities.Event;

import java.util.List;

public class ViewAllAvailableSpeaker {
    public ViewAllAvailableSpeaker(){}
    public StringBuilder printAllAvailableSpeaker(List<Event> AllAvailableSpeake){
        StringBuilder returnString = new StringBuilder("There are all available speaker you may choose:");
        for (int i =0 ; i < AllAvailableSpeake.size();i++){
            returnString.append(i).append(":").append(AllAvailableSpeake.get(i).getEventID()).append("\n");
        }
        return returnString;
    }
}
