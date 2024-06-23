package com.example.journalapp;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context context;
    List<Journal> journalList;
    public MyAdapter(Context context, List<Journal> journalList) {
        this.context = context;
        this.journalList = journalList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.journal_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Journal currentJournal=journalList.get(position);
        holder.title.setText(currentJournal.getTitle());
        holder.thoughts.setText(currentJournal.getThoughts());
        holder.name.setText(currentJournal.getUserName());
        String imageUrl=currentJournal.getImageUrl();
        String timeAgo=(String) DateUtils.getRelativeTimeSpanString(currentJournal.getTimeAdded().getSeconds()*1000);
        holder.dateAdded.setText(timeAgo);
//      we will be using glide library to display Images :
        Glide.with(context)
                .load(imageUrl)
                .fitCenter()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return journalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,thoughts,dateAdded,name;
        public ImageView image,shareBtn;
        public String userId,username;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.journal_title_list);
            thoughts = itemView.findViewById(R.id.journal_thought_list);
            dateAdded = itemView.findViewById(R.id.journal_timestamp_list);
            name = itemView.findViewById(R.id.journal_row_username);
            image = itemView.findViewById(R.id.journal_image_list);
            shareBtn = itemView.findViewById(R.id.journal_row_share_button);
            shareBtn.setOnClickListener(v->{
//                On click :
//                Sharing the post :

            });
        }
    }
}
