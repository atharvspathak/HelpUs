package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Oprofile extends AppCompatActivity {
    private TextView Name,Email,Contact;
    private Button Show,Back;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oprofile);

        Name=(TextView)findViewById(R.id.textView2);
        Email=(TextView)findViewById(R.id.textView3);
        Contact=(TextView)findViewById(R.id.textView4);
        Show=(Button)findViewById(R.id.show);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        Back=(Button)findViewById(R.id.back);
        firebaseAuth=FirebaseAuth.getInstance();                                                    //Instance of auth

        progressBar.setVisibility(View.INVISIBLE);                                //To set progressbar invisible initialy

        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                databaseReference= FirebaseDatabase.getInstance().getReference().child(firebaseAuth.getUid()); // instance of database  get refrence of database by uid
                databaseReference.addValueEventListener(new ValueEventListener() {                  //addValueEvent Listenrt Is neccesory
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //To disable button
                        String name=dataSnapshot.child("name").getValue().toString();               //TO retrive data datasnapshot.chid("name of attribut").getvalue.tostring
                        String email=dataSnapshot.child("email").getValue().toString();
                        String contact=dataSnapshot.child("contact").getValue().toString();

                        Name.setText(name);                                                         //to set values to textview
                        Email.setText(email);
                        Contact.setText(contact);

                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                Back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        startActivity(new Intent(Oprofile.this,OrganisationHome.class));
                    }
                });




            }
        });

    }
}
