package presenters;

import controllers.OutputManager;

public class SpeakerMenu {
    private static OutputManager output;

    public SpeakerMenu() { output = new OutputManager(); }
    public void printSpeakerMenu(String userID){
        StringBuilder returnString = new StringBuilder("Welcome Speaker: ");
        returnString.append(userID).append("\n");
        returnString.append(" 1) view all my events\n 2) view all my conversations\n 0) sign out\n");
        output.printPrompt(returnString);
    }
}
