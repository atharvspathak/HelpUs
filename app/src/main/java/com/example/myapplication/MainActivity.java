package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_TEXT="com.example.myapplication.EXTRA_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button user=(Button) findViewById(R.id.user_button);
        Button ngo =(Button) findViewById(R.id.ngo_button);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,login_activity.class));

            }
        });

        ngo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Orginisation_login.class));
            }
        });


    }
    public void nextActivity(int id){


            Intent intent = new Intent(this, login_activity.class);
            if(id==1){
                intent.putExtra(EXTRA_TEXT,"Login as User");
            }
            else if(id==2){
                intent.putExtra(EXTRA_TEXT,"Login as NGO");
            }
            startActivity(intent);

    }
}
