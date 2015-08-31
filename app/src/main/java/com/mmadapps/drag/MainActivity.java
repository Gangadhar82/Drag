package com.mmadapps.drag;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
    private static int candyInJar = 3;
    private int candyOutJar = 2;
    private int totalCandy;
    //private String candyId;
    private ImageView[] candies = new ImageView[11];
    private ImageView[] candiesOut = new ImageView[candyOutJar];
    private AbsoluteLayout tutLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tutLayout = (AbsoluteLayout) findViewById(R.id.tutLayout);
        tutLayout.setOnTouchListener(new touchListener());
        candiesOut[0] = (ImageView) findViewById(R.id.candyOut1);
        candiesOut[1] = (ImageView) findViewById(R.id.candyOut2);
        candiesOut[0].setOnTouchListener(new touchListener());
        candiesOut[1].setOnTouchListener(new touchListener());




        totalCandy = candyInJar + candyOutJar;
        for(int i=0;i<candies.length;i++){
            candies[i] = new ImageView(this);
        }
        candies[0].setBackgroundResource(R.drawable.run);
        candies[1].setBackgroundResource(R.drawable.u1);
        candies[2].setBackgroundResource(R.drawable.u2);
        candies[3].setBackgroundResource(R.drawable.u10);
        candies[4].setBackgroundResource(R.drawable.u11);
        candies[5].setBackgroundResource(R.drawable.u4);
        candies[6].setBackgroundResource(R.drawable.u7);
        candies[7].setBackgroundResource(R.drawable.u8);
        candies[8].setBackgroundResource(R.drawable.u9);
        candies[9].setBackgroundResource(R.drawable.u11);
        candies[10].setBackgroundResource(R.drawable.u7);

        candies[candyInJar] = (ImageView) findViewById(R.id.candyInJar);

    }

    private boolean dragging = false;
    private Rect hitRect = new Rect();




    class touchListener extends Activity implements OnTouchListener{

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            boolean eventConsumed = true;
            int x = (int)event.getX();
            int y = (int)event.getY();
            int dragging = 0;
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                if (v == candiesOut[0]) {
                    dragging = 0;
                    eventConsumed = false;
                }
                if (v == candiesOut[1]){
                    dragging = 1;
                    eventConsumed = false;
                }
            } else if (action == MotionEvent.ACTION_UP) {

                if (dragging == 0) {
                    candies[candyInJar].getHitRect(hitRect);
                    if (hitRect.contains(x, y)){
                        candiesOut[0].setImageDrawable(null);
                        candies[candyInJar].setImageResource(R.drawable.u1);
                    }
                }
                else if(dragging == 1){
                    candies[candyInJar].getHitRect(hitRect);
                    if (hitRect.contains(x, y)){
                        candiesOut[1].setImageDrawable(null);
                        candies[candyInJar].setImageResource(R.drawable.u2);
                    }
                }
                eventConsumed = false;

            } else if (action == MotionEvent.ACTION_MOVE) {
                if (v != candiesOut[0] && v != candiesOut[1]) {
                    if (dragging != 1) {
                        setAbsoluteLocationCentered(candiesOut[dragging], x, y);
                    }
                }
            }

            return eventConsumed;
        }


        private void setAbsoluteLocationCentered(View v, int x, int y) {
            setAbsoluteLocation(v, x - v.getWidth() / 2, y - v.getHeight() / 2);
        }


        private void setAbsoluteLocation(View v, int x, int y) {
            AbsoluteLayout.LayoutParams alp = (AbsoluteLayout.LayoutParams) v.getLayoutParams();
            alp.x = x;
            alp.y = y;
            v.setLayoutParams(alp);
        }

        private void setSameAbsoluteLocation(View v1, View v2) {
            AbsoluteLayout.LayoutParams alp2 = (AbsoluteLayout.LayoutParams) v2.getLayoutParams();
            setAbsoluteLocation(v1, alp2.x, alp2.y);
        }
    }


}