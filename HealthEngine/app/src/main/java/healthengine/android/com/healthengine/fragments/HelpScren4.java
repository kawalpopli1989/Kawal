package healthengine.android.com.healthengine.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import healthengine.android.com.healthengine.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpScren4#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Created by jsn.
 */
public class HelpScren4 extends Fragment {
    public HelpScren4() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HelpScren1.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpScren4 newInstance() {
        HelpScren4 fragment = new HelpScren4();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_blank, container, false);
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
