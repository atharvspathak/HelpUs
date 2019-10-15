package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Ohelpedto extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView  mUserList;                                       //tO create List View
    private FirebaseAuth firebaseAuth;
    private ArrayList<String> mUsername=new ArrayList<>();               //To Create List Of arraay
    private Button Show,Back;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ohelpedto);


        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("User"); //

        mUserList=(ListView)findViewById(R.id.userList);
        Show=(Button)findViewById(R.id.button);
        Back=(Button)findViewById(R.id.button2);
        progressBar=(ProgressBar)findViewById(R.id.progressBar6);
        progressBar.setVisibility(View.INVISIBLE);

        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(Ohelpedto.this,android.R.layout.simple_expandable_list_item_1,mUsername);

        mUserList.setAdapter(arrayAdapter);                   //Set Array adapter to mUserList



        Show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);


            mDatabase.addChildEventListener(new ChildEventListener() {                            //To get event listener to database
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String name=dataSnapshot.child("name").getValue().toString();               //TO retrive data datasnapshot.chid("name of attribut").getvalue.tostring
                    String email=dataSnapshot.child("email").getValue().toString();
                    String contact=dataSnapshot.child("contact").getValue().toString();


                    mUsername.add(name);                                //Add to list
                    mUsername.add(email);
                    mUsername.add(contact);
                    mUsername.add(" ");

                    arrayAdapter.notifyDataSetChanged();
                    Show.setEnabled(false);




                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Ohelpedto.this,"Retrived",Toast.LENGTH_SHORT).show();

            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Ohelpedto.this,OrganisationHome.class));
            }
        });





    }



}
