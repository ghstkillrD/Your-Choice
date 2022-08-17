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
import com.example.votingapp.database.DBVoter;

public class SingleVoter extends AppCompatActivity {

    TextView name, username, phone;
    Button updateV, deleteV;
    DBVoter DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_voter);
        getSupportActionBar().hide();

        name = findViewById(R.id.singleV_name);
        username = findViewById(R.id.singleV_username);
        phone = findViewById(R.id.singleV_phone);

        updateV = findViewById(R.id.singleV_edit);
        deleteV = findViewById(R.id.singleV_delete);

        Intent intent = getIntent();

        String Username = intent.getExtras().getString("username");

        Cursor res = (Cursor) DB.getVoterInfo(Username);

        String voter_name = res.getString(0);
        String voter_username = res.getString(1);
        String voter_phone = res.getString(2);

        name.setText(voter_name);
        username.setText(voter_username);
        phone.setText(voter_phone);

        //update
        updateV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleVoter.this,UpdateVoter.class);
                startActivity(intent);
            }
        });

        //delete
        deleteV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTXT = username.getText().toString();
                Boolean checkDeleteVoter = DB.deleteVoters(usernameTXT);
                if (checkDeleteVoter == true)
                    Toast.makeText(SingleVoter.this, "Voter deleted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SingleVoter.this, "Voter not deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}