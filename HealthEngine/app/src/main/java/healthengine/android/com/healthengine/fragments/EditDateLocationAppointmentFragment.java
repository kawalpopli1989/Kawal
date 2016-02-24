package healthengine.android.com.healthengine.fragments;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import healthengine.android.com.healthengine.R;


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



@EFragment(R.layout.fragment_edit_datelocation_appointment)
public class EditDateLocationAppointmentFragment extends BaseFragment {

    public static String DATE_OR_LOCATION_KEY="date_or_location";
    public static String DATE="date";
    public static String LOCATION="location";

    @ViewById
    ListView edit_datelocation_listview;
    @ViewById
    LinearLayout edit_location_layout;
    @ViewById
    MaterialCalendarView calendarView;

    @ViewById
    EditText date_edittext, location_edittext;

    EditLocationCustomAdapter adapter;
    ArrayList<String> locations=new ArrayList<>();

    @AfterViews
    void setUIViews() {
        checkForDateOrLocation();
        initListViewAdapter();


    }

    @Click(R.id.date_edittext)
    public void showCalendar(){
        edit_location_layout.setVisibility(View.GONE);
        calendarView.setVisibility(View.VISIBLE);
    }

    @Click(R.id.location_edittext)
    public void showLocations(){
        edit_location_layout.setVisibility(View.VISIBLE);
        calendarView.setVisibility(View.GONE);
    }

    private void checkForDateOrLocation() {
        Bundle bundle=getArguments();
        if(bundle!=null){
            String value=bundle.getString(DATE_OR_LOCATION_KEY);
            if(value!=null && value.equalsIgnoreCase(DATE)){

                edit_location_layout.setVisibility(View.GONE);
                calendarView.setVisibility(View.VISIBLE);
            }
            else{
                edit_location_layout.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.GONE);
            }
        }
    }

    private void initListViewAdapter() {
        locations.add("Sydney");
        locations.add("Melbourne");
        locations.add("Brisbane");
        locations.add("Sydney");
        locations.add("Melbourne");
        locations.add("Brisbane");
        adapter=new EditLocationCustomAdapter(locations);
        edit_datelocation_listview.setAdapter(adapter);

    }


    @Click(R.id.find_appointments)
    public void findAppointments(){
        getActivity().getSupportFragmentManager().popBackStack();
    }



    public static EditDateLocationAppointmentFragment newInstance(){
        return new EditDateLocationAppointmentFragment_();
    }

    public class EditLocationCustomAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> al;
        EditLocationCustomAdapter(ArrayList<String> al){
            this.al=al;
            inflater=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return al.size();
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
                convertView=inflater.inflate(R.layout.edit_location_list_item, null);
                holder.edit_datelocation_list_text=(TextView)convertView.findViewById(R.id.edit_datelocation_list_text);
                convertView.setTag(holder);

            }
            else{
                holder= (ViewHolder) convertView.getTag();
            }

            holder.edit_datelocation_list_text.setText(al.get(position));
            return convertView;
        }

        class ViewHolder{
            TextView edit_datelocation_list_text;
        }

    }

}
