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
        returnString.append(" 1) Create a new accountr\n 2) view all existing events\n 3) view all my events\n " +
                "4) create new event \n " + "5) View all my fiends\n 6) view all my message" +
                "\n 7) send to all attendees of an event/events \n 8) create a room\n 9) add friend\n " +
                "10) cancel an event\n 11) See all User Stat\n 12) //See all Event Enrollment Stat\n 13) get Top5Event Info\n 0) sign out\n");
        output.printPrompt(returnString);

    }
}
