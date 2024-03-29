package com.example.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterPlayerRandomonsList;
import com.example.data.Move;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.example.others.Constants;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.drawable.ColorDrawable;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class Trade extends SlidingActivity implements Constants
{
    private int setRandomon = 1;
    private Context context = this;
    private LinearLayout myRandomonTrade, playerRandomonTrade;
    private ImageView myRandoTradeImg, playerRandoTradeImg, tradeIcon;
    private TextView myRandoTradeName, playerRandoTradeName;
    private Dialog dialog;
    private ListView randomonsDialog;
    private AdapterPlayerRandomonsList myRandomonsAdapter, playerRandomonsAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trade_menu);

        addSlidingMenu();

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.randomons_list_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        findViews();

        addOnClickListeners();

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

        new SlidingMenu(this, getSlidingMenu(), RANKING);
    }

    private void findViews(){

        myRandomonTrade = (LinearLayout) findViewById(R.id.my_trade_randomon);
        playerRandomonTrade = (LinearLayout) findViewById(R.id.player_trade_randomon);
        myRandoTradeImg = (ImageView) findViewById(R.id.my_randomon_trade_img);
        playerRandoTradeImg = (ImageView) findViewById(R.id.player_randomon_trade_img);
        myRandoTradeName = (TextView) findViewById(R.id.my_randomon_trade_name);
        playerRandoTradeName = (TextView) findViewById(R.id.player_randomon_trade_name);
        randomonsDialog = (ListView) dialog.findViewById(R.id.randomons_dialog);
        tradeIcon = (ImageView) findViewById(R.id.trade_icon);

    }

    private void addOnClickListeners(){

        myRandomonTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myRandomonsAdapter = new AdapterPlayerRandomonsList(context);
                randomonsDialog.setAdapter(myRandomonsAdapter);

                myRandomonsAdapter.addItem(new Randomon("Cyclosnake", Randomon.POISONOUS, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,3));
                myRandomonsAdapter.addItem(new Randomon("Tetrauros", Randomon.PREHISTORIC, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.tetrauros,3));
                myRandomonsAdapter.addItem(new Randomon("Canibalape", Randomon.CANNIBAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,3));
                myRandomonsAdapter.addItem(new Randomon("Ponycorn", Randomon.MYTHICAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.ponycorn,3));

                setRandomon = 1;
                dialog.setCancelable(true);
                dialog.show();

            }
        });

        playerRandomonTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerRandomonsAdapter = new AdapterPlayerRandomonsList(context);
                randomonsDialog.setAdapter(playerRandomonsAdapter);



                playerRandomonsAdapter.addItem(new Randomon("Catzinga", Randomon.PSYCHIC, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,3));
                playerRandomonsAdapter.addItem(new Randomon("Chinelong", Randomon.FLYING, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.chinelong,3));
                playerRandomonsAdapter.addItem(new Randomon("Cyclosnake", Randomon.POISONOUS, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,3));
                playerRandomonsAdapter.addItem(new Randomon("Canibalape", Randomon.CANNIBAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,3));

                setRandomon = 2;
                dialog.setCancelable(true);
                dialog.show();

            }
        });

        randomonsDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (setRandomon == 1){
                    myRandoTradeImg.setImageResource(((Randomon)myRandomonsAdapter.getItem(position)).getPicId());
                    myRandoTradeName.setText(((Randomon)myRandomonsAdapter.getItem(position)).getName());
                }else{
                    playerRandoTradeImg.setImageResource(((Randomon)playerRandomonsAdapter.getItem(position)).getPicId());
                    playerRandoTradeName.setText(((Randomon)playerRandomonsAdapter.getItem(position)).getName());
                }
                dialog.dismiss();

            }
        });

        tradeIcon.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                refreshTrade();
            }
        });


    }

    public void refreshTrade(){



    }
}
