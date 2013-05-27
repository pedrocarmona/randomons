package com.example.activities;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.example.adapters.AdapterBagItem;
import com.example.adapters.AdapterHorizontalList;
import com.example.data.CaptureItem;
import com.example.data.Potion;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.drawable.ColorDrawable;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.TextView;
import org.holoeverywhere.widget.Toast;


public class Items extends SlidingActivity
{
    private View selectedView = null;
    private LinearLayout bottomContainer;
    private LinearLayout bottomContent;
    private ImageView itemImg;
    private TextView descLabel;
    private TextView descText;
    private Button selectButton;

    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.bag);


        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.help_my_items);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        bottomContainer = (LinearLayout) findViewById(R.id.choose_bottom);
        bottomContent  = (LinearLayout) View.inflate(this, R.layout.choose_randomon_bottom, null);
        itemImg = (ImageView) findViewById(R.id.choose_bottom_img);
        descLabel = (TextView) findViewById(R.id.choose_descr_label);
        descText = (TextView) findViewById(R.id.choose_desc_text);
        selectButton = (Button) findViewById(R.id.choose_bottom_btn);

        final ActionBar bar = getSupportActionBar();
        bar.setTitle("My Items");

        addSlidingMenu();

        AdapterHorizontalList potionsList = (AdapterHorizontalList) findViewById(R.id.potion_items);
        final AdapterHorizontalList captureList = (AdapterHorizontalList) findViewById(R.id.capture_items);

        final AdapterBagItem captureAdapter = new AdapterBagItem(this);
        captureList.setAdapter(captureAdapter);
        final AdapterBagItem potionsAdapter = new AdapterBagItem(this);
        potionsList.setAdapter(potionsAdapter);

        captureAdapter.addItem(new CaptureItem(R.drawable.cnb, "cenas cima 1", "fixes"));
        captureAdapter.addItem(new CaptureItem(R.drawable.ph,"cenas cima 2", "fixes"));
        captureAdapter.addItem(new CaptureItem(R.drawable.mth,"cenas cima 3", "fixes"));
        captureAdapter.addItem(new CaptureItem(R.drawable.psn,"cenas cima 4", "fixes"));
        captureAdapter.addItem(new CaptureItem(R.drawable.fl,"cenas cima 5", "fixes"));
        captureAdapter.addItem(new CaptureItem(R.drawable.psy,"cenas cima 5", "fixes"));


        potionsAdapter.addItem(new Potion(R.drawable.potion_cnb,"cenas baixo 1", "fixes"));
        potionsAdapter.addItem(new Potion(R.drawable.potion_ph,"cenas baixo 2", "fixes"));
        potionsAdapter.addItem(new Potion(R.drawable.potion_mth,"cenas baixo 3", "fixes"));
        potionsAdapter.addItem(new Potion(R.drawable.potion_psn,"cenas baixo 4", "fixes"));
        potionsAdapter.addItem(new Potion(R.drawable.potion_fl,"cenas baixo 5", "fixes"));
        potionsAdapter.addItem(new Potion(R.drawable.potion_psy,"cenas baixo 5", "fixes"));


        potionsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(selectedView != null) {
                    setUnselected(selectedView);
                }
                setSelected(view);
                selectedView = view;
                updateBottom(((Potion)potionsAdapter.getItem(position)).getName());

            }
        });


        captureList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(selectedView != null) {
                    setUnselected(selectedView);
                }
                setSelected(view);
                selectedView = view;
                updateBottom(((CaptureItem)captureAdapter.getItem(position)).getName());
            }
        });

    }


    private void setUnselected(View view) {

        LinearLayout ll = (LinearLayout) view;
        View v = ll.getChildAt(0); // get first child (linear layout)
        v.setBackgroundResource(R.drawable.full_rounded_list);
    }


    public void setSelected(View view) {

        LinearLayout ll = (LinearLayout) view;
        View v = ll.getChildAt(0); // get first child (linearlayout)
        v.setBackgroundResource(R.drawable.image_view_border_selected);
    }


    private void updateBottom(final String name) {

        bottomContainer.removeAllViews();
        bottomContainer.addView(bottomContent);

        itemImg = (ImageView) findViewById(R.id.choose_bottom_img);
        descLabel = (TextView) findViewById(R.id.choose_descr_label);
        descText = (TextView) findViewById(R.id.choose_desc_text);
        selectButton = (Button) findViewById(R.id.choose_bottom_btn);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* REALLY SELECT IT AND GO BACK TO THE BATTLE */

                Toast.makeText(getApplicationContext(), "Selected item " + name, Toast.LENGTH_SHORT).show();
            }
        });

        descLabel.setText("Item description");
        descText.setText("O Item costuma ser encontrado em regiões pantanosas. É um item muito agressivo, porém é fácil de encontrar. " +
                "\nDescrição do item " + name);
        itemImg.setImageResource(R.drawable.psn);
        selectButton.setText("Use");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                toggle();
                return true;

            case R.id.help_btn:

                showHelpDialog();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void showHelpDialog() {

        dialog.setCancelable(true);
        dialog.show();
    }

    private void addSlidingMenu()
    {
        com.jeremyfeinstein.slidingmenu.lib.SlidingMenu sm = getSlidingMenu();
        // check if the content frame contains the menu frame
        if (findViewById(R.id.menu_frame) == null)
        {
            setBehindContentView(R.layout.slide_menu);
            sm.setSlidingEnabled(true);
            sm.setTouchModeAbove(com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.TOUCHMODE_FULLSCREEN);
            // show home as up so we can toggle
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        else
        {
            // add a dummy view
            View v = new View(this);
            setBehindContentView(v);
            sm.setSlidingEnabled(false);
            sm.setTouchModeAbove(com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.TOUCHMODE_NONE);
        }

        new SlidingMenu(this, getSlidingMenu());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getSupportMenuInflater();
        inflater.inflate(R.menu.actionbarmenu, menu);
        return true;
    }
}


