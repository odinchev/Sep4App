package com.example.examples.sep4app.DeveloperProfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examples.sep4app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// for viewing the Profile both for the user and the other users
public class DevProfile extends AppCompatActivity {

    // this is the class that we use to see the developer profile
    // name certifications years of experience description preferred ide
    ImageView profilePicture;
    TextView name;
    TextView certifications;
   TextView yearsofExperience;
    TextView Description;
    TextView Skills;
    TextView preferredIDE;
    public Developer developer;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_profile);
       // Toolbar mytoolbar=(Toolbar)findViewById(R.id.ToolBar);
      //  setSupportActionBar(mytoolbar);
    profilePicture=(ImageView)findViewById(R.id.imageView);
    name=(TextView)findViewById(R.id.textViewName);
    certifications=(TextView)findViewById(R.id.textViewCertifications);
    yearsofExperience=(TextView)findViewById(R.id.textViewYearsofExperience);
    Description=(TextView)findViewById(R.id.textViewDescription);
    Skills=(TextView)findViewById(R.id.textViewTags);
    preferredIDE=(TextView)findViewById(R.id.textViewPreferredIDE);
    database= FirebaseDatabase.getInstance().getReference();

       // ActionBar ab=getSupportActionBar();
      //  ab.setDisplayShowTitleEnabled(false);
       // ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        // we get the clild node developers  and get the current user
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Developers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    //this is the developer object and the data in it is from the database
                    /*Developer*/ developer=dataSnapshot.getValue(Developer.class);

                        name.setText(developer.getName() +" "+ developer.getLastName());
                        certifications.setText(developer.getCertifications());
                       yearsofExperience.setText(developer.getYearsofExperience());
                       Skills.setText(developer.getSkills().toString());
                       Description.setText(developer.getDescription());
                       preferredIDE.setText(developer.getide());

                       // here we use an api to get the picture from the developer
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
