package com.example.menus;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import com.example.activities.*;
import com.example.activities.R;
import com.example.adapters.AdapterSlideMenu;
import com.example.data.SlideMenuItem;
import org.holoeverywhere.ArrayAdapter;
import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.ListView;

import java.util.ArrayList;

public class SlidingMenu extends Activity {

    private LayoutInflater inflater;
    private View view;
    private ListView slidingMenuList;
    private ArrayList<SlideMenuItem> sMenuItems;
    private AdapterSlideMenu adapterSMenu;

    public SlidingMenu(final Context context, com.jeremyfeinstein.slidingmenu.lib.SlidingMenu sm) {
        //Adicionar items ao slidingmenu
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.slide_menu, null);
        slidingMenuList = (ListView) view.findViewById(R.id.sliding_menu_list);
        sMenuItems = new ArrayList<SlideMenuItem>();
        sMenuItems.add(new SlideMenuItem(R.drawable.home_smenu, "Home"));
        sMenuItems.add(new SlideMenuItem(R.drawable.my_randomons_smenu, "My Randomons"));
        sMenuItems.add(new SlideMenuItem(R.drawable.itens_smenu, "My Items"));
        sMenuItems.add(new SlideMenuItem(R.drawable.ranking_smenu, "Ranking"));
        sMenuItems.add(new SlideMenuItem(R.drawable.r_guide_smenu, "R-Guide"));
        sMenuItems.add(new SlideMenuItem(R.drawable.map_smenu, "Map"));
        sMenuItems.add(new SlideMenuItem(R.drawable.shop_smenu, "Shop"));
        sMenuItems.add(new SlideMenuItem(R.drawable.medics_smenu, "Medical Spot"));

        adapterSMenu = new AdapterSlideMenu(context,R.layout.slide_menu_item,sMenuItems);
        slidingMenuList.setAdapter(adapterSMenu);

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
