package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votingapp.database.DBHelper;
import com.google.android.material.textview.MaterialTextView;

public class Profile extends AppCompatActivity {

    TextView name, username;
    MaterialTextView fullName, email, phone, password;
    Button updateUser, deleteUser;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        //hooks
        name = findViewById(R.id.prof_name);
        username = findViewById(R.id.prof_username);
        fullName = findViewById(R.id.prof_fullName);
        email = findViewById(R.id.prof_email);
        phone = findViewById(R.id.prof_phone);
        password = findViewById(R.id.prof_pass);

        updateUser = findViewById(R.id.prof_update);
        deleteUser = findViewById(R.id.prof_delete);

        DB = new DBHelper(this);

        Intent intent = getIntent();

        //String Username = intent.getExtras().getString("username");

        //Cursor res = (Cursor) DB.getUserInfo(Username);

        /*String user_name = res.getString(0);
        String user_username = res.getString(1);
        String user_fullName = res.getString(0);
        String user_email = res.getString(2);
        String user_phone = res.getString(3);
        String user_password = res.getString(4);

        name.setText(user_name);
        username.setText(user_username);
        fullName.setText(user_fullName);
        email.setText(user_email);
        phone.setText(user_phone);
        password.setText(user_password);*/

        //update
        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile.this,UpdateUser.class);
                //intent.putExtra("username", Username);
                startActivity(intent);
            }
        });

        //delete
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTXT = username.getText().toString();

                Boolean checkDeleteData = DB.deleteData(usernameTXT);
                if (checkDeleteData == true) {
                    Toast.makeText(Profile.this, "User deleted!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Profile.this, SignIn.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Profile.this, "User not deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}