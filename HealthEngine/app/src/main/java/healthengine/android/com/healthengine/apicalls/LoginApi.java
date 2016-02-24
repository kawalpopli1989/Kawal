package healthengine.android.com.healthengine.apicalls;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.FacebookToken;
import healthengine.android.com.healthengine.data.FailureResponse;
import healthengine.android.com.healthengine.data.GoogleToken;
import healthengine.android.com.healthengine.data.LoginRequest;
import healthengine.android.com.healthengine.data.LoginResponse;
import healthengine.android.com.healthengine.data.Token;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.retrofitadapter.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jsn on 30/1/16.
 */
public class LoginApi implements ApiCallInter {

    Call<LoginResponse> mCall = null;

    @Override
    public <T> void doCall(final T t, final Context mContext) {

        if (((DataG) t).getType() instanceof LoginRequest) {
            mCall = ServiceGenerator.createService().signInWithMail((LoginRequest) ((DataG) t).getType());
        } else if(((DataG) t).getType() instanceof FacebookToken){
            mCall  = ServiceGenerator.createService().enterInWithToken("facebook",(Token) ((DataG) t).getType() );
        } else if(((DataG) t).getType() instanceof GoogleToken){
            mCall  = ServiceGenerator.createService().enterInWithToken("google",(Token) ((DataG) t).getType() );
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<LoginResponse> response = mCall.execute();
                    Looper.prepare();
                    if (response.isSuccess()) {
                        DataG<LoginResponse> mData = new DataG<>(response.body());

                        LoginResponse mResponse =   response.body();
                        Gson mGson = new Gson();
                        PrefManager.getInstance(mContext).saveToken(mResponse.getAuthToken());
                        PrefManager.getInstance(mContext).setLoginData(mGson.toJson(mResponse));

                        Token mToken = new Token(mResponse.getAuthToken());
                        DataG<Token> mObj = new DataG<>(mToken);

                        ApiCall<MemberDetailApi> mApicall = new ApiCall<>(new MemberDetailApi());
                        mApicall.doCall(mObj, mContext);

                        Model.getInstance().onSuccess(mData, null);

                    } else {

                        String errorStr = new String(response.errorBody().bytes());
                        Log.i("Error Tag Str",errorStr);

                        Gson mGson = new Gson();
                        FailureResponse mFailureResponse =   mGson.fromJson(errorStr, FailureResponse.class);

                        DataG<FailureResponse> mObj =new DataG<FailureResponse>(mFailureResponse);

                        Model.getInstance().onFailed(mObj, null);
                    }
                } catch (IOException e) {
                    Looper.prepare();
                    e.printStackTrace();
                    Model.getInstance().onFailed("", null);
                }
            }
        }).start();

    }
}
