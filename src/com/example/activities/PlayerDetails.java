package com.example.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterPlayerRandomonsList;
import com.example.data.Move;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;
import org.holoeverywhere.widget.Toast;

import java.util.ArrayList;

public class PlayerDetails extends SlidingFragmentActivity {

    private Context ctx = this;
    private Button but_battle, but_trade;
    private TextView textName, textNat, textRank, textVictories, textRandomons;
    private ListView playerRandomonList;
    private AdapterPlayerRandomonsList plRandomonsAdapter;

    private ArrayList<Randomon> userRandomons = new ArrayList<Randomon>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.player_details);


        ArrayList<Move> moves1 = new ArrayList<Move>();
        moves1.add(new Move("fire",1,1,1,1,1.1,"ataque disc", Randomon.FIRE));
        userRandomons.add(new Randomon("Catzinga", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,1));
        userRandomons.add(new Randomon("Canibalape", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,2));
        userRandomons.add(new Randomon("Cyclosnake", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,3));
        userRandomons.add(new Randomon("T-Lion", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.tetrauros,4));
        userRandomons.add(new Randomon("Ponycorn", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.ponycorn,5));
        userRandomons.add(new Randomon("Chinelong", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.chinelong,6));



        addSlidingMenu();

        but_battle = (Button) findViewById(R.id.but_battle);
        but_trade = (Button) findViewById(R.id.but_trade);
        textName = (TextView) findViewById(R.id.user_name);
        textNat = (TextView) findViewById(R.id.user_nat);
        textRank = (TextView) findViewById(R.id.user_rank);
        textVictories = (TextView) findViewById(R.id.user_victories);
        textRandomons = (TextView) findViewById(R.id.user_randomons);

        textName.setText(Html.fromHtml("<b>Name: </b>" + "O Patriota"));
        textNat.setText(Html.fromHtml("<b>Country: </b>" + "Portugal"));
        textRank.setText(Html.fromHtml("<b>Rank: </b>" + "1º"));
        textVictories.setText(Html.fromHtml("<b>Battles Won: </b>" + "#100"));
        textRandomons.setText(Html.fromHtml("<b># Randomons: </b>" + "#7"));

        playerRandomonList = (ListView) findViewById(R.id.pl_randomons_listview);

        ListView plRandomonsView = (ListView) findViewById(R.id.pl_randomons_listview);

        plRandomonsAdapter = new AdapterPlayerRandomonsList(this);

        plRandomonsView.setAdapter(plRandomonsAdapter);

        for(int i = 0; i<userRandomons.size(); i++)
            plRandomonsAdapter.addItem(userRandomons.get(i));

        but_battle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), Battle.class);
                PlayerDetails.this.startActivity(intent);
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

        playerRandomonList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Carrega no randomon e deve abrir o menu dex do randomon
                Bundle bnd = new Bundle();
                bnd.putSerializable("randomon", ((Randomon) plRandomonsAdapter.getItem(position)));
                Intent intent = new Intent(view.getContext(), RandomonDexInfo.class);
                intent.putExtras(bnd);
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