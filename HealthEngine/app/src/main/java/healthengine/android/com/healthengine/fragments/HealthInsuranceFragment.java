package healthengine.android.com.healthengine.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import healthengine.android.com.healthengine.R;

/**
 * Created by kawal on 27/1/16.
 */
@EFragment(R.layout.fragment_health_insurance)
public class HealthInsuranceFragment extends Fragment{

    @ViewById
    TextView health_insurance_yes_text, health_insurance_no_text;

    @ViewById
    LinearLayout free_health_insurance_layout;


    @AfterViews
    void setUIViews() {

    }

    @Click(R.id.health_insurance_no_text)
    public void onNoClick(){
        health_insurance_no_text.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.arrow_right_black), null);
        health_insurance_yes_text.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        free_health_insurance_layout.setVisibility(View.GONE);
    }
    @Click(R.id.health_insurance_yes_text)
    public void onYesClick(){
        health_insurance_yes_text.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.arrow_right_black), null);
        health_insurance_no_text.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        free_health_insurance_layout.setVisibility(View.VISIBLE);
    }



    // TODO: Rename and change types and number of parameters
    public static HealthInsuranceFragment newInstance() {
        return new HealthInsuranceFragment_();

    }

}
