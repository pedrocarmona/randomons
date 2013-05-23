package com.example.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.TextView;
import org.holoeverywhere.widget.Toast;

public class ChooseRandomon extends SlidingActivity
{
    private boolean LEVER_UP;
    private ImageView leverImg;
    private ImageView randomon1;
    private ImageView randomon2;
    private ImageView randomon3;
    private LinearLayout bottomContainer;
    private LinearLayout bottomContent;
    private TextView pullLever;
    float historicY = Float.NaN;

    static final int DELTA = 40;

    Randomon[] sortedRandomons = new Randomon[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.choose_randomon);

        final ActionBar bar = getSupportActionBar();
        bar.setTitle("Starter Randomon");

        addSlidingMenu();

        LEVER_UP = true;
        leverImg = (ImageView) findViewById(R.id.lever);
        bottomContainer = (LinearLayout) findViewById(R.id.choose_bottom);
        bottomContent  = (LinearLayout) View.inflate(this, R.layout.choose_randomon_bottom, null);
        randomon1 = (ImageView) findViewById(R.id.randomon1);
        randomon2 = (ImageView) findViewById(R.id.randomon2);
        randomon3 = (ImageView) findViewById(R.id.randomon3);
        pullLever = (TextView) findViewById(R.id.choose_info_msg);


        randomon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pad_px = paddingDipToPx(5);

                randomon1.setBackgroundResource(R.drawable.image_view_border_selected);
                randomon1.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon1.setScaleType(ImageView.ScaleType.FIT_XY);
                /* reset other images state */
                randomon2.setBackgroundResource(R.drawable.image_view_border);
                randomon2.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon2.setScaleType(ImageView.ScaleType.FIT_XY);
                randomon3.setBackgroundResource(R.drawable.image_view_border);
                randomon3.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon3.setScaleType(ImageView.ScaleType.FIT_XY);

                updateBottom(0);
            }
        });

        randomon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pad_px = paddingDipToPx(5);

                randomon2.setBackgroundResource(R.drawable.image_view_border_selected);
                randomon2.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon2.setScaleType(ImageView.ScaleType.FIT_XY);
                /* reset other images state */
                randomon1.setBackgroundResource(R.drawable.image_view_border);
                randomon1.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon1.setScaleType(ImageView.ScaleType.FIT_XY);
                randomon3.setBackgroundResource(R.drawable.image_view_border);
                randomon3.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon3.setScaleType(ImageView.ScaleType.FIT_XY);

                updateBottom(1);
            }
        });

        randomon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pad_px = paddingDipToPx(5);

                randomon3.setBackgroundResource(R.drawable.image_view_border_selected);
                randomon3.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon3.setScaleType(ImageView.ScaleType.FIT_XY);
                /* reset other images state */
                randomon1.setBackgroundResource(R.drawable.image_view_border);
                randomon1.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon1.setScaleType(ImageView.ScaleType.FIT_XY);
                randomon2.setBackgroundResource(R.drawable.image_view_border);
                randomon2.setPadding(pad_px, pad_px, pad_px, pad_px);
                randomon2.setScaleType(ImageView.ScaleType.FIT_XY);

                updateBottom(2);
            }
        });

        leverImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        historicY = motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_UP:

                        if (motionEvent.getY() - historicY > DELTA && LEVER_UP) {

                            leverImg.setImageResource(R.drawable.lever_down);

                           /* SORT STARTER RANDOMONS HERE */
                            Randomon r1 = new Randomon("Randomon 1 ", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal", R.drawable.randomom);
                            Randomon r2 = new Randomon("Randomon 2 ", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal", R.drawable.randomom);
                            Randomon r3 = new Randomon("Randomon 3 ", 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal", R.drawable.randomom);
                            sortedRandomons[0] = r1;
                            sortedRandomons[1] = r2;
                            sortedRandomons[2] = r3;

                            randomon1.setImageResource(R.drawable.randomom);
                            randomon2.setImageResource(R.drawable.randomom);
                            randomon3.setImageResource(R.drawable.randomom);

                            pullLever.setText("Press a Randomon to see the description");
                            LEVER_UP = false;
                        }
                        break;

                    default:
                        return true;
                }

                return true;
            }
        });

    }

    private int paddingDipToPx(int dip) {

        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }


    private void updateBottom(final int index) {

        bottomContainer.removeAllViews();
        bottomContainer.addView(bottomContent);

        ImageView randomonMini = (ImageView) findViewById(R.id.choose_bottom_img);
        TextView descRandomon = (TextView) findViewById(R.id.choose_desc_text);
        Button selectButton = (Button) findViewById(R.id.choose_bottom_btn);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* REALLY SELECT IT AND GO TO THE MAIN MENU */

                Toast.makeText(getApplicationContext(), "Selected randomon " + (index + 1), Toast.LENGTH_SHORT).show();

                Intent intent;
                intent = new Intent(v.getContext(), MainMenu.class);
                ChooseRandomon.this.startActivity(intent);
                finish();
            }
        });

        /* set description and image according to the index */
        descRandomon.setText("O Estranhomon costuma ser encontrado em regiões pantanosas. É um randomon muito agressivo, porém é fácil de encontrar. " +
                "\nDescrição do randomon " + sortedRandomons[index].getName());
        randomonMini.setImageResource(R.drawable.randomom);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                toggle();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addSlidingMenu()
    {
        com.jeremyfeinstein.slidingmenu.lib.SlidingMenu sm = getSlidingMenu();
        // check if the content frame contains the menu frame
        if (findViewById(R.id.menu_frame) == null)
        {
            setBehindContentView(R.layout.menu_frame);
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
}
