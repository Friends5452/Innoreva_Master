package com.example.innorevamaster.currentevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.innorevamaster.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditCurrentActivity extends AppCompatActivity {
    private static final String TAG = "EditCurrentActivity";
    private CircleImageView circleImageView;
    private FloatingActionButton floatingActionButton;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mCurrentDatabseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_current_events);
        firebaseDatabase=FirebaseDatabase.getInstance();
        mCurrentDatabseReference=firebaseDatabase.getReference().child("CurrentEvents");
        circleImageView=findViewById(R.id.event_circular_image);
        floatingActionButton=findViewById(R.id.floatingActionButton2);
        
    }
}
