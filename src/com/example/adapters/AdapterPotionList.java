package com.example.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.Potion;
import org.holoeverywhere.widget.NumberPicker;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterPotionList extends BaseAdapter
{
    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private ArrayList<Potion> potionsList = new ArrayList<Potion>();

    public AdapterPotionList(Context context)
    {
        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    }

    public void addItem(final Potion randomon)
    {
        potionsList.add(randomon);
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return potionsList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return potionsList.get(position);
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
            convertView = mInflater.inflate(R.layout.potions_list_item, null);
            holder = new ViewHolder();

            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.potionImage = (ImageView) convertView.findViewById(R.id.potion_image);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.numberPicker = (NumberPicker) convertView.findViewById(R.id.quantity);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(potionsList.get(position).getName());

        holder.description.setText(potionsList.get(position).getDescription());
        holder.potionImage.setImageResource(potionsList.get(position).getType());


        holder.numberPicker.setMaxValue(10);
        holder.numberPicker.setMinValue(0);
        holder.numberPicker.setWrapSelectorWheel(false);

        return convertView;
    }

    static class ViewHolder
    {
        TextView name;
        ImageView potionImage;
        TextView description;
        NumberPicker numberPicker;
    }
}
