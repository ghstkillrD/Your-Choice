package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.votingapp.database.DBCandidate;
import com.example.votingapp.database.DBEvent;
import com.example.votingapp.database.DBVoter;

import java.util.ArrayList;

public class ViewCandidates extends AppCompatActivity {

    DBCandidate DB;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView candidateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_candidates);
        getSupportActionBar().hide();

        DB = new DBCandidate(this);

        listItem = new ArrayList<>();
        candidateList = findViewById(R.id.view_candidates);

        viewCandidates();

        candidateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent;
                String text = candidateList.getItemAtPosition(i).toString();
                intent = new Intent(getApplicationContext(), SingleCandidate.class);
                intent.putExtra("id", listItem);
                startActivity(intent);
            }
        });
    }

    private void viewCandidates() {
        Cursor cursor = DB.getCandidates();
        if (cursor.getCount() == 0) {
            Toast.makeText(ViewCandidates.this, "No candidate to show!", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(0));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            candidateList.setAdapter(adapter);
        }
    }
}