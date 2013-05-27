package com.example.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import org.holoeverywhere.widget.Toast;

public class MyPositionStateListener implements LocationListener
{
    private static final int TWO_MINUTES = 1000 * 60 * 2;
    private Context context;
    private Location myLocation;

    public MyPositionStateListener(Context context)
    {
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location)
    {
        if(isBetterLocation(location, myLocation))
        {
            myLocation = location;
            Log.i("Location1", "location");
        }

        Toast.makeText(context, "Lat:"+myLocation.getLatitude() + "Lon:"+myLocation.getLongitude(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {}

    @Override
    public void onProviderEnabled(String provider)
    {}

    @Override
    public void onProviderDisabled(String provider)
    {}

    /** Determines whether one Location reading is better than the current Location fix
     * @param location  The new Location that you want to evaluate
     * @param currentBestLocation  The current Location fix, to which you want to compare the new one
     */
    protected boolean isBetterLocation(Location location, Location currentBestLocation)
    {
        if (currentBestLocation == null)
        {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        // If the new location is more than two minutes older, it must be worse
        if (isSignificantlyNewer) return true;
        else if (isSignificantlyOlder) return false;

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) return true;
        else if (isNewer && !isLessAccurate) return true;
        else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) return true;

        return false;
    }

    /** Checks whether two providers are the same */
    private boolean isSameProvider(String provider1, String provider2)
    {
        if (provider1 == null)
            return provider2 == null;

        return provider1.equals(provider2);
    }
}
