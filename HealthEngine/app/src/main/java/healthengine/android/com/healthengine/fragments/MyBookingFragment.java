package healthengine.android.com.healthengine.fragments;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

import healthengine.android.com.healthengine.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link MyBookingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Created by jsn.
 */
@EFragment(R.layout.fragment_my_booking)
public class MyBookingFragment extends BaseFragment {

    public static final String TAG="MyBookingFragment";

    @AfterViews
    void setUIViews() {
//        Model.getInstance().changeToolbarSettings(TAG);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MyBookingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyBookingFragment newInstance() {
        return new MyBookingFragment_();
    }


}
