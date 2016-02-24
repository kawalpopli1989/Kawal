package healthengine.android.com.healthengine.backend;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by jsn on 28/1/16.
 */
public class GetUserLocation implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static GetUserLocation mGetUserLocation = new GetUserLocation();

    private GetUserLocation() {
    }

    public static GetUserLocation getInstance() {
        return mGetUserLocation;
    }

    GoogleApiClient mGoogleApiClient = null;
    LocationRequest mLocationRequest = null;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    Context context = null;

    public void getUserLatLong(Context context) {

        this.context = context;

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

        createRequest();


    }

    public void createRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
    }

    @Override
    public void onConnected(Bundle bundle) {
          LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, new LocationListener() {
              @Override
              public void onLocationChanged(Location location) {
                  if(location != null ){
                      Toast.makeText(context,location.getLatitude() + "/" + location.getLongitude(), Toast.LENGTH_LONG).show();

                      PrefManager.getInstance(context).saveLatitude(String.valueOf(location.getLatitude()));
                      PrefManager.getInstance(context).saveLongitude(String.valueOf(location.getLongitude()));

                  }
                  disconnectClient();
              }
          });
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        mGoogleApiClient.disconnect();
    }

    public void disconnectClient(){
        if( mGoogleApiClient != null){
            mGoogleApiClient.disconnect();
            mGoogleApiClient = null;
        }
    }
}
