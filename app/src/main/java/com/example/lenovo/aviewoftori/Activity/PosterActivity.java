package com.example.lenovo.aviewoftori.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.aviewoftori.Other.SeekBarListener;
import com.example.lenovo.aviewoftori.R;

public class PosterActivity extends AppCompatActivity {

    public static final int CHOOSE_PHOTO = 1;

    private ImageView poster_sd_handle;

    private SlidingDrawer poster_sd;

    private TextView poster_tv;

    private TextView poster_setcolor_et;

    private ImageView poster_iv;

    private EditText poster_settext_et;

    private Button poster_setline_btn;

    private Button poster_setlist_btn;

    private Button poster_setType_Tang;

    private Button poster_setType_Blackboard;

    private Button poster_setType_Papercut;

    private Button poster_setType_Happy;

    private SeekBar poster_setcolorR;

    private SeekBar poster_setcolorG;

    private SeekBar poster_setcolorB;

    private SeekBar poster_setSize;

    public SeekBarListener seekBarListener;

    private Toolbar poster_toolbar;

    private Typeface Tang, Blackboard, Papercut, Happy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poster_activity);

        poster_sd_handle = (ImageView) findViewById(R.id.poster_sd_handle);

        poster_sd = (SlidingDrawer) findViewById(R.id.poster_sd);

        posterfindid();

        setToolbar();

        /*SlidingDrawer完全打开监听事件*/
        poster_sd.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {

            public void onDrawerOpened() {

                poster_sd_handle.setImageResource(R.mipmap.poster_handle_down);

            }
        });

        /*SlidingDrawer完全关闭监听事件*/
        poster_sd.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {


            public void onDrawerClosed() {

                poster_sd_handle.setImageResource(R.mipmap.poster_handle_up);

            }
        });
    }

    /*实例化对象*/
    public void posterfindid() {

        type();

        poster_tv = (TextView) findViewById(R.id.poster_tv);

        poster_toolbar = (Toolbar) findViewById(R.id.poster_toolbar);

        poster_settext_et = (EditText) findViewById(R.id.poster_setText);

        poster_setcolor_et = (TextView) findViewById(R.id.poster_setcolor);

        poster_iv = (ImageView) findViewById(R.id.poster_iv);

        poster_setline_btn = (Button) findViewById(R.id.poster_setline_btn);

        poster_setlist_btn = (Button) findViewById(R.id.poster_setlist_btn);

        poster_setType_Tang = (Button) findViewById(R.id.type_Tang);

        poster_setType_Tang.setTypeface(Tang);

        poster_setType_Blackboard = (Button) findViewById(R.id.type_Blackboard);

        poster_setType_Blackboard.setTypeface(Blackboard);

        poster_setType_Papercut = (Button) findViewById(R.id.type_Papercut);

        poster_setType_Papercut.setTypeface(Papercut);

        poster_setType_Happy = (Button) findViewById(R.id.type_Happy);

        poster_setType_Happy.setTypeface(Happy);

        poster_setcolorR = (SeekBar) findViewById(R.id.poster_setcolorR_sb);

        poster_setcolorG = (SeekBar) findViewById(R.id.poster_setcolorG_sb);

        poster_setcolorB = (SeekBar) findViewById(R.id.poster_setcolorB_sb);

        poster_setSize = (SeekBar) findViewById(R.id.poster_setSize_sb);

        /*设置字体点击事件*/
        poster_setType_Tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poster_tv.setTypeface(Tang);
            }
        });

        poster_setType_Blackboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poster_tv.setTypeface(Blackboard);
            }
        });

        poster_setType_Papercut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poster_tv.setTypeface(Papercut);
            }
        });

        poster_setType_Happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poster_tv.setTypeface(Happy);
            }
        });

        /*初始化拖动条监听事件*/
        seekBarListener = new SeekBarListener(poster_setcolorR, poster_setcolorG, poster_setcolorB
                , poster_setSize, poster_setcolor_et, poster_tv);

        poster_setcolorR.setOnSeekBarChangeListener(seekBarListener);

        poster_setcolorG.setOnSeekBarChangeListener(seekBarListener);

        poster_setcolorB.setOnSeekBarChangeListener(seekBarListener);

        poster_setSize.setOnSeekBarChangeListener(seekBarListener);

        /*设置文本*/
        poster_settext_et.addTextChangedListener(new TextWatcher() {

            /*文本改变前*/
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            /*文本改变中*/
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            /*文本改变后*/
            public void afterTextChanged(Editable s) {

                poster_tv.setText(poster_settext_et.getText());

            }
        });

        /*按行显示*/
        poster_setline_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                poster_tv.setMaxEms(10);
            }
        });

        /*按列显示*/
        poster_setlist_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                poster_tv.setMaxEms(1);
            }
        });

        poster_tv.setOnTouchListener(moveSetTouch);
    }

    /*初始化字体*/
    public void type() {

        AssetManager AM = getAssets();

        Tang = Typeface.createFromAsset(AM, "fonts/tang.ttf");

        Blackboard = Typeface.createFromAsset(AM, "fonts/tang.ttf");

        Papercut = Typeface.createFromAsset(AM, "fonts/papercut.ttf");

        Happy = Typeface.createFromAsset(AM, "fonts/happy.ttf");
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

    /*布置toolbar布局*/
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.poster_toolbar, menu);

        return true;
    }

    /*设置toolbar*/
    public void setToolbar() {

        poster_toolbar = (Toolbar) findViewById(R.id.poster_toolbar);//实例化toolbar

        setSupportActionBar(poster_toolbar);//给当前布局添加toolbar

        poster_toolbar.setNavigationIcon(R.mipmap.back);//设置导航图标

        getSupportActionBar().setTitle("");

        /*导航图标监听点击事件*/
        poster_toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                finish();
            }
        });
    }

    /*toolbar按钮监听事件*/
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.poster_toolbar_setpic:

                if (Build.VERSION.SDK_INT >= 23) {

                    if (ContextCompat.checkSelfPermission(PosterActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(PosterActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {

                        openAlbum();

                    }
                }

        }

        return super.onOptionsItemSelected(item);
    }

    /*打开图库*/
    private void openAlbum() {

        Intent intent = new Intent("android.intent.action.GET_CONTENT");

        intent.setType("image/*");

        startActivityForResult(intent, CHOOSE_PHOTO);

    }

    /*用户是否授权*/
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch (requestCode) {

            case 1:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    openAlbum();

                } else {

                    Toast.makeText(this, "You denied the sd permission", Toast.LENGTH_SHORT).show();

                }

                break;

            default:

                break;

        }

    }

    /*图库返回值*/
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_PHOTO) {

            Uri imageUri = data.getData();

            poster_iv.setImageURI(imageUri);
        }
    }
}
