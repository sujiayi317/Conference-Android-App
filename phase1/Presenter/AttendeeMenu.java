package Presenter;

import controllers.OutputManager;
import entities.Attendee;

public class AttendeeMenu {
    private static OutputManager output;

    public AttendeeMenu(){
        output = new OutputManager();
    }
    public void printAttendeeMenu(String userName){
        StringBuilder returnString = new StringBuilder("\nWelcome Attendee: ");
        returnString.append(userName).append("\n");
        returnString.append(" 1) viewAllEvents\n 2) view all my events\n 3) View all my fiends\n 4) view all my message" +
                "\n 5) add friend\n 0) quit\n");
        output.printPrompt(returnString);

    }

}
