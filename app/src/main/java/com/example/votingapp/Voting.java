package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.votingapp.database.DBCandidate;
import com.example.votingapp.database.DBEvent;

public class Voting extends AppCompatActivity {

    TextView name, desc, date, sTime, eTime;

    //SeekBar seekbar;
    TextView option;
    //TextView percent;
    //double count1=1, count2=1, count3=1, count4=1;
    //boolean flag1=true, flag2=true, flag3=true, flag4=true;

    DBEvent eDB;
    DBCandidate cDB;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        getSupportActionBar().hide();

        name = findViewById(R.id.voting_name);
        desc = findViewById(R.id.voting_desc);
        date = findViewById(R.id.voting_date);
        sTime = findViewById(R.id.voting_sTime);
        eTime = findViewById(R.id.voting_eTime);

        //seekbar = findViewById(R.id.seek_bar1);

        //percent = findViewById(R.id.seek_percent1);

        Intent intent = getIntent();

        String eventID = intent.getExtras().getString("eventID");

        Cursor res1 = (Cursor) eDB.getEventInfo(eventID);

        String event_name = res1.getString(0);
        String event_desc = res1.getString(1);
        String event_date = res1.getString(3);
        String event_sTime = res1.getString(4);
        String event_eTime = res1.getString(5);

        name.setText(event_name);
        desc.setText(event_desc);
        date.setText(event_date);
        sTime.setText(event_sTime);
        eTime.setText(event_eTime);

        option = findViewById(R.id.seek_option1);

        Cursor res2 = cDB.getCandidates();

        String candidate_name = res2.getString(0);

        option.setText(candidate_name);

        /*seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });*/

        /*option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check condition
                if (flag1) {
                    //when flag 1 is true
                    count1++;
                    count2 = 1;
                    count3 = 1;
                    count4 = 1;
                    flag1 = false;
                    flag2 = true;
                    flag3 = true;
                    flag4 = true;
                    //percentage calculation
                    calculatePercent();
                }
            }
        });*/

    }

    /*private void calculatePercent() {
        //calculate total
        double total = count1 + count2 + count3 + count4;
        //calculate percentage for all options
        double Percent1 = (count1/total)*100;
        double Percent2 = (count2/total)*100;
        double Percent3 = (count3/total)*100;
        double Percent4 = (count4/total)*100;
        //set percent on text view
        percent1.setText(String.format("%.0f%%",Percent1));
        //set progress on seek bar
        seekbar1.setProgress((int) Percent1);
        percent2

    }*/
}