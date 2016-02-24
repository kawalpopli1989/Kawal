package healthengine.android.com.healthengine.apicalls;

import android.content.Context;
import android.util.Log;

/**
 * Created by jsn on 4/2/16.
 */
public class RegenerateTokenApi implements ApiCallInter {

    @Override
    public <T> void doCall(T t, Context mContext) {


        Log.i("API Called", "Token RegenerateToken Api Called");
    }

//    private RegenerateTokenApi() {
//    }
//
//    public static RegenerateTokenApi getInstance() {
//        return new RegenerateTokenApi();
//    }
//
//    public void doCall(Context context) {
//
//        String token = Preference.getInstance(context).getToken();
//        Token mToken = new Token(token);
//
//
//    }

}
