package healthengine.android.com.healthengine.apicalls;

import android.content.Context;

/**
 * Created by jsn on 4/2/16.
 */
public interface ApiCallInter {
    public <T> void doCall(T t, Context mContext);
}
