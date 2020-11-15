package presenters;

import controllers.OutputManager;

import java.util.ArrayList;

public class ViewAllAvailableRoom {

    private static OutputManager output;

    public ViewAllAvailableRoom(){
        output = new OutputManager();
    }

    public void printAllAvailableRoom(ArrayList<String> allAvailableRoom){
        StringBuilder returnString = new StringBuilder("\nThese are all the available rooms at this time:\n");
        for (int i =0 ; i < allAvailableRoom.size();i++){
            returnString.append(i).append(") ").append(allAvailableRoom.get(i)).append("\n");
        }
        output.printPrompt(returnString);
    }
}
