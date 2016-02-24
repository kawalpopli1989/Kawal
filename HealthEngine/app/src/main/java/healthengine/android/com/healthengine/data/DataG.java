package healthengine.android.com.healthengine.data;

/**
 * Created by jsn on 4/2/16.
 */
public class DataG<T extends DataInter> {

    private T t;
    public DataG(T t){
        this.t = t;
    }

    public T getType(){
        return t;
    }


}
