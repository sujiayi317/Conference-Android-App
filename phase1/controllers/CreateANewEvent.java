package controllers;

import Presenter.ViewAllAvailableRoom;
import Presenter.ViewAllAvailableSpeaker;
import com.sun.tools.javac.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateANewEvent {
    private static OutputManager output;
    private static InputManager input;

    public CreateANewEvent() {
        output = new OutputManager();
        input = new InputManager();
    }

    public void getToCreateANewEvent(EventsController eventsController, ViewAllAvailableRoom viewAllAvailableRoom,
                                     ViewAllAvailableSpeaker viewAllAvailableSpeaker) {
        String timeInput = "666";
        while (!(checkValidTimeFormat(timeInput))) {
            timeInput = input.getInputString("Please enter your event time in the format of yyyy/MM/dd/HH:mm (years/month/date/hour(0 - 24):minute)\n" +
                    "for example, enter '2020/12/09/14:30' to host the event at 2020/Dec/9th at 2:30pm\n" +
                    "enter 'cancel' to go back to main menu\n");
            if ((Integer.parseInt(timeInput) < 0 || Integer.parseInt(timeInput) > 24)&& Integer.parseInt(timeInput) != -1){
                output.printPrompt("The time you chose is not out of bound please enter the correct number\n");
            }
        }
        if (!timeInput.equals("cancel")) {
                    if (eventsController.getAvailableRoom(Integer.parseInt(timeInput)).size() == 0) {
                        output.printPrompt("sorry there is no available room yet, please go to create one first!\n");
                    } else if (eventsController.getAllAvailableSpeaker(Integer.parseInt(timeInput)).size() == 0) {
                        output.printPrompt("sorry there is no available speaker yet, please go to create one first!\n");
                    } else {
                        getAllAvailableRoomInfo(Integer.parseInt(timeInput), viewAllAvailableRoom, eventsController);
                        getAllAvailableSpeaker(Integer.parseInt(timeInput), eventsController, viewAllAvailableSpeaker);
                        String title = input.getInputString("Please enter your event's title\n");
                        String roomNUm = "-1";
                        while (0> Integer.parseInt(roomNUm) || Integer.parseInt(roomNUm)>= eventsController.getAvailableRoom(Integer.parseInt(timeInput)).size()){
                            roomNUm = input.getInputString("Please enter the roomNum of the room you want to use\n");
                            if (0> Integer.parseInt(roomNUm) || Integer.parseInt(roomNUm)>= eventsController.getAvailableRoom(Integer.parseInt(timeInput)).size()){
                                output.printPrompt("The roomNum you chose is out of the bound please enter the correct number\n");
                            }
                        }
                        String speakerNum = "-1";
                        while (0> Integer.parseInt(speakerNum) || Integer.parseInt(speakerNum)>= eventsController.getAllAvailableSpeaker(Integer.parseInt(timeInput)).size()){
                            speakerNum = input.getInputString("Please set the SpeakerNum of your speaker\n");
                            if (0> Integer.parseInt(speakerNum) || Integer.parseInt(speakerNum)>= eventsController.getAllAvailableSpeaker(Integer.parseInt(timeInput)).size()){
                                output.printPrompt("The speakerNum you chose is out of the bound please enter the correct number\n");
                            }
                        }
                        String room = eventsController.getAvailableRoom(Integer.parseInt(timeInput)).get(Integer.parseInt(roomNUm));
                        String speaker = eventsController.getAllAvailableSpeaker(Integer.parseInt(timeInput)).get(Integer.parseInt(speakerNum));
                        if (createEvent(title, eventsController.getRoomManager().changeNumTOID(room), speaker, timeInput, eventsController)) {
                            output.printPrompt("The new Event named " + title + " at Room "
                                    + room + " taught by "
                                    + speaker + " will start at " + Integer.parseInt(timeInput));
                        }
                    }
        }
    }

    private boolean checkValidTimeFormat(String time){
        if (time.length() == 16 && time.charAt(4) == '/' && time.charAt(7) == '/' && time.charAt(10) == '/'
                && time.charAt(13) == ':') {
            String[] timeList = time.split("[/:]+");
            try{
                Integer.parseInt(timeList[0]);
                Integer.parseInt(timeList[1]);
                Integer.parseInt(timeList[2]);
                Integer.parseInt(timeList[3]);
                Integer.parseInt(timeList[4]);
            } catch (NumberFormatException e){
                return false;
            }
        }
        return false;
    }

    private boolean checkValidFutureTime(String time){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/HH:mm");
        Date date = new Date();
        System.out.println(formatter.format(date));
        return true;
    }

    private void getAllAvailableRoomInfo ( int time, ViewAllAvailableRoom viewAllAvailableRoom, EventsController
            eventsController){
        viewAllAvailableRoom.printAllAvailableRoom(eventsController.getAvailableRoom(time));
    }
    private void getAllAvailableSpeaker ( int time, EventsController eventsController, ViewAllAvailableSpeaker
            viewAllAvailableSpeaker){
        output.printPrompt(viewAllAvailableSpeaker.printAllAvailableSpeaker(eventsController.getAllAvailableSpeaker(time)));

    }
    private boolean createEvent (String title, String roomID, String speaker,String startTime, EventsController
            eventsController){
        return eventsController.createEvent(title, roomID, speaker, startTime);
    }
}

