package com.example.alerts;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockActivity;
import com.example.activities.R;
import org.holoeverywhere.widget.TextView;
import org.holoeverywhere.widget.Toast;

public class ProximityAlert extends SherlockActivity
{
    private ImageView alertClose;
    private ImageView alertImage;
    private TextView alertText;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.proximity_alert);

        setContentView(R.layout.map);

//        alertClose = (ImageView) findViewById(R.id.close);
//        alertImage = (ImageView) findViewById(R.id.alert_image);
//        alertText = (TextView) findViewById(R.id.alert_text);

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
                Toast.makeText(v.getContext(), "Ir para o sítio correspondente!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
