package healthengine.android.com.healthengine.fragments;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import healthengine.android.com.healthengine.R;

/**
 * Created by kawal on 27/1/16.
 */
@EFragment(R.layout.fragment_booking_add_or_edit_patient_profile)
public class BookAddOrEditPatientProfileFragment extends Fragment{


    @AfterViews
    void setUIViews() {
    }



    // TODO: Rename and change types and number of parameters
    public static BookAddOrEditPatientProfileFragment newInstance() {
        return new BookAddOrEditPatientProfileFragment_();

    }

}
