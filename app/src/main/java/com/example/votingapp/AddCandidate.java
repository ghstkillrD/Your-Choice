package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBCandidate;
import com.example.votingapp.database.DBEvent;

public class AddCandidate extends AppCompatActivity {

    EditText name, description, id;
    Button addC;

    DBCandidate DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_candidate);
        getSupportActionBar().hide();


        name = findViewById(R.id.addC_name);
        description = findViewById(R.id.addC_desc);
        id = findViewById(R.id.addC_id);


        addC = (Button) findViewById(R.id.addC_add);

        DB = new DBCandidate(this);

        addC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Description = description.getText().toString();
                String ID = id.getText().toString();

                if (Name.equals("")||Description.equals("")||ID.equals(""))
                    Toast.makeText(AddCandidate.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkID = DB.checkCandidateID(ID);
                    if (checkID == false) {
                        Boolean insert = DB.addCandidate(Name, Description, ID);
                        if (insert == true) {
                            Toast.makeText(AddCandidate.this, "Candidate added successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SingleEvent.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(AddCandidate.this, "Couldn't add the candidate! Try again", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(AddCandidate.this, "Candidate ID already exists! Try another ID", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}