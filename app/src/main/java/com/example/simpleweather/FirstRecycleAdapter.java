package com.example.simpleweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstRecycleAdapter extends RecyclerView.Adapter<FirstRecycleAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener;
    private String[] places;
    private String[] temps;

    public FirstRecycleAdapter() {
        WeatherInformer weatherInformer = WeatherInformer.getInstance();
        places = weatherInformer.getPlaces();
        temps = weatherInformer.getTemps();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView placeName;
        private final TextView placeTemp;

        public ViewHolder(View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.place_name);
            placeTemp = itemView.findViewById(R.id.place_temp);
            placeName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(view, getAdapterPosition());
                }
            });
            placeTemp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(view, getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public FirstRecycleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.place_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.placeName.setText(places[i]);
        viewHolder.placeTemp.setText(temps[i]);
    }

    @Override
    public int getItemCount() {
        return places.length;
    }

    @Override
    public int getItemViewType(int position) { return super.getItemViewType(position); }

    public interface OnItemClickListener { void onItemClick(View view, int position); }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void refreshData(){
        WeatherInformer weatherInformer = WeatherInformer.getInstance();
        places = weatherInformer.getPlaces();
        temps = weatherInformer.getTemps();
    }
}
