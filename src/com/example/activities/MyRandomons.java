package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.actionbarsherlock.view.MenuItem;
import com.antipodalwall.AntipodalWallLayout;
import com.example.data.Move;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.widget.Toast;

import java.util.ArrayList;

public class MyRandomons extends SlidingActivity
{
    private ArrayList<Randomon> playerRandomons = new ArrayList<Randomon>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.randomons_my);

        addSlidingMenu();


        AntipodalWallLayout layout = (AntipodalWallLayout)findViewById(R.id.antipodal_wall);

        Toast.makeText(getApplicationContext(),"shit",Toast.LENGTH_LONG).show();

        /*TEMPORARY DATA*/
        ArrayList<Move> moves1 = new ArrayList<Move>();
        moves1.add(new Move("fire",R.drawable.quest_mark, 50));
        playerRandomons.add(new Randomon("Catzinga", Randomon.PSYCHIC, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,1));
        playerRandomons.add(new Randomon("Tetrauros", Randomon.PREHISTORIC, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.tetrauros,2));
        playerRandomons.add(new Randomon("Canibalape", Randomon.CANNIBAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,3));
        playerRandomons.add(new Randomon("Chinelong", Randomon.FLYING, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.chinelong,4));
        playerRandomons.add(new Randomon("Ponycorn", Randomon.MYTHICAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.ponycorn,5));
        playerRandomons.add(new Randomon("Cyclosnake", Randomon.POISONOUS, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,6));

        for (Randomon plRandomons : playerRandomons) {
            ImageView img = new ImageView(this);
            img.setImageResource(plRandomons.getPicId());
            layout.addView(img);
            addImgListener(img, plRandomons);
        }

    }

    public void addImgListener(ImageView img, final Randomon plRandomons){

        img.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Bundle bnd = new Bundle();
                bnd.putSerializable("randomon", plRandomons);
                Intent intent = new Intent(v.getContext(), RandomonInfo.class);
                intent.putExtras(bnd);
                MyRandomons.this.startActivity(intent);
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