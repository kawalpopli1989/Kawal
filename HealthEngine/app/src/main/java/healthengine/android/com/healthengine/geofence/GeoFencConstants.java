package healthengine.android.com.healthengine.geofence;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by rajesh on 21/1/16.
 */
public class GeoFencConstants {

    GeoFencConstants() {

    }

    public static final String PACKAGE_NAME = "healthengine.android.com.healthengine";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    public static final String GEOFENCES_NAME = PACKAGE_NAME + ".GEOFENCES_NAME";

    public static final String GEOFENCES_RADIUS = PACKAGE_NAME + ".GEOFENCES_RADIUS";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 12;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 1000; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<String, LatLng>();

    static {
        // San Francisco International Airport.
        //   BAY_AREA_LANDMARKS.put("SFO", new LatLng(30.723748, 76.846731));

        // Googleplex.
        //   BAY_AREA_LANDMARKS.put("GOOGLE", new LatLng(37.422611,-122.0840577));
    }
}
