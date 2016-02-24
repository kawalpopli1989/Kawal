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
import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.backend.SignInWithFB;
import healthengine.android.com.healthengine.backend.SignInWithGooglePlus;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.FacebookToken;
import healthengine.android.com.healthengine.data.FailureResponse;
import healthengine.android.com.healthengine.data.GoogleToken;
import healthengine.android.com.healthengine.data.LoginRequest;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.utils.Utility;

/**
 * Created by jsn.
 */


public class LoginActivity<T> extends BaseActivity implements View.OnClickListener, Model.MyInter<T> {


    Toolbar mToolbar;
    TextView mCancelBtn, mLoginBtn, mSignupBtn, mLoginWithG, mLoginWithFb;
    private static final int SIGN_IN_WITH_GOOGLE = 100;
    CallbackManager callbackManager = null;
    EditText emailTxt, passTxt;
    String email, pass;
    private boolean isError = false;
    private Object data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.activity_login);
        init();
        setSupportActionBar(mToolbar);
        registerListeners();
    }

    private void init() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mCancelBtn = (TextView) findViewById(R.id.cancel_action);
        mLoginBtn = (TextView) findViewById(R.id.login_btn);
        mSignupBtn = (TextView) findViewById(R.id.signup_text);
        mLoginWithG = (TextView) findViewById(R.id.g_btn);
        mLoginWithFb = (TextView) findViewById(R.id.facebool_btn);

        emailTxt = (EditText) findViewById(R.id.email_field);
        passTxt = (EditText) findViewById(R.id.password);
    }

    private void registerListeners() {
        mCancelBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
        mSignupBtn.setOnClickListener(this);
        mLoginWithG.setOnClickListener(this);
        mLoginWithFb.setOnClickListener(this);
        Model.getInstance().registerModel(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                doSignIn();
                break;

            case R.id.cancel_action:
                onBackPressed();
                break;

            case R.id.signup_text:
                startActivity(new Intent(this, SignupActivity.class));
                finish();
                break;

            case R.id.g_btn:
                if(PrefManager.getInstance(this).getGToken() != null){
                    GoogleToken mToken = new GoogleToken(PrefManager.getInstance(this).getGToken());
                    DataG<GoogleToken> mDataObj = new DataG<>(mToken);
                    success((T)mDataObj);
                }else {
                    Intent intent = SignInWithGooglePlus.getInstance().doSignIn(this);
                    startActivityForResult(intent, SIGN_IN_WITH_GOOGLE);
                }
                break;

            case R.id.facebool_btn:

                if(PrefManager.getInstance(this).getFbToken() != null){
                    FacebookToken mToken = new FacebookToken(PrefManager.getInstance(this).getFbToken());
                    DataG<FacebookToken> mDataObj = new DataG<>(mToken);
                    success((T)mDataObj);
                }else {
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
        } else if(requestCode == 64206 && resultCode == RESULT_OK){
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public  void success(T t) {

       if( ((DataG) t).getType() instanceof FacebookToken || ((DataG) t).getType() instanceof GoogleToken){
            showDialog("Talking to server...");
            ApiCall<LoginApi> mcall = new ApiCall<>(new LoginApi());
            mcall.doCall(t, this);
        }
       else if(isFromHelp()){
            hideDialog();
            showToast("Sign In Successful");
            Utility.goToNextActivity(this, HomeActivity_.class, null, true);
            finish();
        }else{
           hideDialog();
           showToast("Sign In Successful");
           finish();
       }

    }


    @Override
    public void failure(T t) {

        if (((DataG) t).getType() instanceof FailureResponse) {
            FailureResponse mFailureResponse = (FailureResponse) (((DataG) t).getType());

            final List<FailureResponse.Error> mErrorList  = mFailureResponse.getErrors();
            int listSize = mErrorList.size();
            for(int i=0; i<listSize;i++){
                final FailureResponse.Error mError = mErrorList.get(i);
                switch (mError.getParameter()){
                    case "email":
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                emailTxt.setText("");
                                getEmail(mError.getMessage());
                            }
                        });

                        break;

                    case "password":
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                passTxt.setText("");
                                getPass(mError.getMessage());
                            }
                        });

                        break;
                }
            }
        }

        hideDialog();
        showToast("Sign In Failed");

    }





    public void getEmail(final String msg) {

        email = emailTxt.getText().toString();


        if (!Utility.isValidEmail(email)) {
            if(msg == null)
                emailTxt.setError(getString(R.string.field_required));
            else{
                emailTxt.setError(msg);
            }
            isError = true;
        }


    }

    public void getPass(final String msg) {
        pass = passTxt.getText().toString();
                if (!Utility.validateText(pass)) {
                    if(msg == null)
                        passTxt.setError(getString(R.string.field_required));
                    else
                        passTxt.setError(msg);

                    isError = true;
                }
    }

    public LoginRequest buildLoginObj() {

        getEmail(null);
        getPass(null);
        if (isError) {
            isError = false;
            return null;
        } else {
            isError = false;
            return new LoginRequest(email, pass);
        }

    }

    public void doSignIn() {
        LoginRequest mLoginRequest = buildLoginObj();
        if (mLoginRequest != null) {
            showDialog("Talking to Server...");
            DataG<LoginRequest> mObj = new DataG<>(mLoginRequest);
            ApiCall<LoginApi> mcall = new ApiCall<>(new LoginApi());
            mcall.doCall(mObj, this);
        }
    }

    public Boolean isFromHelp() {
        Bundle mBundle = getIntent().getExtras();

        if(mBundle != null){
            return mBundle.getBoolean("from_help",false);
        }

        return false;
    }
}