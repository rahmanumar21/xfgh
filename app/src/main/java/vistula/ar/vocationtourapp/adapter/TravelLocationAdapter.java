package vistula.ar.vocationtourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.squareup.picasso.Picasso;

import java.util.List;

import vistula.ar.vocationtourapp.R;
import vistula.ar.vocationtourapp.model.TopPlacesData;
import vistula.ar.vocationtourapp.model.TravelLocationData;

public class TravelLocationAdapter extends RecyclerView.Adapter<TravelLocationAdapter.TravelLocationViewHolder> {

    private List<TravelLocationData> travelLocationDataList;

    public TravelLocationAdapter(List<TravelLocationData> travelLocationDataList) {
        this.travelLocationDataList = travelLocationDataList;
    }

    @NonNull
    @Override
    public TravelLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_location, parent, false);
        return new TravelLocationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelLocationViewHolder holder, int position) {
        holder.setLocationData(travelLocationDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return travelLocationDataList.size();
    }

    static class TravelLocationViewHolder extends RecyclerView.ViewHolder {

        private KenBurnsView kbvLocation;
        private TextView textTitle, textLocation, textStartRating;

        public TravelLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            kbvLocation = itemView.findViewById(R.id.kbvLocation);
            textTitle = itemView.findViewById(R.id.textTitle);
            textLocation = itemView.findViewById(R.id.textLocation);
            textStartRating = itemView.findViewById(R.id.textStarRating);
        }

        void setLocationData(TravelLocationData travelLocationData) {
            Picasso.get().load(travelLocationData.imageUrl).into(kbvLocation);
            textTitle.setText(travelLocationData.title);
            textLocation.setText(travelLocationData.location);
            textStartRating.setText(String.valueOf(travelLocationData.starRating));
        }
    }
}
