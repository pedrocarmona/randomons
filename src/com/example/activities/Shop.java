package com.example.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterItemList;
import com.example.data.CaptureItem;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.drawable.ColorDrawable;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

public class Shop extends SlidingActivity
{
    private Context ctx = this;
    private Button clean, buy;

    private Dialog dialog;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.shop);

        final ActionBar bar = getSupportActionBar();
        bar.setTitle("Shop");

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.help_shop);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        addSlidingMenu();

        ListView listView = (ListView) findViewById(R.id.items_list);

        clean = (Button) findViewById(R.id.shop_clean_button);
        buy = (Button) findViewById(R.id.shop_buy_button);


        AdapterItemList adapter = new AdapterItemList(this);

        listView.setAdapter(adapter);

        //PARA EXEMPLO
        adapter.addItem(new CaptureItem(R.drawable.psn, "Poison Capture Item", "Useful for everything and something else."));
        adapter.addItem(new CaptureItem(R.drawable.psy, "Psychic Capture Item", "Useful for everything and something else."));
        adapter.addItem(new CaptureItem(R.drawable.mth, "Mythical Capture Item", "Useful for everything and something else."));
        adapter.addItem(new CaptureItem(R.drawable.cnb, "Canibal Capture Item", "Useful for everything and something else."));
        adapter.addItem(new CaptureItem(R.drawable.ph, "Prehistoric Capture Item", "Useful for everything and something else."));
        adapter.addItem(new CaptureItem(R.drawable.fl, "Flying Capture Item", "Useful for everything and something else."));


        addClickListeners();
    }

    private void addClickListeners()
    {
        clean.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ctx,"Tudo Limpo",Toast.LENGTH_LONG).show();

            }
        });

        buy.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(ctx,"Tudo comprado",Toast.LENGTH_LONG).show();

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

        new SlidingMenu(this, getSlidingMenu());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.actionbarmenu, menu);
        return true;
    }

}