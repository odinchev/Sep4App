package com.example.examples.sep4app;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examples.sep4app.DeveloperProfile.DevProfile;
import com.example.examples.sep4app.DeveloperProfile.EditDevProfile;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.Profile;
import com.example.examples.sep4app.signUp.CreateDevProfile_1;
import com.example.examples.sep4app.signUp.CreateProject_1;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigation;
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
        navigation = (NavigationView) findViewById(R.id.navigation_view);
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
                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_Profile:
                        Intent j = new Intent(MainActivity.this,Profile.class);
                        startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(MainActivity.this,EditProfile.class);
                        startActivity(k);
                        break;


                    case R.id.nav_Create_Developer_Profile:
                        Intent l = new Intent(MainActivity.this,CreateDevProfile_1.class);
                        startActivity(l);
                        break;


                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(MainActivity.this,EditDevProfile.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(MainActivity.this,DevProfile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(MainActivity.this,FindDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        Intent p = new Intent(MainActivity.this,FindProjects.class);
                         startActivity(p);
                        //Context context = getApplicationContext();



                        break;


                    case R.id.nav_CreateProject:

                        Intent q = new Intent(MainActivity.this,CreateProject_1.class);
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

    private void go2ProjectSearch() {
        Intent intent =new Intent(MainActivity.this,FindProjects.class);
        startActivity(intent);
    }

    private void go2DevSearch() {

        Intent intent = new Intent(MainActivity.this, FindDevs.class);
        startActivity(intent);
    }

    private void go2CreateDev() {
        Intent intent =new Intent(MainActivity.this,CreateDevProfile_1.class);
        startActivity(intent);
    }

    private void go2CreateProject() {
        Intent intent =new Intent(MainActivity.this,CreateProject_1.class);
        startActivity(intent);
    }
    public void CreateDeveloper(View v)
    {
        Intent intent =new Intent(MainActivity.this,CreateDevProfile_1.class);
        startActivity(intent);
    }
    public void EditProfile(View v)
    {
        Intent intent=new Intent(MainActivity.this,EditProfile.class);
        startActivity(intent);
    }
    public void EditDeveloperProfile(View v)
    {
        Intent intent=new Intent(MainActivity.this,EditDevProfile.class);
        startActivity(intent);

    }
    public void goToProfile(View v)
    {
        Intent intent=new Intent(MainActivity.this,Profile.class);
        startActivity(intent);
    }
}
