package com.example.alerts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockActivity;
import com.example.activities.Battle;
import com.example.activities.R;
import org.holoeverywhere.widget.TextView;

public class ProximityAlert extends SherlockActivity
{
    private ImageView alertClose;
    private ImageView alertImage;
    private TextView alertText;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proximity_alert);

        alertClose = (ImageView) findViewById(R.id.close);
        alertImage = (ImageView) findViewById(R.id.alert_image);
        alertText = (TextView) findViewById(R.id.alert_text);

        String name = getIntent().getExtras().getString("name");

        alertText.setText("A wild "+name+" appears!");

        if(name.equals("Canibalape"))
            alertImage.setBackgroundResource(R.drawable.canibalape);
        else if(name.equals("Tetrauros"))
            alertImage.setBackgroundResource(R.drawable.tetrauros);
        else if(name.equals("Chinelong"))
            alertImage.setBackgroundResource(R.drawable.chinelong);
        else if(name.equals("Catzinga"))
            alertImage.setBackgroundResource(R.drawable.catzinga);
        else if(name.equals("Ponycorn"))
            alertImage.setBackgroundResource(R.drawable.ponycorn);
        else if(name.equals("Cyclosnake"))
            alertImage.setBackgroundResource(R.drawable.cyclosnake);

        addClickListeners();
    }

    private void addClickListeners()
    {
        alertClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });

        alertImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), Battle.class);

                startActivity(intent);

                finish();
            }
        });
    }
}
