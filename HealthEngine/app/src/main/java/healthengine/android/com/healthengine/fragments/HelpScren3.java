package healthengine.android.com.healthengine.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.activities.LoginActivity;
import healthengine.android.com.healthengine.activities.SignupActivity;
import healthengine.android.com.healthengine.utils.Utility;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HelpScren3#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Created by jsn.
 */
public class HelpScren3 extends Fragment implements View.OnClickListener {
    public HelpScren3() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HelpScren1.
     */
    // TODO: Rename and change types and number of parameters
    public static HelpScren3 newInstance() {
        HelpScren3 fragment = new HelpScren3();
        return fragment;
    }

    TextView loginBtn, signupBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.layout_help_3, container, false);
        init(v);
        registerListeners();
        return v;
    }

    private void registerListeners() {
        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
    }

    private void init(View v) {
        loginBtn = (TextView) v.findViewById(R.id.login_btn);
        signupBtn = (TextView) v.findViewById(R.id.signup_btn);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                Bundle mBundle = new Bundle();
                mBundle.putBoolean("from_help", true);
                Utility.goToNextActivity(getActivity(), LoginActivity.class, mBundle, false);
            break;

            case R.id.signup_btn:
                startActivity(new Intent(getActivity(), SignupActivity.class));
                break;
        }
    }
}
