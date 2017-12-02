package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.examples.sep4app.R;
import com.example.examples.sep4app.login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup_3 extends AppCompatActivity {


    EditText editTextSkills;//3
    public String name;
    public String LastName;
    public String email;
    public String password;
    public String certifications;
    public String yearsOfExperience;
    public String description;
    public String preferredIDE;
    ProgressBar progress;

    // have multiple strings for the name and the other stuff pass them with intents
    private FirebaseAuth mAuth;
            DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3);
        database= FirebaseDatabase.getInstance().getReference();
        Intent intent=getIntent();
        name=intent.getExtras().getString("name");
        LastName=intent.getExtras().getString("LastName");
        email= intent.getExtras().getString("email");
        password= intent.getExtras().getString("password");
        certifications=intent.getExtras().getString("certifications");
        yearsOfExperience=intent.getExtras().getString("yearsOfExperience");
        description=intent.getExtras().getString("description");
        preferredIDE=intent.getExtras().getString("preferredIDE");


        editTextSkills=(EditText)findViewById(R.id.editTextSkills);

        Button signup= (Button) findViewById(R.id.Signup);

        progress=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

    }
    public void registerUser(View v)
    {

        progress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progress.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"User registered successfull", Toast.LENGTH_LONG).show();
                    // make the user in the database
                    String id=database.push().getKey();

                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
    public void LogIn(View v)
    {
        startActivity(new Intent(this,login.class));
    }
    // Using two different layouts


}
