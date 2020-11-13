package controllers;

import Presenter.ViewAllAttendeeEvents;
import Presenter.ViewAllAvailableRoom;
import Presenter.ViewAllExistingEvents;
import Presenter.ViewEventInfo;
import entities.Attendee;
import entities.Organizer;
import entities.User;
import use_cases.*;

import java.util.InputMismatchException;

/**
 * This is the main controller that will manage all other controllers (parts of the conference).
 */
public class Conference {
    private final UserManager userManager;
    private final EventsController eventsController;
    private final AttendeeManager attendeeManager;
    private final SpeakerManager speakerManager;
    private final OrganizerManager organizerManager;
    private final ViewAllAvailableRoom viewAllAvailableRoom;
    private final ViewAllAttendeeEvents viewAllAttendeeEvents;
    private final ViewAllExistingEvents viewAllExistingEvents;
    private final ViewEventInfo viewEventInfo;
    /**
     * This is where our conference system starts.
     */
    public Conference(){
        this.attendeeManager = new AttendeeManager();
        this.speakerManager = new SpeakerManager();
        this.organizerManager = new OrganizerManager();
        this.viewAllAvailableRoom = new ViewAllAvailableRoom();
        this.viewAllAttendeeEvents = new ViewAllAttendeeEvents();
        this.viewAllExistingEvents = new ViewAllExistingEvents();
        this.viewEventInfo = new ViewEventInfo();
        this.eventsController = new EventsController();
        this.userManager = new UserManager();
    }
    public void run(){
        try {
            //Initialize all managers, NO FILE FOUND AT THIS POINT, CREATE NEW MANAGERS
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
        new Login().run(attendeeManager, organizerManager, speakerManager, userManager);


    }

    /**
     * Connect to one of the three types of User Controllers.
     */
    private void iteration() {
        String userType = new Login().getUserType();
        String userID = new Login().getUserID();

        switch(userType) {
            case "ATTENDEE":
                new AttendeeController().run(userID, eventsController, viewAllExistingEvents, viewAllAttendeeEvents,
                        viewEventInfo, attendeeManager);
                break;
            case "SPEAKER":
                new SpeakerController().run();
                break;
            case "ORGANIZER":
                new OrganizerController().run(eventsController, viewAllExistingEvents, viewAllAvailableRoom,
                        viewAllAttendeeEvents, attendeeManager,viewEventInfo, organizerManager, speakerManager,
                        userManager, userID);
        }
    }

    private void finish() {
        System.out.print("You have successfully signed out.");
    }

}
