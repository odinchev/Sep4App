package com.example.examples.sep4app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.example.examples.sep4app.DeveloperProfile.developer;
import com.example.examples.sep4app.DeveloperProfile.viewDevProfile;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class findDevs extends AppCompatActivity {


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

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for the drawer side menu ^

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
                developer devClicked = devList.get(position);

                Bundle b = new Bundle();
                b.putString("mName", devClicked.name + " " + devClicked.lastName);
                b.putString("mCertifications", devClicked.certificates);
                b.putString("mYearsOfExperience", devClicked.yearsofExperience);
                b.putString("mDescription", devClicked.description);
                b.putString("mSkills", devClicked.tags);
                b.putString("mPreferredIDE", devClicked.preferredIDE);
                //TODO picture

                Intent intent = new Intent(findDevs.this, viewDevProfile.class);

                intent.putExtras(b);

                startActivity(intent);


            }
        });
        recyclerView.setAdapter(adapter);



        updateList();

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
