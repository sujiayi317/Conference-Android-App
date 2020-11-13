package Presenter;

import controllers.OutputManager;
import entities.Speaker;

public class SpeakerMenu {
    private static OutputManager output;

    public SpeakerMenu() { output = new OutputManager(); }
    public void printSpeakerMenu(String userID){
        StringBuilder returnString = new StringBuilder("Welcome");
        returnString.append(userID).append("\n");
        returnString.append(" 1) view all my events\n ");
        output.printPrompt(returnString);
    }
}
