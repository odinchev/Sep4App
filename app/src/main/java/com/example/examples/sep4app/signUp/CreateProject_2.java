package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.examples.sep4app.DeveloperProfile.DevProfile;
import com.example.examples.sep4app.DeveloperProfile.Developer;
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.project.Project;
import com.example.examples.sep4app.project.ProjectActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProject_2 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    DatabaseReference database;
    private String sName, sSummary,sDuration, sExp, sSkills, sOther;
    private EditText exp, skills, other;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project_2);

        database= FirebaseDatabase.getInstance().getReference("Projects");

        Intent intent=getIntent();
        sName = intent.getExtras().getString("Name");
        sSummary=intent.getExtras().getString("Summary");
        sDuration= intent.getExtras().getString("Duration");



        exp=(EditText)findViewById(R.id.editText_proExp);
        skills=(EditText)findViewById(R.id.editText_proSkills);
        other=(EditText)findViewById(R.id.editText_proOther);


        bar=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
    }

    public void CreateProject(View v)
    {

        sExp = exp.getText().toString().trim();
        sSkills = skills.getText().toString().trim();
        sOther = other.getText().toString().trim();

        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        Project project =new Project(id, sName, sSkills, sExp, sOther, sDuration,sSummary);
        database.child(id).setValue(project);
        Intent intent =new Intent(CreateProject_2.this, ProjectActivity.class); //TODO onStart or other activcity or something
        startActivity(intent);
    }
}
