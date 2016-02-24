package healthengine.android.com.healthengine.fragments;


import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.model.Model;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AppointmentPractionersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Created by jsn.
 */

@EFragment(R.layout.fragment_add_patient_profile)
public class AddPatientProfileFragment extends BaseFragment {

    public static final String TAG="AddPatientProfileFragment";

    @ViewById
    TextView fragment_patient_date_text;


    @AfterViews
    void setUIViews() {

//        Model.getInstance().changeToolbarSettings(TAG);

    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int year, int month, int day) {

            fragment_patient_date_text.setText(day+"-"+(month+1)+"-"+year);

        }
    };

    @Click(R.id.fragment_patient_date_text)
    public void openDateDialog(){
        Calendar cal= Calendar.getInstance();

        DatePickerDialog mDatePickerDialog=new DatePickerDialog(getActivity(), myDateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        mDatePickerDialog.show();

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AppointmentPractioners.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPatientProfileFragment newInstance() {
        return new AddPatientProfileFragment_();

    }


}
