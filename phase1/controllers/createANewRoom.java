package controllers;

import use_cases.RoomManager;
import java.lang.String.*;
public class createANewRoom {
    private static OutputManager output;
    private static InputManager input;

    public createANewRoom(){
        output = new OutputManager();
        input = new InputManager();
    }

    public void getToCreateANewRoom(EventsController eventsController){
        RoomManager roomManager = eventsController.getRoomManager();
        Integer roomNum = 101;
        while (roomNum > 100 || roomNum < 0){
            roomNum = input.getInputInt("Please enter your room Number between 0-100\n");
            if (roomNum > 100 || roomNum < 0){
                output.printPrompt("The room number you entered is invalid! Please enter a number between 0 and 100\n");
            }
        }
        if (roomManager.createRoom(roomNum.toString())){
            output.printPrompt("room" +" " +roomNum.toString()+ " "+"is created successfully\n");
        }
        else {
            output.printPrompt("room" +" " +roomNum.toString()+ " "+"has been already existing\n");
        }
    }
}
