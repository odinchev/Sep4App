package com.example.examples.sep4app.profile;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.examples.sep4app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// for viewing the profile both for the user and the other users
public class profile extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
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

    profilePicture=(ImageView)findViewById(R.id.imageView);
    name=(TextView)findViewById(R.id.textViewName);
    certifications=(TextView)findViewById(R.id.textViewCertifications);
    yearsofExperience=(TextView)findViewById(R.id.textViewYearsofExperience);
    Description=(TextView)findViewById(R.id.textViewDescription);
    preferredIDE=(TextView)findViewById(R.id.textViewPreferredIDE);
    database= FirebaseDatabase.getInstance().getReference("Users");


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item))
        {return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                         User user=dataSnapshot.getValue(User.class);
                         name.setText(user.getName() +" "+ user.getLastName());
                         Description.setText(user.getDescription());
                         // here is where we use glide to take the picture url and put it into the ImageView
                        Glide.with(getApplicationContext())
                            .load(user.getPicture())
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
