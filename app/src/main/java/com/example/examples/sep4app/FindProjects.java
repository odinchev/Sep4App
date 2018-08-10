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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.examples.sep4app.DeveloperProfile.Developer;
import com.example.examples.sep4app.DeveloperProfile.DevProfile;
import com.example.examples.sep4app.DeveloperProfile.ViewDevProfile;
import com.example.examples.sep4app.DeveloperProfile.EditDevProfile;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.Profile;
import com.example.examples.sep4app.project.Project;
import com.example.examples.sep4app.project.ProjectActivity;
import com.example.examples.sep4app.signUp.CreateDevProfile_1;
import com.example.examples.sep4app.signUp.CreateDevProfile_2;
import com.example.examples.sep4app.signUp.CreateProject_1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FindProjects extends AppCompatActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener {

    private NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private RecyclerView recyclerView;
    private List<Project> projectList;
    private List<Project> filteredList;
    private ProjectAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private List<String> skills;
    private ChildEventListener john;

    private int savePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_find_projects);
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
        reference = database.getReference("Projects");

        projectList = new ArrayList<>();

       recyclerView = (RecyclerView) findViewById(R.id.projects_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(lim);

        adapter = new ProjectAdapter(projectList, this, new ProjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Project proClicked = projectList.get(position);

                Bundle b = new Bundle();
                b.putString("mName", proClicked.getName());
                b.putStringArrayList("mReqSkills", (ArrayList<String>)proClicked.getReqSkills());
                b.putString("mReqExp", proClicked.getReqExp());
                b.putString("mOtherReqs", proClicked.getOtherReqs());
                b.putString("mDuration", proClicked.getDuration());
                b.putString("mSummary", proClicked.getSummary());
                b.putString("mEmail", proClicked.getCreatorEmail());
                //TODO picture

                Intent intent = new Intent(FindProjects.this, ProjectActivity.class);

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
        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.spinner_filter_projects);
        multiSelectionSpinner.setItems(tags);
        multiSelectionSpinner.setSelection(new int[]{2, 6});
        multiSelectionSpinner.setListener(this);

        updateList(projectList);

    }


    @Override
    protected void onResume() {
        super.onResume();

        recyclerView.setAdapter(adapter);

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
                        Intent i = new Intent(FindProjects.this, MainActivity.class);
                        startActivity(i);
                        break;

                    case R.id.nav_ExpandProfile:
                        boolean b=!menu.findItem(R.id.nav_Profile).isVisible();
                        menu.findItem(R.id.nav_Profile).setVisible(b);
                        menu.findItem(R.id.nav_EditProfile).setVisible(b);
                        break;

                    case R.id.nav_Profile:
                        Intent j = new Intent(FindProjects.this, Profile.class);
                        startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(FindProjects.this, EditProfile.class);
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
                            Intent l = new Intent(FindProjects.this, CreateDevProfile_1.class);
                            startActivity(l);
                        }
                        break;

                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(FindProjects.this, EditDevProfile.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(FindProjects.this, DevProfile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(FindProjects.this, FindDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        Intent p = new Intent(FindProjects.this, FindProjects.class);
                        startActivity(p);
                        Context context = getApplicationContext();


                        break;

                    case R.id.nav_ExpandProjects:
                        boolean booleanProject =!menu.findItem(R.id.nav_CreateProject).isVisible();
                        menu.findItem(R.id.nav_CreateProject).setVisible(booleanProject);
                        break;

                    case R.id.nav_CreateProject:

                        Intent q = new Intent(FindProjects.this,CreateProject_1.class);
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



    private void updateList(final List<Project> list){

        john = reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(Project.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                int index = getItemIndex(dataSnapshot.getValue(Project.class), list);
                list.set(index, dataSnapshot.getValue(Project.class));
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


                int index = getItemIndex(dataSnapshot.getValue(Project.class), list);

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
    }

    private int getItemIndex(Project project, List<Project> list){

        int index = savePos;

        for(int i=0; i < list.size(); i++){
            if(list.get(i).getId().equals(project.getId())) {
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
            filteredList = projectList;
            adapter = new ProjectAdapter(filteredList, this, new ProjectAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Project proClicked = filteredList.get(position);

                    Bundle b = new Bundle();
                    b.putString("mName", proClicked.getName());
                    b.putStringArrayList("mReqSkills", (ArrayList<String>)proClicked.getReqSkills());
                    b.putString("mReqExp", proClicked.getReqExp());
                    b.putString("mOtherReqs", proClicked.getOtherReqs());
                    b.putString("mDuration", proClicked.getDuration());
                    b.putString("mSummary", proClicked.getSummary());
                    b.putString("mEmail", proClicked.getCreatorEmail());
                    //TODO picture

                    Intent intent = new Intent(FindProjects.this, ProjectActivity.class);

                    intent.putExtras(b);

                    startActivity(intent);
                }
            });

            recyclerView.setAdapter(adapter);

        }
        else{
            filteredList = new ArrayList<>();

            for(int k=0; k<skills.size(); k++) {
                for (int i = 0; i < projectList.size(); i++) {
                    //if dev in devList has same skill as in skills[k] and filteredList does not have it yet then
                    if (projectList.get(i).getReqSkills().contains(skills.get(k)) && !filteredList.contains(projectList.get(i))) {
                        filteredList.add(projectList.get(i));
                    }
                }
            }
            adapter = new ProjectAdapter(filteredList, this, new ProjectAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Project proClicked = filteredList.get(position);

                    Bundle b = new Bundle();
                    b.putString("mName", proClicked.getName());
                    b.putStringArrayList("mReqSkills", (ArrayList<String>)proClicked.getReqSkills());
                    b.putString("mReqExp", proClicked.getReqExp());
                    b.putString("mOtherReqs", proClicked.getOtherReqs());
                    b.putString("mDuration", proClicked.getDuration());
                    b.putString("mSummary", proClicked.getSummary());
                    b.putString("mEmail", proClicked.getCreatorEmail());
                    //TODO picture

                    Intent intent = new Intent(FindProjects.this, ProjectActivity.class);

                    intent.putExtras(b);

                    startActivity(intent);
                }
            });

            recyclerView.setAdapter(adapter);

        }

    }

}
