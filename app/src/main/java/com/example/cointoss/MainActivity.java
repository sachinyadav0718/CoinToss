package com.example.cointoss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    ImageView imgCoin;
    Button btnFlip;
    Animation fromMiddle, toMiddle;
    boolean isStop= true, isBackShow= true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgCoin =(ImageView)findViewById(R.id.imgCoin);
        btnFlip = (Button)findViewById(R.id.btnFlip);
        toMiddle= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.to_middle);
        fromMiddle=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.from_middle);

        toMiddle.setRepeatCount(1);
        fromMiddle.setRepeatCount(1);

        toMiddle.setAnimationListener(this);
        fromMiddle.setAnimationListener(this);

        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isStop){
                    imgCoin.clearAnimation();
                    imgCoin.setAnimation(toMiddle);
                    imgCoin.startAnimation(fromMiddle);

                    btnFlip.setText("Stop");
                }
                else {
                    int number = new Random().nextInt(100)+1;
                    if (number % 2 == 0){
                        imgCoin.setImageResource(R.drawable.heads);
                    }
                    else {
                        imgCoin.setImageResource(R.drawable.tails);
                    }
                    imgCoin.clearAnimation();
                    btnFlip.setText("Flip");
                }
                isStop = !isStop;
            }
        });


    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

        if (animation == toMiddle){
            imgCoin.clearAnimation();
            imgCoin.setAnimation(fromMiddle);
            imgCoin.startAnimation(fromMiddle);


            if (isBackShow)
                imgCoin.setImageResource(R.drawable.tails);
            else
                imgCoin.setImageResource(R.drawable.heads);


            isBackShow = !isBackShow;
        }
        else
        {
            imgCoin.clearAnimation();
            imgCoin.setAnimation(toMiddle);
            imgCoin.startAnimation(toMiddle);


            if (isBackShow)
                imgCoin.setImageResource(R.drawable.tails);
            else
                imgCoin.setImageResource(R.drawable.heads);


            isBackShow = !isBackShow;
        }
    }
}
