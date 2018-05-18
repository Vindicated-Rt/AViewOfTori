package com.example.lenovo.aviewoftori.Activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.aviewoftori.Base.DataBaseHelper;
import com.example.lenovo.aviewoftori.R;

import java.util.Date;


public class LookActivity extends AppCompatActivity {

    private FloatingActionButton look_fab;

    private TextView look_time_tv;

    private EditText look_content_ed;

    private ImageView look_iv;

    private Toolbar look_toolbar;

    private DataBaseHelper dataBaseHelper;

    private SQLiteDatabase dbWriter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.look_activity);

        dataBaseHelper = new DataBaseHelper(this, "Store.db", null, 1);

        dbWriter = dataBaseHelper.getWritableDatabase();

        lookfindid();

        setToolbar();

        setInfo();

        look_fab = (FloatingActionButton) findViewById(R.id.look_fab);

        look_fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                look_content_ed.setInputType(InputType.TYPE_CLASS_TEXT);

                look_toolbar.getMenu().findItem(R.id.look_delete).setVisible(false);

                look_toolbar.getMenu().findItem(R.id.look_add).setVisible(true);

            }
        });
    }

    /*实例化对象*/
    public void lookfindid(){

        look_time_tv = (TextView) findViewById(R.id.look_time_tv);

        look_content_ed = (EditText) findViewById(R.id.look_content_et);

        look_iv = (ImageView) findViewById(R.id.look_iv);

        look_toolbar = (Toolbar) findViewById(R.id.look_toolbar);

        look_content_ed.setInputType(InputType.TYPE_NULL);

    }

    /*布置toolbar布局*/
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.look_toolbar, menu);

        return true;
    }

    /*设置toolbar*/
    public void setToolbar() {

        look_toolbar = (Toolbar) findViewById(R.id.look_toolbar);//实例化toolbar

        setSupportActionBar(look_toolbar);//给当前布局添加toolbar

        look_toolbar.setNavigationIcon(R.mipmap.back);//设置导航图标

        getSupportActionBar().setTitle("");

        /*导航图标监听点击事件*/
        look_toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                finish();

            }
        });
    }

    /*item点击事件*/
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.look_delete:

                deleteData();

                finish();

                break;

            case R.id.look_add:

                storeData();

                finish();

                break;

            default:

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /*设置图片,文本*/
    public void setInfo() {

        look_content_ed.setText(getIntent().getStringExtra("content"));

        look_time_tv.setText(getIntent().getStringExtra("time"));

        /*初始化位图对象,以主页面传的值打开对应图片文件*/
        Bitmap bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra("image"));

        look_iv.setImageBitmap(bitmap);

    }

    /*删除数据*/
    public void deleteData() {

        if(getIntent().getStringExtra("table").equals("memo")){

            dbWriter.delete("Memo", "id=" + getIntent().getIntExtra("id", 0), null);

        }else if (getIntent().getStringExtra("table").equals("diary")){

            dbWriter.delete("Diary", "id=" + getIntent().getIntExtra("id", 0), null);

        }
    }

    /*存储数据*/
    public void storeData() {

        dataBaseHelper.getReadableDatabase();

        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();

        int id = getIntent().getIntExtra("id",0);

        String str = Integer.toString(id);

        values.put("content", look_content_ed.getText().toString());

        values.put("time", getTime());

        if (getIntent().getStringExtra("table").equals("memo")) {

            db.update("memo",values,"id = ?",new String[]{str});

        } else if (getIntent().getStringExtra("table").equals("diary")) {

            db.update("diary",values,"id = ?",new String[]{str});

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
}
