package controllers;

import Presenter.ViewAllAvailableRoom;
import Presenter.ViewAllAvailableSpeaker;

public class CreateANewEvent {
    private static OutputManager output;
    private static InputManager input;
    public CreateANewEvent(){
        output = new OutputManager();
        input = new InputManager();
    }
    public void getToCreateANewEvent(EventsController eventsController,ViewAllAvailableRoom viewAllAvailableRoom,
                                     ViewAllAvailableSpeaker viewAllAvailableSpeaker ){
        String time = "-1";
        while (Integer.parseInt(time) < 0 || Integer.parseInt(time) > 24) {
            int startTime = input.getInputInt("Please enter your event time between 0-24\n");
            if (eventsController.getAvailableRoom(startTime).size() == 0) {
                output.printPrompt("sorry there is no available room yet, please go to create one first!\n");
            } else if (eventsController.getAllAvailableSpeaker(startTime).size() == 0) {
                output.printPrompt("sorry there is no available speaker yet, please go to create one first!\n");
            } else {
                getAllAvailableRoomInfo(startTime, viewAllAvailableRoom, eventsController);
                getAllAvailableSpeaker(startTime, eventsController, viewAllAvailableSpeaker);
                String title = input.getInputString("Please enter your event's title\n");
                String roomNUm = input.getInputString("Please enter your room Num\n");
                String speaker = input.getInputString("Please set your speaker\n");
                if (createEvent(title, eventsController.getRoomManager().changeNumTOID(roomNUm), speaker, startTime, eventsController)){
                output.printPrompt("The new Event named "+title+" at "
                        +roomNUm+" taught by "
                        +speaker+" will start at "+startTime);
                break;}
            }
        }
    }
    private void getAllAvailableRoomInfo(int time, ViewAllAvailableRoom viewAllAvailableRoom, EventsController eventsController){
        viewAllAvailableRoom.printAllAvailableRoom(eventsController.getAvailableRoom(time));
    }
    private void getAllAvailableSpeaker(int time, EventsController eventsController, ViewAllAvailableSpeaker viewAllAvailableSpeaker){
        output.printPrompt(viewAllAvailableSpeaker.printAllAvailableSpeaker(eventsController.getAllAvailableSpeaker(time)));

    }
    private boolean createEvent(String title, String roomID,String speaker,int startTime, EventsController eventsController){
        return eventsController.createEvent(title,roomID,speaker,startTime);
    }
}

