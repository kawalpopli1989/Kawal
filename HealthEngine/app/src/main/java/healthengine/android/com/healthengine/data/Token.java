package healthengine.android.com.healthengine.data;

/**
 * Created by jsn on 3/2/16.
 */
public class Token implements DataInter {

    private String code;
    public Token(String code){
        this.code = code;
    }
    public String getAuthToken() {
        return code;
    }
}
