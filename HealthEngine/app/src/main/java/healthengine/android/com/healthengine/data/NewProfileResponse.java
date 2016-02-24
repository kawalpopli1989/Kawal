package healthengine.android.com.healthengine.data;

/**
 * Created by jsn on 17/2/16.
 */
public class NewProfileResponse extends PatientProfileData{

  private  String id;
  private   String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
