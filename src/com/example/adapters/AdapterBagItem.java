package com.example.adapters;


import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.Item;
import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterBagItem extends BaseAdapter {

    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private ArrayList<Item> items = new ArrayList<Item>();

    public AdapterBagItem(Context context) {

        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    }

    public void addItem(Item item) {
        items.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         ViewHolder holder;

        if(convertView == null) {

            convertView = mInflater.inflate(R.layout.bag_view_item, null);
            holder = new ViewHolder();

            holder.itemImg = (ImageView) convertView.findViewById(R.id.bag_item_image);
            holder.numItems = (TextView) convertView.findViewById(R.id.bag_txt_num_item);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.itemImg.setImageResource(items.get(position).getType());

       /* if(items.get(position).getClass().getSimpleName().equals("Potion")) {

            holder.itemImg.setImageResource(items.get(position).getType());
        }
        else {

            holder.itemImg.setImageResource(R.drawable.capture_net);
        }*/

        return convertView;
    }


    static class ViewHolder {

        ImageView itemImg;
        TextView numItems;
    }
}
