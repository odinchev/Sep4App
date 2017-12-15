package com.example.examples.sep4app.DeveloperProfile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.examples.sep4app.FindDevs;
import com.example.examples.sep4app.FindProjects;
import com.example.examples.sep4app.MainActivity;
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.Profile;
import com.example.examples.sep4app.profile.User;
import com.example.examples.sep4app.signUp.CreateDevProfile_1;
import com.example.examples.sep4app.signUp.CreateProject_1;
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

    private NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


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


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_Layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        navigation = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for the drawer side menu ^
initInstances();

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            name.setText(b.getString("mName"));
            certifications.setText(b.getString("mCertifications"));
            yearsofExperience.setText(b.getString("mYearsOfExperience"));
            Description.setText(b.getString("mDescription"));
            Skills.setText(b.getStringArrayList("mSkills").toString());
            preferredIDE.setText(b.getString("mPreferredIDE"));
            picture = (b.getString("mPic"));

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

    private void initInstances() {

//navbar


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_Main:
                        Intent i = new Intent(ViewDevProfile.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_Profile:
                        Intent j = new Intent(ViewDevProfile.this, Profile.class);
                        startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(ViewDevProfile.this, EditProfile.class);
                        startActivity(k);
                        break;


                    case R.id.nav_Create_Developer_Profile:
                        Intent l = new Intent(ViewDevProfile.this, CreateDevProfile_1.class);
                        startActivity(l);
                        break;


                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(ViewDevProfile.this, EditDevProfile.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(ViewDevProfile.this, DevProfile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(ViewDevProfile.this, FindDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        Intent p = new Intent(ViewDevProfile.this, FindProjects.class);
                        startActivity(p);
                        Context context = getApplicationContext();


                        break;

                    case R.id.nav_CreateProject:

                        Intent q = new Intent(ViewDevProfile.this,CreateProject_1.class);
                        startActivity(q);


                        break;


                }
                return false;
            }
        });

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

    /*@Override
    protected void onStop() {
        super.onStop();
        finish();
    }*/


