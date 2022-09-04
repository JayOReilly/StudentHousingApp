package com.example.a4thyearproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ElderlyLandlord extends AppCompatActivity {

    FirebaseDatabase mDb;
    DatabaseReference dbRef;
    FirebaseStorage mStorage;
    ProgressDialog pd;


    Button add;
    ImageButton mButton;
    EditText et1,et2,et3,et4;

    private static final int Gallery_Code=1;
    Uri imageUrl=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elderly_landlord);

        add = findViewById(R.id.addElder);
        mButton = findViewById(R.id.img);
        et1 = findViewById(R.id.priceElder);
        et2 = findViewById(R.id.locationElder);
        et3 = findViewById(R.id.typeElder);
        et4 = findViewById(R.id.detailsElder);


        pd=new ProgressDialog(this);





        mDb=FirebaseDatabase.getInstance();
        dbRef=mDb.getReference().child("Elderly");
        mStorage = FirebaseStorage.getInstance();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i,Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==Gallery_Code && resultCode==RESULT_OK){
            imageUrl=data.getData();
            mButton.setImageURI(imageUrl);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String loc=et1.getText().toString().trim();
                final String price=et2.getText().toString().trim();
                final String type=et3.getText().toString().trim();
                final String details=et4.getText().toString().trim();

                if(!(loc.isEmpty() && price.isEmpty() && type.isEmpty() && details.isEmpty() && imageUrl!=null)){

                    pd.setTitle("Uploading . . .");
                    pd.show();

                    StorageReference filepath = mStorage.getReference().child("imageElder").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t= task.getResult().toString();

                                    DatabaseReference newPost = dbRef.push();
                                    newPost.child("location").setValue(loc);
                                    newPost.child("price").setValue(price);
                                    newPost.child("type").setValue(type);
                                    newPost.child("details").setValue(details);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    pd.dismiss();

                                    Intent intent = new Intent(ElderlyLandlord.this,LandActivity.class);
                                    startActivity(intent);



                                }
                            });


                        }
                    });

                }
            }
        });

    }
}