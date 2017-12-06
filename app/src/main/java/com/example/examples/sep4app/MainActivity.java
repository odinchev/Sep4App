package com.example.examples.sep4app;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examples.sep4app.DeveloperProfile.editProfileDeveloper;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.profile;
import com.example.examples.sep4app.signUp.create_Developer_profile_1;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private TextView hello, what2do;
    private ImageView profilePic;
    private Button findDev, findProject, createDev, createProject;
    private Button editProfile;
    private Button editDeveloperProfile;
    private Button goToProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        //hello should take firstName from DB and create string "Hello " + firstName + "!"
        hello = (TextView) findViewById(R.id.main_textHello);

        what2do = (TextView) findViewById(R.id.main_what2do);
        profilePic = (ImageView) findViewById(R.id.main_profilePic);

        findProject = (Button)findViewById(R.id.btn_findProjects);
        findProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go2ProjectSearch();
            }
        });
        
        findDev = (Button)findViewById(R.id.btn_findDevs);
        findDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go2DevSearch();
            }
        });
        
        createDev = (Button)findViewById(R.id.btn_createDevProfile);
        /**
        createDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go2CreateDev();
            }
        });
        */
        createProject = (Button)findViewById(R.id.btn_createProject);
        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go2CreateProject();
            }
        });
        editProfile=(Button)findViewById(R.id.buttonEditProfile);
        editDeveloperProfile=(Button)findViewById(R.id.buttonEditDeveloperProfile);
        goToProfile=(Button)findViewById(R.id.buttonProfile);



    }
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item))
        {return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void go2ProjectSearch() {
    }

    private void go2DevSearch() {

        Intent intent = new Intent(MainActivity.this, findDevs.class);
        startActivity(intent);
    }

    private void go2CreateDev() {
    }

    private void go2CreateProject() {
    }
    public void CreateDeveloper(View v)
    {
        Intent intent =new Intent(MainActivity.this,create_Developer_profile_1.class);
        startActivity(intent);
    }
    public void EditProfile(View v)
    {
        Intent intent=new Intent(MainActivity.this,EditProfile.class);
        startActivity(intent);
    }
    public void EditDeveloperProfile(View v)
    {
        Intent intent=new Intent(MainActivity.this,editProfileDeveloper.class);
        startActivity(intent);

    }
    public void goToProfile(View v)
    {
        Intent intent=new Intent(MainActivity.this,profile.class);
        startActivity(intent);
    }
}
