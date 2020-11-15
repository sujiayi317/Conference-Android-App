package Presenter;


import java.util.ArrayList;

public class ViewAllAvailableSpeaker {
    public ViewAllAvailableSpeaker(){}

    public StringBuilder printAllAvailableSpeaker(ArrayList<String> AllAvailableSpeake){
        StringBuilder returnString = new StringBuilder("\nThese are all the available speakers at this time:\n");
        for (int i =0 ; i < AllAvailableSpeake.size();i++){
            returnString.append(i).append(") ").append(AllAvailableSpeake.get(i)).append("\n");
        }
        return returnString;
    }
}
