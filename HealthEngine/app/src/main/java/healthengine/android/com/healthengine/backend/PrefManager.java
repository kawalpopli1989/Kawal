package healthengine.android.com.healthengine.backend;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kawal on 4/2/16.
 */
public class PrefManager {

    private static final String SHARED_PREFRENCES = "HealthEnginePrefs";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String TOKEN = "token";
    private static final String TOKEN_FB = "token_fb";
    private static final String TOKEN_G = "token_g";
    private static final String IS_GEO_FENCE_ADDED = "is_geo_fence_added";
    private static final String SIGN_IN_DETAIL  = "sign_in_detail";
    private static final String MEMEBER_PROFILE="member_profile";

    private static PrefManager userInstance = new PrefManager();

    static SharedPreferences prefrences;
    static SharedPreferences.Editor editor;
    static Context mContext;

    /** returns a shared instance of the {@link SharedPreferences} */
    public static PrefManager getInstance(Context context) {

        mContext = context;
        prefrences = mContext.getSharedPreferences(SHARED_PREFRENCES, Context.MODE_PRIVATE);
        editor = prefrences.edit();

        return userInstance;
    }

    private PrefManager() {
    }

    public void saveLatitude(String lat) {
        editor.putString(LATITUDE, lat);
        editor.commit();
    }

    public String getLatitude() {

        return prefrences.getString(LATITUDE, "0.0");
    }

    public void saveLongitude(String longi) {
        editor.putString(LONGITUDE, longi);
        editor.commit();
    }

    public String getLongitude() {
        return prefrences.getString(LONGITUDE, "0.0");
    }


    public void setGeoFenceAdded(boolean isAdded){
        editor.putBoolean(IS_GEO_FENCE_ADDED, isAdded);
        editor.commit();
    }

    public boolean isGeoFenceAdded(){
        return prefrences.getBoolean(IS_GEO_FENCE_ADDED, false);
    }

    public void saveToken(String token) {
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken() {
        return prefrences.getString(TOKEN, null);
    }

    public void clearToken(){
        editor.putString(TOKEN, null);
        editor.commit();
    }


    public void setLoginData(String data) {
        editor.putString(SIGN_IN_DETAIL, data);
        editor.commit();
    }

    public String getLoginData() {
        return prefrences.getString(SIGN_IN_DETAIL, null);
    }


    public void saveFbToken(String token) {
        editor.putString(TOKEN_FB, token);
        editor.commit();
    }

    public String getFbToken() {
        return prefrences.getString(TOKEN_FB, null);
    }


    public void saveGToken(String token) {
        editor.putString(TOKEN_G, token);
        editor.commit();
    }

    public String getGToken() {
        return prefrences.getString(TOKEN_G, null);
    }


    public void setPatientProfiles(String profileStr){
        editor.putString(MEMEBER_PROFILE, profileStr);
        editor.commit();
    }
    public String getPatientProfiles(){
        return prefrences.getString(MEMEBER_PROFILE, null);
    }




}

