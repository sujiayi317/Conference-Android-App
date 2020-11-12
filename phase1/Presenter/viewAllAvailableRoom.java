package Presenter;

import controllers.OutputManager;

import java.util.ArrayList;

public class viewAllAvailableRoom {

    private static OutputManager output = new OutputManager();

    public void printAllAvailableRoom(ArrayList<String> allAvailableRoom){
        StringBuilder returnString = new StringBuilder("There are all available rooms you may choose:");
        for (int i =0 ; i < allAvailableRoom.size();i++){
            returnString.append(i).append(":").append(allAvailableRoom.get(i)).append("\n");
        }
        output.printPrompt(returnString);
    }
}
