package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.jar.Attributes;

public class login_activity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button register;
    private Button login;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        login =(Button) findViewById(R.id.orglogin);
        register=(Button) findViewById(R.id.register_button);


        Intent intent=getIntent();
        String title=intent.getStringExtra(MainActivity.EXTRA_TEXT);

        TextView t1=(TextView) findViewById(R.id.title);
        t1.setText(title);         //to set title



        username=(EditText)findViewById(R.id.orgun);
        password=(EditText)findViewById(R.id.orgpas);

        firebaseAuth=FirebaseAuth.getInstance();//to get instance

        FirebaseUser user=firebaseAuth.getCurrentUser();//To get current user




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                valdation(username.getText().toString(),password.getText().toString()); //If usname and pass match then go to next activity


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login_activity.this,Register_activity.class));

            }
        });
    }

    private void valdation(String username,String password)//signInwithEmailAndPassword
    {
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(login_activity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(login_activity.this,LoginHome.class));
                }
                else
                {
                    Toast.makeText(login_activity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



}
