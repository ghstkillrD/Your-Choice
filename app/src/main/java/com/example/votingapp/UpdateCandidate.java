package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBCandidate;

public class UpdateCandidate extends AppCompatActivity {

    EditText name, desc, id;
    Button update;

    DBCandidate DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_candidate);
        getSupportActionBar().hide();

        name = findViewById(R.id.updateC_name);
        desc = findViewById(R.id.updateC_desc);

        update = findViewById(R.id.updateC_bt);

        String nameTXT = name.getText().toString();
        String descTXT = desc.getText().toString();
        String idTXT = id.getText().toString();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean checkUpdateCandidate = DB.updateCandidate(nameTXT, descTXT, idTXT);
                if (checkUpdateCandidate == true) {
                    Toast.makeText(UpdateCandidate.this, "Candidate updated!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateCandidate.this, SingleCandidate.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(UpdateCandidate.this, "Candidate not updated!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}