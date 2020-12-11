package unused.presenters;

import unused.controllers.OutputManager;

import java.util.ArrayList;

/**
 * The presenter for viewing all available rooms
 */
public class ViewAllAvailableRoom {

    private static OutputManager output;

    public ViewAllAvailableRoom() {
        output = new OutputManager();
    }

    /**
     * Printing to the screen all available rooms
     * @param allAvailableRoom allAvailableRoom StringBuilder
     */
    public void printAllAvailableRoom(ArrayList<String> allAvailableRoom) {
        StringBuilder returnString = new StringBuilder("\nThese are all the available rooms at this time:\n");
        for (int i = 0; i < allAvailableRoom.size(); i++) {
            returnString.append(i).append(") ").append(allAvailableRoom.get(i)).append("\n");
        }
        output.printPrompt(returnString);
    }
}
