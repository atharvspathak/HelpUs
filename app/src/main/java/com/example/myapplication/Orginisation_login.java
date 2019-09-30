package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Orginisation_login extends AppCompatActivity {

    private Button login;
    private EditText un;
    private EditText pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orginisation_login);

        login=(Button)findViewById(R.id.orglogin);
        un=(EditText) findViewById(R.id.orgun);
        pas=(EditText) findViewById(R.id.orgpas);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(Orginisation_login.this,OrganisationHome.class)));
            }
        });

    }
}
