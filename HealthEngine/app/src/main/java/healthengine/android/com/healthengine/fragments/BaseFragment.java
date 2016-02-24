package healthengine.android.com.healthengine.fragments;


import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    Gson mGson = new Gson();

    private ProgressDialog mProgressDialog;

    protected void showDialog(String message) {
        if (mProgressDialog == null) {
            setProgressDialog(message);
        }
        mProgressDialog.show();
    }

    protected void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    private void setProgressDialog(String message) {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(message);
    }

    protected void showToast(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
