package healthengine.android.com.healthengine.apicalls;

import android.content.Context;
import android.os.Looper;

import java.io.IOException;

import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.LoginResponse;
import healthengine.android.com.healthengine.data.Token;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.retrofitadapter.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jsn on 30/1/16.
 */
public class TokenRegenerateApi implements ApiCallInter {

    Call<LoginResponse> mCall = null;

    @Override
    public <T> void doCall(T t, Context mContext) {

        mCall = ServiceGenerator.createService().regenerateNewToken((Token) ((DataG) t).getType());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<LoginResponse> response = mCall.execute();
                    Looper.prepare();
                    if (response.isSuccess()) {
                        DataG<LoginResponse> mData = new DataG<>(response.body());
                        Model.getInstance().onSuccess(mData, null);
                    } else {
                        Model.getInstance().onFailed("", null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
