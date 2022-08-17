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

public class CastAVote extends AppCompatActivity {

    EditText id, pass;
    Button enter;

    DBEvent DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_avote);
        getSupportActionBar().hide();

        id = (EditText) findViewById(R.id.vote_eID);
        pass = (EditText) findViewById(R.id.vote_ePass);

        enter = (Button) findViewById(R.id.vote_enter);

        DB = new DBEvent(this);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eventID = id.getText().toString();
                String password = pass.getText().toString();

                if (eventID.equals("")||password.equals(""))
                    Toast.makeText(CastAVote.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkEventExist = DB.checkEventExist(eventID, password);
                    if (checkEventExist == true) {
                        Toast.makeText(CastAVote.this, "Successfully Entered!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Voting.class);
                        //passing eventID
                        intent.putExtra("eventID", eventID);
                        startActivity(intent);
                    }else {
                        Toast.makeText(CastAVote.this, "Invalid Event ID or Password! Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}