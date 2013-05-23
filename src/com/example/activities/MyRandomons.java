package com.example.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.actionbarsherlock.view.MenuItem;
import com.antipodalwall.AntipodalWallLayout;
import com.example.menus.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import org.holoeverywhere.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: Telmo
 * Date: 16-05-2013
 * Time: 0:19
 * To change this template use File | Settings | File Templates.
 */
public class MyRandomons extends SlidingActivity
{

    int images[]={  R.drawable.img1,R.drawable.img5,
            R.drawable.img2,R.drawable.img6,
            R.drawable.img3,R.drawable.img7,
            R.drawable.img4,R.drawable.img8,
            R.drawable.item,R.drawable.avatar_img,
            R.drawable.avatar_img
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.randomons_my);

        addSlidingMenu();


        AntipodalWallLayout layout = (AntipodalWallLayout)findViewById(R.id.antipodal_wall);

        /*Bitmap mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mBitmap);
        mCanvasgetResources().getDrawable(R.drawable.img1);
        Drawable drawable;
        drawable.draw(mCanvas);
        */


        setImage(0,layout);
        setImage(1,layout);
        setImage(2,layout);
        setImage(3,layout);
        setImage(4,layout);

        Toast.makeText(getApplicationContext(),"shit",Toast.LENGTH_LONG).show();

        ImageView img5 = new ImageView(this);
        img5.setImageResource(images[5]);
        layout.addView(img5);

        ImageView img6 = new ImageView(this);
        img6.setImageResource(images[6]);
        layout.addView(img6);

        ImageView img7 = new ImageView(this);
        img7.setImageResource(images[7]);
        layout.addView(img7);

        ImageView img8 = new ImageView(this);
        img8.setImageResource(images[8]);
        layout.addView(img8);

        ImageView img9 = new ImageView(this);
        img9.setImageResource(images[9]);
        layout.addView(img9);

        ImageView img10 = new ImageView(this);
        img10.setImageResource(images[10]);
        layout.addView(img10);

        ImageView img11 = new ImageView(this);
        img11.setImageResource(R.drawable.img_pentagono);
        layout.addView(img11);

        ImageView img12 = new ImageView(this);
        img12.setImageResource(R.drawable.img_pentagono);
        layout.addView(img12);

        ImageView img13 = new ImageView(this);
        img13.setImageResource(R.drawable.img_pentagono);
        layout.addView(img13);

        ImageView img14 = new ImageView(this);
        img14.setImageResource(R.drawable.img_pentagono);
    layout.addView(img14);


    }

    public void setImage(int j,AntipodalWallLayout layout){

        ImageView img = new ImageView(this);
        img.setImageResource(images[j]);

        layout.addView(img);



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