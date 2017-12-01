package com.example.examples.sep4app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class findDevs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DevModel> devList;
    private DevAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private int savePos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_devs);

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


    private void updateList(){

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                devList.add(dataSnapshot.getValue(DevModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


                int index = getItemIndex(dataSnapshot.getValue(DevModel.class));
                devList.set(index, dataSnapshot.getValue(DevModel.class));
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


                int index = getItemIndex(dataSnapshot.getValue(DevModel.class));

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

    private int getItemIndex(DevModel developer){

        int index = savePos;

        for(int i=0; i < devList.size(); i++){
            if(devList.get(i).key.equals(developer.key)) {
                index = i;
                break;
            }
        }

        return index;

    }
}
