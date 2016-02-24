package healthengine.android.com.healthengine.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.activities.AboutActivity;
import healthengine.android.com.healthengine.activities.LoginActivity;
import healthengine.android.com.healthengine.activities.PatientProfileActivity_;
import healthengine.android.com.healthengine.adapter.ProfileAdapter;
import healthengine.android.com.healthengine.apicalls.ApiCall;
import healthengine.android.com.healthengine.apicalls.DeleteProfile;
import healthengine.android.com.healthengine.apicalls.LogoutApi;
import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.LoginResponse;
import healthengine.android.com.healthengine.data.MemberDetail;
import healthengine.android.com.healthengine.data.Token;
import healthengine.android.com.healthengine.swipemenu.SwipeMenu;
import healthengine.android.com.healthengine.swipemenu.SwipeMenuCreator;
import healthengine.android.com.healthengine.swipemenu.SwipeMenuItem;
import healthengine.android.com.healthengine.swipemenu.SwipeMenuListView;
import healthengine.android.com.healthengine.utils.Utility;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by jsn.
 */
@EFragment(R.layout.fragment_setting)
public class SettingsFragment extends BaseFragment implements View.OnClickListener{

    public static final String TAG = "SettingsFragment";

    @ViewById
    LinearLayout fragment_settings_about_layout;

    @ViewById
    TextView login_text, logout_text, userName;

    @ViewById
    LinearLayout layout_when_login;

/*    @ViewById
    TableLayout profileTable;*/

    @ViewById
    SwipeMenuListView profileListView;

    ProfileAdapter mProfileAdapter = null;

    private List<MemberDetail> memberDetailList = null;

    Handler mHandler = null;
    @AfterViews
    void setUIViews() {
        mHandler  =new Handler();
        mProfileAdapter = new ProfileAdapter(getActivity());
        setUpSwipeOverListView(profileListView);
    }

    @Override
    public void onResume() {
        super.onResume();
        checkForLogin();
    }

    private void checkForLogin() {
        if (PrefManager.getInstance(getActivity()).getToken() == null) {
            login_text.setVisibility(View.VISIBLE);
            layout_when_login.setVisibility(View.GONE);
        } else {
            login_text.setVisibility(View.GONE);
            layout_when_login.setVisibility(View.VISIBLE);

            String userProfile = PrefManager.getInstance(getActivity()).getLoginData();

            if (userProfile != null) {
                LoginResponse mLoginResponse = mGson.fromJson(userProfile, LoginResponse.class);
                userName.setText(mLoginResponse.getEmail());

                String memberDetail = PrefManager.getInstance(getActivity()).getPatientProfiles();


                if (memberDetail != null) {
                    Type listType = new TypeToken<ArrayList<MemberDetail>>() {
                    }.getType();
                    memberDetailList = mGson.fromJson(memberDetail, listType);
                }


                if (memberDetailList != null && memberDetailList.size() > 0) {

                        mProfileAdapter.addAll(memberDetailList);
                    profileListView.setAdapter(mProfileAdapter);


//                    profileTable.removeAllViews();
//
//                    for (int i = 0; i < memberDetailList.size(); i++) {
//                        View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_member_profile, null);
//                        TextView mName = (TextView) v.findViewById(R.id.name);
//                        mName.setText(memberDetailList.get(i).getFirstname() + " " + memberDetailList.get(i).getLastname());
//                        TableRow tr = new TableRow(getActivity());
//                        tr.addView(v);
//                        tr.setTag(i);
//                        tr.setOnClickListener(this);
//                        profileTable.addView(tr);
//                        profileTable.requestLayout();
//                    }
//
//                    addNewProfileRow();
                } else {
//                    addNewProfileRow();
                }


            } else {

            }


        }
    }

//    private void addNewProfileRow() {
//        View v = LayoutInflater.from(getActivity()).inflate(R.layout.layout_member_profile, null);
//        TextView mName = (TextView) v.findViewById(R.id.name);
//        mName.setText("Add New Profile");
//        mName.setTextColor(getResources().getColor(R.color.color_toolbar));
//        TableRow tr = new TableRow(getActivity());
//        tr.addView(v);
//        tr.setTag(-1);
//        tr.setOnClickListener(this);
//        profileTable.addView(tr);
//        profileTable.requestLayout();
//    }


    @Click(R.id.login_text)
    public void login() {
        Utility.goToNextActivity(getActivity(), LoginActivity.class, null, false);
    }

    public void addNewProfile() {
        Utility.goToNextActivity(getActivity(), PatientProfileActivity_.class, null, false);
//        Utility.pushFragment(AddPatientProfileFragment.newInstance(), true, getActivity());

    }


    @Click(R.id.logout_text)
    public void logout() {


        showDialog("Logging out...");

        Token mToken = new Token(PrefManager.getInstance(getActivity()).getToken());
        DataG<Token> mObj = new DataG<>(mToken);

        ApiCall<LogoutApi> mCall = new ApiCall<>(new LogoutApi());
        mCall.doCall(mObj, getActivity());

//        PrefManager.getInstance(getActivity()).saveToken(null);
//        login_text.setVisibility(View.VISIBLE);
//        layout_when_login.setVisibility(View.GONE);

    }

    @Click(R.id.fragment_settings_about_layout)
    public void callAbout() {

        Utility.goToNextActivity(getActivity(), AboutActivity.class, null, false);

//        Utility.pushFragment(AboutFragment.newInstance(), true, getActivity());

    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment_();
    }


    @Override
    public void onClick(View view) {
        int position = (int) view.getTag();

        if (position == -1) {
            Utility.goToNextActivity(getActivity(), PatientProfileActivity_.class, null, false);
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("obj", memberDetailList.get(position));
            Utility.goToNextActivity(getActivity(), PatientProfileActivity_.class, bundle, false);
        }
    }



    public void onLogout(){

           mHandler.postDelayed(new Runnable() {
               @Override
               public void run() {
                   checkForLogin();
                   hideDialog();
               }
           },0);
       }


    private void setUpSwipeOverListView(final SwipeMenuListView mListView){

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity().getApplicationContext());
                deleteItem.setWidth(Utility.dpToPx(90));

                // set a icon
                deleteItem.setIcon(R.drawable.delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mListView.setMenuCreator(creator);


        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu,
                                           int index) {

                MemberDetail mMemberDetail= (MemberDetail) mListView.getAdapter().getItem(position);

               showDialog("Deleting Profile...");
                DataG<MemberDetail> mObj = new DataG<>(mMemberDetail);
                ApiCall<DeleteProfile> mCall = new ApiCall<DeleteProfile>(new DeleteProfile());
                mCall.doCall(mObj, getActivity());

                return false;
            }
        });

    }

}
