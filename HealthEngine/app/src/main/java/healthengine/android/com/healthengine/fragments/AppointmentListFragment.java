package healthengine.android.com.healthengine.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.activities.AppointmentsActivity;
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



@EFragment(R.layout.fragment_appointment_list)
public class AppointmentListFragment extends BaseFragment {


    @ViewById
    ListView appointment_listview;



    AppointmentsCustomAdapter mAppointmentsCustomAdapter;


    @Click(R.id.appointment_date_layout)
    void callEditDateFragment(){
        Bundle bundle=new Bundle();
        bundle.putString(EditDateLocationAppointmentFragment.DATE_OR_LOCATION_KEY, EditDateLocationAppointmentFragment.DATE);
        EditDateLocationAppointmentFragment mFrag = EditDateLocationAppointmentFragment.newInstance();
        mFrag.setArguments(bundle);
        Utility.replaceAppointmentFragment(getActivity(), mFrag, true);
    }

    @Click(R.id.appointment_location_layout)
    void callEditLocationFragment(){
        Bundle bundle=new Bundle();
        bundle.putString(EditDateLocationAppointmentFragment.DATE_OR_LOCATION_KEY, EditDateLocationAppointmentFragment.LOCATION);

        EditDateLocationAppointmentFragment mFrag = EditDateLocationAppointmentFragment.newInstance();
        mFrag.setArguments(bundle);
        Utility.replaceAppointmentFragment(getActivity(), mFrag, true);
    }

    @AfterViews
    void setUIViews() {
        initToolbar();
        mAppointmentsCustomAdapter=new AppointmentsCustomAdapter();
        appointment_listview.setAdapter(mAppointmentsCustomAdapter);

    }
    @ItemClick(R.id.appointment_listview)
    public void callSelectAppointment(){
        Utility.replaceAppointmentFragment(getActivity(), SelectAppointmentFragment.newInstance(), true);
    }

    private void initToolbar() {
        setHasOptionsMenu(true);


        ((AppointmentsActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppointmentsActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    SearchView searchView;
    MenuItem myActionMenuItem;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate( R.menu.appointment_list_menu, menu);
        myActionMenuItem = menu.findItem( R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public class AppointmentsCustomAdapter extends BaseAdapter {

        LayoutInflater inflater;
        Context context;
        AppointmentsCustomAdapter(){
            inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        ViewHolder holder;
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            holder=null;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=inflater.inflate(R.layout.appointment_listitem, null);

                convertView.setTag(holder);

            }
            else{
                holder= (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

        class ViewHolder{

        }

    }

    public static AppointmentListFragment newInstance(){
        return new AppointmentListFragment_();
    }

}
