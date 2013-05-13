package com.example.activities;

import android.content.Context;
import android.content.Intent;
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
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

import java.util.ArrayList;

public class PlayerDetails extends SherlockFragmentActivity {

    private Context ctx = this;
    private Button but_battle, but_trade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_details);

        but_battle = (Button) findViewById(R.id.but_battle);
        but_trade = (Button) findViewById(R.id.but_trade);

        ListView plRandomonsView = (ListView) findViewById(R.id.pl_randomons_listview);

        AdapterPlayerRandomonsList plRandomonsAdapter = new AdapterPlayerRandomonsList(this);

        plRandomonsView.setAdapter(plRandomonsAdapter);

        //PARA EXEMPLO
        for(int i = 0; i<20; i++)
            plRandomonsAdapter.addItem(new Randomon("Pikachu", 15));

        but_battle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ctx,"No violence",Toast.LENGTH_LONG).show();
            }
        });

        but_trade.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ctx,"No business!",Toast.LENGTH_LONG).show();
            }
        });
    }

}