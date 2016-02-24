package healthengine.android.com.healthengine.fragments;

import android.support.v4.app.Fragment;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.activities.AppointmentsActivity;
import healthengine.android.com.healthengine.utils.Utility;

/**
 * Created by kawal on 27/1/16.
 */
@EFragment(R.layout.fragment_book_appointment)
public class BookAppointmentFragment extends Fragment{

    @ViewById
    TextView select_appointment_type_text;

    @AfterViews
    void setUIViews() {
        select_appointment_type_text.setText(AppointmentsActivity.selectedAppointmentType);
    }

    @Click(R.id.continue_booking_textview)
    public void continueClick(){
        Utility.replaceAppointmentFragment(getActivity(), PracticeInformationFragment.newInstance(), true);
    }

    @Click(R.id.select_appointment_type_text)
    public void callSelectAppointmentTypeFragment(){
        Utility.replaceAppointmentFragment(getActivity(), SelectAppointmentTypeFragment.newInstance(), true);
    }


    // TODO: Rename and change types and number of parameters
    public static BookAppointmentFragment newInstance() {
        return new BookAppointmentFragment_();

    }

}
