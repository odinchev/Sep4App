package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.login;
import com.example.examples.sep4app.profile.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_Developer_profile_2 extends AppCompatActivity {

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

    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item))
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
                        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
