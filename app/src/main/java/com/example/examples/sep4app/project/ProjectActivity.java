package com.example.examples.sep4app.project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examples.sep4app.R;

import java.util.ArrayList;

public class ProjectActivity extends AppCompatActivity {

    private ImageView projectImage;
    private Button btnContact;
    private TextView name, reqSkillLabel, reqSkill, reqExpLabel, reqExp, otherReqsLabel, otherReqs, durLabel,duration, summaryLabel, summary;
    private String email;

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

        Intent in = getIntent();
        Bundle b = in.getExtras();
        if( b != null){
            name.setText(b.getString("mName"));
            reqSkill.setText(b.getStringArrayList("mReqSkills").toString());
            reqExp.setText(b.getString("mReqExp"));
            otherReqs.setText(b.getString("mOtherReqs"));
            duration.setText(b.getString("mDuration"));
            summary.setText(b.getString("mSummary"));
            email = (String)b.getString("mEmail");

            //TODO set Profile pic

        }

        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inEmail = new Intent(Intent.ACTION_SEND);
                    inEmail.setType("message/rfc822");
                    inEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                    inEmail.putExtra(Intent.EXTRA_SUBJECT, "subject");
                    inEmail.putExtra(Intent.EXTRA_TEXT, "Project managers email: " + email);
                startActivity(Intent.createChooser(inEmail, "Choose an Email client :"));
            }
        });
    }
}
