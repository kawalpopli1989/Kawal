package healthengine.android.com.healthengine.apicalls;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.FailureResponse;
import healthengine.android.com.healthengine.data.SignUpRequest;
import healthengine.android.com.healthengine.data.Token;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.retrofitadapter.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jsn on 30/1/16.
 */
public class SignUpApi implements ApiCallInter {

    private Call<Token> call = null;

    @Override
    public <T> void doCall(T t, final Context mContext) {

        if (((DataG) t).getType() instanceof SignUpRequest) {
            call = ServiceGenerator.createService().registerNewMember(((SignUpRequest) ((DataG) t).getType()));
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Looper.prepare();
                    if (call != null) {
                        Response<Token> response = call.execute();
                        if (response.isSuccess()) {

                           Token mResponse =   response.body();
                            Gson mGson = new Gson();
                            PrefManager.getInstance(mContext).saveToken(mResponse.getAuthToken());
                            PrefManager.getInstance(mContext).setLoginData(mGson.toJson(mResponse));

                            DataG<Token> mData = new DataG<>(mResponse);

                            ApiCall<MemberDetailApi> mApicall = new ApiCall<>(new MemberDetailApi());
                            mApicall.doCall(mData, mContext);

                            Model.getInstance().onSuccess(mData, null);

                        } else {

                            String errorMesg = new String(response.errorBody().bytes());
//
                            Gson mGson = new Gson();
                            FailureResponse mFailureResponse =   mGson.fromJson(errorMesg, FailureResponse.class);
//
                            Log.i("Error Tag Str", errorMesg);
                            for(int i=0; i<mFailureResponse.getErrors().size();i++){
                                Log.i("Error Tag",mFailureResponse.getErrors().get(i).getParameter());
                            }


                            DataG<FailureResponse> mObj =new DataG<>(mFailureResponse);
//
                            Model.getInstance().onFailed(mObj, null);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}
