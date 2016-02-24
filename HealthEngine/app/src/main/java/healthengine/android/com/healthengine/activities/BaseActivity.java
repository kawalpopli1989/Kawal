package healthengine.android.com.healthengine.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Created by jsn.
 */
public class BaseActivity extends AppCompatActivity {

    protected Gson mGson = new Gson();
    protected Handler mHandler = null;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    protected void showDialog(String message) {
        if (mProgressDialog == null) {
            setProgressDialog(message);
        }
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void setProgressDialog(String message) {
        mProgressDialog = new ProgressDialog(BaseActivity.this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(message);
    }

    protected void showToast(String message){
        Toast.makeText(BaseActivity.this,message, Toast.LENGTH_SHORT).show();
    }


}
