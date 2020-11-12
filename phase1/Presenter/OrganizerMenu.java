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
        returnString.append(" 1) viewAllEvents\n 2)view all my events\n 3) create new event \n 4)View all my fiends\n 5)view all my message" +
                "\n 6) send message \n 7) send to all attendees");
        output.printPrompt(returnString);

    }
}
