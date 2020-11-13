package Presenter;

import controllers.OutputManager;

public class OrganizerMenu {
    private static OutputManager output;

    public OrganizerMenu(){
        output = new OutputManager();
    }
    public void printOrganizerMenu(String userID){
        StringBuilder returnString = new StringBuilder("Welcome");
        returnString.append(userID).append("\n");
        returnString.append(" 1) create a new speaker\n 2) viewAllEvents\n 3)view all my events\n 4) create new event \n " +
                "5)View all my fiends\n 6)view all my message" +
                "\n 7) send message \n 8) send to all attendees\n 9) create a room\n 0) quit");
        output.printPrompt(returnString);

    }
}
