package com.example.lenovo.aviewoftori.Fragment;


import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.example.lenovo.aviewoftori.Other.SeekBarListener;
import com.example.lenovo.aviewoftori.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolFragment extends Fragment {

    private ImageView tool_sd_handle;

    private SlidingDrawer tool_sd;

    private TextView tool_tv;

    private TextView tool_setcolor_et;

    private EditText tool_settext_et;

    private Button tool_setline_btn;

    private Button tool_setlist_btn;

    private SeekBar tool_setcolorR;

    private SeekBar tool_setcolorG;

    private SeekBar tool_setcolorB;

    public SeekBarListener seekBarListener;

    private View view;

    public ToolFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.tool_fragment, container, false);

        tool_sd_handle = (ImageView) view.findViewById(R.id.tool_sd_handle);

        tool_sd = (SlidingDrawer) view.findViewById(R.id.tool_sd);

        /*SlidingDrawer完全打开监听事件*/
        tool_sd.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {

            public void onDrawerOpened() {

                tool_sd_handle.setImageResource(R.mipmap.tool_handle_down);

            }
        });

        /*SlidingDrawer完全关闭监听事件*/
        tool_sd.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {


            public void onDrawerClosed() {

                tool_sd_handle.setImageResource(R.mipmap.tool_handle_up);

            }
        });

        toolfindid();

        return view;

    }

    /*实例化对象*/
    public void toolfindid() {

        tool_tv = (TextView) view.findViewById(R.id.tool_tv);

        tool_settext_et = (EditText) view.findViewById(R.id.tool_setText);

        tool_setcolor_et = (TextView) view.findViewById(R.id.tool_setcolor);

        tool_setline_btn = (Button) view.findViewById(R.id.tool_setline_btn);

        tool_setlist_btn = (Button) view.findViewById(R.id.tool_setlist_btn);

        tool_setcolorR = (SeekBar) view.findViewById(R.id.tool_setcolorR_sb);

        tool_setcolorG = (SeekBar) view.findViewById(R.id.tool_setcolorG_sb);

        tool_setcolorB = (SeekBar) view.findViewById(R.id.tool_setcolorB_sb);

        seekBarListener = new SeekBarListener(tool_setcolorR, tool_setcolorG, tool_setcolorB
                , tool_setcolor_et, tool_tv);

        tool_setcolorR.setOnSeekBarChangeListener(seekBarListener);

        tool_setcolorG.setOnSeekBarChangeListener(seekBarListener);

        tool_setcolorB.setOnSeekBarChangeListener(seekBarListener);

        /*设置文本*/
        tool_settext_et.addTextChangedListener(new TextWatcher() {

            /*文本改变前*/
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /*文本改变中*/
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /*文本改变后*/
            public void afterTextChanged(Editable s) {

                tool_tv.setText(tool_settext_et.getText());

            }
        });

        tool_tv.setOnTouchListener(moveSetTouch);

        /*按行显示*/
        tool_setline_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tool_tv.setMaxEms(10);
            }
        });

        /*按列显示*/
        tool_setlist_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tool_tv.setMaxEms(1);
            }
        });
    }

    /*新建移动控件触摸监听事件*/
    private View.OnTouchListener moveSetTouch = new View.OnTouchListener() {

        int lastX, lastY;

        public boolean onTouch(View v, MotionEvent event) {

            int ea = event.getAction();

            //得到手机屏幕分辨率
            DisplayMetrics dm = getResources().getDisplayMetrics();

            //得到屏幕长宽
            int screenWidth = dm.widthPixels;

            int screenHeight = dm.heightPixels;

            switch (ea) {

                /*触摸按下*/
                case MotionEvent.ACTION_DOWN:

                    //获取触摸事件触摸位置的原始坐标
                    lastX = (int) event.getRawX();

                    lastY = (int) event.getRawY();

                /*触摸移动*/
                case MotionEvent.ACTION_MOVE:

                    /*获取相对位移*/
                    int dx = (int) event.getRawX() - lastX;

                    int dy = (int) event.getRawY() - lastY;

                    /*获取控件的上左下右坐标*/
                    int t = v.getTop() + dy;

                    int l = v.getLeft() + dx;

                    int b = v.getBottom() + dy;

                    int r = v.getRight() + dx;

                    //判断移动是否超出屏幕
                    if (t < 0) {

                        t = 0;//顶部坐标靠齐屏幕顶部

                        b = t + v.getHeight();//底部坐标为控件高度

                    }

                    if (l < 0) {

                        l = 0;//左部坐标对齐屏幕左部

                        r = l + v.getWidth();//右部坐标为控件宽度

                    }

                    if (b > screenHeight) {

                        b = screenHeight;//底部坐标为屏幕高度

                        t = b - v.getHeight();//顶部坐标为底部坐标-控件高度

                    }

                    if (r > screenWidth) {

                        r = screenWidth;//右部坐标为屏幕宽度

                        l = r - v.getWidth();//左部坐标为右部坐标-控件宽度

                    }

                    v.layout(l, t, r, b);//实时设置控件位置

                    /*实时获取控件位置*/
                    lastX = (int) event.getRawX();

                    lastY = (int) event.getRawY();

                    v.postInvalidate();//在主线程中调用刷新界面

                    break;

                /*触摸停止后*/
                case MotionEvent.ACTION_UP:

                    break;

            }

            return true;

        }
    };
}
