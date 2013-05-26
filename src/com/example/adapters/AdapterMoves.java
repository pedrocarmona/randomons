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
import com.example.data.Move;
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

public class AdapterMoves extends BaseAdapter
{
    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private ArrayList<Move> movesList = new ArrayList<Move>();

    public AdapterMoves(Context context)
    {
        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    }

    public void addItem(final Move mv)
    {
        movesList.add(mv);
        notifyDataSetChanged();
    }

    @Override
    public int getCount()
    {
        return movesList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return movesList.get(position);
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
            convertView = mInflater.inflate(R.layout.randomon_move_item, null);
            holder = new ViewHolder();

            holder.mvDamage = (TextView) convertView.findViewById(R.id.move_damage);
            holder.mvName = (TextView) convertView.findViewById(R.id.move_name);
            holder.mvImg = (ImageView) convertView.findViewById(R.id.move_img);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mvName.setText("Ataque: "+ movesList.get(position).getName());
        holder.mvDamage.setText("Danos: "+ movesList.get(position).getDamage());
        holder.mvImg.setImageResource(movesList.get(position).getAnimationPath());

        return convertView;
    }

    static class ViewHolder
    {
        TextView mvName;
        TextView mvDamage;
        ImageView mvImg;
    }
}
