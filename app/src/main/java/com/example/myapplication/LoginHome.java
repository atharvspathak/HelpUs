package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginHome extends AppCompatActivity {
    private Button usprofile;
    private Button donate;
    private Button helpnow;
    private Button history;
    private Button feedback;
    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_home);
        usprofile=(Button)findViewById(R.id.orgprofile);
        donate=(Button)findViewById(R.id.donations);
        helpnow=(Button)findViewById(R.id.helpto);
        history=(Button)findViewById(R.id.history);
        feedback=(Button)findViewById(R.id.orgfeedback);
        Logout=(Button)findViewById(R.id.orglogout);

        usprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginHome.this,Uprofile.class));

            }
        });

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        helpnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LoginHome.this,MainActivity.class));

            }
        });

    }
}
