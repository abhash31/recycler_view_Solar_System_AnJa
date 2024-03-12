package com.example.solarsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanetRecyclerAdapter extends RecyclerView.Adapter<PlanetRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<PlanetRecyclerModel> list;
    SelectRecyclerItems listener;
    PlanetRecyclerAdapter(Context context, ArrayList<PlanetRecyclerModel> list, SelectRecyclerItems listener){
        this.context = context;
        this.list = list;
        this.listener = listener;
    }
    @NonNull
    @Override
    public PlanetRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.planet_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetRecyclerAdapter.ViewHolder holder, int position) {
        holder.planetName.setText(list.get(position).planetName);
        holder.planetAbout.setText(list.get(position).planetAbout);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView planetName;
        TextView planetAbout;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            planetName = itemView.findViewById(R.id.planetName);
            planetAbout = itemView.findViewById(R.id.planetAbout);
            cardView = itemView.findViewById(R.id.planerContainer);
        }
    }

    public void filterAdapter(ArrayList<PlanetRecyclerModel> filterdList){
        list = filterdList;
        notifyDataSetChanged();
    }
}
