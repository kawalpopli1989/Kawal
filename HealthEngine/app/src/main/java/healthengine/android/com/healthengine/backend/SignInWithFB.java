package healthengine.android.com.healthengine.backend;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.FacebookToken;
import healthengine.android.com.healthengine.model.Model;

/**
 * Created by jsn on 21/1/16.
 */
public class SignInWithFB {

    Context context = null;
    CallbackManager callbackManager = null;


    private SignInWithFB() {
    }

    public static SignInWithFB getInstance() {
        return  new SignInWithFB();
    }

    public void doSignIn(final Context context, CallbackManager callbackManager) {

        this.context = context;
        getHashKey();
        LoginManager.getInstance().logInWithReadPermissions((Activity) context, Arrays.asList("public_profile,email"));
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                PrefManager.getInstance(context).saveFbToken(loginResult.getAccessToken().getToken());

                FacebookToken mToken = new FacebookToken(String.valueOf(loginResult.getAccessToken().getToken()));
                DataG<FacebookToken> mDataObj = new DataG<>(mToken);

                Model.getInstance().onSuccess(mDataObj, context.getClass().getName());
            }

            @Override
            public void onCancel() {
                Toast.makeText(context, "canceled", Toast.LENGTH_SHORT).show();
                Model.getInstance().onFailed(null, context.getClass().getName());
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("facebook", "error");
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                Model.getInstance().onFailed(null, context.getClass().getName());

            }
        });

    }

    void getHashKey() {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo("healthengine.android.com.healthengine", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }





}
