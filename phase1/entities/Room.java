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
    private final String roomNum;

    /**
     * Constructor  
     * @param roomNum unique identification of the room
     */
    public Room(String roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * Get the room number for this room
     *
     * @return the room Number in String format
     */
    public String getRoomNum(){
        return roomNum;
    }

    /**
     * Get the value of roomID
     *
     * @return the value of ID
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * Get current number of people in this room
     *
     * @return current number
     */
    public int getCurrentNum(){
        return this.currentNum;
    }

    /**
     * Get the capacity for this room
     *
     * @return capacity
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * Increase the number of people in this room
     */
    public void increaseCurrentNum(){
        if (this.currentNum < this.capacity){
            this.currentNum +=1;}
    }

    /**
     * Decrease the number of people in this room
     */
    public void decreaseCurrentNum(){
        if (this.currentNum > 0){
            this.currentNum -=1;}
    }

    @Override
    public String toString() {
        return "entities.Room Number " + roomID;

    }
}