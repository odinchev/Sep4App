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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.examples.sep4app.FindProjects;
import com.example.examples.sep4app.Login;
import com.example.examples.sep4app.MainActivity;
import com.example.examples.sep4app.MultiSelectionSpinner;
import com.example.examples.sep4app.R;
import com.example.examples.sep4app.FindDevs;
import com.example.examples.sep4app.profile.EditProfile;
import com.example.examples.sep4app.profile.User;
import com.example.examples.sep4app.profile.Profile;
import com.example.examples.sep4app.signUp.CreateDevProfile_1;
import com.example.examples.sep4app.signUp.CreateProject_1;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 1.12.2017 г..
 */

public class EditDevProfile extends AppCompatActivity implements MultiSelectionSpinner.OnMultipleItemsSelectedListener

{
    // this is the class in which we edit the developers
    private static final int CHOOSE_IMAGE = 101;
    // name certifications years of experience description preferred ide
    ImageView profilePicture;
    ImageView backgroundPicture;
    TextView Firstname;
    TextView LastName;
    EditText Certifications;
    EditText yearsofExperience;
    EditText Description;
    EditText preferredIDE;
    //EditText Skills;
    Button Save;
    Uri uriProfileImage;
    String profileImageURL;

    Uri uriBacgroundImage;
    String backgroundImageURL;


    FirebaseAuth mAuth;
    DatabaseReference database;
    DatabaseReference Users;

    private NavigationView navigation;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    private List<String> skills;

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
        backgroundPicture=(ImageView)findViewById(R.id.BackgroundImage);
        Firstname = (TextView) findViewById(R.id.editTextNameEdit);//text views
        LastName = (TextView) findViewById(R.id.editTextLastName);
        Certifications = (EditText) findViewById(R.id.editTextCertificationsEdit);
        yearsofExperience = (EditText) findViewById(R.id.editTextYearsOfExperienceEdit);
        Description = (EditText) findViewById(R.id.editTextDescriptionEdit);
        //Skills = (EditText) findViewById(R.id.editTextSkills);
        preferredIDE = (EditText) findViewById(R.id.editTextPreferredIDEEdit);
        Save = (Button) findViewById(R.id.SaveProfileEdit);
        // we have two references  for the developer and the user
        database = FirebaseDatabase.getInstance().getReference("Developers");
        Users = FirebaseDatabase.getInstance().getReference().child("Users");

        //spinner
        skills = new ArrayList<>();
        final String[] tags = {
                "C", "C++", "C#", "Java",
                "JavaScript", "Python", "PHP", "SQL"};
        MultiSelectionSpinner multiSelectionSpinner = (MultiSelectionSpinner) findViewById(R.id.spinner);
        multiSelectionSpinner.setItems(tags);
        multiSelectionSpinner.setSelection(new int[]{2, 6});
        multiSelectionSpinner.setListener(this);
        // Fetch data from database
        // we use the data from the user to get the picture firstname and lastname
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
            // and we user the developer reference to get the developer specific stuff

            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists()) {
                    Developer developer = dataSnapshot.getValue(Developer.class);
                    User user = dataSnapshot.getValue(User.class);

                    Description.setText(developer.getDescription());
                    Certifications.setText(developer.getCertifications());
                    yearsofExperience.setText(developer.getYearsofExperience());
                    preferredIDE.setText(developer.getide());
                    skills=developer.getSkills();
                    profileImageURL=developer.getPicture();
                    backgroundImageURL=developer.getBackgroundPicture();
                    Glide.with(getApplicationContext())
                            .load(developer.getPicture())
                            .into(profilePicture);

                    Glide.with(getApplicationContext()).load(developer.getBackgroundPicture()).into(backgroundPicture);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

                Log.d("Error", "error");
            }
        });


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



    private void initInstances() {

//navbar


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                Menu menu =navigation.getMenu();
                switch (id) {
                    case R.id.nav_Main:
                        Intent i = new Intent(EditDevProfile.this, MainActivity.class);
                        startActivity(i);
                        break;

                    case R.id.nav_ExpandProfile:
                        boolean b=!menu.findItem(R.id.nav_Profile).isVisible();
                        menu.findItem(R.id.nav_Profile).setVisible(b);
                        menu.findItem(R.id.nav_EditProfile).setVisible(b);
                        break;

                    case R.id.nav_Profile:
                        Intent j = new Intent(EditDevProfile.this, Profile.class);
                        startActivity(j);
                        break;
                    case R.id.nav_EditProfile:
                        Intent k = new Intent(EditDevProfile.this, EditProfile.class);
                        startActivity(k);
                        break;



                    case R.id.nav_ExpandDeveloper:
                        boolean booleanDevelopers=!menu.findItem(R.id.nav_View_Developer_Profile).isVisible();
                        menu.findItem(R.id.nav_Create_Developer_Profile).setVisible(booleanDevelopers);
                        menu.findItem(R.id.nav_Edit_Developer_Profile).setVisible(booleanDevelopers);
                        menu.findItem(R.id.nav_View_Developer_Profile).setVisible(booleanDevelopers);
                        break;

                    case R.id.nav_Create_Developer_Profile:
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                        if( mDatabase.child("Developers").child(FirebaseAuth.getInstance().getCurrentUser().getUid())!=null)
                        {
                            Toast.makeText(getApplicationContext(),"You already have a developer profile",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent l = new Intent(EditDevProfile.this, CreateDevProfile_1.class);
                            startActivity(l);
                        }
                        break;

                    case R.id.nav_Edit_Developer_Profile:
                        Intent m = new Intent(EditDevProfile.this, EditDevProfile.class);
                        startActivity(m);
                        break;


                    case R.id.nav_View_Developer_Profile:
                        Intent n = new Intent(EditDevProfile.this, DevProfile.class);
                        startActivity(n);
                        break;


                    case R.id.nav_Find_Developers:
                        Intent o = new Intent(EditDevProfile.this, FindDevs.class);
                        startActivity(o);
                        break;

                    case R.id.nav_Find_Projects:
                        Intent p = new Intent(EditDevProfile.this, FindProjects.class);
                        startActivity(p);
                        Context context = getApplicationContext();


                        break;

                    case R.id.nav_ExpandProjects:
                        boolean booleanProject =!menu.findItem(R.id.nav_CreateProject).isVisible();
                        menu.findItem(R.id.nav_CreateProject).setVisible(booleanProject);
                        break;

                    case R.id.nav_CreateProject:

                        Intent q = new Intent(EditDevProfile.this,CreateProject_1.class);
                        startActivity(q);


                        break;

                    case R.id.nav_SignOut:
                        FirebaseAuth.getInstance().signOut();
                        Intent r = new Intent (EditDevProfile.this, Login.class);
                        startActivity(r);
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


















// here we coose an image
    public void ChangePicture(View v)
    {
        ShowImageChooser();
    }
    public void ChangeBackgroundPicture(View v)
    {
        ShowBackgroundImageChooser();
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
        List<String> skillz = new ArrayList<>(skills);
        String PreferredIDE = preferredIDE.getText().toString().trim();



        FirebaseUser user = mAuth.getCurrentUser();
        // here we save data to the developer object
        if (user != null && profileImageURL != null)
        {
            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String email = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Developer editDeveloper = new Developer(id, email, name, lastName, certifications, YearsofExperience, description, skillz, PreferredIDE, profileImageURL, backgroundImageURL);
            database.child(id).setValue(editDeveloper);
        }
        else
        {
            Toast.makeText(EditDevProfile.this,"Select a Profile picture",Toast.LENGTH_LONG).show();

        }


    }

    // this is for the image
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
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            uriBacgroundImage = data.getData();
            try
            {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uriBacgroundImage);
                backgroundPicture.setImageBitmap(bitmap);
                UploadBackgroundImageToFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // thi is for the image
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


                    Toast.makeText(EditDevProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();


                }
            });
        }
    }
    public void UploadBackgroundImageToFirebaseStorage()
    {
        StorageReference backroundImageReference=FirebaseStorage.getInstance().getReference("backroundPics/"+System.currentTimeMillis()+".jpg");
        if(uriBacgroundImage!=null)
        {
            backroundImageReference.putFile(uriBacgroundImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    backgroundImageURL=taskSnapshot.getDownloadUrl().toString();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e)
                {
                    Toast.makeText(EditDevProfile.this,e.getMessage(),Toast.LENGTH_LONG).show();
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
    private void ShowBackgroundImageChooser()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);

        startActivityForResult(intent.createChooser(intent,"Select Backround Image"),1);
    }


}
