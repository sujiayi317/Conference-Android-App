package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The entities.Room class, this is used to create room objects, it holds conference meeting objects held in the room.
 */
public class Room implements Serializable{

    private final String roomID = UUID.randomUUID().toString().split("-")[0];
    private final int capacity = 2;
    private int currentNum = 0;
    private String roomNum;
    /**
     * Constructor  
     * @param roomNum unique identification of the room
     */
    public Room(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRoomNum(){
        return roomNum;
    }
    /**
     * Constructor  
     * @param roomID unique identification of the room
     */
    public Room(Integer roomID) {
        this(roomID.toString());
    }

    /**
     * Get the value of roomID
     *
     * @return the value of ID
     */
    public String getRoomID() {
        return roomID;
    }


    @Override
    public String toString() {
        return "entities.Room Number " + roomID;

    }

    public int getCurrentNum(){
        return this.currentNum;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public void increaseCurrentNum(){
        if (this.currentNum < this.capacity){
            this.currentNum +=1;}
    }

    public void decreaseCurrentNum(){
        if (this.currentNum > 0){
            this.currentNum -=1;}
    }
}