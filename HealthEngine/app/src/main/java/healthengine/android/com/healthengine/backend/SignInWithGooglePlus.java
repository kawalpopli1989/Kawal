package healthengine.android.com.healthengine.backend;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import healthengine.android.com.healthengine.R;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.GoogleToken;
import healthengine.android.com.healthengine.model.Model;

/**
 * Created by jsn on 21/1/16.
 */
public class SignInWithGooglePlus implements GoogleApiClient.OnConnectionFailedListener {

    GoogleSignInOptions mGoogleSignInOptions = null;
    private GoogleApiClient mGoogleApiClient = null;
    Context context = null;

    private static SignInWithGooglePlus mSignInWithGooglePlus = new SignInWithGooglePlus();

    private SignInWithGooglePlus(){
    }

    public static SignInWithGooglePlus getInstance(){
        return mSignInWithGooglePlus;
    }

    public Intent doSignIn(Context context){

        this.context = context;

        mGoogleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.server_client_id))
                .requestEmail()
                .build();

        if(mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .enableAutoManage((FragmentActivity) context, this)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, mGoogleSignInOptions)
                    .build();
        }
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
        return Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        nullifyClient();
    }

    public void handleResult(GoogleSignInResult result) {

        if(result.isSuccess()){

            PrefManager.getInstance(context).saveGToken(result.getSignInAccount().getIdToken());

            GoogleToken mToken = new GoogleToken(String.valueOf(result.getSignInAccount().getIdToken()));
            DataG<GoogleToken> mDataObj = new DataG<>(mToken);
            Model.getInstance().onSuccess(mDataObj, context.getClass().getName());
        }else{
            Model.getInstance().onFailed(result,context.getClass().getName());
        }

        nullifyClient();
    }

    public void nullifyClient(){
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.stopAutoManage((FragmentActivity) context);
            mGoogleApiClient.disconnect();
        }
    }

}
