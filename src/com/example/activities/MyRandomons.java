package com.example.activities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.antipodalwall.AntipodalWallLayout;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.TextView;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: Telmo
 * Date: 16-05-2013
 * Time: 0:19
 * To change this template use File | Settings | File Templates.
 */
public class MyRandomons extends SherlockActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.randomons_my);

        int images[]={  R.drawable.img1,R.drawable.img5,
                        R.drawable.img2,R.drawable.img6,
                        R.drawable.img3,R.drawable.img7,
                        R.drawable.img4,R.drawable.img8,
                        R.drawable.item,R.drawable.avatar_img,
                        R.drawable.avatar_img
        };

        AntipodalWallLayout layout = (AntipodalWallLayout)findViewById(R.id.antipodal_wall);

        /*Bitmap mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(mBitmap);
        mCanvasgetResources().getDrawable(R.drawable.img1);
        Drawable drawable;
        drawable.draw(mCanvas);
        */
        ImageView img = new ImageView(this);
        img.setImageResource(images[0]);
        layout.addView(img);

        ImageView img1 = new ImageView(this);
        img1.setImageResource(images[1]);
        layout.addView(img1);

        ImageView img2 = new ImageView(this);
        img2.setImageResource(images[2]);
        layout.addView(img2);

        ImageView img3 = new ImageView(this);
        img3.setImageResource(images[3]);
        layout.addView(img3);

        ImageView img4 = new ImageView(this);
        img4.setImageResource(images[4]);
        layout.addView(img4);

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

    }
}