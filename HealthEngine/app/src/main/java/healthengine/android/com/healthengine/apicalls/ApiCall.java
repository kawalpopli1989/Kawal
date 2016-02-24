package healthengine.android.com.healthengine.apicalls;

import android.content.Context;

/**
 * Created by jsn on 4/2/16.
 */
public class ApiCall<K extends ApiCallInter>{

    K param1;

    public ApiCall(K param1){
       this.param1 = param1;
    }

   public <T> void doCall(T Obj, Context mContext){
       param1.doCall(Obj,mContext);
   }
}
