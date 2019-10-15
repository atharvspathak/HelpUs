package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Constants;

public class UserDonation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button Submit;
    private EditText quan,cloths,amount;
    private Spinner spinner;
    private FirebaseAuth firebaseAuth;
    String Quan,Cloths,Amount;
    private ProgressBar progressBar;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_donation);


        spinner = findViewById(R.id.spinner);
        Submit=(Button)findViewById(R.id.submit);
        quan=(EditText)findViewById(R.id.quantity);
        cloths=(EditText)findViewById(R.id.noofclothes);
        amount=(EditText)findViewById(R.id.amount);
        progressBar=(ProgressBar)findViewById(R.id.progressBar5);
        progressBar.setVisibility(View.INVISIBLE);
        firebaseAuth=FirebaseAuth.getInstance();




        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.Type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                SendDonation();
                Submit.setEnabled(false);
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(UserDonation.this,"Donation Sucessfull",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void SendDonation(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();                           //Create db instane
        final DatabaseReference databaseReference=firebaseDatabase.getReference().child("Donation").child(firebaseAuth.getUid()); //For donation     //Create refrence and add data user-uid
        SendDataD don=new SendDataD();            //final because need to be use in inner class ie addListenrt

        Quan=quan.getText().toString();
        Cloths=cloths.getText().toString();
        Amount=amount.getText().toString();


        don.setQuantity(Quan);
        don.setCloths(Cloths);
        don.setAmount(Amount);

        databaseReference.setValue(don);

    }

}
class SendDataD{
    String quantity,cloths,amount;

    public SendDataD() {
    }


    public String getQuantity() {

        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCloths() {
        return cloths;
    }

    public void setCloths(String cloths) {
        this.cloths = cloths;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


}