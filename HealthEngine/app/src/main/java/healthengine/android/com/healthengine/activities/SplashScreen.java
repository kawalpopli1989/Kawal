package healthengine.android.com.healthengine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import healthengine.android.com.healthengine.R;

/**
 * Created by jsn.
 */
public class SplashScreen extends AppCompatActivity {

    Handler mHandler = null;

    Toolbar toolbar;
    Window mWindow = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        mHandler = new Handler();

//        GetUserLocation.getInstance().getUserLatLong(this);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, HelpActivity.class));
                finish();
            }
        }, 2 * 100);
    }
}
