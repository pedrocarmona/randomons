package com.example.activities;

import android.os.Bundle;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockActivity;
import com.example.adapters.AdapterLastDetails;
import com.example.adapters.AdapterLastEvents;
import com.example.data.Event;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.ListView;

/**
 * Created with IntelliJ IDEA.
 * User: Telmo
 * Date: 16-05-2013
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class Randomon_Info extends SherlockActivity {

    public void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_randomons);

        ImageView imgPentagono = (ImageView) findViewById(R.id.pentagono);
        imgPentagono.setImageResource(R.drawable.img_pentagono);



        ListView leventsView = (ListView) findViewById(R.id.randomino_details);

        AdapterLastDetails allEvents = new AdapterLastDetails(this);

        leventsView.setAdapter(allEvents);

        //PARA EXEMPLO
        int img = R.drawable.randomom;
        for(int i = 0; i<20; i++){

            Event event = new Event((i+1)+" ",""+i*1.5+" HP");


            allEvents.addEvent(event,img);
        }

    }
}
