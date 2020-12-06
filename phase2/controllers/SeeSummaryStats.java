package controllers;

import use_cases.AttendeeManager;
import use_cases.OrganizerManager;
import use_cases.SpeakerManager;
import use_cases.UserManager;

import java.util.ArrayList;

public class SeeSummaryStats {
    private static OutputManager output;

    public SeeSummaryStats(){
        output = new OutputManager();
    }

    public void getToSeeUserStat(UserManager userManager, SpeakerManager speakerManager,
                                 AttendeeManager attendeeManager, OrganizerManager organizerManager){
        ArrayList<String> overallUser = userManager.;
        ArrayList<String> overallSpeaker = new ArrayList<>();
        ArrayList<String> overallVip = new ArrayList<>();

    }

    public void getToSeeEventEnrollmentStatistics(String eventID, EventsController eventsController){

    }

}
