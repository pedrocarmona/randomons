package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.SlideMenuItem;
import org.holoeverywhere.ArrayAdapter;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterSlideMenu extends ArrayAdapter<SlideMenuItem> {

    Context context;
    int layoutResourceId;
    ArrayList<SlideMenuItem> sMenuItems = new ArrayList<SlideMenuItem>();

    public AdapterSlideMenu(Context context, int layoutResourceId, ArrayList<SlideMenuItem> sMenuItems) {
        super(context, layoutResourceId, sMenuItems);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.sMenuItems = sMenuItems;
    }

    public void addView(final SlideMenuItem sMenuItem){
        sMenuItems.add(sMenuItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SlideMenuHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new SlideMenuHolder();
            holder.slideMenuIcon = (ImageView)row.findViewById(R.id.slide_menu_icon);
            holder.slideMenuText = (TextView)row.findViewById(R.id.slide_menu_txt);

            row.setTag(holder);
        }
        else
        {
            holder = (SlideMenuHolder)row.getTag();
        }

        holder.slideMenuText.setText(sMenuItems.get(position).getSlideText());
        holder.slideMenuIcon.setImageResource(sMenuItems.get(position).getSlideIcon());

        return row;
    }

    static class SlideMenuHolder
    {
        ImageView slideMenuIcon;
        TextView slideMenuText;
    }

}
