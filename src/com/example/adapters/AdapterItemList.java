package com.example.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.CaptureItem;
import org.holoeverywhere.widget.NumberPicker;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterItemList extends BaseAdapter
{
    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private ArrayList<CaptureItem> itemList = new ArrayList<CaptureItem>();

    public AdapterItemList(Context context)
    {
        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    }

    public void addItem(final CaptureItem item)
    {
        itemList.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return itemList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return itemList.get(position);
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
            convertView = mInflater.inflate(R.layout.items_list_item, null);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.item_image);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.numberPicker = (NumberPicker) convertView.findViewById(R.id.quantity);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(itemList.get(position).getName());
        holder.itemImage.setImageResource(itemList.get(position).getType());
        holder.description.setText(itemList.get(position).getDescription());

        holder.numberPicker.setMaxValue(10);
        holder.numberPicker.setMinValue(0);
        holder.numberPicker.setWrapSelectorWheel(false);

        return convertView;
    }

    static class ViewHolder
    {
        TextView name;
        ImageView itemImage;
        TextView description;
        NumberPicker numberPicker;
    }
}
