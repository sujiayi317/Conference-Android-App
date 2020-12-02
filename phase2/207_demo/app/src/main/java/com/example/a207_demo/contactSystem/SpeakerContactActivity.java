package com.example.a207_demo.contactSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a207_demo.R;
import com.example.a207_demo.eventSystem.Event;
import com.example.a207_demo.eventSystem.SpeakerMyEventActivity;
import com.example.a207_demo.messageSystem.SpeakerAnnouncementActivity;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;
import com.example.a207_demo.utility.Settings;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class SpeakerContactActivity extends SetUpActivity {

    //Todo: access Contact Controller
    private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_speaker);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init() {
        super.init(this, R.id.nav_view_speaker, R.id.nav_contacts_speaker);
        createContactMenu(contactList);
    }

    public void createContactMenu(List<Contact> contactList){
        initContacts(contactList);

        RecyclerView recyclerView = findViewById(R.id.contact_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContactAdapter contactAdapter = new ContactAdapter(this, contactList);
        recyclerView.setAdapter(contactAdapter);
    }

    public void initContacts(List<Contact> contactList){
        //Todo: access Contact Use case to generate contacts
        Contact contact1 = new Contact("Jenny Su", R.drawable.jenny);
        contactList.add(contact1);
        Contact contact2 = new Contact("Maggie Ma",  R.drawable.maggie);
        contactList.add(contact2);
        Contact contact3 = new Contact("Shawn Kong",  R.drawable.shawn);
        contactList.add(contact3);
        Contact contact4 = new Contact("Tony Huang",  R.drawable.tony);
        contactList.add(contact4);
        Contact contact5 = new Contact("Hardy Gu",  R.drawable.hardy);
        contactList.add(contact5);
        Contact contact6 = new Contact("Bruce Ma",  R.drawable.bruce);
        contactList.add(contact6);
        Contact contact7 = new Contact("Steve Wu",  R.drawable.steve);
        contactList.add(contact7);
        Contact contact8 = new Contact("Jenny Su", R.drawable.jenny);
        contactList.add(contact8);
        Contact contact9 = new Contact("Maggie Ma",  R.drawable.maggie);
        contactList.add(contact9);
        Contact contact10 = new Contact("Shawn Kong",  R.drawable.shawn);
        contactList.add(contact10);
        Contact contact11 = new Contact("Tony Huang",  R.drawable.tony);
        contactList.add(contact11);
        Contact contact12 = new Contact("Hardy Gu",  R.drawable.hardy);
        contactList.add(contact12);
        Contact contact13 = new Contact("Bruce Ma",  R.drawable.bruce);
        contactList.add(contact13);
        Contact contact14 = new Contact("Steve Wu",  R.drawable.steve);
        contactList.add(contact14);
    }
}