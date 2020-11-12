package Presenter;

import java.util.ArrayList;

public class ViewAllExistingEvents {

    public void printAllExistingEvents(ArrayList<String> AllExistingEvents){
        StringBuilder returnString = new StringBuilder("There are all existing events you may attend:");
        for (int i =0 ; i < AllExistingEvents.size();i++){
            returnString.append(i).append(":").append(AllExistingEvents.get(i)).append("\n");
        }
        System.out.println(returnString);
    }


}
