package com.example.a207_demo.contactSystem;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;
import com.example.a207_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ContactActivity
 */
public class ContactActivity extends SetUpActivity {

    //Todo: access Contact Controller
    private List<com.example.a207_demo.contactSystem.Contact> contactList = new ArrayList<>();

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        init();

        ActivityCollector.addActivity(this);

    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_attendee, R.id.nav_contacts);
        createContactMenu(contactList);
    }

    /**
     * create Contact Menu
     *
     * @param contactList List<Contact>
     */
    public void createContactMenu(List<com.example.a207_demo.contactSystem.Contact> contactList) {
        initContacts(contactList);

        RecyclerView recyclerView = findViewById(R.id.contact_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        com.example.a207_demo.contactSystem.ContactAdapter contactAdapter = new com.example.a207_demo.contactSystem.ContactAdapter(this, contactList);
        recyclerView.setAdapter(contactAdapter);
    }

    /**
     * initContacts
     *
     * @param contactList List<Contact>
     */
    public void initContacts(List<com.example.a207_demo.contactSystem.Contact> contactList) {
        //Todo: access Contact Use case to generate contacts

        com.example.a207_demo.contactSystem.Contact contact1 = new com.example.a207_demo.contactSystem.Contact("Jenny Su", R.drawable.jenny);
        contactList.add(contact1);
        com.example.a207_demo.contactSystem.Contact contact2 = new com.example.a207_demo.contactSystem.Contact("Maggie Ma", R.drawable.maggie);
        contactList.add(contact2);
        com.example.a207_demo.contactSystem.Contact contact3 = new com.example.a207_demo.contactSystem.Contact("Shawn Kong", R.drawable.shawn);
        contactList.add(contact3);
        com.example.a207_demo.contactSystem.Contact contact4 = new com.example.a207_demo.contactSystem.Contact("Tony Huang", R.drawable.tony);
        contactList.add(contact4);
        com.example.a207_demo.contactSystem.Contact contact5 = new com.example.a207_demo.contactSystem.Contact("Hardy Gu", R.drawable.hardy);
        contactList.add(contact5);
        com.example.a207_demo.contactSystem.Contact contact6 = new com.example.a207_demo.contactSystem.Contact("Bruce Ma", R.drawable.bruce);
        contactList.add(contact6);
        com.example.a207_demo.contactSystem.Contact contact7 = new com.example.a207_demo.contactSystem.Contact("Steve Wu", R.drawable.steve);
        contactList.add(contact7);
        com.example.a207_demo.contactSystem.Contact contact8 = new com.example.a207_demo.contactSystem.Contact("Jenny Su", R.drawable.jenny);
        contactList.add(contact8);
        com.example.a207_demo.contactSystem.Contact contact9 = new com.example.a207_demo.contactSystem.Contact("Maggie Ma", R.drawable.maggie);
        contactList.add(contact9);
        com.example.a207_demo.contactSystem.Contact contact10 = new com.example.a207_demo.contactSystem.Contact("Shawn Kong", R.drawable.shawn);
        contactList.add(contact10);
        com.example.a207_demo.contactSystem.Contact contact11 = new com.example.a207_demo.contactSystem.Contact("Tony Huang", R.drawable.tony);
        contactList.add(contact11);
        com.example.a207_demo.contactSystem.Contact contact12 = new com.example.a207_demo.contactSystem.Contact("Hardy Gu", R.drawable.hardy);
        contactList.add(contact12);
        com.example.a207_demo.contactSystem.Contact contact13 = new com.example.a207_demo.contactSystem.Contact("Bruce Ma", R.drawable.bruce);
        contactList.add(contact13);
        com.example.a207_demo.contactSystem.Contact contact14 = new com.example.a207_demo.contactSystem.Contact("Steve Wu", R.drawable.steve);
        contactList.add(contact14);
    }

}
