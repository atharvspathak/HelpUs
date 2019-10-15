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

public class OrganisationDonation extends AppCompatActivity {
    private Button Show,Back;
    private ListView donation;
    private DatabaseReference mDatabase;
    private DatabaseReference nDatabase;//For donar name
    private FirebaseAuth firebaseAuth;
    private ArrayList<String> mUsernamee=new ArrayList<>();
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organisation_donation);


        Show=(Button)findViewById(R.id.button6);
        Back=(Button)findViewById(R.id.button8);
        donation=(ListView)findViewById(R.id.DonationView);
        progressBar=(ProgressBar)findViewById(R.id.progressBar8);

        progressBar.setVisibility(View.INVISIBLE);
        firebaseAuth=FirebaseAuth.getInstance();


        mDatabase= FirebaseDatabase.getInstance().getReference().child("Donation"); //

        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(OrganisationDonation.this,android.R.layout.simple_expandable_list_item_1,mUsernamee);

        donation.setAdapter(arrayAdapter);                   //Set Array adapter to donation
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrganisationDonation.this,OrganisationHome.class));
            }
        });

        Show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                mDatabase.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        
                        String Quantity=dataSnapshot.child("quantity").getValue().toString();         //TO retrive data datasnapshot.chid("name of attribut").getvalue.tostring
                        String Cloths=dataSnapshot.child("cloths").getValue().toString();
                        String Money=dataSnapshot.child("amount").getValue().toString();


                        mUsernamee.add("FOOD QUANTITY(KG):"+Quantity);                                //Add to list
                        mUsernamee.add("Cloths QUANTITY:"+Cloths);
                        mUsernamee.add("AMOUNT(RS):"+Money);
                        mUsernamee.add(" ");

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
                Toast.makeText(OrganisationDonation.this,"Retrived",Toast.LENGTH_SHORT).show();;
            }
        });



    }
}
