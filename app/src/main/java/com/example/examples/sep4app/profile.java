package com.example.examples.sep4app;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar mytoolbar=(Toolbar)findViewById(R.id.ToolBar);
        setSupportActionBar(mytoolbar);



        ActionBar ab=getSupportActionBar();
        ab.setDisplayShowTitleEnabled(false);
        ab.setDisplayHomeAsUpEnabled(true);
    }
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.view1:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                // Toast humidity= Toast.makeText(this,"Android Toast",Toast.LENGTH_LONG);
                //  humidity.show();
               // Intent humidityIntent=new Intent (this,);
               // startActivity( );
                return true;

            case R.id.view2:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                // Toast temperature= Toast.makeText(this,"Android Toast",Toast.LENGTH_LONG);
                // temperature.show();
               // Intent temperatureIntent=new Intent (this, );
               // startActivity( );
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }


    }

}
