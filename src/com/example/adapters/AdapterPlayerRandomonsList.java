package com.example.adapters;

import android.content.ContentResolver;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.Randomon;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterPlayerRandomonsList extends BaseAdapter
{
    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private ArrayList<Randomon> randomonsList = new ArrayList<Randomon>();

    public AdapterPlayerRandomonsList(Context context)
    {
        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    }

    public void addItem(final Randomon randomon)
    {
        randomonsList.add(randomon);
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return randomonsList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return randomonsList.get(position);
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
            convertView = mInflater.inflate(R.layout.pl_randomons_list_item, null);
            holder = new ViewHolder();

            holder.lvl = (TextView) convertView
                    .findViewById(R.id.pl_randomon_lvl);
            holder.randomonImage = (ImageView) convertView.findViewById(R.id.pl_rndmn_img);
            holder.name = (TextView) convertView.findViewById(R.id.pl_rndmn_name);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lvl.setText("lvl. "+randomonsList.get(position).getLevel());

        holder.name.setText(randomonsList.get(position).getName());

        holder.randomonImage.setImageResource(randomonsList.get(position).getPicId());

        return convertView;
    }

    static class ViewHolder
    {
        TextView lvl;
        ImageView randomonImage;
        TextView name;
    }
}
