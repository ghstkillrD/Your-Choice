package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    EditText name, username, email, phoneNumber, password;
    Button signUp;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        name = findViewById(R.id.su_name);
        username = findViewById(R.id.su_username);
        email = findViewById(R.id.su_email);
        phoneNumber = findViewById(R.id.su_phone);
        password = findViewById(R.id.su_pass);

        signUp = (Button) findViewById(R.id.bt_su);
        DB = new DBHelper(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name.getText().toString();
                String Username = username.getText().toString();
                String Email = email.getText().toString();
                String PhoneNumber = phoneNumber.getText().toString();
                String Password = password.getText().toString();

                if (Name.equals("")||Username.equals("")||Email.equals("")||PhoneNumber.equals("")||Password.equals(""))
                    Toast.makeText(SignUp.this, "Fields cannot be empty!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkUser = DB.checkUsername(Username);
                    if (checkUser == false) {
                        Boolean insert = DB.insertData(Name, Username, Email, PhoneNumber, Password);
                        if (insert == true) {
                            Toast.makeText(SignUp.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SignIn.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(SignUp.this, "Registration Unsuccessful!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignUp.this, "User already exists! Try another Username", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}