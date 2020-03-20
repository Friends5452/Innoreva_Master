package com.example.innorevamaster.currentEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.innorevamaster.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CurrentEvent> currentEventArrayList=new ArrayList<>();
    private static final String TAG = "MainActivity";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference currentEventDatabaseReference;
    private ChildEventListener mChildEventListener;
    private FloatingActionButton addFloatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        String currentEventPath=getResources().getString(R.string.current_event_path);
        currentEventDatabaseReference=mFirebaseDatabase.getReference().child(currentEventPath);
        addFloatingActionButton=findViewById(R.id.addCurrentEventfloatingActionButton);
        attachDatabaseReadListener();
        initRecyclerView();
        currentEventArrayList.add(new CurrentEvent("https://firebasestorage.googleapis.com/v0/b/innoreva-master.appspot.com/o/current_event_photos%2Fimage%3A1077142?alt=media&token=b7bd3418-c2e1-41f6-b8f0-4462d1045aae","coded"));

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.current_events_recycler_view);
        CurrentEventsAdapter adapter = new CurrentEventsAdapter(this,currentEventArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void attachDatabaseReadListener() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Toast.makeText(MainActivity.this, "The item will be added fast", Toast.LENGTH_SHORT).show();
                    CurrentEvent currentEvent = dataSnapshot.getValue(CurrentEvent.class);
                    currentEventArrayList.add(currentEvent);
                }
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {}
                public void onChildRemoved(DataSnapshot dataSnapshot) {}
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
                public void onCancelled(DatabaseError databaseError) {}
            };
            currentEventDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
}
