package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votingapp.database.DBCandidate;
import com.example.votingapp.database.DBEvent;

public class SingleCandidate extends AppCompatActivity {

    TextView name, description, id;
    Button updateC, deleteC;
    DBCandidate DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_candidate);
        getSupportActionBar().hide();

        name = findViewById(R.id.singleC_name);
        description = findViewById(R.id.singleC_desc);
        id = findViewById(R.id.singleC_id);

        updateC = findViewById(R.id.singleC_edit);
        deleteC = findViewById(R.id.singleC_delete);

        Intent intent = getIntent();

       //String ID = intent.getExtras().getString("id");

        //Cursor res = (Cursor) DB.getCandidateInfo(ID);

        /*String candidate_name = res.getString(0);
        String candidate_desc = res.getString(1);
        String candidate_id = res.getString(2);

        name.setText(candidate_name);
        description.setText(candidate_desc);
        id.setText(candidate_id);*/

        //update
        updateC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingleCandidate.this,UpdateCandidate.class);
                startActivity(intent);
            }
        });

        //delete
        deleteC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTXT = id.getText().toString();
                Boolean checkDeleteCandidate = DB.deleteCandidate(idTXT);
                if (checkDeleteCandidate == true)
                    Toast.makeText(SingleCandidate.this, "Candidate deleted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(SingleCandidate.this, "Candidate not deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}