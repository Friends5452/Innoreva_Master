package com.example.innorevamaster.currentevents;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.innorevamaster.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditCurrentActivity extends AppCompatActivity {
    private static final int RC_PHOTO_PICKER = 2;
    private static final String TAG = "EditCurrentActivity";
    private CircleImageView circleImageView;
    private Button saveButton;
    private Uri imageUri;
    private FloatingActionButton floatingActionButton;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private StorageReference mPhotoStorage;
    private DatabaseReference mCurrentDatabseReference;
    private EditText eventDescriptionEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_current_events);
        firebaseDatabase=FirebaseDatabase.getInstance();
        firebaseStorage= FirebaseStorage.getInstance();
        mPhotoStorage=firebaseStorage.getReference().child("current_event_photos");


        eventDescriptionEditText=findViewById(R.id.edit_event_description);
        mCurrentDatabseReference=firebaseDatabase.getReference().child("CurrentEvents");
        circleImageView=findViewById(R.id.event_circular_image);
        floatingActionButton=findViewById(R.id.floatingActionButton2);
        saveButton=findViewById(R.id.save_current_events);
        eventDescriptionEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            saveButton.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!eventDescriptionEditText.getText().toString().isEmpty()||imageUri!=null) {
                    CurrentEvent currentEvent = new CurrentEvent(imageUri.toString(), eventDescriptionEditText.getText().toString());
                    mCurrentDatabseReference.push().setValue(currentEvent);
                }
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/jpeg");
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,"Select Image"),RC_PHOTO_PICKER);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_PHOTO_PICKER&&resultCode==RESULT_OK)
        {
            Uri selectedImageUri = data.getData();
            StorageReference photoRef=mPhotoStorage.child(selectedImageUri.getLastPathSegment());
            photoRef.putFile(selectedImageUri)
                    .addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> firebaseUri = taskSnapshot.getStorage().getDownloadUrl()
                                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Uri> task) {
                                             imageUri=task.getResult();
                                            Glide.with(circleImageView.getContext())
                                                    .load(imageUri)
                                                    .into(circleImageView);
                                            Toast.makeText(EditCurrentActivity.this, "Image should load", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
        }
        else
        {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
