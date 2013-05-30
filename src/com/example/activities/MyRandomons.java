package com.example.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterMyRandomonImg;
import com.example.data.Move;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.example.others.Constants;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.drawable.ColorDrawable;
import org.holoeverywhere.widget.GridView;

import java.util.ArrayList;

public class MyRandomons extends SlidingActivity implements Constants
{
    private ArrayList<Randomon> playerRandomons = new ArrayList<Randomon>();
    private AdapterMyRandomonImg myRandoAdapter;

    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.myrandomon_grid);

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.help_my_randomons);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        addSlidingMenu();

        final ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("My Randomons");

        //AntipodalWallLayout layout = (AntipodalWallLayout)findViewById(R.id.antipodal_wall);

        GridView randoGrid = (GridView) findViewById(R.id.grid_randomons);

        myRandoAdapter = new AdapterMyRandomonImg(this);

        randoGrid.setAdapter(myRandoAdapter);
        /*TEMPORARY DATA*/
        ArrayList<Move> moves1 = new ArrayList<Move>();
        moves1.add(new Move("fire",R.drawable.quest_mark, 50));
        playerRandomons.add(new Randomon("Catzinga", Randomon.PSYCHIC, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,1));
        playerRandomons.add(new Randomon("Tetrauros", Randomon.PREHISTORIC, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.tetrauros,2));
        playerRandomons.add(new Randomon("Canibalape", Randomon.CANNIBAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,3));
        playerRandomons.add(new Randomon("Chinelong", Randomon.FLYING, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.chinelong,4));
        playerRandomons.add(new Randomon("Ponycorn", Randomon.MYTHICAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.ponycorn,5));
        playerRandomons.add(new Randomon("Cyclosnake", Randomon.POISONOUS, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,6));

        for (Randomon playerRandomon : playerRandomons) {
            playerRandomon.setMoves(moves1);
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

        new SlidingMenu(this, getSlidingMenu(), MY_RANDOMONS);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.actionbarmenu, menu);
        return true;
    }
}