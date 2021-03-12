package com.niloy.varx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ChatAdapter extends
        RecyclerView.Adapter<ChatAdapter.ViewHolder>{
    Context context;
    ArrayList<String> talks;

    public ChatAdapter(Context context, ArrayList<String> assignments){
        this.context = context;
        this.talks = assignments;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ChatAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_adapter, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.talk.setText(talks.get(i));
    }

    @Override
    public int getItemCount() {
        return talks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView talk;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            talk = itemView.findViewById(R.id.talk);
        }

    }

}
