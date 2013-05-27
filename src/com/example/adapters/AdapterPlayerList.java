package com.example.adapters;


import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.Player;
import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterPlayerList extends BaseAdapter {

    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private ArrayList<Player> playerList = new ArrayList<Player>();

    public AdapterPlayerList(Context context) {

        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    }

    public void addPlayer(final Player player) {
        playerList.add(player);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return playerList.size();
    }

    @Override
    public Object getItem(int position) {
        return playerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null) {

            convertView = mInflater.inflate(R.layout.users_list_item, null);
            holder = new ViewHolder();
            holder.ranking = (TextView) convertView.findViewById(R.id.ranking);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.points = (TextView) convertView.findViewById(R.id.points);
            holder.playerImage = (ImageView) convertView.findViewById(R.id.user_image);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ranking.setText(String.valueOf(playerList.get(position).getRanking()));
        holder.name.setText(playerList.get(position).getName());
        holder.points.setText(String.valueOf(playerList.get(position).getPoints()));

        return convertView;
    }


    class backgroundLoadListView extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... args) {

            return null;
        }
    }



    static class ViewHolder
    {
        TextView ranking;
        TextView name;
        TextView points;
        ImageView playerImage;
    }
}
