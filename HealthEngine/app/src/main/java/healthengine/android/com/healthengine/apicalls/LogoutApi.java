package healthengine.android.com.healthengine.apicalls;

import android.content.Context;
import android.os.Looper;

import java.io.IOException;

import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.LogoutResponse;
import healthengine.android.com.healthengine.data.Token;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.retrofitadapter.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jsn on 30/1/16.
 */
public class LogoutApi implements ApiCallInter {

    Call<LogoutResponse> mCall;

    @Override
    public <T> void doCall(final T t, final Context mContext) {

        mCall = ServiceGenerator.createService().logout((Token) (((DataG) t).getType()));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<LogoutResponse> mresponse = mCall.execute();
                    Looper.prepare();
                    if (mresponse.isSuccess()) {
                        DataG<LogoutResponse> mObj = new DataG<>(mresponse.body());
                        PrefManager.getInstance(mContext).clearToken();

                        Model.getInstance().onSuccess(mObj, "LogoutApi");
                    } else {
                        Model.getInstance().onFailed(null, null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
