package controllers;

import entities.Event;
import entities.Speaker;
import entities.Room;
import use_cases.EventManager;
import use_cases.RoomManager;
import use_cases.SpeakerManager;

import java.util.ArrayList;
import java.util.List;

public class EventsController{
    private final EventManager eventManager;
    private final RoomManager roomManager;
    private final SpeakerManager speakerManager;

    public EventsController(){
        this.eventManager = new EventManager();
        this.roomManager = new RoomManager();
        this.speakerManager = new SpeakerManager();
    }

    public EventManager getEventManager(){
        return this.eventManager;
    }

    public RoomManager getRoomManager(){
        return this.roomManager;
    }

    public List<Event> getAllExistingEvents(){
        return this.eventManager.getAllEvent();
    }

    public ArrayList<String> getALLAttendeeEvents(String userID){
        return this.eventManager.getAllEventForTheAttendee(userID);
    }

    public boolean cancelEvent(String userID, String eventID){
        return this.eventManager.removeAttendeeFromEvent(userID,eventID, this.roomManager);
    }

    public ArrayList<String> getAvailableRoom(int time){
        return this.roomManager.getAvailableRoom(time, this.eventManager);
    }

    public boolean createEvent(String title, String roomID, String speakerID, int startTime){

        Event newEvent = this.eventManager.createEvent(title, roomID, speakerID, startTime);
        if (newEvent == null){
            return false;
        }
        this.roomManager.addEventToRoom(newEvent.getEventID(), roomID);
        return true;
    }

    public ArrayList<String> getEventInfo(String eventID){
        ArrayList<String> info = new ArrayList<>();
        Event event = this.eventManager.getEventFromID(eventID);
        Room room = this.roomManager.getRoomBasedOnItsID(event.getRoomID());
        info.add(event.getTitle());
        info.add(Integer.toString(event.getStartTime()));
        info.add(event.getSpeakers());
        info.add(Integer.toString(room.getCurrentNum()));
        info.add(Integer.toString(room.getCapacity()));
        return info;
    }

    public ArrayList<String> getAllAvailableSpeaker(int time){
        return this.speakerManager.getAllAvailableSpeaker(time, eventManager);
    }
}
