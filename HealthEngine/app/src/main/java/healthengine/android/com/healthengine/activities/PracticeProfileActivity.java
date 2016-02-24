package healthengine.android.com.healthengine.activities;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.viewpagerindicator.CirclePageIndicator;

/**
 * Created by kawal on 27/1/16.
 */
public class PracticeProfileActivity extends AppCompatActivity {

//    CollapsingToolbarLayout collapsing_toolbar;
    RecyclerView practice_profile_recycler_view;
    CirclePageIndicator practice_profile_viewpager_indicator;

    private GoogleMap googleMap;

    ViewPager practice_profile_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_profile_screen);

        initUI();




    }

    private void initUI() {

        practice_profile_viewpager_indicator=(CirclePageIndicator)findViewById(R.id.practice_profile_viewpager_indicator);

//        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        practice_profile_recycler_view=(RecyclerView)findViewById(R.id.practice_profile_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        practice_profile_recycler_view.setLayoutManager(layoutManager);
        CustomRecyclerAdaptor mCustomRecyclerAdaptor=new CustomRecyclerAdaptor();
        practice_profile_recycler_view.setAdapter(mCustomRecyclerAdaptor);



        practice_profile_viewpager=(ViewPager)findViewById(R.id.practice_profile_viewpager);

        MyPagerAdapter mMyPagerAdapter=new MyPagerAdapter();
        practice_profile_viewpager.setAdapter(mMyPagerAdapter);
        practice_profile_viewpager_indicator.setViewPager(practice_profile_viewpager);
        try {
            if (googleMap == null) {
                googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

                mapSetup();

            }


        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to load map", Toast.LENGTH_SHORT).show();
        }

    }

    public class CustomRecyclerAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case

            TextView practice_recycler_text;

            public ViewHolder(View v) {
                super(v);
                practice_recycler_text=(TextView)v.findViewById(R.id.practice_recycler_text);

            }
        }







        // Create new views (invoked by the layout manager)
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {


            View v= LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.practice_profile_recycleritem, parent, false);
            return new ViewHolder(v);

        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder( RecyclerView.ViewHolder viewHolder, final int pos) {
            ViewHolder holder=(ViewHolder)viewHolder;






        }

        @Override
        public int getItemCount() {
            return 20;
        }


        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }





    }


    private void mapSetup() {
        if (googleMap != null) {

            double latitude = -36.993922;
            double longitude = 174.883074;
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setAllGesturesEnabled(true);
            googleMap.getUiSettings().setTiltGesturesEnabled(true);
            googleMap.getUiSettings().setZoomGesturesEnabled(true);
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude, longitude)).zoom(12)
                    .tilt(25)
                    .build();

            googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));//newLatLngZoom(new LatLng(latitude, longitude), 15));



        }

    }



    private class MyPagerAdapter extends PagerAdapter {

        int NumberOfPages = 5;



        @Override
        public int getCount() {
            return NumberOfPages;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {



            ImageView imageView = new ImageView(PracticeProfileActivity.this);
            imageView.setImageResource(R.drawable.image);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView)object);
        }

    }


}

