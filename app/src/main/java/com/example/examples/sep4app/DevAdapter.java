package com.example.examples.sep4app;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        holder.textName.setText(developer.name);
       // holder.textSkills.setText(developer.tagsToString);
        holder.textYears.setText(developer.yearsOfExp);
        holder.textCerts.setText(developer.certificates);
        //holder.textInterests.setText(developer.interests);

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
