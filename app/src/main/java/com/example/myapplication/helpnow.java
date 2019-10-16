package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class helpnow extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText person_name;
    private EditText person_age;
    private EditText person_problem;
    private EditText location;
    private EditText NumberOf_persons;

    private Button get_location;
    private Button submit;
    private Button reset;

    private String p_name,p_age,p_problem,no_of_persons,p_address,latitude,longitude;
    String person_type="";
    String[] needy_type = { "Person Type","Child","Young","Old"};
    String gender="";
    String[] genderType={"*Select","Male","Female"};
    private static double lat;
    private static double log;

    private FirebaseAuth firebaseAuth;



    // private static String address1;
    private static String address;


    private ProgressBar pgb;


    //for spinner


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpnow);

        person_name=(EditText) findViewById(R.id.editText);
        person_age=(EditText) findViewById(R.id.editText2);
        person_problem=(EditText) findViewById(R.id.editText3);
        location=(EditText) findViewById(R.id.editText4);
        NumberOf_persons=(EditText) findViewById(R.id.editText5);

        pgb=(ProgressBar)findViewById(R.id.progressBar7) ;
        pgb.setVisibility(View.INVISIBLE);

        firebaseAuth= FirebaseAuth.getInstance();


        reset=(Button)findViewById(R.id.button3_reset) ;
        submit=(Button)findViewById(R.id.button_submit);
        get_location=(Button)findViewById(R.id.button2);

        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgb.setVisibility(View.VISIBLE);

                startActivity(new Intent(helpnow.this,get_location.class));
                location.setText(address);
                pgb.setVisibility(View.INVISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgb.setVisibility(View.VISIBLE);
                sendPersonInfo();
                submit.setEnabled(false);
                pgb.setVisibility(View.INVISIBLE);
                Toast.makeText(helpnow.this,"Information Uploaded Sucessfully",Toast.LENGTH_SHORT).show();
            }
        });


        //For Spinner category
        //Getting the instance of Spinner and applying OnItemSelectedListener on it
        Spinner spin = (Spinner) findViewById(R.id.spinner_catg);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the person_type list
        // ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(getActivity(), R.layout.spinner_text, years );

        ArrayAdapter<CharSequence> aa = new ArrayAdapter<CharSequence>(helpnow.this, R.layout.spinner_text, needy_type );
        aa.setDropDownViewResource(R.layout.spinner_dropdown);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        Spinner spin2=(Spinner) findViewById(R.id.spinner_gender);
        spin2.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> bb= new ArrayAdapter<CharSequence>(helpnow.this,R.layout.spinner_text,genderType);
        bb.setDropDownViewResource(R.layout.spinner_dropdown);
        spin2.setAdapter(bb);




    }
    public static void setLatLog(double lat1,double log1){

        lat=lat1;
        log=log1;


    }
    public static void setaddress(String address1){
        address=address1;
    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner_catg:
                if(needy_type[position].equals("Person Type")){
                    Toast.makeText(getApplicationContext(),"Please select Person Type" , Toast.LENGTH_SHORT).show();
                }
                else{
                    person_type=needy_type[position];
                    Toast.makeText(helpnow.this,person_type , Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.spinner_gender:
                if(genderType[position].equals("*Select")){
                    Toast.makeText(getApplicationContext(),"Please select Gender Type" , Toast.LENGTH_SHORT).show();
                }
                else{
                    gender=genderType[position];
                    Toast.makeText(helpnow.this,gender , Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


    public void sendPersonInfo(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();                           //Create db instane
        final DatabaseReference databaseReference=firebaseDatabase.getReference().child("HelpInfo").child(firebaseAuth.getUid()); //For donation     //Create refrence and add data user-uid

        person_type=person_type;
        p_name=person_name.getText().toString();
        p_age=person_age.getText().toString();
        gender=gender;
        p_problem=person_problem.getText().toString();
        no_of_persons=NumberOf_persons.getText().toString();
        p_address=location.getText().toString();



        SendInfo si=new SendInfo(person_type,p_name,p_age,gender,p_problem,no_of_persons,p_address,lat,log);


        databaseReference.setValue(si);

    }




}

class SendInfo {

    String personType,personName,personAge,gender,personProblem,numberofpersons,location;
    String latitude,longitude;

    SendInfo(){

    }
    SendInfo(String personType,String personName,String personAge,String gender,String personProblem,String numberofpersons, String location,double latitude,double longitude){
        this.personType=personType;
        this.personName=personName;
        this.personAge=personAge;
        this.gender=gender;
        this.personProblem=personProblem;
        this.numberofpersons=numberofpersons;
        this.location=location;
        this.latitude=Double.toString(latitude);
        this.longitude=Double.toString(longitude);
    }

    public String getPersonType() {
        return personType;
    }



    public String getPersonName() {
        return personName;
    }



    public String getPersonAge() {
        return personAge;
    }



    public String getGender() {
        return gender;
    }


    public String getPersonProblem() {
        return personProblem;
    }



    public String getNumberofpersons() {
        return numberofpersons;
    }



    public String getLocation() {
        return location;
    }



    public String getLatitude() {
        return latitude;
    }


    public String getLongitude() {
        return longitude;
    }


}