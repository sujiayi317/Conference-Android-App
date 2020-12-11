package unused.presenters;

import unused.controllers.OutputManager;

/**
 * The presenter for Speaker's menu.
 */
public class SpeakerMenu {

    private static OutputManager output;

    public SpeakerMenu() {
        output = new OutputManager();
    }

    /**
     * Print prompt menu for this speaker
     * @param userID userID
     */
    public void printSpeakerMenu(String userID) {
        StringBuilder returnString = new StringBuilder("Welcome Speaker: ");
        returnString.append(userID).append("\n");
        returnString.append(" 1) view all my events\n 2) view all my conversations\n 0) sign out\n");
        output.printPrompt(returnString);
    }
}
