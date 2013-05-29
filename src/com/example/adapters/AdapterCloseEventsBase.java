package com.example.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.CloseEvent;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterCloseEventsBase extends BaseAdapter {

    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private static ArrayList<CloseEvent> closeEvents = new ArrayList<CloseEvent>();

    public AdapterCloseEventsBase(Context context){
        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    };


    public void addItem(CloseEvent clEvent)
    {
        closeEvents.add(clEvent);
        notifyDataSetChanged();
    }

    public int getCount() {
        return closeEvents.size();
    }

    public Object getItem(int position) {
        return closeEvents.get(position);
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
            holder.CloseEventDesc = (TextView) convertView.findViewById(R.id.cl_evt_desc);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        if(closeEvents.get(position).getCloseEventType() == 1)
            holder.CloseEventImg.setImageResource(R.drawable.patriota);
        else if(closeEvents.get(position).getCloseEventType() == 2)
            holder.CloseEventImg.setImageResource(R.drawable.randomom);
        else if(closeEvents.get(position).getCloseEventType() == 3)
            holder.CloseEventImg.setImageResource(R.drawable.medics);
        else if(closeEvents.get(position).getCloseEventType() == 4)
            holder.CloseEventImg.setImageResource(R.drawable.shop);

        holder.CloseEventDesc.setText(closeEvents.get(position).getCloseEventDesc());

        return convertView;

    }

    static class ViewHolder
    {
        ImageView CloseEventImg;
        TextView CloseEventDesc;
    }

}
