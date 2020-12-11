package com.example.a207_demo.contactSystem;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;

/**
 * ContactActivity
 */
public abstract class ContactActivity extends SetUpActivity {

    public void createContactMenu(RecyclerView recyclerView, ContactAdapter contactAdapter) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactAdapter);
    }

    /**
     * initContacts
     */
    protected void initContacts(){
        super.reset();
        super.readUser();
    }

}
