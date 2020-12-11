package unused.presenters;


import java.util.ArrayList;

/**
 * The presenter for viewing all available rooms
 */
public class ViewAllAvailableSpeaker {
    public ViewAllAvailableSpeaker(){}

    /**
     * Printing to the screen all available speakers
     * @param AllAvailableSpeaker AllAvailableSpeaker
     * @return StringBuilder
     */
    public StringBuilder printAllAvailableSpeaker(ArrayList<String> AllAvailableSpeaker){
        StringBuilder returnString = new StringBuilder("\nThese are all the available speakers at this time:\n");
        for (int i =0 ; i < AllAvailableSpeaker.size();i++){
            returnString.append(i).append(") ").append(AllAvailableSpeaker.get(i)).append("\n");
        }
        return returnString;
    }
}
