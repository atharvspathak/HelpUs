package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login_activity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button register;
    private Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        login =(Button) findViewById(R.id.orglogin);
        register=(Button) findViewById(R.id.register_button);


        Intent intent=getIntent();
        String title=intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView t1=(TextView) findViewById(R.id.title);
        t1.setText(title);


        username=(EditText)findViewById(R.id.orgun);
        password=(EditText)findViewById(R.id.orgpas);




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this,LoginHome.class));


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this,Register_activity.class));

            }
        });
    }


}
