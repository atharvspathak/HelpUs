package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.RandomAccess;

public class feedback extends AppCompatActivity {
    private RatingBar Ngo;
    private Button submit;
    FirebaseAuth firebaseAuth;
    float a;
    String a1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        Ngo=(RatingBar)findViewById(R.id.ratingBar2);
        submit=(Button)findViewById(R.id.button);

        firebaseAuth=FirebaseAuth.getInstance();






        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            sendFed();
                Toast.makeText(feedback.this,"Feedback Send Successfully",Toast.LENGTH_SHORT).show();;



            }
        });
    }

    public void sendFed()
    {
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference=firebaseDatabase.getReference().child("Feedback").child(firebaseAuth.getUid());
        SendFeedback sf=new SendFeedback();
        a=Ngo.getRating();
        a1=Float.toString(a);
        sf.setFeedback(a1);

        databaseReference.setValue(sf);



    }
}

class SendFeedback
{
    String feedback;
    public SendFeedback() {
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}