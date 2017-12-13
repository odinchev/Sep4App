package com.example.examples.sep4app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examples.sep4app.DeveloperProfile.Developer;
import com.example.examples.sep4app.project.Project;

import java.util.List;

/**
 * Created by MrWhitemount on 08-Dec-17.
 */

class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder>{
    private List<Project> list;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public ProjectAdapter(List<Project> list, Context context, OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
        this.list = list;
        this.mContext = context;
    }


    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_projects, parent, false));
    }

    @Override
    public void onBindViewHolder(final ProjectViewHolder holder, final int position) {

        Project pro = list.get(position);

        holder.textSummary.setText(pro.getSummary());
        holder.textSkills.setText(pro.getReqSkills().toString());
        //TODO add the image

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
                Toast.makeText(mContext, "Interested in!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder{

        TextView textSkills, textSummary;
        Button btnShowInterest;
        ImageView projectPic;



        public ProjectViewHolder(View itemView) {
            super(itemView);

            projectPic = (ImageView) itemView.findViewById(R.id.project_image2);
            textSkills = (TextView) itemView.findViewById(R.id.minPro_knoho);
            textSummary = (TextView) itemView.findViewById(R.id.minPro_tltr);
            btnShowInterest = (Button) itemView.findViewById(R.id.btn_findInterest2);


        }
    }
}
