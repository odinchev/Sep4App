package com.example.examples.sep4app.DeveloperProfile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.examples.sep4app.MainActivity;
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.findDevs;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.User;
import com.example.examples.sep4app.profile.profile;
import com.example.examples.sep4app.signUp.create_Developer_profile_1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

/**
 * Created by PC on 1.12.2017 г..
 */

public class editProfileDeveloper extends AppCompatActivity

{

    private static final int CHOOSE_IMAGE = 101;
    // name certifications years of experience description preferred ide
    ImageView profilePicture;
    TextView Firstname;
    TextView LastName;
    EditText Certifications;
    EditText yearsofExperience;
    EditText Description;
    EditText preferredIDE;
    EditText Skills;
    Button Save;
    Uri uriProfileImage;
    String profileImageURL;
    FirebaseAuth mAuth;
    DatabaseReference database;
    DatabaseReference Users;

    private NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_edit_profile);


        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_Layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        navigation = (NavigationView) findViewById(R.id.navigation_view);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //for the drawer side menu ^
        initInstances();








        mAuth = FirebaseAuth.getInstance();

        profilePicture = (ImageView) findViewById(R.id.imageView);
        Firstname = (TextView) findViewById(R.id.editTextNameEdit);//text views
        LastName = (TextView) findViewById(R.id.editTextLastName);
        Certifications = (EditText) findViewById(R.id.editTextCertificationsEdit);
        yearsofExperience = (EditText) findViewById(R.id.editTextYearsOfExperienceEdit);
        Description = (EditText) findViewById(R.id.editTextDescriptionEdit);
        Skills = (EditText) findViewById(R.id.editTextSkills);
        preferredIDE = (EditText) findViewById(R.id.editTextPreferredIDEEdit);
        Save = (Button) findViewById(R.id.SaveProfileEdit);
        database = FirebaseDatabase.getInstance().getReference("Developers");
        Users = FirebaseDatabase.getInstance().getReference().child("Users");
        // Fetch data from database

        Users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    User user = dataSnapshot.getValue(User.class);
                    Firstname.setText(user.getName());
                    LastName.setText(user.getLastName());
                    // here is where we use glide to take the picture url and put it into the ImageView
                    Glide.with(getApplicationContext())
                            .load(user.getPicture())
                            .into(profilePicture);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Developers").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener()
        {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists()) {
                    developer developer = dataSnapshot.getValue(developer.class);
                    User user = dataSnapshot.getValue(User.class);

                    Description.setText(developer.getDescription());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

                Log.d("Error", "error");
            }
        });


    }



    private void initInstances() {

//navbar


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_Main:
                        Intent i = new Intent(editProfileDeveloper.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.nav_Profile:
                        Intent j = new Intent(editProfileDeveloper.this,profile.class);
                        startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(editProfileDeveloper.this,EditProfile.class);
                        startActivity(k);
                        break;


                    case R.id.nav_Create_Developer_Profile:
                        Intent l = new Intent(editProfileDeveloper.this,create_Developer_profile_1.class);
                        startActivity(l);
                        break;


                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(editProfileDeveloper.this,editProfileDeveloper.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(editProfileDeveloper.this,Developerprofile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(editProfileDeveloper.this,findDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        // Intent p = new Intent(create_Developer_profile_2.this,FindProjects.class);
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


    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item))
        {return true;
        }
        return super.onOptionsItemSelected(item);
    }



















    public void ChangePicture(View v)
    {
        ShowImageChooser();
    }

    public void SaveProfiletoDatabase(View v)
    {
        SaveUserInformation();
    }

    public void SaveUserInformation()
    {
        String name = Firstname.getText().toString().trim();
        String lastName = LastName.getText().toString().trim();
        String certifications = Certifications.getText().toString().trim();
        String YearsofExperience = yearsofExperience.getText().toString().trim();
        String description = Description.getText().toString().trim();
        String skills = Skills.getText().toString().trim();
        String PreferredIDE = preferredIDE.getText().toString().trim();


        FirebaseUser user = mAuth.getCurrentUser();

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        developer Editdeveloper = new developer(id, name, lastName, certifications, YearsofExperience, description, skills, PreferredIDE, profileImageURL);
        database.child(id).setValue(Editdeveloper);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            uriProfileImage = data.getData();
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriProfileImage);
                profilePicture.setImageBitmap(bitmap);
                UploadImageToFirebaseStorage();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void UploadImageToFirebaseStorage()
    {
        StorageReference profileImageReference = FirebaseStorage.getInstance().getReference("profilePics/" + System.currentTimeMillis() + ".jpg");
        if (uriProfileImage != null)
        {
            profileImageReference.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    profileImageURL = taskSnapshot.getDownloadUrl().toString();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {


                    Toast.makeText(editProfileDeveloper.this, e.getMessage(), Toast.LENGTH_LONG).show();


                }
            });
        }
    }

    // for the image choosing
    private void ShowImageChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }


}
