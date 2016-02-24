package healthengine.android.com.healthengine.fragments;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.utils.Utility;

/**
 * Created by kawal on 27/1/16.
 */
@EFragment(R.layout.fragment_practice_information)
public class PracticeInformationFragment extends Fragment{


    @AfterViews
    void setUIViews() {
    }

    @Click(R.id.continue_booking_textview)
    public void continueClick(){
        Utility.replaceAppointmentFragment(getActivity(), BookPatientProfileFragment.newInstance(), true);
    }


    // TODO: Rename and change types and number of parameters
    public static PracticeInformationFragment newInstance() {
        return new PracticeInformationFragment_();

    }

}
