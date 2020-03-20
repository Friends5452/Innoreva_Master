package com.example.innorevamaster.currentEvent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.current_event_card, parent, false);

        return new CurrentEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentEventViewHolder holder, int position) {

        holder.descriptionTextView.setText(currentEventsArrayList.get(position).eventDescriptionString);

    }

    @Override
    public int getItemCount() {
        return currentEventsArrayList.size();
    }

    public static class CurrentEventViewHolder extends RecyclerView.ViewHolder {
        CircleImageView eventImage;
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
