package com.example.examples.sep4app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button signup= (Button) findViewById(R.id.signupbutton);
    }
    public void SignUp(View v)
    {
       startActivity(new Intent(this,signup.class));

    }
}
