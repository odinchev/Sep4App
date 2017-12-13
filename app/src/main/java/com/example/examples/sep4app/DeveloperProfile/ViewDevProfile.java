package com.example.examples.sep4app.DeveloperProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examples.sep4app.R;

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

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if( b != null){
            name.setText(b.getString("mName"));
            certifications.setText(b.getString("mCertifications"));
            Description.setText(b.getString("mDescription"));
            yearsofExperience.setText(b.getString("mYearsOfExperience"));
            Skills.setText(b.getStringArrayList("mSkills").toString());
            preferredIDE.setText(b.getString("mPreferredIDE"));
            //TODO set Profile pic
        }

    }

    /*@Override
    protected void onStop() {
        super.onStop();
        finish();
    }*/
}