package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.examples.sep4app.R;
import com.example.examples.sep4app.profile.User;
import com.example.examples.sep4app.profile.Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by PC on 1.12.2017 г..
 */

public class signup_1 extends AppCompatActivity
{
    // acctual name and registration page
    EditText passWord;
    EditText editTextEmail;//1
    EditText editTextName;//1
    EditText editTextLastName;//1
    EditText profileDescription;//2
    ProgressBar progress;
    private FirebaseAuth mAuth;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextLastName=(EditText)findViewById(R.id.editTextLastName);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        passWord=(EditText)findViewById(R.id.editTextPassWord);
        profileDescription=(EditText)findViewById(R.id.profileDescription);
        Button next=(Button)findViewById(R.id.next);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        database= FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
    }
    public void Register(View v)
    {

        final String name=editTextName.getText().toString().trim();
        final String  LastName=editTextLastName.getText().toString().trim();
        String email= editTextEmail.getText().toString().trim();
        final String description=profileDescription.getText().toString().trim();
        String  password=passWord.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            passWord.setError("Password is required");
            passWord.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            passWord.setError("Minimum length of password should be 6");
            passWord.requestFocus();
            return;
        }
        if(name.isEmpty())
        {
            editTextName.setError("Please enter a name");
            editTextName.requestFocus();
            return;
        }
        if(LastName.isEmpty())
        {
            editTextLastName.setError("Please enter a last name");
            editTextLastName.requestFocus();
            return;
        }
        progress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progress.setVisibility(View.GONE);
                if(task.isSuccessful())
                {

                    // make the user in the database
                    String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
                    User user=new User(id,name,LastName,description,null);
                    database.child(id).setValue(user);
                    Toast.makeText(getApplicationContext(),"User registered successfull", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(signup_1.this,Profile.class);
                    startActivity(intent);

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
        /**
        Intent intent =new Intent(this,signup_2.class);
        intent.putExtra("name",name);
        intent.putExtra("LastName",LastName);
        intent.putExtra("email",email);
        intent.putExtra("password",password);

        startActivity(intent);
         */

    }

}
