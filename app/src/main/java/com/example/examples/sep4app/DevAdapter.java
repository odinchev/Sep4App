package com.example.examples.sep4app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examples.sep4app.DeveloperProfile.developer;

import java.util.List;
import com.example.examples.sep4app.DeveloperProfile.developer;
/**
 * Created by MrWhitemount on 01-Dec-17.
 */

class DevAdapter extends RecyclerView.Adapter<DevAdapter.DevViewHolder>{
    private List<developer> list;


    public DevAdapter(List<developer> list){
        this.list = list;
    }


    @Override
    public DevViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DevViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_devs, parent, false));
    }

    @Override
    public void onBindViewHolder(final DevViewHolder holder, int position) {

        developer developer = list.get(position);

        holder.textName.setText(developer.name + " " + developer.lastName);
        holder.textSkills.setText(developer.tags);
        holder.textYears.setText(developer.yearsofExperience);
        holder.textCerts.setText(developer.certifications);
        holder.textInterests.setText(developer.description);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DevViewHolder extends RecyclerView.ViewHolder{

        TextView textName, textSkills, textYears, textCerts, textInterests;



        public DevViewHolder(View itemView) {
            super(itemView);

            textName = (TextView) itemView.findViewById(R.id.text_name);
            textSkills = (TextView) itemView.findViewById(R.id.text_skills);
            textYears = (TextView) itemView.findViewById(R.id.text_yearsOfExp);
            textCerts = (TextView) itemView.findViewById(R.id.text_certificates);
            textInterests = (TextView) itemView.findViewById(R.id.text_interestedIn);


        }
    }
}
