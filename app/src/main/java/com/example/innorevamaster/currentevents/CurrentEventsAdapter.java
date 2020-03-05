package com.example.innorevamaster.currentevents;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.innorevamaster.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CurrentEventsAdapter extends RecyclerView.Adapter<CurrentEventsAdapter.CurrentEventViewHolder> {

    private ArrayList<CurrentEvent> currentEventsArrayList;
    private Context context;

    public CurrentEventsAdapter(Context context, ArrayList<CurrentEvent> currentEventsArrayList) {
        this.context=context;
        this.currentEventsArrayList=currentEventsArrayList;
    }

    @NonNull
    @Override
    public CurrentEventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentEventViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return currentEventsArrayList.size();
    }

    public static class CurrentEventViewHolder extends RecyclerView.ViewHolder {
        CircleImageView eventImage;
        TextView descriptionTextView;

        public CurrentEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventImage=itemView.findViewById(R.id.current_event_image);
            descriptionTextView=itemView.findViewById(R.id.current_event_description);
        }
    }
}
