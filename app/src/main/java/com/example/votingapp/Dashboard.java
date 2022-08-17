package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{

    public CardView cast, create, recent, myEvents, profile, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getSupportActionBar().hide();

        cast = (CardView) findViewById(R.id.dash_cast);
        create = (CardView) findViewById(R.id.dash_events);
        recent = (CardView) findViewById(R.id.dash_recent);
        myEvents = (CardView) findViewById(R.id.dash_myEvents);
        profile = (CardView) findViewById(R.id.dash_profile);
        logout = (CardView) findViewById(R.id.dash_logout);

        cast.setOnClickListener(this);
        create.setOnClickListener(this);
        recent.setOnClickListener(this);
        myEvents.setOnClickListener(this);
        profile.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()) {
            case R.id.dash_cast:
                intent = new Intent(this, CastAVote.class);
                startActivity(intent);
                break;

            case R.id.dash_events:
                intent = new Intent(this, CreateEvent.class);
                startActivity(intent);
                break;

            /*case R.id.dash_recent:
                intent = new Intent(this, CastAVote.class);
                startActivity(intent);
                break;*/

            case R.id.dash_myEvents:
                intent = new Intent(this, MyEvents.class);
                startActivity(intent);
                break;

            case R.id.dash_profile:
                intent = new Intent(this, Profile.class);
                startActivity(intent);
                break;

            case R.id.dash_logout:
                intent = new Intent(this, MainMenu.class);
                startActivity(intent);
                break;
        }
    }
}