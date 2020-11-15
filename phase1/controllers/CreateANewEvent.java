package controllers;

import Presenter.ViewAllAvailableRoom;
import Presenter.ViewAllAvailableSpeaker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CreateANewEvent {
    private static OutputManager output;
    private static InputManager input;

    /**
     * The constructor of CreateANewEvent class. This class is made for creating new events.
     */
    public CreateANewEvent() {
        output = new OutputManager();
        input = new InputManager();
    }

    /**
     * The method to create an new event.
     * @param eventsController events controller class
     * @param viewAllAvailableRoom valid room presenter to show all valid rooms for organizer.
     * @param viewAllAvailableSpeaker available speaker presenter to show all available speakers for organizer.
     */
    public void getToCreateANewEvent(EventsController eventsController, ViewAllAvailableRoom viewAllAvailableRoom,
                                     ViewAllAvailableSpeaker viewAllAvailableSpeaker) {
        String timeInput = "666";
        boolean canceled = false;
        while (!(checkValidTimeFormat(timeInput) && checkValidFutureTime(timeInput))) {
            timeInput = input.getInputString("Please enter your event time in the format of yyyy/mm/dd/hh:mm (years/month/date/hour)\n" +
                    "for example, enter '2020/12/09/14' to host the event on 2020/Dec/9th at 2pm\n" +
                    "Or, enter 'cancel' to go back to main menu\n");
            if (timeInput.equals("cancel")) {
                canceled = true;
                break;
            }
            if (!(checkValidTimeFormat(timeInput))) {
                output.printPrompt("Your input date is invalid! Please check and ensure that:\n (i)   The starting " +
                        "time is from 9am to 4pm;\n (ii)  It follows the yyyy/mm/dd/hh format;\n (iii) The date " +
                        "actually exits, for example, don't even try to host an event on 30th of February.\n Try enter " +
                        "another date...\n\n");
            } else if (!(checkValidFutureTime(timeInput))) {
                output.printPrompt("You can not create new events in the past! Please choose another time in the future...\n\n");
            }
        }
        if (!timeInput.equals("cancel")) {
            timeInput = timeInput.replace("/", "");
            timeInput = timeInput.replace(":", "");
            if (eventsController.getAvailableRoom(timeInput).size() == 0) {
                output.printPrompt("sorry there is no available room yet, please go to create one first!\n");
            } else if (eventsController.getAllAvailableSpeaker(timeInput).size() == 0) {
                output.printPrompt("sorry there is no available speaker yet, please go to create one first!\n");
            } else {
                getAllAvailableRoomInfo(timeInput, viewAllAvailableRoom, eventsController);
                getAllAvailableSpeaker(timeInput, eventsController, viewAllAvailableSpeaker);
                String title = "";
                while (title.length() < 3) {
                    title = input.getInputString("\nGiven all the information above, please first enter your " +
                            "event's title (with at least 3 characters), or enter 'cancel' to cancel.\n");
                    if (title.length() < 3) {
                        output.printPrompt("The title you entered is invalid...\n");
                    } else if (title.equals("cancel")) {
                        canceled = true;
                        break;
                    }
                }

                String roomNUm = "-1";
                while (!canceled && 0 > Integer.parseInt(roomNUm) || Integer.parseInt(roomNUm) >= eventsController.getAvailableRoom(timeInput).size()) {
                    roomNUm = Integer.toString(input.getInputInt("\nPlease enter a number to choose a room you " +
                            "want to use: (enter '0' to choose the first room shown in the list above.)\n"));
                    if (0 > Integer.parseInt(roomNUm) || Integer.parseInt(roomNUm) >= eventsController.getAvailableRoom(timeInput).size()) {
                        output.printPrompt("The roomNum you chose is out of the bound please enter the correct number\n");
                    }
                }
                String speakerNum = "-1";
                while (! canceled && 0 > Integer.parseInt(speakerNum) || Integer.parseInt(speakerNum) >= eventsController.getAllAvailableSpeaker(timeInput).size()) {
                    speakerNum = Integer.toString(input.getInputInt("Please enter a number to choose Speaker to " +
                            "speak in this event: (enter '0' to choose the first speaker shown in the list above.)\n"));
                    if (0 > Integer.parseInt(speakerNum) || Integer.parseInt(speakerNum) >= eventsController.getAllAvailableSpeaker(timeInput).size()) {
                        output.printPrompt("The speakerNum you chose is out of the bound please enter the correct number\n");
                    }
                }
                if (!canceled){
                    String room = eventsController.getAvailableRoom(timeInput).get(Integer.parseInt(roomNUm));
                    String speaker = eventsController.getAllAvailableSpeaker(timeInput).get(Integer.parseInt(speakerNum));
                    if (createEvent(title, eventsController.getRoomManager().changeNumTOID(room), speaker, timeInput, eventsController)) {
                        output.printPrompt("The new Event named " + title + " at Room "
                                + room + " taught by " + speaker + " will start at " +
                                eventsController.getEventManager().generateFormattedStartTime(timeInput));
                    }
                }
            }
        }
        if (canceled){
            output.printPrompt("Event creation canceled, directing back to main page now...\n\n");
        }
    }

    /**
     * To check if a time format is valid.
     * @param time given time string
     * @return true iff the time format is correct
     */
    private boolean checkValidTimeFormat(String time) {
        if (time.length() == 13 && time.charAt(4) == '/' && time.charAt(7) == '/' && time.charAt(10) == '/') {
            String[] timeList = time.split("/");
            try {
                int year = Integer.parseInt(timeList[0]);
                int month = Integer.parseInt(timeList[1]);
                int date = Integer.parseInt(timeList[2]);
                int hour = Integer.parseInt(timeList[3]);
                if (hour < 9 || hour > 16 || month > 12 || month < 1 || date < 1 || date > 31) {
                    return false;
                }
                switch (month) {
                    case 2:
                        return date <= 28;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        return date < 30;
                }
            } catch (NumberFormatException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * To check if a given event time can be added.
     * @param time given time
     * @return true iff the time slot is available
     */
    private boolean checkValidFutureTime(String time) {
        String eventTime = time.replace("/", "");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd/HH");
        Date date = new Date();
        String currentTime = formatter.format(date).replace("/", "");
        return Long.parseLong(eventTime) > Long.parseLong(currentTime);
    }

    /**
     * The getter to get all available room information.
     * @param time given time
     * @param viewAllAvailableRoom presenter of all available rooms
     * @param eventsController event controller class
     */
    private void getAllAvailableRoomInfo(String time, ViewAllAvailableRoom viewAllAvailableRoom, EventsController
            eventsController) {
        viewAllAvailableRoom.printAllAvailableRoom(eventsController.getAvailableRoom(time));
    }

    private void getAllAvailableSpeaker(String time, EventsController eventsController, ViewAllAvailableSpeaker
            viewAllAvailableSpeaker) {
        output.printPrompt(viewAllAvailableSpeaker.printAllAvailableSpeaker(eventsController.getAllAvailableSpeaker(time)));

    }

    private boolean createEvent(String title, String roomID, String speaker, String startTime, EventsController
            eventsController) {
        return eventsController.createEvent(title, roomID, speaker, startTime);
    }
}

