package com.example.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;

public class LocationReceiver extends BroadcastReceiver
{
    private boolean noCallListener = true;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(noCallListener)
        {
            Log.i("Location1", "AQUI");
            // Acquire a reference to the system Location Manager
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            //locationManager.addNmeaListener(new MyPositionStateListener(context));

            MyPositionStateListener locationListener = new MyPositionStateListener(context);

            // Register the listener with the Location Manager to receive location updates
            //min 1000 msec, and 10 meters
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, locationListener);

            noCallListener = false;
        }
    }
}
