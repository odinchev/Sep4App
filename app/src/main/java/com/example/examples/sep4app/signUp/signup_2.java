package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.examples.sep4app.R;

/**
 * Created by PC on 1.12.2017 г..
 */

public class signup_2 extends AppCompatActivity
{

    EditText editTextCertifications;//2
    EditText editTextYearsOfExperience;//2
    EditText editTextDescription;//2
    EditText editTextPrefferredIDE;//2
    public String name;
    public String LastName;
    public String email;
    public String password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        Intent intent=getIntent();
         name=intent.getExtras().getString("name");
         LastName=intent.getExtras().getString("LastName");
         email= intent.getExtras().getString("email");
         password= intent.getExtras().getString("password");
         editTextCertifications=(EditText)findViewById(R.id.editTextCertifications);
         editTextYearsOfExperience=(EditText)findViewById(R.id.editTextYearsOfExperience);
         editTextDescription=(EditText)findViewById(R.id.editTextDescription);
         editTextPrefferredIDE=(EditText)findViewById(R.id.editTextPreferredIDE);
         Button next=(Button)findViewById(R.id.next);




    }
    public void NextScreen(View v)
    {
        String certifications=editTextCertifications.getText().toString().trim();
        String yearsOfExperience=editTextYearsOfExperience.getText().toString().trim();
        String description=editTextDescription.getText().toString().trim();
        String preferredIDE=editTextPrefferredIDE.getText().toString().trim();

        Intent intent =new Intent(this,signup_3.class);
        intent.putExtra("name",name);
        intent.putExtra("LastName",LastName);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        intent.putExtra("certifications",certifications);
        intent.putExtra("yearsOfExperience",yearsOfExperience);
        intent.putExtra("description",description);
        intent.putExtra("preferredide",preferredIDE);
        startActivity(intent);

    }
}
