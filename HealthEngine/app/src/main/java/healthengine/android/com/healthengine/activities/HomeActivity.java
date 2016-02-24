package healthengine.android.com.healthengine.activities;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.LogoutResponse;
import healthengine.android.com.healthengine.fragments.HomeFragment;
import healthengine.android.com.healthengine.fragments.MyBookingFragment;
import healthengine.android.com.healthengine.fragments.SettingsFragment;
import healthengine.android.com.healthengine.geofence.GeoFencConstants;
import healthengine.android.com.healthengine.geofence.GeoFencingController;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.utils.Utility;


@EActivity(R.layout.activity_home)
public class HomeActivity<T> extends BaseActivity implements Model.MyInter<T> {


    String[] tabs = {"HOME", "MY BOOKINGS", "SETTINGS"};

    String currentTab = "";


    //Toolbar
    @ViewById
    ImageView toolbar_logo_imageview;
    @ViewById
    RelativeLayout layout_inside_toolbar;
    @ViewById
    Button toolbar_back_button;
    @ViewById
    TextView toolbar_title_text, toolbar_right_text;

    @ViewById
    Toolbar toolbar;
    @ViewById
    RelativeLayout home_settings_tab;
    @ViewById
    RelativeLayout home_calendar_tab;
    @ViewById
    RelativeLayout home_doctor_tab;

    @ViewById
    ImageView home_doctor_image;
    @ViewById
    ImageView home_calendar_image;
    @ViewById
    ImageView home_settings_image;
    @ViewById
    View home_settings_view, home_calendar_view, home_doctor_view;

    GeoFencingController mGeoFencingController;

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Disconnect geofence service
//        mGeoFencingController.disconnectClient();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Connect geofence service
//        mGeoFencingController.connectClient();

        // add geo fence
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                setValueForGeoFence();
//            }
//
//        }, 2000);

        Model.getInstance().registerModel(this);
    }

    @AfterViews
    void setUIViews() {

        //initGeoFence();

        changeNavigationBarColor();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        selectDoctorTab();




    }


    private void initGeoFence() {
        try {
            mGeoFencingController = new GeoFencingController(this, new GeoFencingController.GeoFencingUpdateCallback() {
                @Override
                public void updateFencing(Boolean value) {

                    Toast.makeText(HomeActivity.this, "yes ok", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Click(R.id.home_doctor_tab)
    public void selectDoctorTab() {
        if (currentTab.equalsIgnoreCase(tabs[0])) {
            return;
        }
        currentTab = tabs[0];
        Utility.replaceFragment(this, HomeFragment.newInstance(), false);
        home_doctor_image.setImageResource(R.drawable.tabbar_doctor_sel);
        home_calendar_image.setImageResource(R.drawable.tabbar_calendar);
        home_settings_image.setImageResource(R.drawable.tabbar_settings);

        home_doctor_view.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        home_calendar_view.setBackgroundColor(Color.WHITE);
        home_settings_view.setBackgroundColor(Color.WHITE);


    }

    @Click(R.id.home_calendar_tab)
    public void selectCalendarTab() {

        if (currentTab.equalsIgnoreCase(tabs[1])) {
            return;
        }
        currentTab = tabs[1];
        Utility.replaceFragment(this, MyBookingFragment.newInstance(), false);

        home_doctor_image.setImageResource(R.drawable.tabbar_doctor);
        home_calendar_image.setImageResource(R.drawable.tabbar_calendar_sel);
        home_settings_image.setImageResource(R.drawable.tabbar_settings);

        home_doctor_view.setBackgroundColor(Color.WHITE);
        home_calendar_view.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
        home_settings_view.setBackgroundColor(Color.WHITE);
    }

    @Click(R.id.home_settings_tab)
    public void selectSettingsTab() {

        if (currentTab.equalsIgnoreCase(tabs[2])) {
            return;
        }
        currentTab = tabs[2];
        Utility.replaceFragment(this, SettingsFragment.newInstance(), false);

        home_doctor_image.setImageResource(R.drawable.tabbar_doctor);
        home_calendar_image.setImageResource(R.drawable.tabbar_calendar);
        home_settings_image.setImageResource(R.drawable.tabbar_settings_sel);

        home_doctor_view.setBackgroundColor(Color.WHITE);
        home_calendar_view.setBackgroundColor(Color.WHITE);
        home_settings_view.setBackgroundColor(getResources().getColor(R.color.color_toolbar));
    }


    public void changeNavigationBarColor() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_white));
        }
    }


    private void setValueForGeoFence() {

        try {
            GeoFencConstants.BAY_AREA_LANDMARKS.put("GeofenceName", new LatLng(Double.parseDouble("30.7398339"), Double.parseDouble("76.782702")));
            processStartForAddGeoFenc();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processStartForRemoveGeoFenc() {


        mGeoFencingController.removeGeofencesButtonHandler();

    }

    private void processStartForAddGeoFenc() {

        mGeoFencingController.addGeofencesButtonHandler();

    }


    @Override
    public void success(T t) {


        List<Fragment> fragmentList =  getSupportFragmentManager().getFragments();


        if((LogoutResponse)(((DataG)t).getType()) instanceof  LogoutResponse){

            if(fragmentList != null && fragmentList.size() > 0){
                SettingsFragment settingsFragment = (SettingsFragment) fragmentList.get(0);
                settingsFragment.onLogout();
            }
        }


    }

    @Override
    public void failure(T t) {

    }


}
