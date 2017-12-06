package com.example.examples.sep4app.profile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.examples.sep4app.R;
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
import java.io.UnsupportedEncodingException;

/**
 * Created by PC on 1.12.2017 г..
 */

public class EditProfile extends AppCompatActivity

{

    private static final int CHOOSE_IMAGE = 101;
    // name certifications years of experience description preferred ide
    ImageView profilePicture;
    EditText Firstname;
    EditText LastName;

    EditText Description;

    Uri uriProfileImage;
    String profileImageURL;
    FirebaseAuth mAuth;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mAuth = FirebaseAuth.getInstance();

        profilePicture = (ImageView) findViewById(R.id.imageView);
        Firstname = (EditText) findViewById(R.id.editTextNameEdit);
        LastName = (EditText) findViewById(R.id.editTextLastName);

        Description = (EditText) findViewById(R.id.editTextDescriptionEdit);

        database = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        // Fetch data from database

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if(dataSnapshot.exists())
                {
                    User user=dataSnapshot.getValue(User.class);
                   Firstname.setText(user.getName() );
                   LastName.setText( user.getLastName());
                    Description.setText(user.getDescription());
                    // here is where we use glide to take the picture url and put it into the ImageView
                    Glide.with(getApplicationContext())
                            .load(user.getPicture())
                            .into(profilePicture);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

                Log.d("Error","error");
            }
        });
    }



    public void ChangePicture(View v)
    {
        ShowImageChooser();
    }

    public void SaveProfileToDatabase(View v)
    {
        SaveUserInformation();
    }

    public void SaveUserInformation()
    {
        String name = Firstname.getText().toString().trim();
        String lastName = LastName.getText().toString().trim();

        String description = Description.getText().toString().trim();

        if (name.isEmpty()) {
            Firstname.setError("Name Required");
            Firstname.requestFocus();
            LastName.setError("Name Required");
            LastName.requestFocus();
            return;
        }
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null && profileImageURL != null)
        {
            String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
            User Edituser = new User(id, name, lastName, description, profileImageURL);
            database.child(id).setValue(Edituser);


        }


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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void UploadImageToFirebaseStorage()
    {
        StorageReference profileImageReference = FirebaseStorage.getInstance().getReference("profilePics/" + System.currentTimeMillis() + ".jpg");
        if (uriProfileImage != null)
        {
            profileImageReference.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {


                    profileImageURL = taskSnapshot.getDownloadUrl().toString();

                    // put in database as picture in user

                }
            }).addOnFailureListener(new OnFailureListener()
            {
                @Override
                public void onFailure(@NonNull Exception e)
                {


                    Toast.makeText(EditProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();


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
