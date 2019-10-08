package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Orginisation_login extends AppCompatActivity {

    private Button login;
    private EditText un;
    private EditText pas;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orginisation_login);

        login=(Button)findViewById(R.id.orglogin);
        un=(EditText) findViewById(R.id.orgun);
        pas=(EditText) findViewById(R.id.orgpas);
        progressBar=(ProgressBar)findViewById(R.id.progressBar4);

        progressBar.setVisibility(View.INVISIBLE);


        firebaseAuth=FirebaseAuth.getInstance();       //TO get instance if it is not present then show exception
        FirebaseUser user=firebaseAuth.getCurrentUser();//To get current user





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valdation(un.getText().toString(),pas.getText().toString());
            }
        });

    }

    private void valdation(String username,String password)//signInwithEmailAndPassword
    {
        if(username.isEmpty() || password.isEmpty())
        {

            Toast.makeText(Orginisation_login.this,"Fill Details",Toast.LENGTH_SHORT).show();

        }
        else {
            if (username.equals("admin@gmail.com") && password.equals("Admin@1234")) {     //For static login of organisation from firebase
                progressBar.setVisibility(View.VISIBLE);          // visible
                login.setEnabled(false);

                firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.INVISIBLE);                                      //before toast disable
                            Toast.makeText(Orginisation_login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(Orginisation_login.this, OrganisationHome.class));
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);                  //if credintals not match then agaon visible
                            login.setEnabled(true);

                            Toast.makeText(Orginisation_login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else{
                Toast.makeText(Orginisation_login.this,"Organisation Details Not Match",Toast.LENGTH_SHORT).show();

            }

        }

    }
}
