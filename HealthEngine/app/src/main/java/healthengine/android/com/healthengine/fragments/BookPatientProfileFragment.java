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
@EFragment(R.layout.fragment_book_pateint_profile)
public class BookPatientProfileFragment extends Fragment{


    @AfterViews
    void setUIViews() {

    }

    @Click({R.id.book_create_new_patient_profile_text, R.id.book_edit_patient_profile_text})
    public void addPatientProfile(){
        Utility.replaceAppointmentFragment(getActivity(), BookAddOrEditPatientProfileFragment.newInstance(), true);
    }

    @Click(R.id.continue_booking_textview)
    public void countinueBooking(){
        Utility.replaceAppointmentFragment(getActivity(), HealthInsuranceFragment.newInstance(), true);
    }

    // TODO: Rename and change types and number of parameters
    public static BookPatientProfileFragment newInstance() {
        return new BookPatientProfileFragment_();

    }

}
