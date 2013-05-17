package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterItemList;
import com.example.data.CaptureItem;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;

public class Shop extends SlidingActivity
{
    private Button clean;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shop);

        final ActionBar bar = getSupportActionBar();
        bar.setTitle("Shop");

        addSlidingMenu();

        ListView listView = (ListView) findViewById(R.id.items_list);

        clean = (Button) findViewById(R.id.shop_clean_button);

        AdapterItemList adapter = new AdapterItemList(this);

        listView.setAdapter(adapter);

        //PARA EXEMPLO
        for(int i = 0; i<20; i++)
            adapter.addItem(new CaptureItem("Swiss Army Knife", "Useful for everything and something else."));

        addClickListeners();
    }

    private void addClickListeners()
    {
        clean.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), Ranking.class);
                Shop.this.startActivity(intent);
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