package com.example.innorevamaster.currentEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.innorevamaster.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CurrentEvent> currentEventArrayList;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLoader();
    }
    private void initLoader(){
        CurrentEvent a=new CurrentEvent("https://firebasestorage.googleapis.com/v0/b/innoreva-master.appspot.com/o/current_event_photos%2Fimage%3A1125334?alt=media&token=9c4e49cb-241d-4a26-93d0-c62b8b2c5590","hello");
        currentEventArrayList.add(a);
        currentEventArrayList.add(a);
        currentEventArrayList.add(a);
        currentEventArrayList.add(a);
        currentEventArrayList.add(a);
        initRecyclerView();
    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.current_events_recycler_view);
        CurrentEventsAdapter adapter = new CurrentEventsAdapter(this,currentEventArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
