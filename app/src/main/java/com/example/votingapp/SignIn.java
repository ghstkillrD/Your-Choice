package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBHelper;

public class SignIn extends AppCompatActivity {

    EditText username, password;
    Button signIn;
    //db connectivity
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();

        username = (EditText) findViewById(R.id.si_username);
        password = (EditText) findViewById(R.id.si_pass);

        signIn = (Button) findViewById(R.id.bt_si);

        DB = new DBHelper(this);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = username.getText().toString();
                String Password = password.getText().toString();

                if (Username.equals("")||Password.equals(""))
                    Toast.makeText(SignIn.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUsernamePassword = DB.checkUsernamePassword(Username, Password);
                    if (checkUsernamePassword == true) {
                        Toast.makeText(SignIn.this, "Successfully Signed In!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        //passing username
                        intent.putExtra("username", Username);
                        startActivity(intent);
                    }else {
                        Toast.makeText(SignIn.this, "Invalid Username or Password! Try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}