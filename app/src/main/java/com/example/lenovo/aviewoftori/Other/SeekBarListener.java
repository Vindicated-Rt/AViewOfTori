package com.example.lenovo.aviewoftori.Other;

import android.graphics.Color;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by lenovo on 2018/5/8.
 */

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

    private SeekBar seekbarR, seekbarG, seekbarB;

    private TextView setColor_tv,setColorinfo_et;

    /*构造方法*/
    public SeekBarListener(SeekBar seekbarR, SeekBar seekbarG, SeekBar seekbarB,
                           TextView setColorinfo_et, TextView setColor_tv) {

        this.seekbarR = seekbarR;

        this.seekbarG = seekbarG;

        this.seekbarB = seekbarB;

        this.setColorinfo_et = setColorinfo_et;

        this.setColor_tv = setColor_tv;
    }

    /*进度条改变*/
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        int progress1 = seekbarR.getProgress();

        int progress2 = seekbarG.getProgress();

        int progress3 = seekbarB.getProgress();

        setColor_tv.setTextColor(Color.rgb(progress1, progress2, progress3));

        setColorinfo_et.setBackgroundColor(Color.rgb(progress1, progress2, progress3));

        setColorinfo_et.setText(Integer.toHexString(progress1) + Integer.toHexString(progress2) + Integer.toHexString(progress3));

    }

    /*进度条开始拖动*/
    public void onStartTrackingTouch(SeekBar seekBar) {

        int progress1 = seekbarR.getProgress();

        int progress2 = seekbarG.getProgress();

        int progress3 = seekbarB.getProgress();

        setColor_tv.setTextColor(Color.rgb(progress1, progress2, progress3));

        setColorinfo_et.setBackgroundColor(Color.rgb(progress1, progress2, progress3));

        setColorinfo_et.setText(Integer.toHexString(progress1) + Integer.toHexString(progress2) + Integer.toHexString(progress3));

    }

    /*进度条停止拖动*/
    public void onStopTrackingTouch(SeekBar seekBar) {

        int progress1 = seekbarR.getProgress();

        int progress2 = seekbarG.getProgress();

        int progress3 = seekbarB.getProgress();

        setColor_tv.setTextColor(Color.rgb(progress1, progress2, progress3));

        setColorinfo_et.setBackgroundColor(Color.rgb(progress1, progress2, progress3));

        setColorinfo_et.setText(Integer.toHexString(progress1) + Integer.toHexString(progress2) + Integer.toHexString(progress3));

    }
}
