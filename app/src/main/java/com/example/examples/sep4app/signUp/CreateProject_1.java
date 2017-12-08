package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.examples.sep4app.R;

public class CreateProject_1 extends AppCompatActivity {

    private EditText pName, pSummary, pDuration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project_1);

        pName=(EditText)findViewById(R.id.editText_proName);
        pSummary=(EditText)findViewById(R.id.editText_proSummary);
        pDuration=(EditText)findViewById(R.id.editText_proDuration);

    }

    public void NextScreen(View v)
    {
        String name=pName.getText().toString().trim();
        String summary=pSummary.getText().toString().trim();
        String duration=pDuration.getText().toString().trim();


        Intent intent =new Intent(this,CreateProject_2.class);
        intent.putExtra("Name",name);
        intent.putExtra("Summary",summary);
        intent.putExtra("Duration",duration);
        startActivity(intent);

    }
}
