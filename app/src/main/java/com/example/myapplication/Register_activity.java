package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class Register_activity extends AppCompatActivity {
    private EditText full_name_text;
    private EditText email_addreass_text;
    private EditText user_name_text;
    private EditText password_text;
    private EditText confirm_password_text;
    private EditText mobile_number_text;
    private FirebaseAuth firebaseAuth;

    private Button register;

    private Button login_again;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activity);

        full_name_text = (EditText) findViewById(R.id.fullname_text);
        email_addreass_text=(EditText) findViewById(R.id.email_text);

        password_text=(EditText) findViewById(R.id.password_text_register);
        confirm_password_text=(EditText) findViewById(R.id.confirm_password);
        mobile_number_text=(EditText) findViewById(R.id.mobile_number);

        register=(Button) findViewById(R.id.register_button_register);

        firebaseAuth=FirebaseAuth.getInstance();






        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                    String Email = email_addreass_text.getText().toString().trim();
                    String Password = password_text.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Register_activity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();

                                startActivity(new Intent(Register_activity.this, MainActivity.class));

                            } else {
                                Toast.makeText(Register_activity.this, "Error", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                } else {
                    Toast.makeText(Register_activity.this, "Please Fill All Information", Toast.LENGTH_SHORT).show();

                }
            }

    });
    }

     boolean validate() {
        String fnt=full_name_text.getText().toString();
        String email=email_addreass_text.getText().toString().trim();
        String pass=password_text.getText().toString().trim();
        String cpass=confirm_password_text.getText().toString().trim();
        String mobile=mobile_number_text.getText().toString().trim();

        if(fnt.isEmpty() || email.isEmpty() || pass.isEmpty() || cpass.isEmpty() || mobile.isEmpty())
        {
            Toast.makeText(this, "Please Filed All Details", Toast.LENGTH_SHORT).show();
            return false;

        }
        else {

            if(pass.equals(cpass))
            {
              return true;
            }
            return  false;

        }
    }







}

