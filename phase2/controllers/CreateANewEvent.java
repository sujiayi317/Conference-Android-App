package controllers;

import presenters.ViewAllAvailableRoom;
import presenters.ViewAllAvailableSpeaker;
import presenters.ViewAllEventType;

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
                                     ViewAllAvailableSpeaker viewAllAvailableSpeaker, ViewAllEventType viewAllEventType) {
        String timeInput = "666";
        boolean canceled = false;
        while (!(checkValidTimeFormat(timeInput) && checkValidFutureTime(timeInput))) {
            timeInput = input.getInputString("Please enter your event time in the format as below\n yyyy/mm/dd/hh (years/month/date/hour)\n" +
                    "e.g enter '2020/12/09/14' \nto host the event on 2020/Dec/9th at 2pm\n" +
                    "Or, enter 'cancel' to go back to main menu\n");
            if (timeInput.equals("cancel")) {
                canceled = true;
                break;
            }
            if (!(checkValidTimeFormat(timeInput))) {
                output.printPrompt("Your input date is invalid!\n Please check and ensure that:\n (i)   The starting " +
                        "time is from 9am to 4pm;\n (ii)  It follows the yyyy/mm/dd/hh format;\n (iii) The date " +
                        "actually exits,\n e.g, don't even try to host an event on 30th of February.\n Try enter " +
                        "another date...\n\n");
            } else if (!(checkValidFutureTime(timeInput))) {
                output.printPrompt("You can not create new events in the past!\n Please choose another time in the future...\n\n");
            }
        }
        if (!timeInput.equals("cancel")) {
            timeInput = timeInput.replace("/", "");
            timeInput = timeInput.replace(":", "");
            String durationNum = "-1";
            while (1 > Integer.parseInt(durationNum) || Integer.parseInt(durationNum) > 12) {
                durationNum = Integer.toString(input.getInputInt("Please enter the duration for the event\n (Integer between 1 and 12)\n"));
                if (1 > Integer.parseInt(durationNum) || Integer.parseInt(durationNum) >12) {
                    output.printPrompt("The duration you enter is out of the bound please enter the correct integer\n");
                }
            }
            if (eventsController.getAvailableRoom(timeInput).size() == 0) {
                output.printPrompt("sorry there is no available room yet,\n please go to create one first!\n");
            } else if (eventsController.getAllAvailableSpeaker(timeInput, durationNum).size() == 0) {
                output.printPrompt("sorry there is no available speaker yet,\n please go to create one first!\n");
            } else {
                getAllAvailableRoomInfo(timeInput, viewAllAvailableRoom, eventsController);
                getAllAvailableSpeaker(timeInput, eventsController, viewAllAvailableSpeaker, durationNum);
                viewAllEventType.printALlEventType();
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
                ArrayList<String> allType = eventsController.getEventManager().getAllEventType();
                String eventType = "-1";
                while (! canceled && 0 > Integer.parseInt(eventType) || Integer.parseInt(eventType) >= allType.size() ) {
                    eventType = Integer.toString(input.getInputInt("Please enter the choice for the event type\n"));
                    if (0 > Integer.parseInt(eventType) || Integer.parseInt(eventType) >= allType.size()) {
                        output.printPrompt("The event type you enter is out of the bound please enter the correct number\n");
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
                if (!eventType.equals("PARTY")) {
                    while (!canceled && 0 > Integer.parseInt(speakerNum) || Integer.parseInt(speakerNum) >= eventsController.getAllAvailableSpeaker(timeInput, durationNum).size()) {
                        speakerNum = Integer.toString(input.getInputInt("Please enter a number to choose Speaker to " +
                                "speak in this event: (enter '0' to choose the first speaker shown in the list above.)\n"));
                        if (0 > Integer.parseInt(speakerNum) || Integer.parseInt(speakerNum) >= eventsController.getAllAvailableSpeaker(timeInput, durationNum).size()) {
                            output.printPrompt("The speakerNum you chose is out of the bound please enter the correct number\n");
                        }
                    }
                }
                String restriction = "";
                while (!restriction.equals("PUBLIC") && !restriction.equals("VIP-ONLY")){
                    restriction = input.getInputString("Please enter the restriction for the event, type" +
                            "'PUBLIC' for a public event or 'VIP-ONLY' for a VIP-only event, or enter 'cancel' to cancel.\n");
                    if (!restriction.equals("PUBLIC") && !restriction.equals("VIP-ONLY") &&!restriction.equals("cancel")){
                        output.printPrompt("The type of restriction you chose is invalid, please try again\n.");
                    } else if (restriction.equals("cancel")) {
                        canceled = true;
                        break;
                    }
                }
                if (!canceled){
                    String room = eventsController.getAvailableRoom(timeInput).get(Integer.parseInt(roomNUm));
                    String speaker = eventsController.getAllAvailableSpeaker(timeInput,durationNum).get(Integer.parseInt(speakerNum));
                    if (createEvent(title, eventsController.getRoomManager().changeNumTOID(room), speaker, timeInput, eventsController, durationNum, restriction, allType.get(Integer.parseInt(eventType)))) {
                        output.printPrompt("The new " + restriction + " " + allType.get(Integer.parseInt(eventType)) + " named " + title + " at Room "
                                + room + " taught by " + speaker + " will start at " +
                                eventsController.getEventManager().generateFormattedStartTime(timeInput) + " with the duration" + durationNum + " hour/hours.");
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
            viewAllAvailableSpeaker, String duration) {
        output.printPrompt(viewAllAvailableSpeaker.printAllAvailableSpeaker(eventsController.getAllAvailableSpeaker(time, duration)));

    }

    private boolean createEvent(String title, String roomID, String speaker, String startTime, EventsController
            eventsController, String duration, String restriction, String type) {
        return eventsController.createEvent(title, roomID, speaker, startTime, duration, restriction, type);
    }
}