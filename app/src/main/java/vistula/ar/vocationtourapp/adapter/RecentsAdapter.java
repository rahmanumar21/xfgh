package vistula.ar.vocationtourapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vistula.ar.vocationtourapp.DetailActivity;
import vistula.ar.vocationtourapp.R;
import vistula.ar.vocationtourapp.model.RecentsData;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder> {

    Context context;
    List<RecentsData> recentsDataList;

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_item, parent, false);
        return new RecentsViewHolder(view);
    }

    public RecentsAdapter(Context context, List<RecentsData> recentsDataList) {
        this.context = context;
        this.recentsDataList = recentsDataList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        holder.placeImage.setImageResource(recentsDataList.get(position).getImageUrl());
        holder.placeName.setText(recentsDataList.get(position).getPlaceName());
        holder.countryName.setText(recentsDataList.get(position).getCountryName());
        holder.price.setText(recentsDataList.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return recentsDataList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder{
        ImageView placeImage;
        TextView placeName, countryName, price;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.placeImage);
            placeName = itemView.findViewById(R.id.placeName);
            countryName = itemView.findViewById(R.id.countryName);
            price = itemView.findViewById(R.id.price);
        }
    }
}
