package com.example.others;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: Telmo
 * Date: 22-05-2013
 * Time: 0:29
 * To change this template use File | Settings | File Templates.
 */
public class DrawView extends View {

    Paint paint = new Paint();

    public DrawView(Context context) {

        super(context);
    }


    public void onDraw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(3);
        canvas.drawRect(30, 30, 80, 80, paint);
        paint.setStrokeWidth(0);
        paint.setColor(Color.CYAN);
        canvas.drawRect(33, 60, 77, 77, paint );
        paint.setColor(Color.YELLOW);
        canvas.drawRect(33, 33, 77, 60, paint );

    }

}


