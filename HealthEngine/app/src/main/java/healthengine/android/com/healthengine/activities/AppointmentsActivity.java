package healthengine.android.com.healthengine.activities;


import android.support.v7.widget.Toolbar;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.fragments.AppointmentListFragment;
import healthengine.android.com.healthengine.utils.Utility;


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

@EActivity(R.layout.activity_appointment)
public class AppointmentsActivity extends BaseActivity {

    public static String selectedPractitioner="Any";
    public static String selectedAppointmentType="General Practitioner";


    @ViewById
    Toolbar toolbar;

//    SearchView searchView;
//    MenuItem myActionMenuItem;



    @AfterViews
    void setUIViews() {

        setSupportActionBar(toolbar);
        toolbar.setTitle("Appointments");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Utility.replaceAppointmentFragment(this, AppointmentListFragment.newInstance(), false);




    }


//    @Override
//    public boolean onCreateOptionsMenu( Menu menu) {
//        getMenuInflater().inflate( R.menu.appointment_list_menu, menu);
//
//        myActionMenuItem = menu.findItem( R.id.action_search);
//        searchView = (SearchView) myActionMenuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if( ! searchView.isIconified()) {
//                    searchView.setIconified(true);
//                }
//                myActionMenuItem.collapseActionView();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
//                return false;
//            }
//        });
//        return true;
//    }



}
