package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        login =(Button) findViewById(R.id.orglogin);
        register=(Button) findViewById(R.id.register_button);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2) ;        //for progress bar

        progressBar.setVisibility(View.INVISIBLE);                        //initialy invisible
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
        if(username.isEmpty() || password.isEmpty())
        {
            progressBar.setVisibility(View.INVISIBLE);

            Toast.makeText(login_activity.this,"Fill Details",Toast.LENGTH_SHORT).show();

        }
        else {
            progressBar.setVisibility(View.VISIBLE);          // visible
            login.setEnabled(false);
            register.setEnabled(false);

            firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressBar.setVisibility(View.INVISIBLE);                                      //before toast disable
                        Toast.makeText(login_activity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(login_activity.this, LoginHome.class));
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);                  //if credintals not match then agaon visible
                        login.setEnabled(true);
                        register.setEnabled((true));
                        Toast.makeText(login_activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }



}
