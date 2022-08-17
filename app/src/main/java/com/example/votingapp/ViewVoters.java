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

import com.example.votingapp.database.DBVoter;

import java.util.ArrayList;

public class ViewVoters extends AppCompatActivity {

    DBVoter DB;

    ArrayList<String> listItem;
    ArrayAdapter adapter;

    ListView voterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_voters);
        getSupportActionBar().hide();

        DB = new DBVoter(this);
        listItem = new ArrayList<>();
        voterList = findViewById(R.id.view_events);
        viewVoters();
        voterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Intent intent;
                String text = voterList.getItemAtPosition(i).toString();
                intent = new Intent(getApplicationContext(), SingleVoter.class);
                intent.putExtra("username", listItem);
                startActivity(intent);
            }
        });
    }

    private void viewVoters() {
        Cursor cursor = DB.getVoter();
        if (cursor.getCount() == 0) {
            Toast.makeText(ViewVoters.this, "No voters to show!", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()) {
                listItem.add(cursor.getString(0));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            voterList.setAdapter(adapter);
        }
    }

}