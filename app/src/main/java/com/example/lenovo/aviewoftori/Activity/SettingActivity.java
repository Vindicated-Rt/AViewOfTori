package com.example.lenovo.aviewoftori.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.aviewoftori.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SettingActivity extends AppCompatActivity {

    private Toolbar setting_toolbar;

    private Switch passwore_switch;

    private SharedPreferences setting_info;

    private SharedPreferences.Editor editor;

    private TextView setpassword_tv;

    private TextView setemail_tv;

    private TextView setbio_tv;

    private EditText dialog_et;

    private AlertDialog checkoldPassword;

    private AlertDialog checkPassword;

    private AlertDialog setnewPassword;

    private AlertDialog setemail;

    private AlertDialog setbio;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        passwore_switch = (Switch) findViewById(R.id.setting_lock_sw);

        setting_toolbar = (Toolbar) findViewById(R.id.setting_toolbar);

        setpassword_tv = (TextView) findViewById(R.id.setting_lock_tv);

        setemail_tv = (TextView) findViewById(R.id.setting_email_tv);

        setbio_tv = (TextView) findViewById(R.id.setting_bio_tv);

        setSupportActionBar(setting_toolbar);

        getSupportActionBar().setTitle(getString(R.string.setting));

        setting_info = getSharedPreferences("info", MODE_PRIVATE);

        editor = getSharedPreferences("info", MODE_PRIVATE).edit();

        setting_toolbar.setNavigationIcon(R.mipmap.back);

        /*设置返回导航按钮*/
        setting_toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                finish();
            }
        });

        /*设置密码监听事件*/
        setpassword_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                SharedPreferences setting_info = getSharedPreferences("info", MODE_PRIVATE);

                String info_password = setting_info.getString("password", "def");

                if(info_password.equals("def")){

                    Toast.makeText(SettingActivity.this,"没有初始化密码",Toast.LENGTH_SHORT).show();

                }else {

                    dialogcheckold();

                }
            }
        });

        setSwitvh();

        /*开关设置监听事件*/
        passwore_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            String info_password = setting_info.getString("password", "def");

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if(info_password.equals("def")){

                        dialogsetnew();

                    }

                    if(!info_password.equals("def")){

                        editor.putBoolean("lock", true).commit();

                    }

                } else {

                    dialogCheck();

                }
            }
        });

        /*设置邮箱监听事件*/
        setemail_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                dialogsetemail();

            }
        });

        /*设置签名*/
        setbio_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                dialogsetbio();

            }
        });
    }

    /*输入密码的dialog*/
    private void dialogCheck() {

        checkPassword = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        checkPassword.setTitle("输入密码");

        checkPassword.setView(dialogView);

        checkPassword.setIcon(R.mipmap.dialog_setlock_icon);

        checkPassword.setCancelable(false);

        ImageButton setlock_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setlock_dialog_cancedl_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认监听事件*/
        setlock_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String password = dialog_et.getText().toString();

                String info_password = setting_info.getString("password", "def");

                if (info_password.equals(password) || info_password.equals("def")) {

                    editor.putBoolean("lock", false).commit();

                    checkPassword.cancel();

                } else {

                    passwore_switch.setChecked(true);

                    Toast.makeText(SettingActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    
                }
            }
        });

        setlock_dialog_cancedl_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                passwore_switch.setChecked(true);
                
                checkPassword.dismiss();

            }
        });

        checkPassword.show();
    }

    /*检查旧密码的dialog*/
    private void dialogcheckold() {

        checkoldPassword = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        checkoldPassword.setTitle("输入旧密码");

        checkoldPassword.setView(dialogView);

        checkoldPassword.setIcon(R.mipmap.dialog_setlock_icon);

        checkoldPassword.setCancelable(false);

        ImageButton setlock_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setlock_dialog_cancedl_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认监听事件*/
        setlock_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String info_password = setting_info.getString("password", "def");

                if (!"".equals(dialog_et.getText().toString()) && info_password.equals(dialog_et.getText().toString())) {

                    dialogsetnew();

                    checkoldPassword.cancel();

                } else if ("".equals(dialog_et.getText().toString())){

                    Toast.makeText(SettingActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(SettingActivity.this, "密码错误", Toast.LENGTH_SHORT).show();

                }
            }
        });

        setlock_dialog_cancedl_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                checkoldPassword.cancel();

            }
        });

        checkoldPassword.show();
    }
    
    /*设置新密码dialog*/
    private void dialogsetnew(){

        setnewPassword = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        setnewPassword.setTitle("输入新密码");

        setnewPassword.setView(dialogView);

        setnewPassword.setIcon(R.mipmap.dialog_setlock_icon);

        setnewPassword.setCancelable(false);

        ImageButton setlock_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setlock_dialog_cancedl_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认监听事件*/
        setlock_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String password = dialog_et.getText().toString();

                if (!"".equals(password)) {

                    editor.putString("password",password).commit();

                    setnewPassword.cancel();

                } else {

                    Toast.makeText(SettingActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setlock_dialog_cancedl_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                setnewPassword.cancel();

            }
        });

        setnewPassword.show();
        
    }

    /*设置邮箱dialog*/
    private void dialogsetemail(){

        setemail = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        setemail.setTitle("输入邮箱");

        setemail.setView(dialogView);

        setemail.setIcon(R.mipmap.dialog_setemail_icon);

        setemail.setCancelable(false);

        ImageButton setemail_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setemail_dialog_cancedl_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认按钮*/
        setemail_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String email = dialog_et.getText().toString();

                String str = "^([a-zA-Z0-9_\\-\\.]+)@" +
                        "((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)" +
                        "|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

                Pattern p = Pattern.compile(str);

                Matcher m = p.matcher(email);

                if (!"".equals(email)) {

                    if(m.matches()){

                        editor.putString("email",email).commit();

                        setemail.cancel();

                    }else {

                        Toast.makeText(SettingActivity.this, "邮箱有误", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(SettingActivity.this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*取消按钮*/
        setemail_dialog_cancedl_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                setemail.cancel();

            }
        });

        setemail.show();

    }

    /*设置邮箱dialog*/
    private void dialogsetbio(){

        setbio = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        setbio.setTitle("输入签名");

        setbio.setView(dialogView);

        setbio.setIcon(R.mipmap.dialog_setbio_icon);

        setbio.setCancelable(false);

        ImageButton setebio_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setebio_dialog_cancedl_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认按钮*/
        setebio_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String bio = dialog_et.getText().toString();

                if (!"".equals(bio)) {

                    editor.putString("bio",bio).commit();

                    setbio.cancel();

                } else {

                    Toast.makeText(SettingActivity.this, "请输入签名", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*取消按钮*/
        setebio_dialog_cancedl_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                setbio.cancel();

            }
        });

        setbio.show();

    }

    /*设置开关*/
    public void setSwitvh() {

        boolean chek = setting_info.getBoolean("lock", false);

        if (chek) {

            passwore_switch.setChecked(true);

        } else {

            passwore_switch.setChecked(false);

        }
    }
}
