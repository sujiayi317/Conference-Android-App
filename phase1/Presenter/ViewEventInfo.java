package Presenter;

import controllers.OutputManager;

import java.util.ArrayList;

public class ViewEventInfo {
    private final ArrayList<String> infoPattern;

    public ViewEventInfo(){
        this.infoPattern = new ArrayList<>();
        this.infoPattern.add("Title");
        this.infoPattern.add("Time");
        this.infoPattern.add("Speaker");
        this.infoPattern.add("Speakers");
        this.infoPattern.add("Current_Capacity");

    }
    public StringBuilder getEventInfo(ArrayList<String> eventInfo){
        StringBuilder returnString = new StringBuilder("There are all existing events you may attend:");
        for (int i =0 ; i < eventInfo.size()-2;i++){
            returnString.append(this.infoPattern.get(i)).append(") :\t").append(eventInfo.get(i)).append("\n");
        }
        returnString.append(this.infoPattern.get(3)).append(") :\t").append(eventInfo.get(3)).append("/").append(eventInfo.get(4)).append("\n");
        return returnString;
    }

    }
