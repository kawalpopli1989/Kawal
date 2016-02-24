package healthengine.android.com.healthengine.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.apicalls.ApiCall;
import healthengine.android.com.healthengine.apicalls.PatientProfileApi;
import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.MemberDetail;
import healthengine.android.com.healthengine.data.PatientProfileData;
import healthengine.android.com.healthengine.model.Model;

/**
 * Created by jsn.
 */

@EActivity(R.layout.activity_patient_profile)
public class PatientProfileActivity<T> extends BaseActivity implements Model.MyInter<T> {

    @ViewById
    EditText fName;
    @ViewById
    EditText lName;
    @ViewById
    EditText mobileNumber;
    @ViewById
    EditText email;
    @ViewById
    EditText street;
    @ViewById
    EditText subUrb;
    @ViewById
    EditText postcode;
    @ViewById
    TextView dob;
    @ViewById
    DatePicker patient_datepicker;
    @ViewById
    Switch notification_switch;
    @ViewById
    TextView done_action;

    @AfterViews
    public void setUi() {
        MemberDetail mMemberDetail = getDataFromBundle();
        if (mMemberDetail != null) {
            fName.setText(mMemberDetail.getFirstname());
            lName.setText(mMemberDetail.getLastname());
            mobileNumber.setText(mMemberDetail.getPhone());
            email.setText(mMemberDetail.getEmail());
            street.setText(mMemberDetail.getAddress());
            subUrb.setText(mMemberDetail.getSuburb());
            postcode.setText(mMemberDetail.getPostcode());
            dob.setText(mMemberDetail.getDob());
        } else {
            done_action.setVisibility(View.VISIBLE);
        }

        patient_datepicker.setMaxDate(Calendar.getInstance().getTimeInMillis());
    }

    @Click(R.id.cancel_action)
    public void finishActivity() {
        finish();
    }

    @Click(R.id.done_action)
    public void saveProfile() {



        if (getData() != null) {
            showDialog("Creating New Profile...");
            DataG<PatientProfileData> mObj = new DataG<>(getData());

            ApiCall<PatientProfileApi> mCall = new ApiCall<>(new PatientProfileApi());
            mCall.doCall(mObj, this);
        }
    }

    @Click(R.id.dob)
    public void dateOfBirth() {
        dob.setVisibility(View.GONE);
        patient_datepicker.setVisibility(View.VISIBLE);
    }


    String firstName, lastName, mobileNum, emailId, streetAdd, subUrbAdd, postCodeAdd;

    public boolean isValidFirstName(String msg) {
        firstName = fName.getText().toString();

        if (TextUtils.isEmpty(firstName)) {
            if(msg == null)
            fName.setError("Field required");
            else
                fName.setError(msg);
            return false;
        }

        return true;
    }

    public boolean isValidLastName(String msg) {
        lastName = lName.getText().toString();

        if (TextUtils.isEmpty(lastName)) {
            if(msg == null)
                lName.setError("Field required");
            else
                lName.setError(msg);
            return false;
        }

        return true;
    }

    public boolean isValidMobile(String msg) {
        mobileNum = mobileNumber.getText().toString();

        if (TextUtils.isEmpty(mobileNum)) {
            if(msg == null)
                mobileNumber.setError("Field required");
            else
                mobileNumber.setError(msg);
            return false;
        }

        return true;
    }

    public boolean isValidEmail(String msg) {
        emailId = email.getText().toString();

        if (!healthengine.android.com.healthengine.utils.Utility.isValidEmail(emailId)) {
            if(msg == null)
                email.setError("Field required");
            else
                email.setError(msg);

            return false;
        }

        return true;
    }

    public boolean isValidStreet(String msg) {
        streetAdd = street.getText().toString();

        if (TextUtils.isEmpty(streetAdd)) {

            if(msg == null)
                street.setError("Field required");
            else
                street.setError(msg);

            return false;
        }

        return true;
    }

    public boolean isValidSubUrb(String msg) {
        subUrbAdd = subUrb.getText().toString();

        if (TextUtils.isEmpty(subUrbAdd)) {
            if(msg == null)
                subUrb.setError("Field required");
            else
                subUrb.setError(msg);

            return false;
        }

        return true;
    }

    public boolean isValidPostCode(String msg) {
        postCodeAdd = postcode.getText().toString();

        if (TextUtils.isEmpty(postCodeAdd)) {

            if(msg == null)
                postcode.setError("Field required");
            else
                postcode.setError(msg);


            return false;
        }

        return true;
    }

    public void isValidDob() {

    }

    public PatientProfileData getData() {

        if (isValidFirstName(null) && isValidLastName(null) && isValidMobile(null) && isValidEmail(null) && isValidStreet(null) && isValidSubUrb(null) && isValidPostCode(null)) {

            PatientProfileData ppd = new PatientProfileData();

            ppd.setAuth(PrefManager.getInstance(this).getToken());
            ppd.setFirstname(firstName);
            ppd.setLastname(lastName);
            ppd.setPhone(mobileNum);
            ppd.setEmail(emailId);
            ppd.setState("");
            ppd.setAddress(streetAdd);
            ppd.setSuburb(subUrbAdd);
            ppd.setPostcode(postCodeAdd);
            ppd.setDob("");


            return ppd;
        }

        return null;

    }


    public MemberDetail getDataFromBundle() {
        Bundle mBundle = getIntent().getExtras();
        if (mBundle != null) {
            return (MemberDetail) mBundle.getSerializable("obj");
        }
        return null;
    }

    @Override
    public void success(T t) {
        hideDialog();
        showToast("Patient Created Successfully");
    }

    @Override
    public void failure(T t) {

//        if (((DataG) t).getType() instanceof FailureResponse) {
//            FailureResponse mFailureResponse = (FailureResponse) (((DataG) t).getType());
//
//            final List<FailureResponse.Error> mErrorList  = mFailureResponse.getErrors();
//            int listSize = mErrorList.size();
//            for(int i=0; i<listSize;i++){
//                final FailureResponse.Error mError = mErrorList.get(i);
//                switch (mError.getParameter()){
//                    case "":
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//
//                        break;
//
//
//                }
//            }
//        }

        hideDialog();
        showToast("Error while creating profile");
    }
}
