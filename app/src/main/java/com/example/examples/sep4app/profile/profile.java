package com.example.examples.sep4app.profile;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examples.sep4app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

// for viewing the profile both for the user and the other users
public class profile extends AppCompatActivity {

    // name certifications years of experience description preferred ide
    ImageView profilePicture;
    TextView name;
    TextView certifications;
    TextView yearsofExperience;
    TextView Description;
    TextView preferredIDE;
    public User user;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
       // Toolbar mytoolbar=(Toolbar)findViewById(R.id.ToolBar);
      //  setSupportActionBar(mytoolbar);
    profilePicture=(ImageView)findViewById(R.id.imageView);
    name=(TextView)findViewById(R.id.textViewName);
    certifications=(TextView)findViewById(R.id.textViewCertifications);
    yearsofExperience=(TextView)findViewById(R.id.textViewYearsofExperience);
    Description=(TextView)findViewById(R.id.textViewDescription);
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
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.getRef().child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    User user=dataSnapshot.getValue(User.class);

                        name.setText(user.getName());
                       certifications.setText(user.getCertifications());

                       yearsofExperience.setText(user.getYearsofExperience());
                       Description.setText(user.getDescription());
                       preferredIDE.setText(user.getide());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                   Log.d("Error","error");
            }
        });
    }
}
