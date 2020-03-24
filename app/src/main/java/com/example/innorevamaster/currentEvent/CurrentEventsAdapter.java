package com.example.innorevamaster.currentEvent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.innorevamaster.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurrentEventsAdapter extends RecyclerView.Adapter<CurrentEventsAdapter.CurrentEventViewHolder> {

    private ArrayList<CurrentEvent> currentEventsArrayList;
    private Context context;
    private static final String TAG = "CurrentEventsAdapter";
    private DatabaseReference databasetoDeleteReference;
    private StorageReference storageDeleteReference;
    public CurrentEventsAdapter(Context context, ArrayList<CurrentEvent> currentEventsArrayList) {
        this.context=context;
        this.currentEventsArrayList=currentEventsArrayList;
    }

    @NonNull
    @Override
    public CurrentEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_event_card, parent, false);
        return new CurrentEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentEventViewHolder holder, final int position) {

        holder.descriptionTextView.setText(currentEventsArrayList.get(position).getEventDescriptionString());
        Glide.with(context)
                .asBitmap()
                .placeholder(R.drawable.loadinggif)
                .load(currentEventsArrayList.get(position).getEventImageUrl())
                .into(holder.eventImage);
       // Toast.makeText(context, currentEventsArrayList.get(position).toString(), Toast.LENGTH_SHORT).show();
        holder.eventCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(position);
            }
        });
    }
    public  void remove(int position)
    {
        Toast.makeText(context, "Item is being removed", Toast.LENGTH_SHORT).show();
        String key=currentEventsArrayList.get(position).getEventKey();
        databasetoDeleteReference=FirebaseDatabase.getInstance().getReference().child("CurrentEvents/"+key);
        storageDeleteReference= FirebaseStorage.getInstance().getReferenceFromUrl(currentEventsArrayList.get(position).getEventImageUrl());
        storageDeleteReference.delete();
        Toast.makeText(context, databasetoDeleteReference.toString(), Toast.LENGTH_SHORT).show();
        databasetoDeleteReference.removeValue();
        currentEventsArrayList.remove(position);
        MainActivity.adapter.notifyItemRemoved(position);

    }
    @Override
    public int getItemCount() {
        return currentEventsArrayList.size();
    }

    public static class CurrentEventViewHolder extends RecyclerView.ViewHolder {
        ImageView eventImage;
        TextView descriptionTextView;
        CardView eventCardView;

        public CurrentEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventCardView=itemView.findViewById(R.id.event_card_view);
            eventImage=itemView.findViewById(R.id.current_event_image);
            descriptionTextView=itemView.findViewById(R.id.current_event_description);
        }
    }
}
