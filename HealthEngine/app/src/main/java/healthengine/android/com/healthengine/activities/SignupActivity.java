package healthengine.android.com.healthengine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import java.util.List;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.apicalls.ApiCall;
import healthengine.android.com.healthengine.apicalls.LoginApi;
import healthengine.android.com.healthengine.apicalls.SignUpApi;
import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.backend.SignInWithFB;
import healthengine.android.com.healthengine.backend.SignInWithGooglePlus;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.FacebookToken;
import healthengine.android.com.healthengine.data.FailureResponse;
import healthengine.android.com.healthengine.data.GoogleToken;
import healthengine.android.com.healthengine.data.LoginResponse;
import healthengine.android.com.healthengine.data.SignUpRequest;
import healthengine.android.com.healthengine.data.Token;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.utils.Utility;

/**
 * Created by jsn.
 */

public class SignupActivity<T> extends BaseActivity implements View.OnClickListener, Model.MyInter<T> {

    //    LinearLayout signup_social_layout;
    Toolbar mToolbar;
    TextView mCancelBtn, mSignupBtn, mLoginWithG, mLoginWithFb;
    EditText fNameTxt, lNameTxt, emailTxt, passwordTxt;
    private static final int SIGN_IN_WITH_GOOGLE = 100;
    CallbackManager callbackManager = null;

    private boolean isError = false;
    private String fName, lName, email, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_signup);
        init();
        setSupportActionBar(mToolbar);
        registerListeners();
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mCancelBtn = (TextView) findViewById(R.id.cancel_action);
        mSignupBtn = (TextView) findViewById(R.id.signup_btn);
        mLoginWithG = (TextView) findViewById(R.id.g_btn);
        mLoginWithFb = (TextView) findViewById(R.id.facebool_btn);

        fNameTxt = (EditText) findViewById(R.id.fname_field);
        lNameTxt = (EditText) findViewById(R.id.lname_field);
        emailTxt = (EditText) findViewById(R.id.email_field);
        passwordTxt = (EditText) findViewById(R.id.password);
//        signup_social_layout=(LinearLayout) findViewById(R.id.signup_social_layout);

    }

    //    boolean isOpened = false;
//
//    public void setListenerToRootView(){
//        final View activityRootView = getWindow().getDecorView().findViewById(android.R.id.content);
//        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
//                if (heightDiff >= 100 ) { // 99% of the time the height diff will be due to a keyboard.
//
//                    if(isOpened == false){
//
//                        signup_social_layout.setVisibility(View.GONE);
//                    }
//                    isOpened = true;
//                }else if(isOpened == true){
//                    signup_social_layout.setVisibility(View.VISIBLE);
//                    isOpened = false;
//                }
//            }
//        });
//    }
    private void registerListeners() {
        mCancelBtn.setOnClickListener(this);
        mSignupBtn.setOnClickListener(this);
        mLoginWithG.setOnClickListener(this);
        mLoginWithFb.setOnClickListener(this);
        Model.getInstance().registerModel(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signup_btn:
                registerUser();
                break;

            case R.id.cancel_action:
                onBackPressed();
                break;
            case R.id.g_btn:
                if (PrefManager.getInstance(this).getGToken() != null) {
                    GoogleToken mToken = new GoogleToken(PrefManager.getInstance(this).getGToken());
                    DataG<GoogleToken> mDataObj = new DataG<>(mToken);
                    success((T) mDataObj);
                } else {
                    Intent intent = SignInWithGooglePlus.getInstance().doSignIn(this);
                    startActivityForResult(intent, SIGN_IN_WITH_GOOGLE);
                }
                break;
            case R.id.facebool_btn:
                if (PrefManager.getInstance(this).getFbToken() != null) {
                    FacebookToken mToken = new FacebookToken(PrefManager.getInstance(this).getFbToken());
                    DataG<FacebookToken> mDataObj = new DataG<>(mToken);
                    success((T) mDataObj);
                } else {
                    callbackManager = CallbackManager.Factory.create();
                    SignInWithFB.getInstance().doSignIn(this, callbackManager);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_WITH_GOOGLE && resultCode == RESULT_OK) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            SignInWithGooglePlus.getInstance().handleResult(result);
        } else if (requestCode == 64206 && resultCode == RESULT_OK) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void success(T t) {

        if (((DataG) t).getType() instanceof FacebookToken || ((DataG) t).getType() instanceof GoogleToken) {
            showDialog("Talking to server...");
            ApiCall<LoginApi> mcall = new ApiCall<>(new LoginApi());
            mcall.doCall(t, this);
        } else if (((DataG) t).getType() instanceof Token) {
            Token mToken = (Token) (((DataG) t).getType());
            PrefManager.getInstance(this).saveToken(mToken.getAuthToken());
            hideDialog();
            showToast("Sign Up Successfull");
            Utility.goToNextActivity(this, HomeActivity_.class, null, true);
            finish();
        } else if (((DataG) t).getType() instanceof LoginResponse) {
            LoginResponse mLoginResponse = (LoginResponse) (((DataG) t).getType());
            PrefManager.getInstance(this).saveToken(mLoginResponse.getAuthToken());
            PrefManager.getInstance(this).setLoginData(mGson.toJson(mLoginResponse));
            hideDialog();
            showToast("Sign Up Successfull");
            Utility.goToNextActivity(this, HomeActivity_.class, null, true);
            finish();
        }
    }

    @Override
    public void failure(T t) {


        if (((DataG) t).getType() instanceof FailureResponse) {
            FailureResponse mFailureResponse = (FailureResponse) (((DataG) t).getType());
            List<FailureResponse.Error> errors = mFailureResponse.getErrors();

            int listSize = errors.size();

            for (int i = 0; i < listSize; i++) {

            final FailureResponse.Error mErrorObj = errors.get(i);

            switch (mErrorObj.getParameter()){
                case "firstname":
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            fNameTxt.setText("");
                            getFName(mErrorObj.getMessage());
                        }
                    });

                    break;

                case "lastname":
                 mHandler.post(new Runnable() {
                     @Override
                     public void run() {
                         lNameTxt.setText("");
                         getLName(mErrorObj.getMessage());
                     }
                 });

                    break;

                case "email":
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            emailTxt.setText("");
                            getEmail(mErrorObj.getMessage());
                        }
                    });

                    break;
                case "password":
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            passwordTxt.setText("");
                            getPass(mErrorObj.getMessage());
                        }
                    });
                    break;
            }
        }

        }

        hideDialog();

        showToast("Sign Up Failed");
    }


    public void registerUser() {
        SignUpRequest mSignUpRequest = buildSignUpObj();

        DataG<SignUpRequest> mDataObj;
        if (mSignUpRequest != null) {
            showDialog("Talking to server...");
            mDataObj = new DataG<>(mSignUpRequest);
            ApiCall<SignUpApi> mcall = new ApiCall<>(new SignUpApi());
            mcall.doCall(mDataObj, this);
        }
    }

    public SignUpRequest buildSignUpObj() {

        getFName(null);
        getLName(null);
        getEmail(null);
        getPass(null);

        if (isError) {
            isError = false;
            return null;
        } else {
            isError = false;

            SignUpRequest mSignUpRequest = new SignUpRequest();
            mSignUpRequest.firstname = fName;
            mSignUpRequest.lastname = lName;
            mSignUpRequest.email = email;
            mSignUpRequest.password = password;

            return mSignUpRequest;
        }

    }

    public void getFName(String msg) {
        fName = fNameTxt.getText().toString();

        if (!Utility.validateText(fName)) {
            if(msg == null) {
                fNameTxt.setError(getString(R.string.field_required));
            } else{
                fNameTxt.setError(msg);
            }
            isError = true;
        }
    }

    public void getLName(String msg) {
        lName = lNameTxt.getText().toString();

        if (!Utility.validateText(lName)) {
            if(msg == null) {
                lNameTxt.setError(getString(R.string.field_required));
            } else{
                lNameTxt.setError(msg);
            }
            isError = true;
        }
    }

    public void getEmail(String msg) {
        email = emailTxt.getText().toString();

        if (!Utility.isValidEmail(email)) {

            if(msg == null) {
                emailTxt.setError(getString(R.string.field_required));
            } else{
                emailTxt.setError(msg);
            }

            isError = true;
        }

    }

    public void getPass(String msg) {
        password = passwordTxt.getText().toString();
        if (!Utility.validateText(password)) {
            if(msg == null) {
                passwordTxt.setError(getString(R.string.field_required));
            } else{
                passwordTxt.setError(msg);
            }

            isError = true;
        }
    }


}