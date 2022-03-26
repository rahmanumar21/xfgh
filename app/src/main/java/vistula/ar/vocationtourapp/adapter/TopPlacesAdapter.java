package vistula.ar.vocationtourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vistula.ar.vocationtourapp.R;
import vistula.ar.vocationtourapp.model.RecentsData;
import vistula.ar.vocationtourapp.model.TopPlacesData;

public class TopPlacesAdapter extends RecyclerView.Adapter<TopPlacesAdapter.TopPlacesViewHolder> {

    Context context;
    List<TopPlacesData> topPlacesDataList;

    @NonNull
    @Override
    public TopPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_places_row_item, parent, false);
        return new TopPlacesViewHolder(view);
    }

    public TopPlacesAdapter(Context context, List<TopPlacesData> topPlacesDataList) {
        this.context = context;
        this.topPlacesDataList = topPlacesDataList;
    }

    @Override
    public void onBindViewHolder(@NonNull TopPlacesViewHolder holder, int position) {
        holder.placeImage.setImageResource(topPlacesDataList.get(position).getImageUrl());
        holder.placeName.setText(topPlacesDataList.get(position).getPlaceName());
        holder.countryName.setText(topPlacesDataList.get(position).getCountryName());
        holder.price.setText(topPlacesDataList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return topPlacesDataList.size();
    }

    public static final class TopPlacesViewHolder extends RecyclerView.ViewHolder{
        ImageView placeImage;
        TextView placeName, countryName, price;

        public TopPlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.placeImage);
            placeName = itemView.findViewById(R.id.placeName);
            countryName = itemView.findViewById(R.id.countryName);
            price = itemView.findViewById(R.id.price);
        }
    }
}
