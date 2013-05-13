package com.example.adapters;


import android.content.ContentResolver;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.activities.R;
import com.example.data.User;
import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.TextView;

import java.util.ArrayList;

public class AdapterUserList extends BaseAdapter {

    private LayoutInflater mInflater;
    private ContentResolver ctx;
    private ArrayList<User> userList = new ArrayList<User>();

    public AdapterUserList(Context context) {

        mInflater = LayoutInflater.from(context);
        ctx = context.getContentResolver();
    }

    public void addUser(final User user) {
        userList.add(user);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
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
            holder.userImage = (ImageView) convertView.findViewById(R.id.user_image);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ranking.setText(String.valueOf(userList.get(position).getRanking()));
        holder.name.setText(userList.get(position).getName());
        holder.points.setText(String.valueOf(userList.get(position).getPoints()));

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
        ImageView userImage;
    }
}
