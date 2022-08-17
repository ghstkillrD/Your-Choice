package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.votingapp.database.DBEvent;

import java.util.ArrayList;

public class MyEvents extends AppCompatActivity {

    DBEvent DB;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_events);
        getSupportActionBar().hide();

        DB = new DBEvent(this);
        listItem = new ArrayList<>();
        eventList = findViewById(R.id.view_events);
        viewEvents();
        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent;
                String text = eventList.getItemAtPosition(i).toString();
                //Toast.makeText(MyEvents.this, ""+text, Toast.LENGTH_SHORT).show();
                intent = new Intent(getApplicationContext(), SingleEvent.class);
                intent.putExtra("eventID", listItem);
                startActivity(intent);
            }
        });
    }
    private void viewEvents() {
        Cursor cursor = DB.getEvents();
        if (cursor.getCount() == 0) {
            Toast.makeText(MyEvents.this, "No events to show!", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(0));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            eventList.setAdapter(adapter);
        }
    }
}