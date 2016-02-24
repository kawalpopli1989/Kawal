package healthengine.android.com.healthengine.activities;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import healthengine.android.com.healthengine.R;


/**
 * Created by jsn.
 */
@EActivity(R.layout.activity_practice_search)
public class PracticeSearchActivity extends BaseActivity {


    @Click(R.id.cancel_action)
    public void finishActivity(){
        finish();
    }

}
