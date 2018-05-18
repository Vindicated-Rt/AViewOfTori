package com.example.lenovo.aviewoftori.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.aviewoftori.Base.DataBaseHelper;
import com.example.lenovo.aviewoftori.Other.TimeDatePickerDialog;
import com.example.lenovo.aviewoftori.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by asus on 2018/4/23.
 */

public class
AddActivity extends AppCompatActivity implements TimeDatePickerDialog.TimeDatePickerDialogInterface{

    private Toolbar add_toolbar;

    private ImageView add_show;

    private ImageButton add_camera;

    private ImageButton add_album;

    private ImageButton add_alarm;

    private EditText add_et;

    private TextView add_alarm_tv;

    private LinearLayout add_alarm_layout;

    private LinearLayout add_tool_layout;

    private Calendar calendar;

    private Uri imageUri;

    private File storeImage;

    private String imagePath = null;

    public TimeDatePickerDialog timeDatePickerDialog;

    public AlarmManager alarmManager;

    //跳转页面标识符
    public static final int TAKE_PHOTO = 1;

    public static final int CHOOSE_PHOTO = 2;

    private int alarm = 0;

    private final String[] singleList = {"相册", "相机"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_activity);

        add_et = (EditText) findViewById(R.id.add_et);

        add_show = (ImageView) findViewById(R.id.add_show);

        add_alarm_tv = (TextView) findViewById(R.id.add_alarm_tv);

        add_alarm_layout = (LinearLayout) findViewById(R.id.add_alarm_layout);

        add_tool_layout  = (LinearLayout) findViewById(R.id.add_tool_layout);

        add_tool_layout.getBackground().setAlpha(99);

        setTitle();

        addAlarm();

        changeAlarm();

        addCamera();

        addAlbum();

    }

    private void addAlarm() {

        add_alarm = (ImageButton) findViewById(R.id.add_alarm);

        add_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            timeDatePickerDialog = new TimeDatePickerDialog(AddActivity.this);

            timeDatePickerDialog.showDialog();


            }
        });

    }

    private void addAlbum() {

        add_album = (ImageButton) findViewById(R.id.add_album);

        add_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 23){

                    if (ContextCompat.checkSelfPermission(AddActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(AddActivity.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {

                        openAlbum();

                    }

                }

            }
        });

    }

    private void addCamera() {

        add_camera = (ImageButton) findViewById(R.id.add_camera);

        add_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //打开相机选图,是否授权
                if (Build.VERSION.SDK_INT >= 23) {

                    if (ContextCompat.checkSelfPermission(AddActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(AddActivity.this, new String[]{android.Manifest.permission.CAMERA}, 2);

                    } else {

                        //打开相机
                        openCamera();
                    }

                }

            }
        });

    }


    /*打开相机*/
    private void openCamera() {

        storeImage = new File(getExternalCacheDir(), getTime() + ".jpg");

        try {

            storeImage.createNewFile();

        } catch (IOException e) {

            e.printStackTrace();

        }

        //判断运行设备的系统版本
        if (Build.VERSION.SDK_INT >= 24) {

            //高于Android7.0调用此方法，将File对象转换为封装过Uri对象
            //参数：上下文对象，任意唯一字符但要与内容提供器中authorities相同，File对象
            //FileProvider是一种特殊的内容提供器，要在菜单文件进行注册
            imageUri = FileProvider.getUriForFile(AddActivity.this, "com.example.lenovo.aviewoftori.fileprovider", storeImage);

        } else {

            imageUri = Uri.fromFile(storeImage);

        }
        //启动相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(intent, TAKE_PHOTO);

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

            case 2:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    openCamera();

                } else {

                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();

                }
                break;

            default:

                break;

        }

    }

    public void setTitle() {

        add_toolbar = (Toolbar) findViewById(R.id.add_toolbar);

        setSupportActionBar(add_toolbar);

        add_toolbar.setNavigationIcon(R.mipmap.back);

        //为NavigationIcons设置监听事件，返回
        add_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        //设置菜单点击事件
        add_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.add_comfirm:

                        //储存数据
                        storeData();

                        finish();
                }

                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case TAKE_PHOTO:

                if (resultCode == RESULT_OK) {

                    try {
                        //将拍摄图片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));

                        add_show.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {

                        e.printStackTrace();

                    }
                }

                break;

            case CHOOSE_PHOTO:

                /*判断版本号是否相同*/
                if (resultCode == RESULT_OK) {

                    if (Build.VERSION.SDK_INT >= 19) {

                        handleImageOnKitKat(data);

                    } else {

                        handleImageBeforeKitKat(data);

                    }

                    storeImage = new File(imagePath);
                }

                break;

            default:

                break;

        }

    }

    private void handleImageOnKitKat(Intent data) {

        Uri uri = data.getData();

        if (DocumentsContract.isDocumentUri(this, uri)) {

            String docId = DocumentsContract.getDocumentId(uri);

            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {

                String id = docId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=" + id;

                imagePath = getImxagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {

                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));

                imagePath = getImxagePath(contentUri, null);

            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {

            imagePath = getImxagePath(uri, null);

        } else if ("file".equalsIgnoreCase(uri.getScheme())) {

            imagePath = uri.getPath();

        }

        displayImage(imagePath);

    }

    private void handleImageBeforeKitKat(Intent data) {

        Uri uri = data.getData();

        String imagePath = getImxagePath(uri, null);

        displayImage(imagePath);

    }

    private String getImxagePath(Uri uri, String selection) {

        String path = null;

        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);

        if (cursor != null) {

            if (cursor.moveToFirst()) {

                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));

            }

            cursor.close();
        }

        return path;

    }

    private void displayImage(String imagePath) {

        if (imagePath != null) {

            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

            add_show.setImageBitmap(bitmap);

        } else {

            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();

        }

    }

    public void storeData() {

        DataBaseHelper dataBaseHelper;

        dataBaseHelper = new DataBaseHelper(this, "Store.db", null, 1);

        dataBaseHelper.getReadableDatabase();

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put("content", add_et.getText().toString());

        values.put("time", getTime());

        values.put("image", storeImage + "");



        if (getIntent().getStringExtra("flag").equals("0")) {

            db.insert("Diary", null, values);

        } else if (getIntent().getStringExtra("flag").equals("1")) {

            values.put("alarm",add_alarm_tv.getText().toString());

            db.insert("Memo", null, values);

        }
    }

    public String getTime() {
        //设置日期格式
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        //获取时间对象
        Date now = new Date();

        //将时间转化为字符串
        String time = format.format(now);

        return time;

    }

    //为toolbar绑定菜单
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_toolbar_menu, menu);

        return true;
    }


    /*点击闹钟时间修改*/
    public void changeAlarm(){

        add_alarm_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timeDatePickerDialog = new TimeDatePickerDialog(AddActivity.this);

                timeDatePickerDialog.showDialog();
            }
        });

    }

    /*用接口实现Dialog点击确认的监听器*/
    @Override
    public void positiveListener() {

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);



        calendar = Calendar.getInstance();

        //将dialog选择的时间赋值给calendar
        calendar.set(Calendar.YEAR,timeDatePickerDialog.getmYear());

        calendar.set(Calendar.MONTH,timeDatePickerDialog.getmMonth());

        calendar.set(Calendar.DAY_OF_MONTH,timeDatePickerDialog.getmDay());

        calendar.set(Calendar.HOUR_OF_DAY,timeDatePickerDialog.getmHour());

        calendar.set(Calendar.MINUTE,timeDatePickerDialog.getmMinute());

        add_alarm_tv.setText(calendar.getTime()+"");

        add_alarm_layout.setVisibility(View.VISIBLE);

        //intent发送广播
        Intent intent = new Intent("com.example.lenovo.aviewoftori.Activity.RING");

        intent.setAction(timeDatePickerDialog.getmYear()+"年"+timeDatePickerDialog.getmMonth()+"月"+timeDatePickerDialog.getmDay()+"日"+timeDatePickerDialog.getmHour()+"时"+timeDatePickerDialog.getmMinute()+"分");

        //闹钟到点要执行的动作，动作意图为intent
        PendingIntent pi = PendingIntent.getBroadcast(AddActivity.this,alarm++,intent,0);
        //设置定时器：时钟类型，时间，动作
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),pi);

        Toast.makeText(getBaseContext(),calendar.getTime()+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void negativeListener() {
        int alarm_temp = alarm;

        //intent发送广播
        Intent intent = new Intent("com.example.lenovo.aviewoftori.Activity.RING");

        intent.setAction(timeDatePickerDialog.getmYear()+"年"+timeDatePickerDialog.getmMonth()+"月"+timeDatePickerDialog.getmDay()+"日"+timeDatePickerDialog.getmHour()+"时"+timeDatePickerDialog.getmMinute()+"分");

        //闹钟到点要执行的动作，动作意图为intent
        PendingIntent pi = PendingIntent.getBroadcast(AddActivity.this,alarm_temp,intent,0);
        //设置定时器：时钟类型，时间，动作
        alarmManager.cancel(pi);

    }

    //重写该方法显示图标 (不懂)
//    @Override
//    protected boolean onPrepareOptionsPanel(View view,Menu menu){
//
//        if(menu != null){
//
//            if(menu.getClass() == MenuBuilder.class){
//
//                try{
//
//                    Method m = menu.getClass().getDeclaredMethod("setOptionlIconsVisible",Boolean.TYPE);
//
//                    m.setAccessible(true);
//
//                    m.invoke(menu,true);
//
//                } catch (Exception e) {
//
//                    e.printStackTrace();
//
//                }
//            }
//        }
//
//        return super.onPrepareOptionsPanel(view,menu);
//
//    }

}
