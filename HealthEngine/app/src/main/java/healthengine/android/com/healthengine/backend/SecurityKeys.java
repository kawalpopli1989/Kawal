package healthengine.android.com.healthengine.backend;

import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by jsn on 31/1/16.
 */
public class SecurityKeys {

    private static final String PROPERTIES_FILE_NAME = "local.properties";
    private static final String[] MANDATORY_PROPERTIES = { "HE-API-ACCESS-KEY", "HE-API-SECRET-KEY" };

    private SecurityKeys(){
    }

    public static Properties loadProperties(){

        Properties mProperties = null;

        mProperties = new Properties();
        try {
            mProperties.load(new FileInputStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String key : MANDATORY_PROPERTIES) {

            String value = mProperties.getProperty(key);

            Log.i(key, value);
            System.out.println(MANDATORY_PROPERTIES +  " : " + key);
            if (value == null || value.trim().isEmpty()) {
               return null;
            }
        }

        return mProperties;

    }


}
