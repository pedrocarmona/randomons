package com.example.activities;

import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.view.Display;
import com.example.adapters.AdapterMoves;
import com.example.data.Move;
import com.example.data.Randomon;
import com.example.menus.SlidingMenu;
import com.example.others.Constants;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.TextView;

public class RandomonInfo extends SlidingFragmentActivity implements Constants
{

    private Randomon randomonSelected;
    private ImageView randomonInfoImg;
    private TextView randomonInfoName, randomonInfoType, randomonInfoLvl;

    public void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_randomons);

        randomonSelected = (Randomon) getIntent().getSerializableExtra("randomon");
        randomonInfoImg = (ImageView) findViewById(R.id.randomon_info_img);
        randomonInfoName = (TextView) findViewById(R.id.randomon_info_name);
        randomonInfoLvl = (TextView) findViewById(R.id.randomon_info_lvl);
        randomonInfoType = (TextView) findViewById(R.id.randomon_info_type);

        View layout = findViewById(R.id.layout_pentagono);

        int cima = 30;
        int esquerda = 10;
        int direita = 40;
        int baixo_esquerda=20;
        int baixo_direita=40;

        ImageView imgPentagono = (ImageView) findViewById(R.id.pentagono);

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


        ListView movesView = (ListView) findViewById(R.id.randomino_details);

        AdapterMoves randomonMoves = new AdapterMoves(this);

        movesView.setAdapter(randomonMoves);

        //PARA EXEMPLO


        randomonInfoImg.setImageResource(randomonSelected.getPicId());
        randomonInfoLvl.setText("Level " + randomonSelected.getLevel());
        randomonInfoName.setText(randomonSelected.getName());
        randomonInfoType.setText("Type: " + randomonSelected.getType());

        for(Move mv: randomonSelected.getMoves()){
            randomonMoves.addItem(mv);
        }

        addSlidingMenu();

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

        new SlidingMenu(this, getSlidingMenu(), MY_RANDOMONS);
    }
}
