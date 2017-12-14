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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.examples.sep4app.DeveloperProfile.Developer;
import com.example.examples.sep4app.DeveloperProfile.DevProfile;
import com.example.examples.sep4app.DeveloperProfile.ViewDevProfile;
import com.example.examples.sep4app.DeveloperProfile.EditDevProfile;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.Profile;
import com.example.examples.sep4app.signUp.CreateDevProfile_1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindDevs extends AppCompatActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    private NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private RecyclerView recyclerView;
    private List<Developer> devList;
    private List<Developer> filteredList;
    private DevAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private List<String> skills;
    private ChildEventListener bob;

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

        adapter = new DevAdapter(devList, this, new DevAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Developer devClicked = devList.get(position);

                Bundle b = new Bundle();
                b.putString("mName", devClicked.name + " " + devClicked.lastName);
                b.putString("mCertifications", devClicked.certifications);
                b.putString("mYearsOfExperience", devClicked.yearsofExperience);
                b.putString("mDescription", devClicked.description);
                b.putStringArrayList("mSkills", (ArrayList)devClicked.skills);
                b.putString("mPreferredIDE", devClicked.preferredIDE);
                b.putString("mEmail", devClicked.getEmail());
                b.putString("mPic",devClicked.getPicture());
                //TODO picture

                Intent intent = new Intent(FindDevs.this, ViewDevProfile.class);

                intent.putExtras(b);

                startActivity(intent);


            }
        });

        recyclerView.setAdapter(adapter);

        //spinner
        skills = new ArrayList<>();
        final String[] tags = {
                "C", "C++", "C#", "Java",
                "JavaScript", "Python", "PHP", "SQL"};
        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.spinner_filter_devs);
        multiSelectionSpinner.setItems(tags);
        multiSelectionSpinner.setSelection(new int[]{2, 6});
        multiSelectionSpinner.setListener(this);

        updateList(devList);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void updateList(final List<Developer> list){

        bob = reference.addChildEventListener(new ChildEventListener()  {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(Developer.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                int index = getItemIndex(dataSnapshot.getValue(Developer.class), list);
                list.set(index, dataSnapshot.getValue(Developer.class));
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


                int index = getItemIndex(dataSnapshot.getValue(Developer.class), list);

                list.remove(index);
                adapter.notifyItemRemoved(index);

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //reference.removeEventListener(bob);
    }

    private int getItemIndex(Developer developer, List<Developer> list){

        int index = savePos;

        for(int i=0; i < list.size(); i++){
            if(list.get(i).id.equals(developer.id)) {
                index = i;
                break;
            }
        }

        return index;

    }

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        skills = strings;
        Toast.makeText(this, skills.toString(), Toast.LENGTH_LONG).show();
        filterBy(skills);
    }
    //filtering done here
    private void filterBy(List<String> skills){


        if(skills.equals(new ArrayList<String>())){
            filteredList = new ArrayList<>();
            filteredList = devList;
            //adapter.setList(filteredList);
            adapter = new DevAdapter(devList, this, new DevAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Developer devClicked = devList.get(position);

                    Bundle b = new Bundle();
                    b.putString("mName", devClicked.name + " " + devClicked.lastName);
                    b.putString("mCertifications", devClicked.certifications);
                    b.putString("mYearsOfExperience", devClicked.yearsofExperience);
                    b.putString("mDescription", devClicked.description);
                    b.putStringArrayList("mSkills", (ArrayList)devClicked.skills);
                    b.putString("mPreferredIDE", devClicked.preferredIDE);
                    b.putString("mEmail", devClicked.getEmail());
                    //TODO picture

                    Intent intent = new Intent(FindDevs.this, ViewDevProfile.class);

                    intent.putExtras(b);

                    startActivity(intent);


                }
            });
            recyclerView.setAdapter(adapter);

        }
        else{
            filteredList = new ArrayList<>();

            for(int k=0; k<skills.size(); k++) {
                for (int i = 0; i < devList.size(); i++) {
                    //if dev in devList has same skill as in skills[k] and filteredList does not have it yet then
                    if (devList.get(i).getSkills().contains(skills.get(k)) && !filteredList.contains(devList.get(i))) {
                        filteredList.add(devList.get(i));
                    }
                }
            }
            adapter = new DevAdapter(filteredList, this, new DevAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Developer devClicked = filteredList.get(position);

                    Bundle b = new Bundle();
                    b.putString("mName", devClicked.name + " " + devClicked.lastName);
                    b.putString("mCertifications", devClicked.certifications);
                    b.putString("mYearsOfExperience", devClicked.yearsofExperience);
                    b.putString("mDescription", devClicked.description);
                    b.putStringArrayList("mSkills", (ArrayList)devClicked.skills);
                    b.putString("mPreferredIDE", devClicked.preferredIDE);
                    b.putString("mEmail", devClicked.getEmail());
                    //TODO picture

                    Intent intent = new Intent(FindDevs.this, ViewDevProfile.class);

                    intent.putExtras(b);

                    startActivity(intent);


                }
            });
            //adapter.setList(filteredList);
            recyclerView.setAdapter(adapter);

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
                        Intent i = new Intent(FindDevs.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_Profile:
                        Intent j = new Intent(FindDevs.this,Profile.class);
                        startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(FindDevs.this,EditProfile.class);
                        startActivity(k);
                        break;


                    case R.id.nav_Create_Developer_Profile:
                        Intent l = new Intent(FindDevs.this,CreateDevProfile_1.class);
                        startActivity(l);
                        break;


                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(FindDevs.this,EditDevProfile.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(FindDevs.this, DevProfile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(FindDevs.this,FindDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        // Intent p = new Intent(CreateDevProfile_2.this,FindProjects.class);
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

    /*private Developer viewOwnDevProfile(){
        Developer me = new Developer();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Developers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Developer me = dataSnapshot.getValue(Developer.class);


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.d("Error","error");
            }
        });
        return me;
    }*/


    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item))
        {return true;
        }
        return super.onOptionsItemSelected(item);
    }





}
