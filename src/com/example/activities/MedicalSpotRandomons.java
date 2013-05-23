package com.example.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.adapters.AdapterRandomonsList;
import com.example.data.Randomon;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

public class MedicalSpotRandomons extends SherlockFragment
{
    private View view;
    private Button heal;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.medical_spot_randomons, container, false);

        ListView listView = (ListView) view.findViewById(R.id.randomons_list);

        heal = (Button) view.findViewById(R.id.heal_button);

        AdapterRandomonsList adapter = new AdapterRandomonsList(view.getContext());

        listView.setAdapter(adapter);

        //PARA EXEMPLO
        for(int i = 0; i<10; i++)
            adapter.addItem(new Randomon("Coiso "+i, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal", R.drawable.randomom));

        addClickListeners();

        return view;
    }

    private void addClickListeners()
    {
        heal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getSherlockActivity(),"Tudo curado",Toast.LENGTH_LONG).show();
            }
        });
    }
}
