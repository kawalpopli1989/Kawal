package healthengine.android.com.healthengine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.data.MemberDetail;

/**
 * Created by kawal on 20/2/16.
 */
public class ProfileAdapter extends ArrayAdapter<MemberDetail> {

    Holder mHolder = null;

    public ProfileAdapter(Context context) {
        super(context,0);

    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_member_profile, parent, false);
            mHolder = new Holder(convertView);
            convertView.setTag(mHolder);
        }else{
            mHolder = (Holder) convertView.getTag();
        }

        MemberDetail mMemberDetail =  getItem(position);

        mHolder.mText.setText(mMemberDetail.getFirstname() + " " + mMemberDetail.getLastname());

        return convertView;
    }

    private class Holder{
        TextView mText;
        Holder(View view){
            mText = (TextView) view.findViewById(R.id.name);
        }
    }
}
