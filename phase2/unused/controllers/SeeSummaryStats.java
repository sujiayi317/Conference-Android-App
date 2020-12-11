package unused.controllers;

import unused.VIPUserManager;
import use_cases.*;

import java.util.ArrayList;

public class SeeSummaryStats {
    private static OutputManager output;

    public SeeSummaryStats(){
        output = new OutputManager();
    }

    public void getToSeeUserStat(EventsController eventsController,
                                 AttendeeManager attendeeManager, OrganizerManager organizerManager,
                                 VIPUserManager vipUserManager){
        ArrayList<StringBuilder> overallUser = eventsController.getUserManager().getAllUsersInfo();
        ArrayList<StringBuilder> overallSpeaker = eventsController.getSpeakerManager().getAllSpeakerInfo();
        ArrayList<StringBuilder> overallVip = vipUserManager.getAllVIPInfo();
        ArrayList<StringBuilder> overallAttendee = attendeeManager.getAllAttendeeInfo();
        ArrayList<StringBuilder> overallOrganizer = organizerManager.getAllOrganizerInfo();
        StringBuilder overallInfo = new StringBuilder();
        overallInfo.append("There are all Users' Info:\n");
        for (StringBuilder line: overallUser){
            overallInfo.append(line).append("\n");
        }
        overallInfo.append("There are all Attendees' Info:\n");
        for (StringBuilder line: overallAttendee){
            overallInfo.append(line).append("\n");
        }
        overallInfo.append("There are all Speakers' Info:\n");
        for (StringBuilder line: overallSpeaker){
            overallInfo.append(line).append("\n");
        }
        overallInfo.append("There are all VIPs' Info:\n");
        for (StringBuilder line: overallVip){
            overallInfo.append(line).append("\n");
        }
        overallInfo.append("There are all Organizers' Info:\n");
        for (StringBuilder line: overallOrganizer){
            overallInfo.append(line).append("\n");
        }
        output.printPrompt(overallInfo);

    }

    public void getToSeeEventEnrollmentStatistics(EventsController eventsController){
        StringBuilder overallInfo = new StringBuilder();
        overallInfo.append("There are all Events' Info:\n");
        for (int i = 0; i < eventsController.getAllExistingEventsSize(); i++){
            String currentEventID = eventsController.getAllExistingEvents().get(i).getEventID();
            String currentName = eventsController.getAllExistingEvents().get(i).getTitle();
            ArrayList<String> allAttendee = eventsController.getAttendeesFromEvent(currentEventID);
            overallInfo.append(currentName).append("has attendees as following:\n");
            for (String attendeeID: allAttendee){
                overallInfo.append(eventsController.getUserManager().getUserNameFromID(attendeeID));
            }
        }
        output.printPrompt(overallInfo);
    }

    public void getTop5EventInfo(EventsController eventsController){
        StringBuilder overallInfo = new StringBuilder();
        overallInfo.append("There are Top5 Events' Info:\n");
        ArrayList<String> top5Events = eventsController.getRoomManager().getTop5Events();
        for (int i = 0; i < top5Events.size(); i++){
            overallInfo.append("No_").append(i).append(eventsController.getEventInfo(top5Events.get(i)));
        }
        output.printPrompt(overallInfo);
    }

}
