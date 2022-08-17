package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votingapp.database.DBEvent;

public class SingleEvent extends AppCompatActivity {

    TextView name, fullName, description, noOfVotes, date, sTime, eTime, id, password;
    Button updateEvent, deleteEvent, addC, addV, viewC, viewV;
    DBEvent DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);
        getSupportActionBar().hide();

        //hooks
        name = findViewById(R.id.singleE_name);
        fullName = findViewById(R.id.singleE_fullname);
        description = findViewById(R.id.singleE_desc);
        noOfVotes = findViewById(R.id.singleE_noOfVotes);
        date = findViewById(R.id.singleE_date);
        sTime = findViewById(R.id.singleE_sTime);
        eTime = findViewById(R.id.singleE_eTime);
        id = findViewById(R.id.singleE_ID);
        password = findViewById(R.id.singleE_pass);

        updateEvent = findViewById(R.id.singleE_edit);
        deleteEvent = findViewById(R.id.singleE_delete);

        addC = findViewById(R.id.singleE_addC);
        addV = findViewById(R.id.singleE_addV);
        viewC = findViewById(R.id.singleE_viewC);
        viewV = findViewById(R.id.singleE_viewV);

        Intent intent = getIntent();

        //String eventID = intent.getExtras().getString("eventID");

        //Cursor res = (Cursor) DB.getEventInfo(eventID);

        /*String event_name = res.getString(0);
        String event_fullName = res.getString(0);
        String event_desc = res.getString(1);
        String event_noOfVotes = res.getString(2);
        String event_date = res.getString(3);
        String event_sTime = res.getString(4);
        String event_eTime = res.getString(5);
        String event_id = res.getString(6);
        String user_password = res.getString(7);

        name.setText(event_name);
        fullName.setText(event_fullName);
        description.setText(event_desc);
        noOfVotes.setText(event_noOfVotes);
        date.setText(event_date);
        sTime.setText(event_sTime);
        eTime.setText(event_eTime);
        id.setText(event_id);
        password.setText(user_password);*/

        addC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddCandidate.class);
                startActivity(intent);
            }
        });

        addV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddVoter.class);
                startActivity(intent);
            }
        });

        viewC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewCandidates.class);
                startActivity(intent);
            }
        });

        viewV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewVoters.class);
                startActivity(intent);
            }
        });

        //update
        updateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleEvent.this,UpdateEvent.class);
                startActivity(intent);
            }
        });

        //delete
        deleteEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT = id.getText().toString();
                Boolean checkDeleteEvent = DB.deleteEvents(idTXT);
                if (checkDeleteEvent == true)
                    Toast.makeText(SingleEvent.this, "Event deleted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SingleEvent.this, "Event not deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}