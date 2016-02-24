package healthengine.android.com.healthengine.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import healthengine.android.com.healthengine.R;

/**
 * Created by kawal on 21/1/16.
 */
public class Utility {


    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean validateText(String string) {

        if (TextUtils.isEmpty(string)) {
            return false;
        }

        return true;
    }

    public static boolean isValidEmail(String emailtxt){
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailtxt);

        if (matcher.matches()) {
            return matcher.matches();
        }
        return false;
    }

    public static void goToNextActivity(Context context, Class<?> name, Bundle bundle, boolean isFinish) {

        Intent mIntent  =new Intent(context, name);
        if(bundle != null) {
            mIntent.putExtras(bundle);
        }
        context.startActivity(mIntent);

        if(isFinish) {
            ((Activity) context).finish();
        }
    }

public static void replaceFragment(FragmentActivity activity, Fragment fragment, boolean addToBackStack){
    if(addToBackStack) {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getName())
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }
    else{
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment, fragment.getClass().getName())
                .commit();
    }
}

    public static void replaceAppointmentFragment(FragmentActivity activity, Fragment fragment, boolean addToBackStack){
        if(addToBackStack) {
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.appointment_frame, fragment, fragment.getClass().getName())
                    .addToBackStack(fragment.getClass().getName())
                    .commit();
        }
        else{
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.appointment_frame, fragment, fragment.getClass().getName())
                    .commit();
        }
    }

    public static void setHeightofListView(ListView listView) {

        ListAdapter mAdapter = listView.getAdapter();

        int totalHeight = 0;

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),

                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
            Log.w("HEIGHT" + i, String.valueOf(totalHeight));

        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (mAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();

    }


}
