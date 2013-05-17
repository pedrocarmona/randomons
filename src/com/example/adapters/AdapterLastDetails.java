package com.example.adapters;

/**
 * Created with IntelliJ IDEA.
 * User: Telmo
 * Date: 17-05-2013
 * Time: 4:57
 * To change this template use File | Settings | File Templates.
 */

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.Event;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.Event;
import com.example.data.Randomon;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterLastDetails extends BaseAdapter
{
    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private ArrayList<Event> eventsList = new ArrayList<Event>();
    private int img;

    public AdapterLastDetails(Context context)
    {
        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    }

    public void addEvent(final Event evnt,int img)
    {
        this.img=img;
        eventsList.add(evnt);
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return eventsList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return eventsList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.last_events_item, null);
            holder = new ViewHolder();

            holder.evntDate = (TextView) convertView.findViewById(R.id.evt_date);
            holder.evntDescrip = (TextView) convertView.findViewById(R.id.evt_descript);
            holder.evntImg = (ImageView) convertView.findViewById(R.id.evt_img);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.evntDate.setText("Ataque: "+ eventsList.get(position).getDate());
        holder.evntDescrip.setText("Danos: "+eventsList.get(position).getDescription());
        holder.evntImg.setImageResource(img);
        return convertView;
    }

    static class ViewHolder
    {
        TextView evntDate;
        TextView evntDescrip;
        ImageView evntImg;
    }
}
