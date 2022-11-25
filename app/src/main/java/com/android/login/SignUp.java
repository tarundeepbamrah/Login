package com.android.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    //Database
    FirebaseDatabase dbref = FirebaseDatabase.getInstance("https://login-154fc-default-rtdb.firebaseio.com/");

    EditText mail,mob,password,repass;
    Button createacc;
    TextView login;
    //authentication
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        login=findViewById(R.id.log);
        //Firebase
        mAuth=FirebaseAuth.getInstance();



        mail=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        repass=findViewById(R.id.cpass);
        mob=findViewById(R.id.mobile);
        createacc= findViewById(R.id.create);

        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mail.getText().toString();
                String pass = password.getText().toString();
                String confirmpass= repass.getText().toString();
                String mobile = mob.getText().toString();
                //mAuth = FirebaseAuth.getInstance();

                if(pass.equals(confirmpass)){
                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            UserDetails user = new UserDetails(email, mobile, pass);

                            String id = task.getResult().getUser().getUid();
                            dbref.getReference("Users").child(id).setValue(user);
                            Toast.makeText(SignUp.this, "Account Created", Toast.LENGTH_SHORT).show();
                            Intent i= new Intent(SignUp.this, SignedUp.class);
                            startActivity(i);


                        } else {
                            Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }

               /* dbref.getReference("users").child("user 3").child("email").setValue(email);
                dbref.getReference("users").child("user 3").child("password").setValue(pass);
                dbref.getReference("users").child("user 3").child("mobile").setValue(mobile);*/

                else{
                    Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(SignUp.this, Login.class);
                startActivity(i);

            }
        });
    }
}