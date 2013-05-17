package com.example.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterUserList;
import com.example.data.User;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

public class Ranking extends SlidingActivity
{
    float historicX = Float.NaN;
    static final int DELTA = 50;
    boolean swiped = false;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ranking);

        addSlidingMenu();

        final ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Ranking");

        ListView listView = (ListView) findViewById(R.id.users_list);

        AdapterUserList adapter = new AdapterUserList(this);

        listView.setAdapter(adapter);

        //PARA EXEMPLO
        for(int i = 0; i<500; i++)
            if(i%2 == 0)
                adapter.addUser(new User((i+1), "Monteiro", 2094851));
           else
                adapter.addUser(new User((i+1), "Carmonaman", 2094852));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(swiped) {

                    String msg =  "Open profile from the user in the place " + (id+1) + ".";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                    swiped = false;

                    Intent intent;
                    intent = new Intent(view.getContext(), PlayerDetails.class);
                    Ranking.this.startActivity(intent);
                }

            }
        });

        listView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        historicX = event.getX();
                        break;

                    case MotionEvent.ACTION_UP:

                        if(event.getX() - historicX < -DELTA) {

                            swiped = true;
                        }
                        break;

                    default: return false;
                }
                return false;
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
