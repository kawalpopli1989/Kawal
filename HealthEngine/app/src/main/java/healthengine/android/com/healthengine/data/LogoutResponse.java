package healthengine.android.com.healthengine.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mani on 17/2/16.
 */
public class LogoutResponse implements DataInter {

    @SerializedName("auth-token")
    @Expose
    private String authToken;

}
