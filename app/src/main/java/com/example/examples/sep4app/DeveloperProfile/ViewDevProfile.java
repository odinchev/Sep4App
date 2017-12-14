package com.example.examples.sep4app.DeveloperProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.profile.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by MrWhitemount on 07-Dec-17.
 */

public class ViewDevProfile extends AppCompatActivity {

    ImageView profilePicture;
    TextView name;
    TextView certifications;
    TextView yearsofExperience;
    TextView Description;
    TextView Skills;
    TextView preferredIDE;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_profile);
        profilePicture = (ImageView) findViewById(R.id.imageView);
        name = (TextView) findViewById(R.id.textViewName);
        certifications = (TextView) findViewById(R.id.textViewCertifications);
        yearsofExperience = (TextView) findViewById(R.id.textViewYearsofExperience);
        Description = (TextView) findViewById(R.id.textViewDescription);
        Skills = (TextView) findViewById(R.id.textViewTags);
        preferredIDE = (TextView) findViewById(R.id.textViewPreferredIDE);



    }

    /*@Override
    protected void onStop() {
        super.onStop();
        finish();
    }*/
    @Override
    protected void onStart()
    {
        super.onStart();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Developers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Developer developer=dataSnapshot.getValue(Developer.class);
                    name.setText(developer.getName() +" "+ developer.getLastName());
                    Description.setText(developer.getDescription());
                    certifications .setText(developer.getCertifications());
                    yearsofExperience .setText(developer.getYearsofExperience());
                    Skills .setText(developer.getSkills().toString());
                    preferredIDE .setText(developer.getide());
                    // here is where we use glide to take the picture url and put it into the ImageView
                    Glide.with(getApplicationContext())
                            .load(developer.getPicture())
                            .into(profilePicture);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("Error","error");
            }
        });
    }
}
