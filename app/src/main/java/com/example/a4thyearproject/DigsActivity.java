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

public class DigsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_digs);

        mButton = findViewById(R.id.imgDigs);
        add = findViewById(R.id.addDigs);
        et1 = findViewById(R.id.locationDigs);
        et2 = findViewById(R.id.priceDigs);
        et4 = findViewById(R.id.detailsDigs);


        et3 = findViewById(R.id.type1Digs);

        pd=new ProgressDialog(this);





        mDb=FirebaseDatabase.getInstance();
        dbRef=mDb.getReference().child("Digs");
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

                if(!(loc.isEmpty() && price.isEmpty() && type.isEmpty() && imageUrl!=null)){

                    pd.setTitle("Uploading . . .");
                    pd.show();

                    StorageReference filepath = mStorage.getReference().child("imageDigs").child(imageUrl.getLastPathSegment());
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
                                    newPost.child("Conditions").setValue(details);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    pd.dismiss();

                                    Intent intent = new Intent(DigsActivity.this,LandActivity.class);
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