package com.example.a207_demo.contactSystem;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;
import com.example.a207_demo.R;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends SetUpActivity {

    //Todo: access Contact Controller
    private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        init();

        ActivityCollector.addActivity(this);

    }

    public void init(){
        super.createActionBar();
        super.createNavView(this, R.id.nav_contacts);
        createContactMenu();
    }

    public void createContactMenu(){
        initContacts();

        RecyclerView recyclerView = findViewById(R.id.contact_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContactAdapter contactAdapter = new ContactAdapter(this, contactList);
        recyclerView.setAdapter(contactAdapter);
    }

    public void initContacts(){
        //Todo: access Event Use case to generate event
        com.example.a207_demo.contactSystem.Contact contact1 = new com.example.a207_demo.contactSystem.Contact("Jenny Su", R.drawable.jenny);
        contactList.add(contact1);
        com.example.a207_demo.contactSystem.Contact contact2 = new com.example.a207_demo.contactSystem.Contact("Maggie Ma",  R.drawable.maggie);
        contactList.add(contact2);
        com.example.a207_demo.contactSystem.Contact contact3 = new com.example.a207_demo.contactSystem.Contact("Shawn Kong",  R.drawable.shawn);
        contactList.add(contact3);
        com.example.a207_demo.contactSystem.Contact contact4 = new com.example.a207_demo.contactSystem.Contact("Tony Huang",  R.drawable.tony);
        contactList.add(contact4);
        com.example.a207_demo.contactSystem.Contact contact5 = new com.example.a207_demo.contactSystem.Contact("Hardy Gu",  R.drawable.hardy);
        contactList.add(contact5);
        com.example.a207_demo.contactSystem.Contact contact6 = new com.example.a207_demo.contactSystem.Contact("Bruce Ma",  R.drawable.bruce);
        contactList.add(contact6);
        com.example.a207_demo.contactSystem.Contact contact7 = new com.example.a207_demo.contactSystem.Contact("Steve Wu",  R.drawable.steve);
        contactList.add(contact7);
        com.example.a207_demo.contactSystem.Contact contact8 = new com.example.a207_demo.contactSystem.Contact("Jenny Su", R.drawable.jenny);
        contactList.add(contact8);
        com.example.a207_demo.contactSystem.Contact contact9 = new com.example.a207_demo.contactSystem.Contact("Maggie Ma",  R.drawable.maggie);
        contactList.add(contact9);
        com.example.a207_demo.contactSystem.Contact contact10 = new com.example.a207_demo.contactSystem.Contact("Shawn Kong",  R.drawable.shawn);
        contactList.add(contact10);
        com.example.a207_demo.contactSystem.Contact contact11 = new com.example.a207_demo.contactSystem.Contact("Tony Huang",  R.drawable.tony);
        contactList.add(contact11);
        com.example.a207_demo.contactSystem.Contact contact12 = new com.example.a207_demo.contactSystem.Contact("Hardy Gu",  R.drawable.hardy);
        contactList.add(contact12);
        com.example.a207_demo.contactSystem.Contact contact13 = new com.example.a207_demo.contactSystem.Contact("Bruce Ma",  R.drawable.bruce);
        contactList.add(contact13);
        com.example.a207_demo.contactSystem.Contact contact14 = new com.example.a207_demo.contactSystem.Contact("Steve Wu",  R.drawable.steve);
        contactList.add(contact14);
    }

}
