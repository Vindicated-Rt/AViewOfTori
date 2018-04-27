package com.example.lenovo.aviewoftori.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.aviewoftori.R;


/**
 * Created by asus on 2018/4/23.
 */

public class AddActivity extends AppCompatActivity {

    private Toolbar add_toolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        setTitle();

    }


    public void setTitle(){

         add_toolbar = (Toolbar) findViewById(R.id.add_toolbar);

        setSupportActionBar(add_toolbar);

        add_toolbar.setNavigationIcon(R.mipmap.addback);

        //为NavigationIcons设置监听事件，返回
        add_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Intent intent = new Intent(AddActivity.this,)

                finish();
            }
        });

        //设置菜单点击事件
        add_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()){

                    case R.id.add_comfirm:

                        Toast.makeText(AddActivity.this, "confirm", Toast.LENGTH_SHORT).show();

                }

                return true;
            }
        });

    }



    //为toolbar绑定菜单
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.add_toolbar_menu,menu);

        return true;
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
