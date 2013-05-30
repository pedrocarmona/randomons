package com.example.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.example.others.Constants;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.widget.TextView;

public class RandomonDexInfo extends SlidingActivity implements Constants
{

    private Randomon randomonSelected;
    private ImageView randomonDexImg;
    private TextView randomonDexName, randomonDexType, randomonDexDesc;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randomon_dex_info);

        randomonSelected = (Randomon) getIntent().getSerializableExtra("randomon");
        randomonDexImg = (ImageView) findViewById(R.id.randomon_dex_img);
        randomonDexName = (TextView) findViewById(R.id.randomon_dex_name);
        randomonDexType = (TextView) findViewById(R.id.randomon_dex_type);
        randomonDexDesc = (TextView) findViewById(R.id.randomon_dex_desc);


        addSlidingMenu();

        final ActionBar bar = getSupportActionBar();
        bar.setTitle("Randomon Info");

        randomonDexImg.setImageResource(randomonSelected.getPicId());
        randomonDexName.setText(randomonSelected.getName());
        randomonDexType.setText(randomonSelected.getType());
        randomonDexDesc.setText(randomonSelected.getDescription());
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

        new SlidingMenu(this, getSlidingMenu(), R_GUIDE);
    }
}