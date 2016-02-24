package healthengine.android.com.healthengine.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
@EFragment(R.layout.fragment_select_practitioner)
public class SelectPractitionerFragment extends Fragment{

    public static String PRACTITIONER_TYPE="practitioner_type";


    SelectPractitionerListAdapter mSelectPractitionerListAdapter;
    ArrayList<String> practitionerAl=new ArrayList<>();

    @ViewById
    ListView select_practitioner_listview;




    @AfterViews
    void setUIViews() {
        fillDataToListView();
    }

    public String getSelectedValue(){

        Bundle bundle=getArguments();
        if(bundle!=null){
            String selectedValue=bundle.getString(PRACTITIONER_TYPE);
            if(selectedValue!=null){
                return selectedValue;
            }
        }
        return "Any";
    }

    private void fillDataToListView() {
        practitionerAl.add("Any");
        practitionerAl.add("Dr. Sally Partington");
        practitionerAl.add("Dr. Mark Kent");
        practitionerAl.add("Dr. Lisa Surman");
        practitionerAl.add("Dr. Amanda Croft");
        practitionerAl.add("Dr. ElizaBethKarr");
        practitionerAl.add("Dr. Sally Partington");
        practitionerAl.add("Dr. Mark Kent");
        practitionerAl.add("Dr. Lisa Surman");
        practitionerAl.add("Dr. Amanda Croft");
        practitionerAl.add("Dr. ElizaBethKarr");

        mSelectPractitionerListAdapter=new SelectPractitionerListAdapter(practitionerAl, getSelectedValue());
        select_practitioner_listview.setAdapter(mSelectPractitionerListAdapter);

    }

    @ItemClick(R.id.select_practitioner_listview)
    public void selectListItem(int position){
        AppointmentsActivity.selectedPractitioner=practitionerAl.get(position);
        getActivity().getSupportFragmentManager().popBackStack();
    }





    public class SelectPractitionerListAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> al;
        String selectedValue;
        SelectPractitionerListAdapter(ArrayList<String> al, String selectedValue){
            this.al=al;
            this.selectedValue=selectedValue;
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
                convertView=inflater.inflate(R.layout.select_practitioner_listview_row, null);
                holder.appointment_time_text=(TextView)convertView.findViewById(R.id.appointment_time_text);
                holder.select_practitioner_check_image=(ImageView)convertView.findViewById(R.id.select_practitioner_check_image);
                convertView.setTag(holder);

            }
            else{
                holder= (ViewHolder) convertView.getTag();
            }

            holder.appointment_time_text.setText(al.get(position));
            if(selectedValue.equalsIgnoreCase(al.get(position))){
                holder.select_practitioner_check_image.setVisibility(View.VISIBLE);
            }
            else{
                holder.select_practitioner_check_image.setVisibility(View.GONE);
            }
            return convertView;
        }

        class ViewHolder{
            TextView appointment_time_text;
            ImageView select_practitioner_check_image;
        }

    }

    // TODO: Rename and change types and number of parameters
    public static SelectPractitionerFragment newInstance() {
        return new SelectPractitionerFragment_();

    }

}
