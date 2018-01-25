package com.example.examples.sep4app;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.examples.sep4app.profile.Profile;
import com.example.examples.sep4app.signUp.Signup_1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity
    /* This is the login class it logs the user into the app and also has an intent to go to the signup activity*/
{
    // Authentication object
    FirebaseAuth mAuth;
    EditText Email;
    EditText Password;
    ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email=(EditText) findViewById(R.id.editTextEmail);
        Password=(EditText)findViewById(R.id.editTextPassWordLogin);
        // here we get the instance of firebase auth for users
        mAuth=FirebaseAuth.getInstance();
        progress=(ProgressBar)findViewById(R.id.progressBar2);
        Button signup= (Button) findViewById(R.id.signupbutton);
        Button Login=(Button)findViewById(R.id.loginbutton1);
    }
    // here we get the email for the user from the edit texts if they are empty we request focus on them
    public void Login1(View v)
    {
        String email= Email.getText().toString().trim();
        String  password=Password.getText().toString().trim();
        if(email.isEmpty())
        {
            Email.setError("Email is required");
            Email.requestFocus();
            return;
        }
        // if the email is not a valid email
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Email.setError("Please enter a valid email");
            Email.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            Password.setError("Password is required");
            Password.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            Password.setError("Minimum length of password should be 6");
            Password.requestFocus();
            return;
        }
        // we set the spinner to be visible
        progress.setVisibility(View.VISIBLE);
        // here we sign in the user with the email and password
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                // if the task is succesull we create a new intent that leads to the profile
                // and we clear the top of the app so we cannot navigate to the login screens
                if(task.isSuccessful())
                {
                    Intent intent=new Intent(Login.this,Profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    // if the activity failed then we get wahtever exception occurred and we display it.
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void SignUp(View v)
    {
        // this is the intent to the signup class
       startActivity(new Intent(this,Signup_1.class));

    }
}
