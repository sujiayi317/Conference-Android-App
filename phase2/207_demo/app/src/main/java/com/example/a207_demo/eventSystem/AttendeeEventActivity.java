package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Event activity to show attendee.
 */
public class AttendeeEventActivity extends EventActivity {

    //Todo: generate event list through manager
    List<Event> eventList = new ArrayList<>();

    /**
     * Required function to initiate an Activity class.
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_attendee);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * Set up the activity.
     */
    public void init(){
        super.init(this, R.id.nav_view_attendee, R.id.nav_allevents);
        createEventMenu(eventList);
    }

    protected void createEventMenu(List<Event> eventList){
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        super.createEventMenu(eventList, recyclerView);
        AttendeeEventAdapter attendeeEventAdapter = new AttendeeEventAdapter(this, eventList);
        recyclerView.setAdapter(attendeeEventAdapter);
    }

    protected void initEvents(List<Event> eventList){
        //Todo: generate Event through use case

        for(int i = 0; i < 2; i++) {
            Event event1 = new Event("Event1", "BF101", "", "13:00", R.drawable.default_image);
            eventList.add(event1);
            Event event2 = new Event("Event2", "TH305", "", "13:00", R.drawable.default_image);
            eventList.add(event2);
            Event event3 = new Event("Event3", "RC104", "", "13:00", R.drawable.default_image);
            eventList.add(event3);
            Event event4 = new Event("Event4", "RC507", "", "13:00", R.drawable.default_image);
            eventList.add(event4);
            Event event5 = new Event("Event5", "SU302", "", "13:00", R.drawable.default_image);
            eventList.add(event5);
        }
    }
}
