package com.example.examples.sep4app.signUp;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.examples.sep4app.DeveloperProfile.Developerprofile;
import com.example.examples.sep4app.DeveloperProfile.developer;
import com.example.examples.sep4app.DeveloperProfile.editProfileDeveloper;
import com.example.examples.sep4app.MainActivity;
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.findDevs;
import com.example.examples.sep4app.login;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.User;
import com.example.examples.sep4app.profile.profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;

public class create_Developer_profile_2 extends AppCompatActivity {
 private   NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
// this is the screen for developers  not a sign up but editing stuff that means no register user with mauth but push dev to database with getkey we need a map
    EditText editTextSkills;//3
    public String name;
    public String LastName;
    public String email;
    public String password;
    public String certifications;
    public String yearsOfExperience;
    public String description;
    public String preferredIDE;
    public String Picture;
    ProgressBar progress;

    // have multiple strings for the name and the other stuff pass them with intents
    private FirebaseAuth mAuth;
            DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_developer_profile2);

        navigation = (NavigationView) findViewById(R.id.navigation_view);

        //for the Drawer side menu
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for the drawer side menu ^^^^


        database= FirebaseDatabase.getInstance().getReference("Developers");

        Intent intent=getIntent();
        name=intent.getExtras().getString("name");
        LastName=intent.getExtras().getString("LastName");
        email= intent.getExtras().getString("email");
        password= intent.getExtras().getString("password");
        certifications=intent.getExtras().getString("certifications");
        yearsOfExperience=intent.getExtras().getString("yearsOfExperience");
        description=intent.getExtras().getString("description");
        preferredIDE=intent.getExtras().getString("preferredide");
        Picture=intent.getExtras().getString("Picture");


        editTextSkills=(EditText)findViewById(R.id.editTextSkills);

        Button signup= (Button) findViewById(R.id.Signup);

        progress=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        initInstances();

    }





    private void initInstances() {

//navbar


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_Main:
                        Intent i = new Intent(create_Developer_profile_2.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_Profile:
                        Intent j = new Intent(create_Developer_profile_2.this,profile.class);
                       startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(create_Developer_profile_2.this,EditProfile.class);
                        startActivity(k);
                        break;


                    case R.id.nav_Create_Developer_Profile:
                        Intent l = new Intent(create_Developer_profile_2.this,create_Developer_profile_1.class);
                        startActivity(l);
                        break;


                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(create_Developer_profile_2.this,editProfileDeveloper.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(create_Developer_profile_2.this,Developerprofile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(create_Developer_profile_2.this,findDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        // Intent p = new Intent(create_Developer_profile_2.this,FindProjects.class);
                       // startActivity(p);
                        Context context = getApplicationContext();
                            CharSequence text = "EMPTINESS!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();



                        break;



                }
                return false;
            }
        });

    }





















    public boolean onOptionsItemSelected(MenuItem item){




       if(mToggle.onOptionsItemSelected(item ))



        {return true;
        }
        return super.onOptionsItemSelected(item);

    //for selecting stuff in the drawer menu
    }
    public void registerUser(View v)
    {

        progress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progress.setVisibility(View.GONE);
                if(task.isSuccessful())
                {

                    // make the user in the database
                    String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    User user=new User(id,name,LastName,certifications,yearsOfExperience,description,preferredIDE);
                    database.child(id).setValue(user);
                    Toast.makeText(getApplicationContext(),"User registered successfull", Toast.LENGTH_LONG).show();

                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "User already exists", LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public void RegisterDeveloper(View v)
    {

        String skills=editTextSkills.getText().toString().trim();

        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        developer developer =new developer(id,name,LastName,certifications,yearsOfExperience,description,skills,preferredIDE,Picture);
        database.child(id).setValue(developer);
        Intent intent =new Intent(create_Developer_profile_2.this, Developerprofile.class);
        startActivity(intent);
    }
    public void LogIn(View v)
    {
        startActivity(new Intent(this,login.class));
    }
    // Using two different layouts


}
