package Presenter;

import controllers.OutputManager;

import java.util.ArrayList;

public class ViewAllExistingEvents {
    private static OutputManager output = new OutputManager();
    public void printAllExistingEvents(ArrayList<String> AllExistingEvents){
        StringBuilder returnString = new StringBuilder("There are all existing events you may attend:");
        for (int i =0 ; i < AllExistingEvents.size();i++){
            returnString.append(i).append(":").append(AllExistingEvents.get(i)).append("\n");
        }
        output.printPrompt(returnString);
    }


}
