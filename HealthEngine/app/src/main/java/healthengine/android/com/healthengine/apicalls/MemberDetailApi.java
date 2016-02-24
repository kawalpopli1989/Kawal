package healthengine.android.com.healthengine.apicalls;

import android.content.Context;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.MemberDetail;
import healthengine.android.com.healthengine.data.Token;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.retrofitadapter.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jsn on 30/1/16.
 */
public class MemberDetailApi implements ApiCallInter {

    Call<List<MemberDetail>> mCall = null;

    @Override
    public <T> void doCall(T t, final Context mContext) {

        Token mToken = (Token) ((DataG) t).getType();

        mCall =  ServiceGenerator.createService().getMemberDetail(mToken.getAuthToken());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<List<MemberDetail>> response = mCall.execute();
                    Looper.prepare();
                    if (response.isSuccess()) {
                        Gson mGson  =new Gson();
                        PrefManager.getInstance(mContext).setPatientProfiles(mGson.toJson(response.body()));
                    } else {
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
