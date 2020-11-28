package entities;

import java.util.ArrayList;

public class EventFactory {
    public Addtendable createEvent(String title, String roomID, ArrayList<String> speakerID, String startTime,String duration, String type){
        if (type == "TALK"){
            return new Talk(title, roomID, speakerID, startTime, duration);
        }
        else if (type == "DISCUSSION"){
            return new Discussion(title, roomID, startTime, speakerID, duration);
        }
        else{
            return new Party(title, roomID, startTime, duration);
        }
    }
}
