package com.android.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;

public class Login extends AppCompatActivity {

    EditText mail,password;
    Button login;
    TextView signup;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        signup=findViewById(R.id.signup);
        mAuth=FirebaseAuth.getInstance();



        mail=findViewById(R.id.mail);
        password=findViewById(R.id.password);
        login= findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=mail.getText().toString();
                String pass=password.getText().toString();

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Logged in", Toast.LENGTH_SHORT).show();
                                    Intent i= new Intent(Login.this, LoggedIn.class);
                                    startActivity(i);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this,task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();

                                }

                            }
                        });

                /*dbref.getReference("users").child("user 1").child("email").setValue(email);
                dbref.getReference("users").child("user 1").child("password").setValue(pass);*/



            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });

    }
}