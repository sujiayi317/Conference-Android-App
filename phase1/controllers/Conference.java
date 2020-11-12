package controllers;

import java.util.InputMismatchException;

/**
 * This is the main controller that will manage all other controllers (parts of the conference).
 */
public class Conference {

    /**
     * This is where our conference system starts.
     */
    public void run(){
        try {
            conferenceSystem();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main program flow-of-control.
     */
    private void conferenceSystem() {
        start();

        iteration();

        finish();
    }

    /**
     * Any part of the login system that should be set up before an User logs into the account.
     */
    private void start() {
        //connect to Gateway: set up database
        //connect to Login Controller - log User in
    }

    /**
     * Connect to one of the three types of User Controllers.
     */
    private void iteration() {
        String userType = Login.getUserType();

        switch(userType) {
            case "ATTENDEE":
                new AttendeeController().run();
                break;
            case "SPEAKER":
                new SpeakerController().run();
                break;
            case "ORGANIZER":
                new OrganizerController().run();
        }
    }

    private void finish() {
        System.out.print("You have successfully signed out.");
    }

}
