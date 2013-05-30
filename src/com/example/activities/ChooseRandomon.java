package com.example.activities;


import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.MenuItem;
import com.example.data.Randomon;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.TextView;

public class ChooseRandomon extends SlidingActivity
{

    //add
    int soundIDClick;
    boolean loadedClick= false;
    SoundPool soundPoolClick;

    int soundIDSlot;
    boolean loadedSlot= false;
    SoundPool soundPoolSlot;



    int priority = 1;
    int no_loop = 0;
    float normal_playback_rate = 1f;

    //end add

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


        /*add*/

        soundPoolClick= new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPoolClick.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loadedClick = true;
            }
        });
        soundIDClick = soundPoolClick.load(this, R.raw.click, 1);

        soundPoolSlot= new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPoolSlot.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                loadedSlot = true;
            }
        });
        soundIDSlot = soundPoolSlot.load(this, R.raw.slotsound, 1);


        /*end add*/


        final ActionBar bar = getSupportActionBar();
        bar.setTitle("Starter Randomon");
        bar.setLogo(R.drawable.icon_app);

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

                if (loadedClick==true){

                    soundPoolClick.play(soundIDClick, 0.99f, 0.99f, priority, 0, normal_playback_rate);

                }
                if(!LEVER_UP) {
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
            }
        });

        randomon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loadedClick==true){

                    soundPoolClick.play(soundIDClick, 0.99f, 0.99f, priority, 0, normal_playback_rate);

                }

                if(!LEVER_UP) {
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
            }
        });

        randomon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loadedClick==true){

                    soundPoolClick.play(soundIDClick, 0.99f, 0.99f, priority, 0, normal_playback_rate);

                }

                if(!LEVER_UP) {
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

                            if (loadedSlot==true){

                                soundPoolSlot.play(soundIDSlot, 0.99f, 0.99f, priority, 0, normal_playback_rate);

                            }



                           /* SORT STARTER RANDOMONS HERE */
                            Randomon r1 = new Randomon("Catzinga", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.catzinga,1);
                            Randomon r2 = new Randomon("Canibalape", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.canibalape,2);
                            Randomon r3 = new Randomon("Cyclosnake", Randomon.NORMAL, 40, 30, 60, 1.1, 200, 4, 190, 13, "Normal","fast randomon lives in mountains", R.drawable.cyclosnake,3);

                            sortedRandomons[0] = r1;
                            sortedRandomons[1] = r2;
                            sortedRandomons[2] = r3;

                            randomon1.setImageResource(r1.getPicId());
                            randomon2.setImageResource(r2.getPicId());
                            randomon3.setImageResource(r3.getPicId());

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

                //Toast.makeText(getApplicationContext(), "Selected randomon " + (index + 1), Toast.LENGTH_SHORT).show();

                Intent intent;
                intent = new Intent(v.getContext(), MainMenu.class);
                ChooseRandomon.this.startActivity(intent);
                finish();
            }
        });

        /* set description and image according to the index */
        descRandomon.setText("O Estranhomon costuma ser encontrado em regiões pantanosas. É um randomon muito agressivo, porém é fácil de encontrar. " +
                "\nDescrição do randomon " + sortedRandomons[index].getName());
        randomonMini.setImageResource(sortedRandomons[index].getPicId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
