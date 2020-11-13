package controllers;

import Presenter.ViewAllAvailableRoom;
import Presenter.ViewAllAvailableSpeaker;

public class CreateANewEvent {
    private static OutputManager output;
    private static InputManager input;

    public CreateANewEvent() {
        output = new OutputManager();
        input = new InputManager();
    }

    public void getToCreateANewEvent(EventsController eventsController, ViewAllAvailableRoom viewAllAvailableRoom,
                                     ViewAllAvailableSpeaker viewAllAvailableSpeaker) {
        String timeInput = "-2";
        while (((Integer.parseInt(timeInput) < 0 || Integer.parseInt(timeInput) > 24)) && Integer.parseInt(timeInput) != -1) {
            timeInput = input.getInputString("Please enter your event time between 0-24 OR PRESS -1 back to Menu\n");
        }
        if (!timeInput.equals("-1")) {
                    if (eventsController.getAvailableRoom(Integer.parseInt(timeInput)).size() == 0) {
                        output.printPrompt("sorry there is no available room yet, please go to create one first!\n");
                    } else if (eventsController.getAllAvailableSpeaker(Integer.parseInt(timeInput)).size() == 0) {
                        output.printPrompt("sorry there is no available speaker yet, please go to create one first!\n");
                    } else {
                        getAllAvailableRoomInfo(Integer.parseInt(timeInput), viewAllAvailableRoom, eventsController);
                        getAllAvailableSpeaker(Integer.parseInt(timeInput), eventsController, viewAllAvailableSpeaker);
                        String title = input.getInputString("Please enter your event's title\n");
                        String roomNUm = input.getInputString("Please enter the roomNum of the room you want to use\n");
                        String room = eventsController.getAvailableRoom(Integer.parseInt(timeInput)).get(Integer.parseInt(roomNUm));
                        String speakerNum = input.getInputString("Please set the SpeakerNum of your speaker\n");
                        String speaker = eventsController.getAllAvailableSpeaker(Integer.parseInt(timeInput)).get(Integer.parseInt(speakerNum));
                        if (createEvent(title, eventsController.getRoomManager().changeNumTOID(room), speaker, Integer.parseInt(timeInput), eventsController)) {
                            output.printPrompt("The new Event named " + title + " at Room "
                                    + room + " taught by "
                                    + speaker + " will start at " + Integer.parseInt(timeInput));
                        }
                    }
        }
    }
        private void getAllAvailableRoomInfo ( int time, ViewAllAvailableRoom viewAllAvailableRoom, EventsController
        eventsController){
            viewAllAvailableRoom.printAllAvailableRoom(eventsController.getAvailableRoom(time));
        }
        private void getAllAvailableSpeaker ( int time, EventsController eventsController, ViewAllAvailableSpeaker
        viewAllAvailableSpeaker){
            output.printPrompt(viewAllAvailableSpeaker.printAllAvailableSpeaker(eventsController.getAllAvailableSpeaker(time)));

        }
        private boolean createEvent (String title, String roomID, String speaker,int startTime, EventsController
        eventsController){
            return eventsController.createEvent(title, roomID, speaker, startTime);
        }
}

