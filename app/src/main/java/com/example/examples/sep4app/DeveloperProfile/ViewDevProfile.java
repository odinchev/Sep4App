package com.example.examples.sep4app.DeveloperProfile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    Button contactBtn;
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
        String picture;



        Intent in = getIntent();
        Bundle b = in.getExtras();
        if( b != null){
            name.setText(b.getString("mName"));
            certifications.setText(b.getString("mCertifications"));
            yearsofExperience.setText(b.getString("mYearsOfExperience"));
            Description.setText(b.getString("mDescription"));
            Skills.setText(b.getStringArrayList("mSkills").toString());
            preferredIDE.setText(b.getString("mPreferredIDE"));
            picture=(b.getString("mPic"));

            Glide.with(getApplicationContext())
                    .load(picture)
                    .into(profilePicture);

            final String email = b.getString("mEmail");


            contactBtn = findViewById(R.id.btn_contactDeveloper);
            contactBtn.setVisibility(View.VISIBLE);
            contactBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inEmail = new Intent(Intent.ACTION_SEND);
                    inEmail.setType("message/rfc822");
                    inEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    inEmail.putExtra(Intent.EXTRA_SUBJECT, "subject");
                    inEmail.putExtra(Intent.EXTRA_TEXT, "Project managers email: " + email);
                    startActivity(Intent.createChooser(inEmail, "Choose an Email client :"));

                }
            });

            //TODO set Profile pic
        }



    }

    /*@Override
    protected void onStop() {
        super.onStop();
        finish();
    }*/

}
