package com.example.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.adapters.AdapterCloseEvents;
import com.example.adapters.AdapterCloseEventsBase;
import com.example.adapters.AdapterLastEvents;
import com.example.data.Event;
import com.example.data.Item;
import com.example.data.Randomon;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

public class MainMenu extends SherlockFragmentActivity {
    private SharedPreferences mPreferences;
    final Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_menu);


        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

        if (mPreferences.contains("AuthToken")) {
            //loadTasksFromAPI(TASKS_URL);
            Log.v("erros", "Auth="+ mPreferences.getString("AuthToken", ""));
        } else {
            Log.v("erros", "devia comecar o login");

            Intent intent = new Intent(MainMenu.this,Login.class);
            startActivityForResult(intent, 0);
        }

        AdapterCloseEvents proxView = (AdapterCloseEvents) findViewById(R.id.prox_listview);
        AdapterCloseEventsBase proxAdapter = new AdapterCloseEventsBase(this);
        proxView.setAdapter(proxAdapter);

        for(int i = 0; i<20; i++)
            proxAdapter.addItem("Coisas");

        proxView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    Intent intent = new Intent(view.getContext(), PlayerDetails.class);
                    MainMenu.this.startActivity(intent);
                }
            }
        });

        ListView leventsView = (ListView) findViewById(R.id.levents_listview);

        AdapterLastEvents leventsAdapter = new AdapterLastEvents(this);

        leventsView.setAdapter(leventsAdapter);

        //PARA EXEMPLO
        for(int i = 0; i<20; i++)
            leventsAdapter.addEvent(new Event((i+1)+" minutos atras","Perdeu com o utilizador facadas."));

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v("erros", "no resume");

        if (mPreferences.contains("AuthToken")) {
            //loadTasksFromAPI(TASKS_URL);
            Log.v("erros", "Auth="+ mPreferences.getString("AuthToken", ""));

        } else {
            Log.v("erros", "devia comecar o login");

            Intent intent = new Intent(MainMenu.this,Login.class);
            startActivityForResult(intent, 0);
        }
    }

}
