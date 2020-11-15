import controllers.EventsController;
import entities.Event;
import use_cases.SpeakerManager;
import use_cases.*;

import java.io.*;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class FileReadWriter {
    private EventsController eventsController;
    private AttendeeManager attendeeManager;
    private OrganizerManager organizerManager;
    private UserManager userManager;

    public FileReadWriter() {
        userManager = new UserManager();
        eventsController = new EventsController();
        attendeeManager = new AttendeeManager();
        organizerManager = new OrganizerManager();
    }

    public void UserReader() {
        ArrayList<String> lines = new ArrayList();
        try {
            File UserFile = new File("./phase1/Users.txt");
            Scanner myReader = new Scanner(UserFile);
            while (myReader.hasNextLine()) {
                while (myReader.hasNextLine()) {
                    lines.add(myReader.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User File Not Found");
        }
        SpeakerManager speakermanager = eventsController.getSpeakerManager();
        ArrayList<ArrayList> LineList = new ArrayList();
        for (int i = 0; i < lines.size(); i++) {
            ArrayList<String> wordList = new ArrayList<String>();
            for (String word : lines.get(i).split(" ")) {
                wordList.add(word);

            }

            if (wordList.get(0).equals("SPEAKER")) {
                speakermanager.loadSpeaker(wordList.get(1), wordList.get(2), wordList.get(3), wordList.get(4));
            } else if (wordList.get(0).equals("ATTENDEE")) {
                attendeeManager.loadAttendee(wordList.get(1), wordList.get(2), wordList.get(3), wordList.get(4));
            } else if (wordList.get(0).equals("ORGANIZER")) {
                organizerManager.loadOrganizer(wordList.get(1), wordList.get(2), wordList.get(3), wordList.get(4));
            }
            LineList.add(wordList);
        }
        for (ArrayList<String> wordList : LineList){
            if (wordList.size() > 5){
                for (int index = 5; index < wordList.size(); index++){
                    userManager.addFriend(wordList.get(4), wordList.get(index));
                }
            }
        }
    }

    public void UserWriter(){
        try {
            PrintWriter pw = new PrintWriter("./phase1/Users.txt");
            for (String userID : userManager.UsersIdsGetter()){
                String line = userManager.getUserType(userID) + " " + userManager.getUserEmail(userID) + " " +
                        userManager.getUserName(userID) + " "+ userManager.getUserPassword(userID) + " " + userID;
                for (String friendID : userManager.friendListGetter(userID)){
                    line += " " + friendID;
                }
                line += "\n";
                pw.write(line);
            }
            pw.close();
        } catch (FileNotFoundException e){
            System.out.println("User File Not Found.");
        }

    }

    public void RoomReader(){
        ArrayList<String> lines = new ArrayList<>();
        try {
            File UserFile = new File("./phase1/rooms.txt");
            Scanner myReader = new Scanner(UserFile);
            while (myReader.hasNextLine()) {
                while (myReader.hasNextLine()) {
                    lines.add(myReader.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("room File Not Found");
        }
        for (String line : lines) {
            ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
            eventsController.getRoomManager().loadRoom(wordList.get(0), wordList.get(1));
        }
    }

    public void RoomWriter(){
        ArrayList<String> NumList = eventsController.getRoomManager().getAllRoomNum();
        ArrayList<String> IDList = eventsController.getRoomManager().getAllRoomID();
        try {
            PrintWriter pw = new PrintWriter("./phase1/rooms.txt");
            for (int index = 0; index < NumList.size(); index++){
                String line = NumList.get(index) + " " + IDList.get(index);
                line += "\n";
                pw.write(line);
            }
            pw.close();
        } catch (FileNotFoundException e){
            System.out.println("room File Not Found.");
        }
    }
    public void EventReader(){
        ArrayList<String> lines = new ArrayList<>();
        try {
            File UserFile = new File("./phase1/Events.txt");
            Scanner myReader = new Scanner(UserFile);
            while (myReader.hasNextLine()) {
                while (myReader.hasNextLine()) {
                    lines.add(myReader.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("room File Not Found");
        }

        for (String line : lines) {
            ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(line.split(" ")));
            ArrayList<String> Attendees = new ArrayList<>();
            if (wordList.size() > 5){
                for (int index = 5; index < wordList.size(); index++){
                    Attendees.add(wordList.get(index));
                }
            }
            eventsController.getRoomManager().addEventToRoom(wordList.get(4), wordList.get(1));
            eventsController.getEventManager().loadEvent(wordList.get(0).replace("_", " "),
                    wordList.get(1), wordList.get(2), wordList.get(3), wordList.get(4), Attendees,
                    GetEventsController().getRoomManager());
        }
    }

    public void EventWriter(){
        List<Event> EventList = eventsController.getEventManager().getAllEvent();
        try {
            PrintWriter pw = new PrintWriter("./phase1/Events.txt");
            for (Event event : EventList) {
                String line = event.getTitle().replace(" ", "_") + " " + event.getRoomID() + " " +
                        event.getSpeakers() + " " + event.getStartTime() + " " +
                        event.getEventID();
                for (String attendee : event.getAttendees()) {
                    line += " " + attendee;
                }
                line += "\n";
                pw.write(line);
            }
            pw.close();
        } catch (FileNotFoundException e){
            System.out.println("room File Not Found.");
        }
    }

    public EventsController GetEventsController(){
        return eventsController;
    }
    public OrganizerManager GetOrganizerManager(){
        return organizerManager;
    }
    public AttendeeManager GetAttendeeManager(){
        return attendeeManager;
    }
    public UserManager GetUserManager(){
        return userManager;
    }
}
//    public EventManager readFromEventFile(String path) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//
//            // deserialize the use_cases.EventManager
//            EventManager sm = (EventManager) input.readObject();
//            input.close();
//            return sm;
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new use_cases.EventManager.", ex);
//            return new EventManager();
//        }
//    }
//
//    public SpeakerManager readFromSpeakerFile(String path) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//
//            // deserialize the use_cases.SpeakerManager
//            SpeakerManager sm = (SpeakerManager) input.readObject();
//            input.close();
//            return sm;
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new use_cases.SpeakerManager.", ex);
//            return new SpeakerManager();
//        }
//    }
//
//    public RoomManager readFromRoomFile(String path) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
//            InputStream buffer = new BufferedInputStream(file);
//            ObjectInput input = new ObjectInputStream(buffer);
//
//            // deserialize the use_cases.RoomManager
//            RoomManager sm = (RoomManager) input.readObject();
//            input.close();
//            return sm;
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new use_cases.RoomManager.", ex);
//            return new RoomManager();
//        }
//    }
//
//    public ArrayList<User> readFromUserFile(String path) throws ClassNotFoundException {
//        try {
//            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
//            InputStream buffer = new BufferedInputStream(file);
//
//            ArrayList<User> objList = new ArrayList<>();
//            boolean load = true;
//            while (load)
//            {
//                try (ObjectInput input = new ObjectInputStream(buffer))
//                {
//                    User sm = (User) input.readObject();
//                    if (sm != null) {
//                        objList.add(sm);
//                    } else {
//                        load = false;
//                    }
//                }
//                catch (Exception e)
//                {
//                }
//            }
//            input.close(); //can't close input TODO
//            return objList;
//        } catch (IOException ex) {
//            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
//                    "a new entities.User.", ex);
//            return new ArrayList<>();
//        }
//    }
