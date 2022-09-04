package com.example.a4thyearproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class EditProfileActivity extends AppCompatActivity {

    EditText etFirstName, etLastName, etBio, etUniversity, etAge, etGender;
    Button update;
    ImageButton backBtn;
    ImageView imageView;
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    SharedPreferences sp;
    String fNameStr,sNameStr,strBio, strUniversity, strAge, strGender;


    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);


        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        imageView = findViewById(R.id.profilePhoto);



        //Edit Texts
        etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.LastName);
        etBio = findViewById(R.id.BioMultiLine);
        etUniversity = findViewById(R.id.University);
        etAge = findViewById(R.id.Age);
        etGender = findViewById(R.id.gender);

        sp = getSharedPreferences("myPref",MODE_PRIVATE);



        //Buttons
        update = findViewById(R.id.updateBtn);
        backBtn = findViewById(R.id.backButton);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start of updating profile


                fNameStr = etFirstName.getText().toString();
                sNameStr = etLastName.getText().toString();
                strBio = etBio.getText().toString();
                strUniversity = etUniversity.getText().toString();
                strAge = etAge.getText().toString();
                strGender = etGender.getText().toString();

                SharedPreferences.Editor ed = sp.edit();
                ed.putString("fName",fNameStr);
                ed.putString("sName",sNameStr);
                ed.putString("bio",strBio);
                ed.putString("uni",strUniversity);
                ed.putString("age",strAge);
                ed.putString("gender",strGender);
                ed.commit();
                Toast.makeText(EditProfileActivity.this,"Information saved", Toast.LENGTH_LONG).show();
                Intent i = new Intent(EditProfileActivity.this,ProfileActivity.class);
                startActivity(i);











            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosePicture();
            }
        });



    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
            uploadPicture();
        }

    }

    private void uploadPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Uploading . . . .");

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Snackbar.make(findViewById(android.R.id.content),"Image Uploaded", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Failed to upload",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        pd.setMessage("Percentage: *" + (int) progressPercent + "");
                    }
                });


    }





}