package presenters;


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
        this.infoPattern.add("Duration");
        this.infoPattern.add("Speaker");
        this.infoPattern.add("Current_Capacity");
        this.infoPattern.add("Event_Restriction");

    }

    /**
     * the presenter of an event, which will display the event information for users.
     *
     * @param eventInfo An array list of event information.
     * @return The string which can present the information.
     */
    public StringBuilder getEventInfo(ArrayList<String> eventInfo) {
        StringBuilder returnString = new StringBuilder("There is the detail for " + eventInfo.get(0) + " you may view:\n");
        for (int i = 0; i < eventInfo.size() - 2; i++) {
            returnString.append(this.infoPattern.get(i)).append(": ").append(eventInfo.get(i)).append("\n");
        }
        returnString.append("Ending: ").append(Integer.parseInt(eventInfo.get(1)) + Integer.parseInt(eventInfo.get((2))));
        returnString.append(this.infoPattern.get(4)).append(": ").append(eventInfo.get(4)).append("/").append(eventInfo.get(5)).append("\n");
        returnString.append(this.infoPattern.get(5)).append(": ").append(eventInfo.get(6)).append("\n");
        return returnString;
    }

}
