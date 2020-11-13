package controllers;

import use_cases.RoomManager;

public class createANewRoom {
    private static OutputManager output;
    private static InputManager input;

    public createANewRoom(){
        output = new OutputManager();
        input = new InputManager();
    }

    public void getToCreateANewRoom(EventsController eventsController){
        RoomManager roomManager = eventsController.getRoomManager();
        String roomNum = "-1";
        while (Integer.parseInt(roomNum)>100 || Integer.parseInt(roomNum)<0){
            roomNum = input.getInputString("Please enter your room Number between 0-100\n");
            if (Integer.parseInt(roomNum)>100 || Integer.parseInt(roomNum)<0){
                output.printPrompt("The RoomNum you chose is out of the bound please enter the correct number\n");
            }
        }
        if (roomManager.createRoom(roomNum)){
            output.printPrompt("room" +" " +roomNum+ " "+"is created successfully\n");
        }
        else {
            output.printPrompt("room" +" " +roomNum+ " "+"has been already existing\n");
        }
    }
}
