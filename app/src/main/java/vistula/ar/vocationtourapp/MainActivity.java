package vistula.ar.vocationtourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import vistula.ar.vocationtourapp.adapter.RecentsAdapter;
import vistula.ar.vocationtourapp.adapter.TopPlacesAdapter;
import vistula.ar.vocationtourapp.adapter.TravelLocationAdapter;
import vistula.ar.vocationtourapp.model.RecentsData;
import vistula.ar.vocationtourapp.model.TopPlacesData;
import vistula.ar.vocationtourapp.model.TravelLocationData;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, recentRecycler2, topPlaceRecycler;
    RecentsAdapter recentsAdapter, recentsAdapter2;
    TopPlacesAdapter topPlacesAdapter;
    ViewPager2 locatiosViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_activity);

        locatiosViewPager = findViewById(R.id.locationViewPager);
        List<TravelLocationData> travelLocationDataList = new ArrayList<>();

        TravelLocationData travelLocationEiffelTower = new TravelLocationData();
        travelLocationEiffelTower.imageUrl = "https://www.planetware.com/photos-large/F/eiffel-tower.jpg";
        travelLocationEiffelTower.title = "France";
        travelLocationEiffelTower.location = "Eiffel Tower";
        travelLocationEiffelTower.starRating = 4.8f;
        travelLocationDataList.add(travelLocationEiffelTower);

        TravelLocationData travelLocationTajmahal = new TravelLocationData();
        travelLocationTajmahal.imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/74/Taj_Mahal%2C_Agra%2C_India_edit2.jpg";
        travelLocationTajmahal.title = "India";
        travelLocationTajmahal.location = "Taj Mahal";
        travelLocationTajmahal.starRating = 4.3f;
        travelLocationDataList.add(travelLocationTajmahal);

        TravelLocationData travelLocationMountainView = new TravelLocationData();
        travelLocationMountainView.imageUrl = "https://www.goodnewsfromindonesia.id/uploads/post/large-wisata-banyuwangi-1280x768-4620a484ed5928607bb8489f007763f7.jpg";
        travelLocationMountainView.title = "Indonesia";
        travelLocationMountainView.location = "Mountain View";
        travelLocationMountainView.starRating = 4.5f;
        travelLocationDataList.add(travelLocationMountainView);

        locatiosViewPager.setAdapter(new TravelLocationAdapter(travelLocationDataList));
        locatiosViewPager.setClipToPadding(false);
        locatiosViewPager.setClipChildren(false);
        locatiosViewPager.setOffscreenPageLimit(5);
        locatiosViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);
            }
        });

        locatiosViewPager.setPageTransformer(compositePageTransformer);


        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("AM Lake","India", "$200", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Sumbawa Hils", "Indonesa", "$400", R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("Lombok Hils", "Indonesia", "$300", R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Bali Lake", "Indonesia", "$600", R.drawable.recentimage2));
        recentsDataList.add(new RecentsData("Warsaw Lake", "Polandia", "$800", R.drawable.recentimage1));

        setRecentsRecycler(recentsDataList);

        List<RecentsData> recentsDataList2 = new ArrayList<>();
        recentsDataList2.add(new RecentsData("AM Lake","India", "$200", R.drawable.recentimage1));
        recentsDataList2.add(new RecentsData("Sumbawa Hils", "Indonesa", "$400", R.drawable.recentimage2));
        recentsDataList2.add(new RecentsData("Lombok Hils", "Indonesia", "$300", R.drawable.recentimage1));
        recentsDataList2.add(new RecentsData("Bali Lake", "Indonesia", "$600", R.drawable.recentimage2));
        recentsDataList2.add(new RecentsData("Warsaw Lake", "Polandia", "$800", R.drawable.recentimage1));

        setRecentsRecycler2(recentsDataList2);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Sumbawa Hils", "Indonesia", "$200", R.drawable.recentimage2));
        topPlacesDataList.add(new TopPlacesData("Bima Lake","Indonesia", "$500", R.drawable.recentimage1));
        topPlacesDataList.add(new TopPlacesData("Moyo Island", "Indonesia", "$1000", R.drawable.recentimage2));
        topPlacesDataList.add(new TopPlacesData("Tana Samawa", "Indonesia", "$210",  R.drawable.recentimage1));

        setTopPlaceRecycler(topPlacesDataList);
    }

    private void setRecentsRecycler(List<RecentsData> recentsDataList){
        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private void setRecentsRecycler2(List<RecentsData> recentsDataList2){
        recentRecycler2 = findViewById(R.id.recent_recycler2);
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        recentRecycler2.setLayoutManager(layoutManager2);
        recentsAdapter2 = new RecentsAdapter(this, recentsDataList2);
        recentRecycler2.setAdapter(recentsAdapter2);

    }

    private void setTopPlaceRecycler(List<TopPlacesData> topPlacesDataList){
        topPlaceRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL , false);

        topPlaceRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlaceRecycler.setAdapter(topPlacesAdapter);

    }

}