package com.example.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.Html;
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
import org.holoeverywhere.widget.TextView;
import org.holoeverywhere.widget.Toast;

import java.util.ArrayList;

public class PlayerDetails extends SherlockFragmentActivity {

    private Context ctx = this;
    private Button but_battle, but_trade;
    private TextView textName, textNat, textRank, textVictories, textRandomons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_details);

        but_battle = (Button) findViewById(R.id.but_battle);
        but_trade = (Button) findViewById(R.id.but_trade);
        textName = (TextView) findViewById(R.id.user_name);
        textNat = (TextView) findViewById(R.id.user_nat);
        textRank = (TextView) findViewById(R.id.user_rank);
        textVictories = (TextView) findViewById(R.id.user_victories);
        textRandomons = (TextView) findViewById(R.id.user_randomons);

        textName.setText(Html.fromHtml("<b>Name: </b>" + "John Ramboias"));
        textNat.setText(Html.fromHtml("<b>Country: </b>" + "Hawai"));
        textRank.setText(Html.fromHtml("<b>Rank: </b>" + "-5000"));
        textVictories.setText(Html.fromHtml("<b>Battles Won: </b>" + "-1"));
        textRandomons.setText(Html.fromHtml("<b># Randomons: </b>" + "Nem um"));


        ListView plRandomonsView = (ListView) findViewById(R.id.pl_randomons_listview);

        AdapterPlayerRandomonsList plRandomonsAdapter = new AdapterPlayerRandomonsList(this);

        plRandomonsView.setAdapter(plRandomonsAdapter);

        for(int i = 0; i<20; i++)
            plRandomonsAdapter.addItem(new Randomon("Randomon "+i, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal"));

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