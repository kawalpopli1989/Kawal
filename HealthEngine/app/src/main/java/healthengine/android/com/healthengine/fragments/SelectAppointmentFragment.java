package healthengine.android.com.healthengine.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.activities.AppointmentsActivity;
import healthengine.android.com.healthengine.data.DatesDTO;
import healthengine.android.com.healthengine.utils.Utility;

/**
 * Created by kawal on 27/1/16.
 */
@EFragment(R.layout.fragment_select_appointment)
public class SelectAppointmentFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    public static final String TAG="SelectAppointmentFragment";

    int currentDayOfYear;

    private View lastview;
    AppointmentTimeListAdapter mAppointmentTimeListAdapter;
    ArrayList<String> timeAl=new ArrayList<>();
    @ViewById
    ListView select_appointment_time_listview;


    @ViewById
    Gallery date_gallery;

    CalendarAdapter calAdapter;

    @ViewById
    TextView monthyear_text, day_date_month_text;

    @ViewById
    TextView practitioner_selection_text;

    int currentYear, previousYear, nextYear;
    int daysCurrentYear, daysPreviousYear, daysNextYear;
    ArrayList<DatesDTO> alWithDays=new ArrayList<>();

    @AfterViews
    void setUIViews() {
        practitioner_selection_text.setText(AppointmentsActivity.selectedPractitioner);
        setUpCalendar();
        setUpGalleryToDeviceSpecs();
        fillDataToListView();
    }

    @Click(R.id.practitioner_selection_text)
    public void callSelectPractitionerFragment(){
        Bundle bundle=new Bundle();
        bundle.putString(SelectPractitionerFragment.PRACTITIONER_TYPE, practitioner_selection_text.getText().toString());
        SelectPractitionerFragment fragment=SelectPractitionerFragment.newInstance();
        fragment.setArguments(bundle);
        Utility.replaceAppointmentFragment(getActivity(), fragment, true);
    }



    @Click(R.id.continue_booking_textview)
    public void callBookAppointment(){
        Utility.replaceAppointmentFragment(getActivity(), BookAppointmentFragment.newInstance(), true);
    }

    private void fillDataToListView() {
        timeAl.add("08:00am");
        timeAl.add("08:10am");
        timeAl.add("08:20am");
        timeAl.add("08:30am");
        timeAl.add("08:40am");
        timeAl.add("08:50am");
        timeAl.add("09:00am");
        mAppointmentTimeListAdapter=new AppointmentTimeListAdapter(timeAl);
        select_appointment_time_listview.setAdapter(mAppointmentTimeListAdapter);

    }

    // TODO: Rename and change types and number of parameters
    public static SelectAppointmentFragment newInstance() {
        return new SelectAppointmentFragment_();

    }

    private void setUpGalleryToDeviceSpecs() {


        date_gallery.setFadingEdgeLength(100);
        date_gallery.setHorizontalFadingEdgeEnabled(true);
        date_gallery.setSelected(true);
        Calendar c=Calendar.getInstance();
        currentDayOfYear=c.get(Calendar.DAY_OF_YEAR)-1;

        date_gallery.setSelection(daysPreviousYear+currentDayOfYear);

        date_gallery.setOnItemSelectedListener(this);

    }




    private void setUpCalendar() {
        Calendar c = Calendar.getInstance();

        currentYear = c.get(Calendar.YEAR);
        previousYear=currentYear-1;
        nextYear=currentYear+1;

        alWithDays.clear();

        // Calculate number of days in previous year
        if(previousYear%4==0){
            daysPreviousYear=366;
        }
        else{
            daysPreviousYear=365;
        }
        for (int i = 1; i <=daysPreviousYear; i++) {
            alWithDays.add(getDatesDTO(i, previousYear));
        }



        // Calculate number of days in current year
        if(currentYear%4==0){
            daysCurrentYear=366;
        }
        else{
            daysCurrentYear=365;
        }

        for (int i = 1; i <=daysCurrentYear; i++) {
            alWithDays.add(getDatesDTO(i, currentYear));
        }

        // Calculate number of days in next year
        if(nextYear%4==0){
            daysNextYear=366;
        }
        else{
            daysNextYear=365;
        }

        for (int i = 1; i <=daysNextYear; i++) {
            alWithDays.add(getDatesDTO(i, nextYear));
        }


        calAdapter = new CalendarAdapter();
        date_gallery.setAdapter(calAdapter);



    }

    private DatesDTO getDatesDTO(int dayOfYear, int year) {
        DatesDTO mDatesDTO=new DatesDTO();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, dayOfYear);
        calendar.set(Calendar.YEAR, year);

        SimpleDateFormat df = new SimpleDateFormat("dd");
        SimpleDateFormat df1 = new SimpleDateFormat("EEE");

        SimpleDateFormat df3 = new SimpleDateFormat("MMMM yyyy");
        SimpleDateFormat df4 = new SimpleDateFormat("EEEE dd MMMM");

        mDatesDTO.dayValue=df.format(calendar.getTime());
        mDatesDTO.dayName=df1.format(calendar.getTime());
        mDatesDTO.millis=""+calendar.getTimeInMillis();
        mDatesDTO.monthAndYear=df3.format(calendar.getTime());
        mDatesDTO.dayDateAndMonth=df4.format(calendar.getTime());
        return mDatesDTO;
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        try {

            monthyear_text.setText(alWithDays.get(pos).monthAndYear);

            day_date_month_text.setText(alWithDays.get(pos).dayDateAndMonth);
            if (lastview != null) {

                LinearLayout calendar_row_layout=(LinearLayout)lastview.findViewById(R.id.calendar_row_layout);
                calendar_row_layout.setBackground(null);
                TextView textdayName = (TextView) lastview.findViewById(R.id.textdayName);
                textdayName.setTextColor(Color.parseColor("#000000"));

                TextView textdayValue = (TextView) lastview.findViewById(R.id.textdayValue);

                textdayValue.setTextColor(Color.parseColor("#000000"));
            }
            if (view != null) {

                LinearLayout calendar_row_layout=(LinearLayout)view.findViewById(R.id.calendar_row_layout);
                calendar_row_layout.setBackgroundResource(R.drawable.dategallery_item_background);

                TextView textdayName = (TextView) view.findViewById(R.id.textdayName);
                textdayName.setTextColor(Color.parseColor("#ffffff"));

                TextView textdayValue = (TextView) view.findViewById(R.id.textdayValue);

                textdayValue.setTextColor(Color.parseColor("#ffffff"));

            }

            lastview = view;



        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class CalendarAdapter extends BaseAdapter {

        LayoutInflater inflator;
        public CalendarAdapter() {
            inflator=(LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return alWithDays.size();
        }
        @Override
        public Object getItem(int position) {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        class CalanderViewHolder{
            TextView textdayName, textdayValue;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(int position, View convertView,ViewGroup parent) {

            CalanderViewHolder holder=null;

            if(convertView==null){
                holder=new CalanderViewHolder();
                convertView=inflator.inflate(R.layout.calander_row_layout, null);
                holder.textdayName=(TextView)convertView.findViewById(R.id.textdayName);
                holder.textdayValue=(TextView)convertView.findViewById(R.id.textdayValue);
                convertView.setTag(holder);
            }
            else{
                holder=(CalanderViewHolder) convertView.getTag();
            }




            holder.textdayValue.setText(alWithDays.get(position).dayValue);

            holder.textdayName.setText(alWithDays.get(position).dayName);


            return convertView;
        }



    }

    public class AppointmentTimeListAdapter extends BaseAdapter {

        LayoutInflater inflater;
        ArrayList<String> al;
        AppointmentTimeListAdapter(ArrayList<String> al){
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
                convertView=inflater.inflate(R.layout.appointment_time_listview_row, null);
                holder.appointment_time_text=(TextView)convertView.findViewById(R.id.appointment_time_text);
                convertView.setTag(holder);

            }
            else{
                holder= (ViewHolder) convertView.getTag();
            }

            holder.appointment_time_text.setText(al.get(position));
            return convertView;
        }

        class ViewHolder{
            TextView appointment_time_text;
        }

    }


}
