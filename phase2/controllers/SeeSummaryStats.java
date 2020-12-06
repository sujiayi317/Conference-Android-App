package controllers;

import use_cases.UserManager;

import java.util.ArrayList;

public class SeeSummaryStats {
    private static OutputManager output;

    public SeeSummaryStats(){
        output = new OutputManager();
    }

    public void getToSeeSummaryStat(EventsController eventsController, UserManager userManager ){
        ArrayList<String> overallUser = new ArrayList<>();
        ArrayList<String> topFiveEvents = new ArrayList<>();
        ArrayList<String> vipInfo = new ArrayList<>();

    }

    public void getToSeeEventEnrollmentStatistics(String eventID, EventsController eventsController){

    }

}
