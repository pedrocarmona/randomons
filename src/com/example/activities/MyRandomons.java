package com.example.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterMyRandomonImg;
import com.example.data.Move;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.widget.GridView;

import java.util.ArrayList;

public class MyRandomons extends SlidingActivity
{
    private ArrayList<Randomon> playerRandomons = new ArrayList<Randomon>();
    private AdapterMyRandomonImg myRandoAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.myrandomon_grid);

        addSlidingMenu();


        //AntipodalWallLayout layout = (AntipodalWallLayout)findViewById(R.id.antipodal_wall);

        GridView randoGrid = (GridView) findViewById(R.id.grid_randomons);

        myRandoAdapter = new AdapterMyRandomonImg(this);

        randoGrid.setAdapter(myRandoAdapter);
        /*TEMPORARY DATA*/
        ArrayList<Move> moves1 = new ArrayList<Move>();
        moves1.add(new Move("fire",R.drawable.quest_mark, 50));
        playerRandomons.add(new Randomon("Catzinga", "Psychic", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,1,moves1));
        playerRandomons.add(new Randomon("Tetrauros", "Prehistoric", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.tetrauros,2,moves1));
        playerRandomons.add(new Randomon("Canibalape", "Canibal", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,3,moves1));
        playerRandomons.add(new Randomon("Chinelong", "Flying", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.chinelong,4,moves1));
        playerRandomons.add(new Randomon("Ponycorn", "Mythical", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.ponycorn,5,moves1));
        playerRandomons.add(new Randomon("Cyclosnake", "Poison", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,6,moves1));
        playerRandomons.add(new Randomon("Catzinga", "Psychic", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,1,moves1));
        playerRandomons.add(new Randomon("Tetrauros", "Prehistoric", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.tetrauros,2,moves1));
        playerRandomons.add(new Randomon("Canibalape", "Canibal", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,3,moves1));
        playerRandomons.add(new Randomon("Chinelong", "Flying", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.chinelong,4,moves1));
        playerRandomons.add(new Randomon("Ponycorn", "Mythical", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.ponycorn,5,moves1));
        playerRandomons.add(new Randomon("Cyclosnake", "Poison", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,6,moves1));
        playerRandomons.add(new Randomon("Catzinga", "Psychic", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,1,moves1));
        playerRandomons.add(new Randomon("Tetrauros", "Prehistoric", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.tetrauros,2,moves1));
        playerRandomons.add(new Randomon("Canibalape", "Canibal", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,3,moves1));
        playerRandomons.add(new Randomon("Chinelong", "Flying", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.chinelong,4,moves1));
        playerRandomons.add(new Randomon("Ponycorn", "Mythical", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.ponycorn,5,moves1));
        playerRandomons.add(new Randomon("Cyclosnake", "Poison", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,6,moves1));

        for (Randomon playerRandomon : playerRandomons) {
            myRandoAdapter.addItem(playerRandomon);
        }

        randoGrid.setOnItemClickListener(new GridView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bnd = new Bundle();
                bnd.putSerializable("randomon", (Randomon) myRandoAdapter.getItem(position));
                Intent intent = new Intent(view.getContext(), RandomonInfo.class);
                intent.putExtras(bnd);
                MyRandomons.this.startActivity(intent);            }
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
}