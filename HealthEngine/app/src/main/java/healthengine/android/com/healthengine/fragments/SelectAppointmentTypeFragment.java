package healthengine.android.com.healthengine.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.activities.AppointmentsActivity;

/**
 * Created by kawal on 27/1/16.
 */
@EFragment(R.layout.fragment_select_appointment_type)
public class SelectAppointmentTypeFragment extends Fragment{



    SelectAppointmentTypeListAdapter mSelectAppointmentTypeListAdapter;
    ArrayList<String> appointmentTypeAl=new ArrayList<>();

    @ViewById
    ListView select_appointment_type_listview;




    @AfterViews
    void setUIViews() {
        fillDataToListView();
    }


    private void fillDataToListView() {
        appointmentTypeAl.add("General Appointment");
        appointmentTypeAl.add("Illness");
        appointmentTypeAl.add("Medical Certificate");
        appointmentTypeAl.add("Car Accident");
        appointmentTypeAl.add("Non-workplace Injury");
        appointmentTypeAl.add("Workplace Injury");
        appointmentTypeAl.add("Prescription");
        appointmentTypeAl.add("unsure/Other");

        mSelectAppointmentTypeListAdapter=new SelectAppointmentTypeListAdapter(appointmentTypeAl);
        select_appointment_type_listview.setAdapter(mSelectAppointmentTypeListAdapter);

    }

    @ItemClick(R.id.select_appointment_type_listview)
    public void selectListItem(int position){
        AppointmentsActivity.selectedAppointmentType=appointmentTypeAl.get(position);
        getActivity().getSupportFragmentManager().popBackStack();
    }





    public class SelectAppointmentTypeListAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> al;
        SelectAppointmentTypeListAdapter(ArrayList<String> al){
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
                convertView=inflater.inflate(R.layout.select_appointment_type_listview_row, null);
                holder.appointment_type_text=(TextView)convertView.findViewById(R.id.appointment_type_text);
                convertView.setTag(holder);

            }
            else{
                holder= (ViewHolder) convertView.getTag();
            }

            holder.appointment_type_text.setText(al.get(position));
            return convertView;
        }

        class ViewHolder{
            TextView appointment_type_text;
        }

    }

    // TODO: Rename and change types and number of parameters
    public static SelectAppointmentTypeFragment newInstance() {
        return new SelectAppointmentTypeFragment_();

    }

}
