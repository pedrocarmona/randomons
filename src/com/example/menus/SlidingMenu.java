package com.example.menus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import com.example.activities.*;
import org.holoeverywhere.ArrayAdapter;
import org.holoeverywhere.widget.ListView;

public class SlidingMenu
{
    public SlidingMenu(final Context context, com.jeremyfeinstein.slidingmenu.lib.SlidingMenu sm)
    {
        //Adicionar items ao slidingmenu
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.menu_frame, null);
        ListView slidingMenuList = (ListView) view.findViewById(R.id.sliding_menu_list);
        String[] items = new String[]{"Home","My Randomons","My Items","Ranking","R-Guide","Map","Store","Medical Spot"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, android.R.id.text1, items);
        slidingMenuList.setAdapter(adapter);

        // customize the SlidingMenu
        sm.setTouchModeAbove(com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.TOUCHMODE_MARGIN);
        sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        sm.setShadowWidthRes(R.dimen.shadow_width);
        sm.setShadowDrawable(R.drawable.shadow);
        sm.setBehindScrollScale(0.25f);
        sm.setFadeDegree(0.25f);
        sm.setMenu(view);

        // adicionar listener

        slidingMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent;

                switch (position)
                {
                    case 0:
                        intent = new Intent(view.getContext(), MainMenu.class);
                        context.startActivity(intent);
                        //finish();
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), MyRandomons.class);
                        context.startActivity(intent);
                        //finish();
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), Items.class);
                        context.startActivity(intent);
                        //finish();
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), Ranking.class);
                        context.startActivity(intent);
                        //finish();
                        break;
                    case 4:
                        intent = new Intent(view.getContext(), MedicalSpot.class);
                        context.startActivity(intent);
                        //finish();
                        break;
                    case 5:
                        intent = new Intent(view.getContext(), MedicalSpot.class);
                        context.startActivity(intent);
                        //finish();
                        break;
                    case 6:
                        intent = new Intent(view.getContext(), Shop.class);
                        context.startActivity(intent);
                        //finish();
                        break;
                    case 7:
                        intent = new Intent(view.getContext(), MedicalSpot.class);
                        context.startActivity(intent);
                        //finish();
                        break;
                }
            }
        });
    }
}
