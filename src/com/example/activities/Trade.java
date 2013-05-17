package com.example.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.actionbarsherlock.app.SherlockActivity;
import com.example.adapters.AdapterPlayerRandomonsList;
import com.example.data.Randomon;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.drawable.ColorDrawable;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;

public class Trade extends SherlockActivity{

    private Context context = this;
    private LinearLayout myRandomonTrade, playerRandomonTrade;
    private ImageView myRandoTradeImg, playerRandoTradeImg;
    private TextView myRandoTradeName, playerRandoTradeName;
    private Dialog dialog;
    private ListView randomonsDialog;
    private AdapterPlayerRandomonsList plRandomonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.trade_menu);

        myRandomonTrade = (LinearLayout) findViewById(R.id.my_trade_randomon);
        playerRandomonTrade = (LinearLayout) findViewById(R.id.player_trade_randomon);
        myRandoTradeImg = (ImageView) findViewById(R.id.my_randomon_trade_img);
        playerRandoTradeImg = (ImageView) findViewById(R.id.player_randomon_trade_img);
        myRandoTradeName = (TextView) findViewById(R.id.my_randomon_trade_name);
        playerRandoTradeName = (TextView) findViewById(R.id.player_randomon_trade_name);

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.randomons_list_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        randomonsDialog = (ListView) dialog.findViewById(R.id.randomons_dialog);
        plRandomonsAdapter = new AdapterPlayerRandomonsList(context);
        randomonsDialog.setAdapter(plRandomonsAdapter);

        myRandomonTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i<20; i++)
                    plRandomonsAdapter.addItem(new Randomon("Pikachu", 15));

                dialog.setCancelable(true);
                dialog.show();
                refreshTrade();

            }
        });

        playerRandomonTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i<20; i++)
                    plRandomonsAdapter.addItem(new Randomon("Charmander", 16));

                dialog.setCancelable(true);
                dialog.show();
                refreshTrade();

            }
        });

    }


    public void refreshTrade(){

    }
}
