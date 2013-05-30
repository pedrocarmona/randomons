package com.example.activities;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.actionbarsherlock.view.Menu;
import com.example.adapters.AdapterHorizontalList;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuInflater;
import com.example.adapters.AdapterCloseEventsBase;
import com.example.adapters.AdapterLastEvents;
import com.example.data.CloseEvent;
import com.example.data.Event;
import com.example.data.Player;
import com.example.data.SharedData;
import com.example.location.LocationReceiver;
import com.example.menus.SlidingMenu;
import com.example.others.Constants;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.drawable.ColorDrawable;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class MainMenu extends SlidingFragmentActivity implements Constants
{

    final Context ctx = this;

    private SharedPreferences mPreferences;
    private AdapterCloseEventsBase proxAdapter;
    private TextView usernameTV;
    private ImageView profPic;


    private Player playerLogged;
    private ArrayList<CloseEvent> closeEvents = new ArrayList<CloseEvent>();
    private ArrayList<Event> lastEvents = new ArrayList<Event>();
    private SharedData shared;
    private Dialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);
        shared = SharedData.getInstance();

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.help_main_menu);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        usernameTV = (TextView) findViewById(R.id.user_on);
        profPic = (ImageView) findViewById(R.id.avatar_img);


        /*TEMPORARY DATA*/
        closeEvents.add(new CloseEvent(1, "Patriota"));
        closeEvents.add(new CloseEvent(4, "Shop"));
        closeEvents.add(new CloseEvent(2, "Randobattle"));
        closeEvents.add(new CloseEvent(3, "Medic"));
        closeEvents.add(new CloseEvent(1, "Patriota"));
        closeEvents.add(new CloseEvent(4, "Shop"));
        closeEvents.add(new CloseEvent(2, "Patriota"));
        closeEvents.add(new CloseEvent(3, "Medic"));

        lastEvents.add(new Event("X minutos atras","Lost a battle against Chefe Marques", R.drawable.avatar_img));
        lastEvents.add(new Event("X minutos atras","Wild battle with Tetrauros.", R.drawable.tetrauros));
        lastEvents.add(new Event("X minutos atras","Bought items on shop.", R.drawable.shop));

        addSlidingMenu();

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);


        if(shared.getPlayer() != null) {

            usernameTV.setText(shared.getPlayer().getName());
//            shared.getPlayer().getPlayerImg();
            profPic.setImageResource(R.drawable.user_image);

        }
        else {
            Intent intent = new Intent(MainMenu.this,Login.class);
            startActivityForResult(intent, 0);
        }


        if (mPreferences.contains("AuthToken")) {
            //loadTasksFromAPI(TASKS_URL);
            Log.v("erros", "Auth="+ mPreferences.getString("AuthToken", ""));
        } else {
            Log.v("erros", "devia comecar o login");

            Intent intent = new Intent(MainMenu.this,Login.class);
            startActivityForResult(intent, 0);
        }

        profPic = (ImageView) findViewById(R.id.avatar_img);

        AdapterHorizontalList proxView = (AdapterHorizontalList) findViewById(R.id.prox_listview);
        proxAdapter = new AdapterCloseEventsBase(this);
        proxView.setAdapter(proxAdapter);

        for (CloseEvent clEvt : closeEvents)
            proxAdapter.addItem(clEvt);

        profPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // Toast.makeText(ctx, "Sou bonito eu sei!", Toast.LENGTH_LONG).show();
            }
        });

        proxView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            if (((CloseEvent) proxAdapter.getItem(position)).getCloseEventType() == 1) {
                Intent intent = new Intent(view.getContext(), PlayerDetails.class);
                MainMenu.this.startActivity(intent);
            } else if (((CloseEvent) proxAdapter.getItem(position)).getCloseEventType() == 2) {
                Bundle bnd = new Bundle();
                bnd.putSerializable("randomons_list", playerLogged.getRandomonCollection());
                Intent intent = new Intent(view.getContext(), Battle.class);
                intent.putExtras(bnd);
                MainMenu.this.startActivity(intent);
            } else if (((CloseEvent) proxAdapter.getItem(position)).getCloseEventType() == 3) {
                Intent intent = new Intent(view.getContext(), MedicalSpot.class);
                MainMenu.this.startActivity(intent);
            } else if (((CloseEvent) proxAdapter.getItem(position)).getCloseEventType() == 4) {
                Intent intent = new Intent(view.getContext(), Shop.class);
                MainMenu.this.startActivity(intent);
            }

            }
        });

        ListView leventsView = (ListView) findViewById(R.id.levents_listview);

        AdapterLastEvents leventsAdapter = new AdapterLastEvents(this);

        leventsView.setAdapter(leventsAdapter);

        for (Event lEvt : lastEvents)
            leventsAdapter.addEvent(lEvt);

        //###################################################

        Intent intent = new Intent(this, LocationReceiver.class);

        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        PendingIntent launchIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 10, launchIntent);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, launchIntent);

        //###################################################

        //Corrigir erros do Mapa com o SlidingMenu
        GoogleMapOptions op = new GoogleMapOptions();
        op.zOrderOnTop(true);
        SupportMapFragment supportMapFragment = SupportMapFragment.newInstance(op);

        GoogleMap mMap = supportMapFragment.getMap();

        setMapTransparent((ViewGroup) getWindow().getDecorView().getRootView());

        if(mMap != null)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            mMap.getUiSettings().setZoomControlsEnabled(false);
            //mMap.getUiSettings().setCompassEnabled(true);
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
    public void onResume() {
        super.onResume();
        Log.v("erros", "no resume");
/**/
        if (mPreferences.contains("AuthToken")) {
            //loadTasksFromAPI(TASKS_URL);
            Log.v("erros", "Auth="+ mPreferences.getString("AuthToken", ""));

        } else {
            Log.v("erros", "devia comecar o login");

            Intent intent = new Intent(MainMenu.this,Login.class);
            startActivityForResult(intent, 0);
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

            case R.id.help_btn:

                showHelpDialog();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void showHelpDialog() {

        dialog.setCancelable(true);
        dialog.show();
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

        new SlidingMenu(this, getSlidingMenu(), MAIN_MENU);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.actionbarmenu, menu);
        return true;
    }

}
