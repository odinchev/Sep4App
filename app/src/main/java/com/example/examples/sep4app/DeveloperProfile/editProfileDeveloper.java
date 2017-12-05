package com.example.examples.sep4app.DeveloperProfile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.examples.sep4app.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
        EditText Firstname;
        EditText LastName;
        EditText Certifications;
        EditText yearsofExperience;
        EditText Description;
        EditText preferredIDE;
        Uri uriProfileImage;
        String profileImageURL;
        FirebaseAuth mAuth;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_profile);
        mAuth=FirebaseAuth.getInstance();
        // Toolbar mytoolbar=(Toolbar)findViewById(R.id.ToolBar);
        //  setSupportActionBar(mytoolbar);
        profilePicture=(ImageView)findViewById(R.id.imageView);
        Firstname=(EditText) findViewById(R.id.editTextNameEdit);
        LastName=(EditText)findViewById(R.id.editTextLastName);
        Certifications=(EditText) findViewById(R.id.editTextCertificationsEdit);
        yearsofExperience=(EditText) findViewById(R.id.editTextYearsOfExperienceEdit);
        Description=(EditText) findViewById(R.id.editTextDescriptionEdit);
        preferredIDE=(EditText) findViewById(R.id.editTextPreferredIDEEdit);


        // ActionBar ab=getSupportActionBar();
        //  ab.setDisplayShowTitleEnabled(false);
        // ab.setDisplayHomeAsUpEnabled(true);
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
            String name=Firstname.getText().toString().trim()+" "+LastName.getText().toString().trim();
            String certifications=Certifications.getText().toString().trim();
            String YearsofExperience=yearsofExperience.getText().toString().trim();
            String description=Description.getText().toString().trim();
            String PreferredIDE=preferredIDE.getText().toString().trim();


            if(name.isEmpty())
            {
                Firstname.setError("Name Required");
                Firstname.requestFocus();
                LastName.setError("Name Required");
                LastName.requestFocus();
                return;
            }
            FirebaseUser user=mAuth.getCurrentUser();
            if(user!=null&&profileImageURL!=null)
            {
               //writre all the stuff to the database



            }



        }
        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==CHOOSE_IMAGE&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null)
            {
                uriProfileImage=data.getData();
                try
                {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
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
            StorageReference profileImageReference= FirebaseStorage.getInstance().getReference("profilePics/"+ System.currentTimeMillis()+".jpg");
            if(uriProfileImage!=null)
            {
                profileImageReference.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        profileImageURL=taskSnapshot.getDownloadUrl().toString();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        Toast.makeText(editProfileDeveloper.this,e.getMessage(),Toast.LENGTH_LONG).show();


                    }
                });
            }
        }
            // for the image choosing
        private void ShowImageChooser()
        {
            Intent intent=new Intent();
            intent.setType("image/*");
            intent.setAction(intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),CHOOSE_IMAGE);
        }


}
