package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import com.example.votingapp.database.DBCandidate;
import com.example.votingapp.database.DBEvent;
import com.example.votingapp.database.DBVoter;

import java.text.BreakIterator;

public class Casting extends AppCompatActivity {

    TextView Ename, Edesc, Edate, Cname, Cusername;

    DBEvent DB;
    DBCandidate db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casting);

        showVotingData();
        //onVote();
    }

    private void showVotingData() {

        Cursor res1 = DB.getEvents();

        String event_name = res1.getString(0);
        String event_desc = res1.getString(1);
        String event_date = res1.getString(2);
        String event_time = res1.getString(3);

        Ename.setText(event_name);
        Edesc.setText(event_desc);
        Edate.setText(event_date);
        Edate.setText(event_time);

        Cursor res2 = db.getCandidates();

        String c_name = res2.getString(0);
        String c_desc = res2.getString(1);

        Cname.setText(c_name);
        Cusername.setText(c_desc);
    }

    /*public void onVote(){
        BreakIterator vote = null;
        String VoteCount = vote.getText().toString().trim();
        int count = Integer.parseInt(VoteCount);
        count++;
        vote.setText(String.valueOf(count);
    }*/
}