package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.examples.sep4app.R;
import com.example.examples.sep4app.profile.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by PC on 1.12.2017 г..
 */

public class create_Developer_profile_1 extends AppCompatActivity
{

    EditText editTextCertifications;//2
    EditText editTextYearsOfExperience;//2
    EditText editTextDescription;//2
    EditText editTextPrefferredIDE;//2
    public String name;
    public String LastName;
    public String email;
    public String password;
    private FirebaseAuth mAuth;
    DatabaseReference database;
    DataSnapshot dataSnapshot;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_developer_profile1);

       // database= FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    User user=dataSnapshot.getValue(User.class);
                    name=user.getName();
                    LastName=user.getLastName();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("Error","error");
            }
        });
       // User user=dataSnapshot.getValue(User.class);




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

        Intent intent =new Intent(this,create_Developer_profile_2.class);
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
