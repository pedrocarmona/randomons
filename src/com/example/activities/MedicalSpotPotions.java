package com.example.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.example.adapters.AdapterPotionList;
import com.example.data.Potion;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

public class MedicalSpotPotions extends SherlockFragment
{
    private View view;
    private Button cleanButton, buyButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.medical_spot_potions, container, false);

        ListView listView = (ListView) view.findViewById(R.id.potions_list);

        AdapterPotionList adapter = new AdapterPotionList(view.getContext());

        listView.setAdapter(adapter);

        //PARA EXEMPLO
        for(int i = 0; i<20; i++)
            adapter.addItem(new Potion("Whisky", "Pain reliever"));

        addClickListeners();

        return view;
    }

    private void addClickListeners()
    {
        cleanButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getSherlockActivity(), "Tudo curado", Toast.LENGTH_LONG).show();
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getSherlockActivity(), "Tudo comprado", Toast.LENGTH_LONG).show();
            }
        });
    }

}
