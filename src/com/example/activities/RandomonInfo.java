package com.example.activities;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.Display;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;
import com.example.adapters.AdapterLastDetails;
import com.example.adapters.AdapterLastEvents;
import com.example.data.Event;
import com.example.others.DrawView;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.ListView;

/**
 * Created with IntelliJ IDEA.
 * User: Telmo
 * Date: 16-05-2013
 * Time: 14:53
 * To change this template use File | Settings | File Templates.
 */
public class RandomonInfo extends SherlockActivity {

    public void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_randomons);


        View layout = findViewById(R.id.layout_pentagono);

        int cima = 70;
        int esquerda = 80;
        int direita = 80;
        int baixo_esquerda=20;
        int baixo_direita=60;

        ImageView imgPentagono = (ImageView) findViewById(R.id.pentagono);

        //imgPentagono.setImageResource(R.drawable.pentagono);

        Bitmap bitmapOrg = BitmapFactory.decodeResource(getResources(),R.drawable.pentagon);
        int targetWidth  = bitmapOrg.getWidth();
        int targetHeight = bitmapOrg.getHeight() ;
        Bitmap tempBitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.RGB_565);
        Canvas tempCanvas = new Canvas(tempBitmap);
        tempCanvas.drawColor(Color.WHITE);
        tempCanvas.drawBitmap(bitmapOrg, 0, 0, null);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        tempCanvas.drawCircle(targetWidth/2,targetHeight/2,20,paint);



        float tmp_top = (float)(targetHeight/2-((cima*0.01)*targetHeight/2));
        float tmp_left = (float)(targetWidth/2-((esquerda*0.01)*targetWidth/2));
        float tmp_right = (float)(targetWidth/2+((direita*0.01)*targetWidth/2));
        float tmp_down_l = (float)(targetHeight/2+((baixo_esquerda*0.01)*targetHeight/2));
        float tmp_down_r = (float)(targetHeight/2+((baixo_direita*0.01)*targetHeight/2));

        int array_screen[] = getScreenSize();
        float screen_Size_esquerda =  (float) ((array_screen[1]*0.01 + 0.12*(targetHeight/2))*(0.01*esquerda));
        float screen_Size_direita = (float) ((array_screen[1]*0.01+0.12*(targetHeight/2))*(0.01*direita));

        tempCanvas.drawLine(targetWidth/2,tmp_top,tmp_left+screen_Size_esquerda,targetHeight/2-screen_Size_esquerda,paint);
        tempCanvas.drawLine(targetWidth/2,tmp_top,tmp_right-screen_Size_direita,targetHeight/2-screen_Size_direita,paint);

        tempCanvas.drawLine(tmp_left+screen_Size_esquerda,targetHeight/2-screen_Size_esquerda,(float)(targetWidth/2-targetWidth/2*Math.sin(0.45)),tmp_down_l,paint);

        tempCanvas.drawLine(tmp_right-screen_Size_direita,targetHeight/2-screen_Size_direita,(float)(targetWidth/2+targetWidth/2*Math.sin(0.45)),tmp_down_r,paint);

        tempCanvas.drawLine((float)(targetWidth/2+targetWidth/2*Math.sin(0.45)),tmp_down_r,(float)(targetWidth/2-targetWidth/2*Math.sin(0.45)),tmp_down_l,paint);


        imgPentagono.setImageDrawable(new BitmapDrawable(getResources(), tempBitmap));


        ListView leventsView = (ListView) findViewById(R.id.randomino_details);

        AdapterLastDetails allEvents = new AdapterLastDetails(this);

        leventsView.setAdapter(allEvents);

        //PARA EXEMPLO
        int img = R.drawable.randomom;
        for(int i = 0; i<20; i++){

            Event event = new Event((i+1)+" ",""+i*1.5+" HP");


            allEvents.addEvent(event,img);
        }

    }

    public int[] getScreenSize(){
        Display display = getWindowManager().getDefaultDisplay();

        int array[]=new int[2];
        int apiSDK = Integer.valueOf(android.os.Build.VERSION.SDK_INT);

        if (apiSDK > 12){
            Point size = new Point();
            display.getSize(size);
            array[0] = size.x;
            array[1] = size.y;

        }
        else{
            array[0] = display.getWidth();  // deprecated
            array[1] = display.getHeight();
        }


        return array;

    }
}
