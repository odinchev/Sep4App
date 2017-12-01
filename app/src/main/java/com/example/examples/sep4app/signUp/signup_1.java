package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.examples.sep4app.R;

/**
 * Created by PC on 1.12.2017 г..
 */

public class signup_1 extends AppCompatActivity
{
    EditText passWord;
    EditText editTextEmail;//1
    EditText editTextName;//1
    EditText editTextLastName;//1
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextLastName=(EditText)findViewById(R.id.editTextLastName);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail);
        passWord=(EditText)findViewById(R.id.editTextPassWord);
        Button next=(Button)findViewById(R.id.next);
    }
    public void NextScreen(View v)
    {

        String name=editTextName.getText().toString().trim();
        String  LastName=editTextLastName.getText().toString().trim();
        String email= editTextEmail.getText().toString().trim();
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
        Intent intent =new Intent(this,signup_2.class);
        intent.putExtra("name",name);
        intent.putExtra("LastName",LastName);
        intent.putExtra("email",email);
        intent.putExtra("password",password);

        startActivity(intent);

    }

}
