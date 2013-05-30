package com.example.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.Randomon;
import com.example.others.Tools;
import org.holoeverywhere.widget.LinearLayout;

import java.util.ArrayList;

public class AdapterMyRandomonImg extends BaseAdapter {
    private Context mContext;
    private ArrayList<Randomon> myRandomons = new ArrayList<Randomon>();
    private Tools tools = Tools.getInstance();

    // Constructor
    public AdapterMyRandomonImg(Context c){
        mContext = c;
    }

    public void addItem(final Randomon myRandomon)
    {
        myRandomons.add(myRandomon);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return myRandomons.size();
    }

    @Override
    public Object getItem(int position) {
        return myRandomons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(tools.getPicId(myRandomons.get(position).getPicId()));
        imageView.setBackgroundResource(R.drawable.full_rounded_list);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
        return imageView;
    }

}