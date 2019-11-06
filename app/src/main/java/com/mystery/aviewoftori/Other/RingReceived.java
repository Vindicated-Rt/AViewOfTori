package com.mystery.aviewoftori.Other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mystery.aviewoftori.Activity.RingActivity;

/**
 * Created by asus on 2018/5/17.
 */

public class RingReceived extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.lenovo.aviewoftori.Activity.RING".equals(intent.getAction())) {

            //接收广播，跳转铃声页面
            Intent i = new Intent(context, RingActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
