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

import com.example.examples.sep4app.profile.profile;
import com.example.examples.sep4app.signUp.signup_1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity
{
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
        mAuth=FirebaseAuth.getInstance();
        progress=(ProgressBar)findViewById(R.id.progressBar2);
        Button signup= (Button) findViewById(R.id.signupbutton);
        Button Login=(Button)findViewById(R.id.loginbutton1);
    }
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
        progress.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Intent intent=new Intent(login.this,profile.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void SignUp(View v)
    {

       startActivity(new Intent(this,signup_1.class));

    }
}
