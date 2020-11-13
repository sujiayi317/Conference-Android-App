package Presenter;

import controllers.OutputManager;

import java.util.ArrayList;

public class ViewAllAvailableRoom {

    private static OutputManager output;

    public ViewAllAvailableRoom(){
        output = new OutputManager();
    }

    public void printAllAvailableRoom(ArrayList<String> allAvailableRoom){
        StringBuilder returnString = new StringBuilder("There are all available rooms you may choose:\n");
        for (int i =0 ; i < allAvailableRoom.size();i++){
            returnString.append(i).append(") :\t").append(allAvailableRoom.get(i)).append("\n");
        }
        output.printPrompt(returnString);
    }
}
