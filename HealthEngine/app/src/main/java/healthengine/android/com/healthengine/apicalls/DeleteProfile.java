package healthengine.android.com.healthengine.apicalls;

import android.content.Context;

import java.io.IOException;

import healthengine.android.com.healthengine.backend.PrefManager;
import healthengine.android.com.healthengine.data.DataG;
import healthengine.android.com.healthengine.data.MemberDetail;
import healthengine.android.com.healthengine.data.Token;
import healthengine.android.com.healthengine.retrofitadapter.ServiceGenerator;
import retrofit2.Call;

/**
 * Created by jsn on 20/2/16.
 */
public class DeleteProfile implements ApiCallInter {

    Call<DataG> mCall = null;

    @Override
    public <T> void doCall(T t, Context mContext) {


        MemberDetail mMemberDetail= (MemberDetail)((DataG)t).getType();

        Token mToken = new Token(PrefManager.getInstance(mContext).getToken());

        mCall =  ServiceGenerator.createService().deleteMemberProfile(mMemberDetail.getId(), mToken);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                   mCall.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
