package com.example.votingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votingapp.database.DBHelper;

public class UpdateUser extends AppCompatActivity {

    EditText name, username, email, phone, password;
    Button update;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        getSupportActionBar().hide();

        name = findViewById(R.id.updateU_name);
        email = findViewById(R.id.updateU_email);
        phone = findViewById(R.id.updateU_phone);
        password = findViewById(R.id.updateU_pass);

        update = findViewById(R.id.updateU_bt);

        String nameTXT = name.getText().toString();
        String usernameTXT = username.getText().toString();
        String emailTXT = email.getText().toString();
        String phoneTXT = phone.getText().toString();
        String passTXT = password.getText().toString();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean checkUpdateData = DB.updateData(nameTXT, usernameTXT, emailTXT, phoneTXT, passTXT);
                if (checkUpdateData == true) {
                    Toast.makeText(UpdateUser.this, "User updated!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateUser.this, Profile.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(UpdateUser.this, "User not updated!", Toast.LENGTH_SHORT).show();
            }
        });




    }
}