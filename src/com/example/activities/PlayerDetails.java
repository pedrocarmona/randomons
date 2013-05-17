package com.example.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterPlayerRandomonsList;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;
import org.holoeverywhere.widget.Toast;

public class PlayerDetails extends SlidingFragmentActivity {

    private Context ctx = this;
    private Button but_battle, but_trade;
    private TextView textName, textNat, textRank, textVictories, textRandomons;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_details);

        addSlidingMenu();

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
                Intent intent = new Intent(v.getContext(), Trade.class);
                PlayerDetails.this.startActivity(intent);
            }
        });
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
            setBehindContentView(R.layout.menu_frame);
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

        new SlidingMenu(this, getSlidingMenu());
    }

}