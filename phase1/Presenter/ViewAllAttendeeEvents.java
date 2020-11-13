package Presenter;


import java.util.ArrayList;

public class ViewAllAttendeeEvents {

    public ViewAllAttendeeEvents(){}

    public StringBuilder printAllAttendeeEvents(ArrayList<String> ALLAttendeeEvents){
        if (ALLAttendeeEvents.size() == 0){
            return new StringBuilder("You have not attended any events yet\nPlease go back to main menu and attend one\n");
        }
        StringBuilder returnString = new StringBuilder("There are all the events that you have attended\n");
        for (int i =0 ; i < ALLAttendeeEvents.size();i++){
            returnString.append(i).append(") ").append(ALLAttendeeEvents.get(i)).append("\n");
        }
        return returnString;
    }
}
