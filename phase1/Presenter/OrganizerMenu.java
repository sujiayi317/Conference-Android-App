package Presenter;

import controllers.OutputManager;

public class OrganizerMenu {
    private static OutputManager output;

    public OrganizerMenu(){
        output = new OutputManager();
    }
    public void printOrganizerMenu(String userName){
        StringBuilder returnString = new StringBuilder("\nWelcome Organizer: ");
        returnString.append(userName).append("\n");
        returnString.append(" 1) create a new speaker\n 2) viewAllEvents\n 3) view all my events\n 4) create new event \n " +
                "5) View all my fiends\n 6) view all my message" +
                "\n 7) send to all attendees of an event/events \n 8) create a room\n 9) add friend\n 0) quit\n");
        output.printPrompt(returnString);

    }
}
