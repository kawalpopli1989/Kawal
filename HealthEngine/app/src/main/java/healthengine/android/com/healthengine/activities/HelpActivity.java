package healthengine.android.com.healthengine.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.fragments.HelpScren1;
import healthengine.android.com.healthengine.fragments.HelpScren2;
import healthengine.android.com.healthengine.fragments.HelpScren3;
import healthengine.android.com.healthengine.fragments.HelpScren4;
import healthengine.android.com.healthengine.viewpagerindicator.CirclePageIndicator;
import healthengine.android.com.healthengine.viewpagerindicator.PageIndicator;

/**
 * Created by jsn.
 */

public class HelpActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    ViewPager mPager;
    PageIndicator mIndicator;
    TextView mSkipBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_help);
        init();
        mPager.setAdapter(new ScreenSlidePagerAdapter(getSupportFragmentManager()));
        mPager.addOnPageChangeListener(this);
        mIndicator.setViewPager(mPager);
        registerListener();

        changeNavigationBarColor(0);
    }

    private void init() {
        mPager = (ViewPager) findViewById(R.id.pager);
        mIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mSkipBtn = (TextView) findViewById(R.id.btn_skip);
    }

    private void registerListener() {
        mSkipBtn.setOnClickListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {

        if(position < 3){
            changeNavigationBarColor(position);
        }

        switch (position){
            case 0:
                mSkipBtn.setText(R.string.skip_text);
                break;

            case 1:
                mSkipBtn.setText(R.string.skip_text);
                break;

            case 2:
                mSkipBtn.setText(R.string.start_text);
                break;

            case 3:
                goToNextScreen();
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onClick(View view) {
        goToNextScreen();
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return HelpScren1.newInstance();

                case 1:
                    return HelpScren2.newInstance();

                case 2:
                    return HelpScren3.newInstance();

                case 3:
                    return HelpScren4.newInstance();
            }
            return null;
        }


        @Override
        public int getCount() {
            return 4;
        }
    }

    private void goToNextScreen() {
        startActivity(new Intent(this, HomeActivity_.class));
        finish();
    }

    public void changeNavigationBarColor(int position){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            switch (position) {
                case 0:
                    window.setStatusBarColor(getResources().getColor(R.color.color_end_1));
                    break;
                case 1:
                    window.setStatusBarColor(getResources().getColor(R.color.color_end_2));
                    break;
                case 2:
                    window.setStatusBarColor(getResources().getColor(R.color.color_end_3));
                    break;
            }

        }
    }
}