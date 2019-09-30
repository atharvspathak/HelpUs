package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrganisationHome extends AppCompatActivity {

    private Button usprofile;
    private Button donate;
    private Button helpnow;
    private Button feedback;
    private Button Logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_home);



        usprofile=(Button)findViewById(R.id.orgprofile);
        donate=(Button)findViewById(R.id.donations);
        helpnow=(Button)findViewById(R.id.helpto);

        feedback=(Button)findViewById(R.id.orgfeedback);
        Logout=(Button)findViewById(R.id.orglogout);

        usprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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



        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(OrganisationHome.this,MainActivity.class));

            }
        });

    }
}
