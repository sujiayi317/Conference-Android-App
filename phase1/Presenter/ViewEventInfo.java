package Presenter;

import java.util.ArrayList;

public class ViewEventInfo {
    private ArrayList<String> infoPattern;
    public void ViewEventInfo(){
        this.infoPattern.add("Title");
        this.infoPattern.add("Time");
        this.infoPattern.add("Speaker");
        this.infoPattern.add("Speakers");
        this.infoPattern.add("Current_Capacity");

    }
    public void getEventInfo(ArrayList<String> eventInfo){
        StringBuilder returnString = new StringBuilder("There are all existing events you may attend:");
        for (int i =0 ; i < eventInfo.size()-2;i++){
            returnString.append(this.infoPattern.get(i)).append(":").append(eventInfo.get(i)).append("\n");
        }
        returnString.append(this.infoPattern.get(3)).append(":").append(eventInfo.get(3)).append("/").append(eventInfo.get(4));
        System.out.println(returnString);
    }

    }
}
