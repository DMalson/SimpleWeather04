package com.example.simpleweather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondRecycleAdapter extends RecyclerView.Adapter<SecondRecycleAdapter.ViewHolder> {
    private FirstRecycleAdapter.OnItemClickListener itemClickListener;
    private String[] arrData;
    private String[] arrTemp;
    private int[] arrPrecip;
    private int[] precipPictures;

    public SecondRecycleAdapter (String place,int[] precipPictures){
        WeatherInformer weatherInformer= WeatherInformer.getInstance();
        PlaceWeather placeWeather=weatherInformer.getPlaceWeather(place);
        arrData=placeWeather.getDatas();
        arrTemp=placeWeather.getTemps();
        arrPrecip=placeWeather.getPrecip();
        this.precipPictures=precipPictures;
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private final TextView cardData;
        private final TextView cardTemp;
        private final ImageView cardImage;

        public ViewHolder (View itemView){
            super(itemView);
            cardData = itemView.findViewById(R.id.cardData);
            cardTemp=itemView.findViewById(R.id.cardTemp);
            cardImage=itemView.findViewById(R.id.cardImage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
        // Создаём новый элемент пользовательского интерфейса через Inflater
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_card, parent, false);
        // Здесь можно установить параметры
        SecondRecycleAdapter.ViewHolder viewHolder = new SecondRecycleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.cardImage.setImageResource(precipPictures[arrPrecip[i]]);
            viewHolder.cardData.setText(arrData[i]);
            viewHolder.cardTemp.setText(arrTemp[i]);
    }

    @Override
    public int getItemCount() {
        return arrData.length;
    }
}
