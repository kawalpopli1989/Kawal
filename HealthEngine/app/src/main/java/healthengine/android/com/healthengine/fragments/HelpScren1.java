package healthengine.android.com.healthengine.fragments;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import healthengine.android.com.healthengine.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpScren1#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Created by jsn.
 */
public class HelpScren1 extends Fragment {

    public HelpScren1() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HelpScren1.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpScren1 newInstance() {
        HelpScren1 fragment = new HelpScren1();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_help_1, container, false);
        return v;
    }

}
