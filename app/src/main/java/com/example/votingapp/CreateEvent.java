package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBEvent;
import com.example.votingapp.database.DBHelper;

public class CreateEvent extends AppCompatActivity {

    EditText name, description, noOfVotes, date, startTime, endTime, eventID, password;
    Button createEvent;

    DBEvent DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        getSupportActionBar().hide();

        name = findViewById(R.id.createE_name);
        description = findViewById(R.id.createE_desc);
        noOfVotes = findViewById(R.id.createE_noOfVotes);
        date = findViewById(R.id.createE_date);
        startTime = findViewById(R.id.createE_sTime);
        endTime = findViewById(R.id.createE_eTime);
        eventID = findViewById(R.id.createE_ID);
        password = findViewById(R.id.createE_pass);

        createEvent = (Button) findViewById(R.id.createE_submit);

        DB = new DBEvent(this);

        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Description = description.getText().toString();
                String NoOfVotes = noOfVotes.getText().toString();
                String Date = date.getText().toString();
                String StartTime = startTime.getText().toString();
                String EndTime = endTime.getText().toString();
                String EventID = eventID.getText().toString();
                String Password = password.getText().toString();

                if (Name.equals("")||Description.equals("")||NoOfVotes.equals("")||Date.equals("")||StartTime.equals("")||EndTime.equals("")||EventID.equals("")||Password.equals(""))
                    Toast.makeText(CreateEvent.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkID = DB.checkEventID(EventID);
                    if (checkID == false) {
                        Boolean insert = DB.addEvents(Name, Description, NoOfVotes, Date, StartTime, EndTime, EventID, Password);
                        if (insert == true) {
                            Toast.makeText(CreateEvent.this, "Event created successfully!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(CreateEvent.this, "Couldn't create the event! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(CreateEvent.this, "Event ID already exists! Try another ID", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}