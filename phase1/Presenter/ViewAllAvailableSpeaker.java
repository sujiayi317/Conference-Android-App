package Presenter;

import entities.Event;

import java.util.ArrayList;
import java.util.List;

public class ViewAllAvailableSpeaker {
    public ViewAllAvailableSpeaker(){}

    public StringBuilder printAllAvailableSpeaker(ArrayList<String> AllAvailableSpeake){
        StringBuilder returnString = new StringBuilder("There are all available speaker you may choose:\n");
        for (int i =0 ; i < AllAvailableSpeake.size();i++){
            returnString.append(i).append(") :\t").append(AllAvailableSpeake.get(i)).append("\n");
        }
        return returnString;
    }
}
