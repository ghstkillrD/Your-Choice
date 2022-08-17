package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBEvent;

public class UpdateEvent extends AppCompatActivity {

    EditText name, desc, noOfVotes, date, sTime, eTime, id, password;
    Button update;

    DBEvent DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);
        getSupportActionBar().hide();

        name = findViewById(R.id.updateE_name);
        desc = findViewById(R.id.updateE_desc);
        noOfVotes = findViewById(R.id.updateE_noOfVotes);
        date = findViewById(R.id.updateE_date);
        sTime = findViewById(R.id.updateE_sTime);
        eTime = findViewById(R.id.updateE_eTime);

        update = findViewById(R.id.updateE_bt);

        String nameTXT = name.getText().toString();
        String descTXT = desc.getText().toString();
        String noOfVotesTXT = noOfVotes.getText().toString();
        String dateTXT = date.getText().toString();
        String sTimeTXT = sTime.getText().toString();
        String eTimeTXT = eTime.getText().toString();
        String idTXT = id.getText().toString();
        String passTXT = password.getText().toString();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean checkUpdateEvent = DB.updateEvents(nameTXT, descTXT, noOfVotesTXT, dateTXT, sTimeTXT, eTimeTXT, idTXT, passTXT);
                if (checkUpdateEvent == true) {
                    Toast.makeText(UpdateEvent.this, "Event updated!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateEvent.this, SingleEvent.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(UpdateEvent.this, "Event not updated!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}