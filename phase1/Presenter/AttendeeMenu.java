package Presenter;

import controllers.OutputManager;
import entities.Attendee;

public class AttendeeMenu {
    private static OutputManager output;
    public AttendeeMenu(){
        output = new OutputManager();
    }
    public void printAttendeeMenu(String userID){
        StringBuilder returnString = new StringBuilder("Welcome");
        returnString.append(userID).append("\n");
        returnString.append("1) viewAllEvents\n 2)view all my events\n 3)View all my fiends\n 4)view all my message");
        output.printPrompt(returnString);

    }

}
