package unused.presenters;

import unused.controllers.OutputManager;

/**
 * The presenter for organizer's menu.
 */
public class OrganizerMenu {
    private static OutputManager output;


    public OrganizerMenu() {
        output = new OutputManager();
    }

    /**
     * Print organizer's menu
     */
    public void printOrganizerMenu(String userName) {
        StringBuilder returnString = new StringBuilder("\nWelcome Organizer: ");
        returnString.append(userName).append("\n");
        returnString.append(" 1) create a new speaker\n 2) view all existing events\n 3) view all my events\n " +
                "4) create new event \n " + "5) View all my fiends\n 6) view all my message" +
                "\n 7) send to all attendees of an event/events \n 8) create a room\n 9) add friend\n 0) sign out\n");
        output.printPrompt(returnString);

    }
}
