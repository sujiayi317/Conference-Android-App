package controllers;

import use_cases.RoomManager;
import java.lang.String.*;

/**
 * This is the class for Creating a New room
 */
public class CreateANewRoom {
    private static OutputManager output;
    private static InputManager input;

    /**
     * constructor of createANewRoom class. This class is made for creating rooms.
     */
    public CreateANewRoom(){
        output = new OutputManager();
        input = new InputManager();
    }

    /**
     * The method to create a new room.
     * @param eventsController event controller class.
     */
    public void getToCreateANewRoom(EventsController eventsController){
        RoomManager roomManager = eventsController.getRoomManager();
        int roomNum = 101;
        int roomCapacity = 101;
        while (roomNum > 100 || roomNum < 0){
            roomNum = input.getInputInt("Please enter your room Number between 0-100\n");
            if (roomNum > 100 || roomNum < 0){
                output.printPrompt("The room number you entered is invalid! Please enter a number between 0 and 100\n");
            }
        }
        while (roomCapacity > 100 || roomCapacity < 0){
            roomCapacity = input.getInputInt("Please enter your room Number between 0-100\n");
            if (roomCapacity > 100 || roomCapacity < 0){
                output.printPrompt("The room number you entered is invalid! Please enter a number between 0 and 100\n");
            }
        }
        if (roomManager.createRoom(String.valueOf(roomNum), roomCapacity)){
            output.printPrompt("room" +" " + roomNum + " "+"is created successfully\n");
        }
        else {
            output.printPrompt("room" +" " + roomNum + " "+"has been already existing\n");
        }
    }
}
