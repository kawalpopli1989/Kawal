package healthengine.android.com.healthengine.apicalls;

import android.content.Context;

import java.io.IOException;

import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.FailureResponse;
import healthengine.android.com.healthengine.data.NewProfileResponse;
import healthengine.android.com.healthengine.data.PatientProfileData;
import healthengine.android.com.healthengine.model.Model;
import healthengine.android.com.healthengine.retrofitadapter.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by jsn on 16/2/16.
 */
public class PatientProfileApi implements ApiCallInter {

    Call<NewProfileResponse> mCall = null;

    @Override
    public <T> void doCall(final T t, Context mContext) {

        mCall = ServiceGenerator.createService().newMemberProfile((PatientProfileData)((DataG)t).getType());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Response<NewProfileResponse> mresponse =   mCall.execute();
                    if(mresponse.isSuccess()){

                        NewProfileResponse mNewProfileResponse=  mresponse.body();

                        NewProfileResponse mNewProfile=    (NewProfileResponse)((DataG)t).getType();
                        mNewProfile.setId(mNewProfileResponse.getId());
                        mNewProfile.setUrl(mNewProfileResponse.getUrl());

                        DataG<NewProfileResponse> mData = new DataG<NewProfileResponse>(mNewProfile);
                        Model.getInstance().onSuccess(mData, null);

                    }else{
//                        String errorMesg = new String(mresponse.errorBody().bytes());
//                        Gson mGson = new Gson();
//                        FailureResponse mFailureResponse =   mGson.fromJson(errorMesg, FailureResponse.class);
//                        List<FailureResponse.Error> mList =  mFailureResponse.getErrors();
//
//
//                        for(int i=0; i<mList.size(); i++){
//                            Log.i("Error Tag",mList.get(i).getParameter());
//                        }
                        DataG<FailureResponse> mObj = new DataG<>(new FailureResponse());
                        Model.getInstance().onFailed(mObj, null);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Model.getInstance().onFailed(null, null);
                }
            }
        }).start();
    }
}
