package com.example.examples.sep4app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.examples.sep4app.DeveloperProfile.Developer;
import com.example.examples.sep4app.Friends.Friends;
import com.example.examples.sep4app.profile.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrWhitemount on 01-Dec-17.
 */

class DevAdapter extends RecyclerView.Adapter<DevAdapter.DevViewHolder>{
    private List<Developer> list;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    ArrayList <Developer>FriendsList=new ArrayList<>();
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    // friends fields
    FirebaseAuth mAuth;
    DatabaseReference database;
    String id= FirebaseAuth.getInstance().getCurrentUser().getUid();

    Friends friend=new Friends(id);

    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }


    public DevAdapter(List<Developer> list, Context context, OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
        this.list = list;
        this.mContext = context;
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference().child("Friends");
    }


    @Override
    public DevViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DevViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_devs, parent, false));
    }

    @Override
    public void onBindViewHolder(final DevViewHolder holder, final int position) {

        final Developer developer = list.get(position);

        holder.textName.setText(developer.name + " " + developer.lastName);
        holder.textSkills.setText(developer.skills.toString()); //TODO correct this, right now a stub
        holder.textYears.setText(developer.yearsofExperience);
        holder.textCerts.setText(developer.certifications);
        holder.textInterests.setText(developer.description);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Recycle Click" + position, Toast.LENGTH_SHORT).show();
                mOnItemClickListener.onItemClick(view, position);

            }
        });





        holder.btnShowInterest.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO do button click work here
                // find project id
                // instantiate new friends object
                // use addtoList method
                // put object in database







                mDatabase.child("Projects").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener()
                {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        if (dataSnapshot.exists()) {

                            Developer Developer=list.get(position);
                            friend.AddtoList(Developer);
                            database.child(id).setValue(friend);
                            Toast.makeText(mContext,"Developer added to the project", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(mContext, "Interested in!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {

                        Log.d("Error", "error");
                    }
                });



            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DevViewHolder extends RecyclerView.ViewHolder{

        TextView textName, textSkills, textYears, textCerts, textInterests;
        Button btnShowInterest;



        public DevViewHolder(View itemView) {
            super(itemView);

            textName = (TextView) itemView.findViewById(R.id.text_name);
            textSkills = (TextView) itemView.findViewById(R.id.text_skills);
            textYears = (TextView) itemView.findViewById(R.id.text_yearsOfExp);
            textCerts = (TextView) itemView.findViewById(R.id.text_certificates);
            textInterests = (TextView) itemView.findViewById(R.id.text_interestedIn);
            btnShowInterest = (Button) itemView.findViewById(R.id.btn_findInterest1);


        }
    }

    public List<Developer> getList() {
        return list;
    }

    public void setList(List<Developer> list) {
        this.list = list;
    }
}


