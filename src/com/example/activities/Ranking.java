package com.example.activities;


import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.example.adapters.AdapterItemList;
import com.example.adapters.AdapterUserList;
import com.example.data.Item;
import com.example.data.User;
import org.holoeverywhere.widget.ListView;

public class Ranking extends SherlockActivity {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ranking);

        final ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setTitle("Ranking");

        ListView listView = (ListView) findViewById(R.id.users_list);

        AdapterUserList adapter = new AdapterUserList(this);

        listView.setAdapter(adapter);

        //PARA EXEMPLO
        for(int i = 0; i<500; i++)
            adapter.addUser(new User((i+1), "Monteiro", 2094851));

    }

}
