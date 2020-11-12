package controllers;

import java.util.InputMismatchException;

/**
 * This is the main controller that will manage all other controllers (parts of the conference).
 */
public class Conference {

    private static InputManager in = new InputManager();
    private static OutputManager out = new OutputManager();
    private static DataManager data = new DataManager();

    private static


    /**
     * This is where our conference system starts.
     */
    public void run(){
        try {
            conferenceLoop();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        } finally {
            in.closeSanner();
        }
    }




}
