package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBVoter;

public class UpdateVoter extends AppCompatActivity {

    EditText name, username, phone;
    Button update;

    DBVoter DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_voter);
        getSupportActionBar().hide();

        name = findViewById(R.id.updateV_name);
        phone = findViewById(R.id.updateV_phone);

        update = findViewById(R.id.updateV_bt);

        String nameTXT = name.getText().toString();
        String usernameTXT = username.getText().toString();
        String phoneTXT = phone.getText().toString();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkUpdateVoter = DB.updateVoter(nameTXT, usernameTXT, phoneTXT);
                if (checkUpdateVoter == true) {
                    Toast.makeText(UpdateVoter.this, "Voter updated!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateVoter.this, SingleVoter.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(UpdateVoter.this, "Voter not updated!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}