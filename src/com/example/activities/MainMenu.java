package com.example.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.adapters.AdapterHorizontalList;
import com.example.adapters.AdapterCloseEventsBase;
import com.example.adapters.AdapterLastEvents;
import com.example.data.CloseEvent;
import com.example.data.Event;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

public class MainMenu extends SherlockFragmentActivity {
    private SharedPreferences mPreferences;
    private AdapterCloseEventsBase proxAdapter;
    private ImageView profPic;

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

        profPic = (ImageView) findViewById(R.id.avatar_img);

        AdapterHorizontalList proxView = (AdapterHorizontalList) findViewById(R.id.prox_listview);
        proxAdapter = new AdapterCloseEventsBase(this);
        proxView.setAdapter(proxAdapter);

        proxAdapter.addItem(new CloseEvent(1, "Monteirovsky"));
        proxAdapter.addItem(new CloseEvent(4, "Shop"));
        proxAdapter.addItem(new CloseEvent(2, "Randobattle"));
        proxAdapter.addItem(new CloseEvent(3, "Medic"));
        proxAdapter.addItem(new CloseEvent(1, "Monteirovsky"));
        proxAdapter.addItem(new CloseEvent(4, "Shop"));
        proxAdapter.addItem(new CloseEvent(2, "Randobattle"));
        proxAdapter.addItem(new CloseEvent(3, "Medic"));

        profPic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(), Ranking.class);
                MainMenu.this.startActivity(intent);            }
        });

        proxView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (((CloseEvent) proxAdapter.getItem(position)).getCloseEventType() == 1) {
                    Intent intent = new Intent(view.getContext(), PlayerDetails.class);
                    MainMenu.this.startActivity(intent);
                } else if (((CloseEvent) proxAdapter.getItem(position)).getCloseEventType() == 2) {
                    Toast.makeText(ctx, "Randomon escaped", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(view.getContext(), Shop.class);
//                    MainMenu.this.startActivity(intent);
                } else if (((CloseEvent) proxAdapter.getItem(position)).getCloseEventType() == 3) {
                    Intent intent = new Intent(view.getContext(), MedicalSpot.class);
                    MainMenu.this.startActivity(intent);
                } else if (((CloseEvent) proxAdapter.getItem(position)).getCloseEventType() == 4) {
                    Intent intent = new Intent(view.getContext(), Shop.class);
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
