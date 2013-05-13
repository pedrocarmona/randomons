package com.example.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.adapters.AdapterCloseEvents;
import com.example.adapters.AdapterLastEvents;
import com.example.adapters.AdapterPlayerRandomonsList;
import com.example.adapters.AdapterRandomonsList;
import com.example.data.Event;
import com.example.data.Randomon;
import org.holoeverywhere.widget.ListView;

import java.util.ArrayList;

public class PlayerDetails extends SherlockFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_details);

        ListView plRandomonsView = (ListView) findViewById(R.id.pl_randomons_listview);

        AdapterPlayerRandomonsList plRandomonsAdapter = new AdapterPlayerRandomonsList(this);

        plRandomonsView.setAdapter(plRandomonsAdapter);

        //PARA EXEMPLO
        for(int i = 0; i<20; i++)
            plRandomonsAdapter.addItem(new Randomon("Pikachu", 15));

    }

}