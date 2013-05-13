package com.example.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;

import java.util.ArrayList;

public class AdapterCloseEventsBase extends BaseAdapter {

    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private static ArrayList<Integer> proxObjects = new ArrayList<Integer>();

    public AdapterCloseEventsBase(Context context){
        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    };


    public void addItem(int closeEventType)
    {
        proxObjects.add(closeEventType);
        notifyDataSetChanged();
    }

    public int getCount() {
        return proxObjects.size();
    }

    public Object getItem(int position) {
        return proxObjects.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null)
        {
            convertView = mInflater.inflate(R.layout.prox_view_item, null);
            holder = new ViewHolder();

            holder.CloseEventImg = (ImageView) convertView.findViewById(R.id.prox_event_image);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        if(proxObjects.get(position) == 1)
            holder.CloseEventImg.setImageResource(R.drawable.avatar_img);
        else if(proxObjects.get(position) == 2)
            holder.CloseEventImg.setImageResource(R.drawable.randomom);
        else if(proxObjects.get(position) == 3)
            holder.CloseEventImg.setImageResource(R.drawable.potion);
        else if(proxObjects.get(position) == 4)
            holder.CloseEventImg.setImageResource(R.drawable.item);

        return convertView;

    }

    static class ViewHolder
    {
        ImageView CloseEventImg;
    }

}
