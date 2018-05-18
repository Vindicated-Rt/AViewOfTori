package com.example.lenovo.aviewoftori.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo.aviewoftori.R;

/**
 * Created by asus on 2018/5/17.
 */

public class RingActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ring);

        //播放铃声
        mediaPlayer = new MediaPlayer();
        mediaPlayer = MediaPlayer.create(this,R.raw.one);
        mediaPlayer.start();
    }

    public  void stop(View view){
        mediaPlayer.stop();
        finish();
    }

//    protected void onDestroy(){
//        super.onDestroy();
//        if(mediaPlayer != null){
//            mediaPlayer.stop();;
//            mediaPlayer.release();
//        }
//    }
}
