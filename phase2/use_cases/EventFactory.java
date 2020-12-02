package use_cases;

import entities.*;

import java.util.ArrayList;

public class EventFactory {
    public Event createEvent(String title, String roomID, ArrayList<String> speakerID, String startTime, String duration, String type) {
        switch (type) {
            case "TALK":
                return new Talk(title, roomID, speakerID, startTime, duration);
            case "DISCUSSION":
                return new Discussion(title, roomID, startTime, speakerID, duration);
            case "PARTY":
                return new Party(title, roomID, startTime, duration);
        }
        return null;
    }
}
