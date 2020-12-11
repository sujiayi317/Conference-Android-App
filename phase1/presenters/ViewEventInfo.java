package unused.presenters;


import java.util.ArrayList;

/**
 * The presenter for viewing all the event information
 */
public class ViewEventInfo {
    private final ArrayList<String> infoPattern;

    public ViewEventInfo() {
        this.infoPattern = new ArrayList<>();
        this.infoPattern.add("Title");
        this.infoPattern.add("Time");
        this.infoPattern.add("Speaker");
        this.infoPattern.add("Current_Capacity");

    }

    /**
     * the presenter of an event, which will display the event information for users.
     *
     * @param eventInfo An array list of event information.
     * @return The string which can present the information.
     */
    public StringBuilder getEventInfo(ArrayList<String> eventInfo) {
        StringBuilder returnString = new StringBuilder("There is the detail for " + eventInfo.get(0) + " you can view:\n");
        for (int i = 0; i < eventInfo.size() - 2; i++) {
            returnString.append(this.infoPattern.get(i)).append(": ").append(eventInfo.get(i)).append("\n");
        }
        returnString.append(this.infoPattern.get(3)).append(": ").append(eventInfo.get(3)).append("/").append(eventInfo.get(4)).append("\n");
        return returnString;
    }

}
