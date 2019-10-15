package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.RandomAccess;

public class feedback extends AppCompatActivity {
    private RatingBar Ngo;
    private RatingBar us;
    private Button submit;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Ngo=(RatingBar)findViewById(R.id.ratingBar2);
        us=(RatingBar)findViewById(R.id.ratingBar);
        submit=(Button)findViewById(R.id.button);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Feedback").child(firebaseAuth.getUid());






        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }

    public void sendFed()
    {

    }
}
