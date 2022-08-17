package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBEvent;
import com.example.votingapp.database.DBVoter;

public class AddVoter extends AppCompatActivity {

    EditText name, username, phone;
    Button addV;

    DBVoter DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_voter);
        getSupportActionBar().hide();

        name = findViewById(R.id.addV_name);
        username = findViewById(R.id.addV_username);
        phone = findViewById(R.id.addV_phone);

        addV = (Button) findViewById(R.id.addV_add);

        DB = new DBVoter(this);
        addV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Username = username.getText().toString();
                String Phone = phone.getText().toString();

                if (Name.equals("")||Username.equals("")||Phone.equals(""))
                    Toast.makeText(AddVoter.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUsername = DB.checkVoterUsername(Username);
                    if (checkUsername == false) {
                        Boolean insert = DB.addVoters(Name, Username, Phone);
                        if (insert == true) {
                            Toast.makeText(AddVoter.this, "Voter added successfully!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), SingleEvent.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(AddVoter.this, "Couldn't add the voter! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(AddVoter.this, "Voter username already exists! Try another username", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}