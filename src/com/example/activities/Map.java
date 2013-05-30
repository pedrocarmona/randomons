package com.example.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.example.data.Globals;
import com.example.menus.SlidingMenu;
import com.example.others.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;

public class Map extends SlidingActivity implements Constants
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        final ActionBar bar = getSupportActionBar();
        bar.setTitle("Map");

        addSlidingMenu();

        //Corrigir erros do Mapa com o SlidingMenu
        GoogleMap mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();

        setMapTransparent((ViewGroup) getWindow().getDecorView().getRootView());

        if(mMap != null)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mMap.setMyLocationEnabled(true);

            Location location = mMap.getMyLocation();

            mMap.addMarker(new MarkerOptions()
                    .position(Globals.hospital)
                    .title("Hospital")
                    .snippet("Come here to heal your Randomons for free!")
                    .icon(BitmapDescriptorFactory.fromBitmap(
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.medics), 10, 10, false))));

            mMap.addMarker(new MarkerOptions()
                    .position(Globals.shop)
                    .title("Shop")
                    .snippet("Come here to by your items!")
                    .icon(BitmapDescriptorFactory.fromBitmap(
                            Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.shop),10,10,false))));

            if(location != null)
            {
                LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());

                // Move the camera instantly to user location with a zoom of 15.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15));

                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomIn());
            }

        }
    }

    private void setMapTransparent(ViewGroup group)
    {
        int childCount = group.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            View child = group.getChildAt(i);

            if (child instanceof ViewGroup)
            {
                setMapTransparent((ViewGroup) child);
            }
            else if (child instanceof SurfaceView)
            {
                child.setBackgroundColor(0x00000000);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                toggle();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addSlidingMenu()
    {
        com.jeremyfeinstein.slidingmenu.lib.SlidingMenu sm = getSlidingMenu();
        // check if the content frame contains the menu frame
        if (findViewById(R.id.menu_frame) == null)
        {
            setBehindContentView(R.layout.slide_menu);
            sm.setSlidingEnabled(true);
            sm.setTouchModeAbove(com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.TOUCHMODE_FULLSCREEN);
            // show home as up so we can toggle
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else
        {
            // add a dummy view
            View v = new View(this);
            setBehindContentView(v);
            sm.setSlidingEnabled(false);
            sm.setTouchModeAbove(com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.TOUCHMODE_NONE);
        }

        new SlidingMenu(this, getSlidingMenu(), MAP);
    }
}
