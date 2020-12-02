package com.example.a207_demo.eventSystem;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a207_demo.R;

import java.util.List;

public class OrganizerEventAdapter extends EventAdapter {
    private Context context;

    public OrganizerEventAdapter(Context context, List<Event> eventList){
        super(context, eventList);
        this.context = context;
    }

    @NonNull
    @Override
    public VHEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        VHEvent holder = new VHEvent(v);
        setClickEventListener(holder, OrganizerEventContentActivity.class);
        return holder;
    }

}
