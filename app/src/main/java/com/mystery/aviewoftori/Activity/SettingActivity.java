package com.mystery.aviewoftori.Activity;

import android.annotation.SuppressLint;
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

import com.mystery.aviewoftori.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@SuppressLint("InflateParams")
public class SettingActivity extends AppCompatActivity {

    private Toolbar setting_toolbar;

    private Switch passWord_switch;

    private SharedPreferences setting_info;

    private SharedPreferences.Editor editor;

    private TextView setPassWord_tv;

    private TextView setEmail_tv;

    private TextView setBio_tv;

    private EditText dialog_et;

    private AlertDialog checkOldPassWord;

    private AlertDialog checkPassword;

    private AlertDialog setNewPassword;

    private AlertDialog setEmail;

    private AlertDialog setBio;

    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        passWord_switch = (Switch) findViewById(R.id.setting_lock_sw);

        setting_toolbar = (Toolbar) findViewById(R.id.setting_toolbar);

        setPassWord_tv = (TextView) findViewById(R.id.setting_lock_tv);

        setEmail_tv = (TextView) findViewById(R.id.setting_email_tv);

        setBio_tv = (TextView) findViewById(R.id.setting_bio_tv);

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
        setPassWord_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                SharedPreferences setting_info = getSharedPreferences("info", MODE_PRIVATE);

                String info_password = setting_info.getString("password", "def");

                if (info_password.equals("def")) {

                    Toast.makeText(SettingActivity.this, "没有初始化密码", Toast.LENGTH_SHORT).show();

                } else {

                    dialogcheckold();

                }
            }
        });

        setSwitch();

        /*开关设置监听事件*/
        passWord_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            String info_password = setting_info.getString("password", "def");

            @SuppressLint("ApplySharedPref")
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if (info_password.equals("def")) {

                        dialogSetNew();

                    }

                    if (!info_password.equals("def")) {

                        editor.putBoolean("lock", true).commit();

                    }

                } else {

                    dialogCheck();

                }
            }
        });

        /*设置邮箱监听事件*/
        setEmail_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                dialogSetEmail();

                Toast.makeText(getBaseContext(), "设置成功", Toast.LENGTH_SHORT).show();

            }
        });

        /*设置签名*/
        setBio_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                dialogSetBio();

                Toast.makeText(getBaseContext(), "设置成功", Toast.LENGTH_SHORT).show();

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

        ImageButton setLock_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setLock_dialog_cancel_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认监听事件*/
        setLock_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String password = dialog_et.getText().toString();

                String info_password = setting_info.getString("password", "def");

                if (info_password.equals(password) || info_password.equals("def")) {

                    editor.putBoolean("lock", false).commit();

                    checkPassword.cancel();

                } else {

                    passWord_switch.setChecked(true);

                    Toast.makeText(SettingActivity.this, "密码错误", Toast.LENGTH_SHORT).show();

                }
            }
        });

        setLock_dialog_cancel_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                passWord_switch.setChecked(true);

                checkPassword.dismiss();

            }
        });

        checkPassword.show();
    }

    /*检查旧密码的dialog*/
    private void dialogcheckold() {

        checkOldPassWord = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        checkOldPassWord.setTitle("输入旧密码");

        checkOldPassWord.setView(dialogView);

        checkOldPassWord.setIcon(R.mipmap.dialog_setlock_icon);

        checkOldPassWord.setCancelable(false);

        ImageButton setlock_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setLock_dialog_cancel_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认监听事件*/
        setlock_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String info_password = setting_info.getString("password", "def");

                if (!"".equals(dialog_et.getText().toString()) && info_password.equals(dialog_et.getText().toString())) {

                    dialogSetNew();

                    checkOldPassWord.cancel();

                } else if ("".equals(dialog_et.getText().toString())) {

                    Toast.makeText(SettingActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(SettingActivity.this, "密码错误", Toast.LENGTH_SHORT).show();

                }
            }
        });

        setLock_dialog_cancel_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                checkOldPassWord.cancel();

            }
        });

        checkOldPassWord.show();
    }

    /*设置新密码dialog*/
    private void dialogSetNew() {

        setNewPassword = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        setNewPassword.setTitle("输入新密码");

        setNewPassword.setView(dialogView);

        setNewPassword.setIcon(R.mipmap.dialog_setlock_icon);

        setNewPassword.setCancelable(false);

        ImageButton setLock_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setLock_dialog_cancel_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认监听事件*/
        setLock_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String password = dialog_et.getText().toString();

                if (!"".equals(password)) {

                    editor.putString("password", password).commit();

                    setNewPassword.cancel();

                } else {

                    Toast.makeText(SettingActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }
            }
        });

        setLock_dialog_cancel_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                setNewPassword.cancel();

            }
        });

        setNewPassword.show();

    }

    /*设置邮箱dialog*/
    private void dialogSetEmail() {

        setEmail = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        setEmail.setTitle("输入邮箱");

        setEmail.setView(dialogView);

        setEmail.setIcon(R.mipmap.dialog_setemail_icon);

        setEmail.setCancelable(false);

        ImageButton setEmail_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setEmail_dialog_cancel_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认按钮*/
        setEmail_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String email = dialog_et.getText().toString();

                String str = "^([a-zA-Z0-9_\\-\\.]+)@" +
                        "((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)" +
                        "|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

                Pattern p = Pattern.compile(str);

                Matcher m = p.matcher(email);

                if (!"".equals(email)) {

                    if (m.matches()) {

                        editor.putString("email", email).commit();

                        setEmail.cancel();

                    } else {

                        Toast.makeText(SettingActivity.this, "邮箱有误", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Toast.makeText(SettingActivity.this, "请输入邮箱", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*取消按钮*/
        setEmail_dialog_cancel_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                setEmail.cancel();

            }
        });

        setEmail.show();

    }

    /*设置邮箱dialog*/
    private void dialogSetBio() {

        setBio = new AlertDialog.Builder(SettingActivity.this).create();

        View dialogView = LayoutInflater.from(SettingActivity.this)
                .inflate(R.layout.set_dialog_layuout, null);

        setBio.setTitle("输入签名");

        setBio.setView(dialogView);

        setBio.setIcon(R.mipmap.dialog_setbio_icon);

        setBio.setCancelable(false);

        ImageButton setBio_dialog_ok_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_ok_btn);

        ImageButton setBio_dialog_cancel_btn = (ImageButton) dialogView
                .findViewById(R.id.dialog_cancel_btn);

        dialog_et = (EditText) dialogView.findViewById(R.id.dialog_et);

        /*确认按钮*/
        setBio_dialog_ok_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String bio = dialog_et.getText().toString();

                if (!"".equals(bio)) {

                    editor.putString("bio", bio).commit();

                    setBio.cancel();

                } else {

                    Toast.makeText(SettingActivity.this, "请输入签名", Toast.LENGTH_SHORT).show();
                }

            }
        });

        /*取消按钮*/
        setBio_dialog_cancel_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                setBio.cancel();

            }
        });

        setBio.show();

    }

    /*设置开关*/
    public void setSwitch() {

        boolean chek = setting_info.getBoolean("lock", false);

        if (chek) {

            passWord_switch.setChecked(true);

        } else {

            passWord_switch.setChecked(false);

        }
    }
}
