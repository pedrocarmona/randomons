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
    private static ArrayList<String> proxObjects = new ArrayList<String>();

    public AdapterCloseEventsBase(Context context){
        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    };


    public void addItem(String txt)
    {
        proxObjects.add(txt);
        notifyDataSetChanged();
    }

    public int getCount() {
        return proxObjects.size();
    }

    public Object getItem(int position) {
        return null;
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

            holder.CloseEventImg = (ImageView) convertView.findViewById(R.id.avatar_img);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;

    }

    static class ViewHolder
    {
        ImageView CloseEventImg;
    }

}
