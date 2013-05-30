package com.example.data;

import android.app.Application;
import android.location.Location;


public class SharedData extends Application {

    private static SharedData instance = null;
    private Player player = null;
    private Location location = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static SharedData getInstance() {
        return instance;
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

