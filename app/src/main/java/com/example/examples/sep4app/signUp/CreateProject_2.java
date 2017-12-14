package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.examples.sep4app.DeveloperProfile.DevProfile;
import com.example.examples.sep4app.DeveloperProfile.Developer;
import com.example.examples.sep4app.MultiSelectionSpinner;
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.project.Project;
import com.example.examples.sep4app.project.ProjectActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreateProject_2 extends AppCompatActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener{
    private FirebaseAuth mAuth;
    DatabaseReference database;
    private String sName, sSummary,sDuration, sExp, sOther;
    private List<String> skills;
    private EditText exp, other;
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
        other=(EditText)findViewById(R.id.editText_proOther);


        bar=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();

        skills = new ArrayList<>();
        final String[] tags = {
                "C", "C++", "C#", "Java",
                "JavaScript", "Python", "PHP", "SQL"};
        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.spinner_proSkills);
        multiSelectionSpinner.setItems(tags);
        multiSelectionSpinner.setSelection(new int[]{2, 6});
        multiSelectionSpinner.setListener(this);
    }
    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        //Toast.makeText(this, strings.toString(), Toast.LENGTH_LONG).show();
        skills = strings;
        Toast.makeText(this, skills.toString(), Toast.LENGTH_LONG).show();
    }

    public void CreateProject(View v)
    {

        sExp = exp.getText().toString().trim();
        sOther = other.getText().toString().trim();

        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        Project project =new Project(id, sName, skills, sExp, sOther, sDuration,sSummary);
        database.child(id).setValue(project);
        Intent intent =new Intent(CreateProject_2.this, ProjectActivity.class);
        startActivity(intent);
    }
}
