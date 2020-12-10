package unused.presenters;

import unused.controllers.OutputManager;

public class ViewAllEventType {
    private static OutputManager output;

    public ViewAllEventType(){
        output = new OutputManager();
    }

    public void printALlEventType(){
        StringBuilder returnString = new StringBuilder("\nBelow are all event-types you may choose: ");
        returnString.append(" 0) TALK\n 1) PARTY\n 2) DISCUSSION\n");
        output.printPrompt(returnString);
    }
}
