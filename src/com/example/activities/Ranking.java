package com.example.activities;


import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.example.adapters.AdapterUserList;
import com.example.data.User;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

public class Ranking extends SherlockActivity {


    float historicX = Float.NaN;
    static final int DELTA = 50;
    boolean swiped = false;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ranking);

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

                    String msg =  "Swiped left on row  " + (id+1) + ".";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                    swiped = false;
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

}
