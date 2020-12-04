package presenters;

import controllers.OutputManager;

public class ViewAllTypeOfAccount {
    private static OutputManager output;

    public ViewAllTypeOfAccount(){
        output = new OutputManager();
    }

    public void printAllTypeOfAccount(){
        StringBuilder returnString = new StringBuilder("\nBelow are all account-types you may choose: ");
        returnString.append(" 0) ORGANIZER\n 1) ATTENDEE\n 2) SPEAKER\n");
        output.printPrompt(returnString);
    }
}
