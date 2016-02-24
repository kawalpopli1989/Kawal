package healthengine.android.com.healthengine.activities;//package healthengine.android.com.healthengine.activities;
//
//import android.os.Build;
//import android.os.Bundle;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentStatePagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.Window;
//import android.view.WindowManager;
//
//import healthengine.android.com.healthengine.R;
//import healthengine.android.com.healthengine.fragments.HomeFragment;
//import healthengine.android.com.healthengine.fragments.MyBookingFragment;
//import healthengine.android.com.healthengine.fragments.SettingsFragment;
//import healthengine.android.com.healthengine.model.Model;
//
//public class HomeScreen extends AppCompatActivity implements TabLayout.OnTabSelectedListener, Model.MyInter {
//
//    private ViewPager main_viewpager;
//    TabLayout tab_layout;
//    PagerAdapter adapter;
//    Toolbar toolbar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_screen);
//        changeNavigationBarColor();
//        initUI();
//        setSupportActionBar(toolbar);
//        setTabs();
//        main_viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
//        tab_layout.setOnTabSelectedListener(this);
//        setViewPagerAdapter();
//    }
//
//    private void initUI() {
//        toolbar=(Toolbar)findViewById(R.id.toolbar);
//        toolbar.setTitle("");
//
//        tab_layout=(TabLayout)findViewById(R.id.tab_layout);
//        main_viewpager = (ViewPager) findViewById(R.id.main_viewpager);
//    }
//
//    public void setTabs(){
//        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.tabbar_doctor_sel));
//        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.tabbar_calendar));
//        tab_layout.addTab(tab_layout.newTab().setIcon(R.drawable.tabbar_settings));
//    }
//
//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        main_viewpager.setCurrentItem(tab.getPosition());
//        switch (tab.getPosition()) {
//            case 0:
//                selectTab("doctor", 0);
//                break;
//            case 1:
//                selectTab("calendar", 1);
//                break;
//            case 2:
//                selectTab("setting", 2);
//                break;
//        }
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//    }
//
//    @Override
//    public void success(Object o) {
//
//    }
//
//    @Override
//    public void failure(Object o) {
//
//    }
//
//
//    public class PagerAdapter extends FragmentStatePagerAdapter {
//
//        public PagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            switch (position){
//                case 0:
//                    return HomeFragment.newInstance();
//
//                case 1:
//                    return MyBookingFragment.newInstance();
//
//                case 2:
//                    return SettingsFragment.newInstance();
//            }
//
//            return null;
//        }
//
//        @Override
//        public int getItemPosition(Object object) {
//            return POSITION_NONE;
//        }
//
//        @Override
//        public int getCount() {
//            return 3;
//        }
//    }
//
//    public void selectTab(String tabName, int position){
//
//        tab_layout.getTabAt(0).setIcon(R.drawable.tabbar_doctor);
//        tab_layout.getTabAt(1).setIcon(R.drawable.tabbar_calendar);
//        tab_layout.getTabAt(2).setIcon(R.drawable.tabbar_settings);
//
//        switch (tabName){
//            case "doctor":
//                tab_layout.getTabAt(position).setIcon(R.drawable.tabbar_doctor_sel);
//                break;
//
//            case "calendar":
//                tab_layout.getTabAt(position).setIcon(R.drawable.tabbar_calendar_sel);
//                break;
//
//            case "setting":
//                tab_layout.getTabAt(position).setIcon(R.drawable.tabbar_settings_sel);
//                break;
//        }
//    }
//
//    public void setViewPagerAdapter(){
//        adapter = new PagerAdapter(getSupportFragmentManager());
//        main_viewpager.setAdapter(adapter);
//    }
//
//    public void changeNavigationBarColor(){
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.color_white));
//        }
//    }
//
//}
