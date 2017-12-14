package com.example.examples.sep4app.signUp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.example.examples.sep4app.Login;
import com.example.examples.sep4app.profile.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class CreateDevProfile_2 extends AppCompatActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener {
 private   NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
// this is the screen for developers  not a sign up but editing stuff that means no register user with mauth but push dev to database with getkey we need a map

    EditText editTextSkills;//3

    public String name;
    public String LastName;
    public String email;
    public String password;
    public String certifications;
    public String yearsOfExperience;
    public String description;
    public String preferredIDE;
    public String Picture;
    ProgressBar progress;
    private List<String> skills;

    // have multiple strings for the name and the other stuff pass them with intents
    private FirebaseAuth mAuth;
            DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_developer_profile2);

       /* navigation = (NavigationView) findViewById(R.id.navigation_view);

        //for the Drawer side menu
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for the drawer side menu ^^^^
        */

        database= FirebaseDatabase.getInstance().getReference("Developers");

        Intent intent=getIntent();
        name=intent.getExtras().getString("name");
        LastName=intent.getExtras().getString("LastName");
        email= intent.getExtras().getString("email");
        password= intent.getExtras().getString("password");
        certifications=intent.getExtras().getString("certifications");
        yearsOfExperience=intent.getExtras().getString("yearsOfExperience");
        description=intent.getExtras().getString("description");
        preferredIDE=intent.getExtras().getString("preferredide");
        Picture=intent.getExtras().getString("Picture");


        //editTextSkills=(EditText)findViewById(R.id.editTextSkills);

        Button signup= (Button) findViewById(R.id.Signup);

        progress=(ProgressBar)findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        //initInstances();


        skills = new ArrayList<>();
        final String[] tags = {
                "C", "C++", "C#", "Java",
                "JavaScript", "Python", "PHP", "SQL"};
        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.spinner);
        multiSelectionSpinner.setItems(tags);
        multiSelectionSpinner.setSelection(new int[]{2, 6});
        multiSelectionSpinner.setListener(this);

    }


    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void selectedIndices(List<Integer> indices) {

    }

    @Override
    public void selectedStrings(List<String> strings) {
        //Toast.makeText(this, strings.toString(), Toast.LENGTH_LONG).show();
        skills = strings;
        Toast.makeText(this, skills.toString(), Toast.LENGTH_LONG).show();
    }





   /* private void initInstances() {

//navbar


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_Main:
                        Intent i = new Intent(CreateDevProfile_2.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_Profile:
                        Intent j = new Intent(CreateDevProfile_2.this,Profile.class);
                       startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(CreateDevProfile_2.this,EditProfile.class);
                        startActivity(k);
                        break;


                    case R.id.nav_Create_Developer_Profile:
                        Intent l = new Intent(CreateDevProfile_2.this,CreateDevProfile_1.class);
                        startActivity(l);
                        break;


                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(CreateDevProfile_2.this,EditDevProfile.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(CreateDevProfile_2.this,DevProfile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(CreateDevProfile_2.this,FindDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        // Intent p = new Intent(CreateDevProfile_2.this,FindProjects.class);
                       // startActivity(p);
                        Context context = getApplicationContext();
                            CharSequence text = "EMPTINESS!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast.makeText(context, text, duration).show();



                        break;



                }
                return false;
            }
        });

    }



*/















/*

    public boolean onOptionsItemSelected(MenuItem item){




       if(mToggle.onOptionsItemSelected(item ))



        {return true;
        }
        return super.onOptionsItemSelected(item);

    //for selecting stuff in the drawer menu
    }*/


    //TODO is this used anywhere???
    public void registerUser(View v)
    {

        progress.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progress.setVisibility(View.GONE);
                if(task.isSuccessful())
                {

                    // make the user in the database
                    String id=FirebaseAuth.getInstance().getCurrentUser().getUid();
                    User user=new User(id,name,LastName,certifications,yearsOfExperience,description,preferredIDE);
                    database.child(id).setValue(user);
                    Toast.makeText(getApplicationContext(),"User registered successfull", Toast.LENGTH_LONG).show();

                }
                else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(), "User already exists", LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public void RegisterDeveloper(View v)
    {

        //String[] skills = editTextSkills.getText().toString().trim();
        //String[] tags = {"stub"};

        String id= FirebaseAuth.getInstance().getCurrentUser().getUid();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        Developer developer =new Developer(id,name,LastName,certifications,yearsOfExperience,description,skills,preferredIDE,Picture);
        developer.setEmail(email);
        database.child(id).setValue(developer);
        Intent intent =new Intent(CreateDevProfile_2.this, DevProfile.class);
        startActivity(intent);

    }

    //TODO is it used somewhere???
    public void LogIn(View v)
    {
        startActivity(new Intent(this,Login.class));
    }




    // Using two different layouts


}
