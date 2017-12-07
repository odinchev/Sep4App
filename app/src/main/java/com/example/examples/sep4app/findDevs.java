package com.example.examples.sep4app;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.examples.sep4app.DeveloperProfile.Developerprofile;
import com.example.examples.sep4app.DeveloperProfile.developer;
import com.example.examples.sep4app.DeveloperProfile.editProfileDeveloper;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.profile;
import com.example.examples.sep4app.signUp.create_Developer_profile_1;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class findDevs extends AppCompatActivity {

    private NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private RecyclerView recyclerView;
    private List<developer> devList;
    private DevAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private int savePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_devs);
        //for the Drawer side menu
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        navigation = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for the drawer side menu ^
        initInstances();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Developers");

        devList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.devs_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(lim);

        adapter = new DevAdapter(devList);
        recyclerView.setAdapter(adapter);

        updateList();

    }




    private void initInstances() {

//navbar


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_Main:
                        Intent i = new Intent(findDevs.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_Profile:
                        Intent j = new Intent(findDevs.this,profile.class);
                        startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(findDevs.this,EditProfile.class);
                        startActivity(k);
                        break;


                    case R.id.nav_Create_Developer_Profile:
                        Intent l = new Intent(findDevs.this,create_Developer_profile_1.class);
                        startActivity(l);
                        break;


                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(findDevs.this,editProfileDeveloper.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(findDevs.this,Developerprofile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(findDevs.this,findDevs.class);
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
        if(mToggle.onOptionsItemSelected(item))
        {return true;
        }
        return super.onOptionsItemSelected(item);
    }











    private void updateList(){

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                devList.add(dataSnapshot.getValue(developer.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                int index = getItemIndex(dataSnapshot.getValue(developer.class));
                devList.set(index, dataSnapshot.getValue(developer.class));
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


                int index = getItemIndex(dataSnapshot.getValue(developer.class));

                devList.remove(index);
                adapter.notifyItemRemoved(index);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private int getItemIndex(developer developer){

        int index = savePos;

        for(int i=0; i < devList.size(); i++){
            if(devList.get(i).id.equals(developer.id)) {
                index = i;
                break;
            }
        }

        return index;

    }

}
