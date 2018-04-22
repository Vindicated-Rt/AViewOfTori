package com.example.lenovo.aviewoftori.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.lenovo.aviewoftori.R;

public class LaunchAcitivity extends AppCompatActivity {

    private ImageView launch_iv;

    private Boolean isFirstIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.launch_activity);

        initImage();

    }

    /*设置动画*/
    public void initImage(){

        launch_iv = (ImageView) findViewById(R.id.launch_iv);

        launch_iv.setImageResource(R.drawable.launchimg);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.4f,1.0f,1.4f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);

        scaleAnimation.setDuration(1000);

        scaleAnimation.setFillAfter(true);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                init();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        launch_iv.startAnimation(scaleAnimation);

    }

    /*判断进入哪个页面*/
    public void init(){

        SharedPreferences sharedPreferences = getSharedPreferences("times",MODE_PRIVATE);

        isFirstIn = sharedPreferences.getBoolean("FirstIn",false);

        if(isFirstIn){

            goHome();

        }else{

            goGuide();

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean("FirstIn",true);

            editor.commit();

        }

    }


    public void goHome(){

        Intent intent = new Intent(LaunchAcitivity.this,HomeActivity.class);

        startActivity(intent);

        finish();

    }

    public void goGuide(){

        Intent intent = new Intent(LaunchAcitivity.this,GuideActivity.class);

        startActivity(intent);

        finish();

    }

}
