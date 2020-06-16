package com.example.ahsan.catchmeifucan;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private TextView ScoreLabel;
    private TextView TapToStart;
    private ImageView orange;
    private ImageView mango;
    private ImageView grasp;
    private ImageView boy;
    private ImageView beer;

    private int frameHeight;
    private int boyHeight;
    private int screenWidth;
    private int screenHeight;

    private int boyY;
    private int orangeX;
    private int orangeY;
    private int graspX;
    private int graspY;
    private int mangoX;
    private int mangoY;
    private int beerX;
    private int beerY;


    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private boolean setFlag = false;
    private boolean startFlag = false;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScoreLabel = (TextView) findViewById(R.id.ScoreBar);
        TapToStart = (TextView) findViewById(R.id.TapToStart);
        orange = (ImageView) findViewById(R.id.orange);
        mango = (ImageView) findViewById(R.id.mango);
        grasp = (ImageView) findViewById(R.id.grasp);
        beer = (ImageView) findViewById(R.id.beer);
        boy = (ImageView) findViewById(R.id.boy);

        WindowManager wm = getWindowManager();
        Display dp = wm.getDefaultDisplay();
        Point size = new Point();
        dp.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;


        orange.setX(-80);
        orange.setY(-80);
        mango.setX(-80);
        mango.setY(-80);
        grasp.setX(-80);
        grasp.setY(-80);
        beer.setX(-80);
        beer.setY(-80);


    }


    private void changPos() {

        hitCheck();

        orangeX -=10;
        if (orangeX < 0 ){
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getWidth()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        mangoX -=12;
        if (mangoX < 0){
            mangoX = screenWidth + 20;
            mangoY = (int) Math.floor(Math.random() * (frameHeight - mango.getWidth()));
        }
        mango.setX(mangoX);
        mango.setY(mangoY);

        graspX -=10;
        if (graspX < 0){
            graspX = screenWidth + 20;
            graspY = (int) Math.floor(Math.random() * (frameHeight - grasp.getWidth()));
        }
        grasp.setX(graspX);
        grasp.setY(graspY);

        beerX -=10;
        if (beerX < 0){
            beerX = screenWidth + 20;
            beerY = (int) Math.floor(Math.random() * (frameHeight - beer.getWidth()));
        }
        beer.setX(beerX);
        beer.setY(beerY);


        if (setFlag == true){
            boyY -= 25;
        }else {
            boyY += 15;
        }

        if (boyY < 0)
            boyY = 0;

        if (boyY > frameHeight - boyHeight)
            boyY = frameHeight -boyHeight;

        boy.setY(boyY);

        ScoreLabel.setText("Score : "+score);

    }

    private void hitCheck() {
        int orangeCenterX = orangeX + orange.getWidth()/2;
        int orangeCenterY = orangeY + orange.getHeight()/2;

        if (0<= orangeCenterX && orangeCenterX <= boyHeight && boyY <=orangeCenterY && orangeCenterY <= boyY + boyHeight){
            orangeX = -10;
            score+=10;
        }


        int mangoCenterX = mangoX + mango.getWidth()/2;
        int mangoCenterY = mangoY + mango.getHeight()/2;

        if (0<= mangoCenterX && mangoCenterX <= boyHeight && boyY <=mangoCenterY && mangoCenterY <= boyY + boyHeight){
            mangoX = -10;
            score+=10;
        }


        int graspCenterX = graspX + grasp.getWidth()/2;
        int graspCenterY = graspY + grasp.getHeight()/2;

        if (0<= graspCenterX && graspCenterX <= boyHeight && boyY <=graspCenterY && graspCenterY <= boyY + boyHeight){
            graspX = -10;
            score+=10;
        }

        int beerCenterX = beerX + beer.getWidth()/2;
        int beerCenterY = beerY + beer.getHeight()/2;

        if (0<= beerCenterX && beerCenterX <= boyHeight && boyY <= beerCenterY && beerCenterY <= boyY + boyHeight){

            timer.cancel();
            timer = null;


            Intent i = new Intent(MainActivity.this,result.class);
            i.putExtra("SCORE",score);
            startActivity(i);

/*            Intent intent = new Intent(MainActivity.this,lol.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);*/

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (startFlag == false){

            startFlag = true;

            TapToStart.setVisibility(View.GONE);

            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
            frameHeight = frameLayout.getHeight();

            boyHeight = boy.getHeight();
            boyY = (int) boy.getY();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changPos();
                        }
                    });
                }
            },0,20);       //1st parameter Task to do, 2nd delay, 3rd period


        }else {

            if (event.getAction() == MotionEvent.ACTION_DOWN){
                setFlag = true;
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                setFlag = false;
            }

        }

        return super.onTouchEvent(event);
    }

}
