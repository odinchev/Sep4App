package com.example.examples.sep4app.DeveloperProfile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.examples.sep4app.FindDevs;
import com.example.examples.sep4app.FindProjects;
import com.example.examples.sep4app.MainActivity;
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.Profile;
import com.example.examples.sep4app.signUp.CreateDevProfile_1;
import com.example.examples.sep4app.signUp.CreateProject_1;
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

    private NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_profile);

    profilePicture=(ImageView)findViewById(R.id.imageView);
    name=(TextView)findViewById(R.id.textViewName);
    certifications=(TextView)findViewById(R.id.textViewCertifications);
    yearsofExperience=(TextView)findViewById(R.id.textViewYearsofExperience);
    Description=(TextView)findViewById(R.id.textViewDescription);
    Skills=(TextView)findViewById(R.id.textViewTags);
    preferredIDE=(TextView)findViewById(R.id.textViewPreferredIDE);
    database= FirebaseDatabase.getInstance().getReference();
    mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
    mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
    navigation = (NavigationView) findViewById(R.id.navigation_view);
    mDrawerLayout.addDrawerListener(mToggle);
    mToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //for the drawer side menu ^
    initInstances();

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


    private void initInstances() {

//navbar


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Menu menu =navigation.getMenu();
                switch (id) {
                    case R.id.nav_Main:
                        Intent i = new Intent(DevProfile.this, MainActivity.class);
                        startActivity(i);
                        break;

                    case R.id.nav_ExpandProfile:
                        boolean b=!menu.findItem(R.id.nav_Profile).isVisible();
                        menu.findItem(R.id.nav_Profile).setVisible(b);
                        menu.findItem(R.id.nav_EditProfile).setVisible(b);
                        break;

                    case R.id.nav_Profile:
                        Intent j = new Intent(DevProfile.this, Profile.class);
                        startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(DevProfile.this, EditProfile.class);
                        startActivity(k);
                        break;



                    case R.id.nav_ExpandDeveloper:
                        boolean booleanDevelopers=!menu.findItem(R.id.nav_View_Developer_Profile).isVisible();
                        menu.findItem(R.id.nav_Create_Developer_Profile).setVisible(booleanDevelopers);
                        menu.findItem(R.id.nav_Edit_Developer_Profile).setVisible(booleanDevelopers);
                        menu.findItem(R.id.nav_View_Developer_Profile).setVisible(booleanDevelopers);
                        break;

                    case R.id.nav_Create_Developer_Profile:
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                        if( mDatabase.child("Developers").child(FirebaseAuth.getInstance().getCurrentUser().getUid())!=null)
                        {
                            Toast.makeText(getApplicationContext(),"You already have a developer profile",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent l = new Intent(DevProfile.this, CreateDevProfile_1.class);
                            startActivity(l);
                        }
                        break;

                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(DevProfile.this, EditDevProfile.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(DevProfile.this, DevProfile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(DevProfile.this, FindDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        Intent p = new Intent(DevProfile.this, FindProjects.class);
                        startActivity(p);
                        Context context = getApplicationContext();


                        break;

                    case R.id.nav_ExpandProjects:
                        boolean booleanProject =!menu.findItem(R.id.nav_CreateProject).isVisible();
                        menu.findItem(R.id.nav_CreateProject).setVisible(booleanProject);
                        break;

                    case R.id.nav_CreateProject:

                        Intent q = new Intent(DevProfile.this,CreateProject_1.class);
                        startActivity(q);


                        break;


                }
                return false;
            }
        });

    }



    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item))
        {return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
