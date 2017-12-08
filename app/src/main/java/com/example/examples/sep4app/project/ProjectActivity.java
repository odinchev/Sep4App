package com.example.examples.sep4app.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examples.sep4app.R;

public class ProjectActivity extends AppCompatActivity {

    private ImageView projectImage;
    private Button btnContact;
    private TextView name, reqSkillLabel, reqSkill, reqExpLabel, reqExp, otherReqsLabel, otherReqs, durLabel,duration, summaryLabel, summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        projectImage = findViewById(R.id.project_image2);
        btnContact = findViewById(R.id.btn_contactProject);
        name = findViewById(R.id.text_project_name);
        reqSkill = findViewById(R.id.text_project_skills);
        reqExp = findViewById(R.id.text_project_exp);
        otherReqs = findViewById(R.id.text_project_otherReqs);
        duration = findViewById(R.id.text_project_duration);
        summary = findViewById(R.id.text_project_summary);
      //  reqSkillLabel = findViewById(R.id.text_project_skills_label);
     //   reqExpLabel = findViewById(R.id.text_project_exp_label);
     //   otherReqsLabel = findViewById(R.id.text_project_otherReqs_label);
    //    durLabel = findViewById(R.id.text_project_duration_label);
    //    summaryLabel = findViewById(R.id.text_project_summary_label);

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if( b != null){
            name.setText(b.getString("mName"));
            reqSkill.setText(b.getString("mReqSkills"));
            reqExp.setText(b.getString("mReqExp"));
            otherReqs.setText(b.getString("mOtherReqs"));
            duration.setText(b.getString("mDuration"));
            summary.setText(b.getString("mSummary"));
            //TODO set Profile pic
        }
    }
}
