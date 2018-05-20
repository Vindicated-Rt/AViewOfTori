package com.example.lenovo.aviewoftori.Other;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.example.lenovo.aviewoftori.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/5/14.
 */


public class TimeDatePickerDialog {

    private Context mContext;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private TimePicker timedate_tp;

    private DatePicker timedate_dp;

    private AlertDialog.Builder alertDialogBuilder;

    private TimeDatePickerDialogInterface timeDatePickerDialogInterface;

    public TimeDatePickerDialog(Context context) {

        super();

        mContext = context;

        timeDatePickerDialogInterface = (TimeDatePickerDialogInterface) context;

    }


    /*显示时间*/
    public void showDialog() {

        View view = initTimeDataPicker();

        alertDialogBuilder = new AlertDialog.Builder(mContext);

        initDialog(view);

        alertDialogBuilder.show();

    }

    /*初始化picker*/
    private View initTimeDataPicker() {

        View view = LayoutInflater.from(mContext).inflate(R.layout.timedatepicker_dialog_layout, null);

        timedate_dp = (DatePicker) view.findViewById(R.id.timedate_dp);

        timedate_tp = (TimePicker) view.findViewById(R.id.timedate_tp);

        timedate_tp.setIs24HourView(true);

        resizePikcer(timedate_tp);

        resizePikcer(timedate_dp);

        return view;

    }

    /*创建dialog*/
    private void initDialog(View view) {

        alertDialogBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

                getDatePickerValue();

                getTimePickerValue();

                //这里调用接口，使其要完成的操作通过“确认点击”完成
                timeDatePickerDialogInterface.positiveListener();

            }
        });

        alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                timeDatePickerDialogInterface.negativeListener();
                dialog.dismiss();

            }
        });

        alertDialogBuilder.setView(view);

    }


    //接受选择的值
    private void getTimePickerValue() {

        mHour = timedate_tp.getHour();

        mMinute = timedate_tp.getMinute();

    }

    private void getDatePickerValue() {

        mYear = timedate_dp.getYear();

        mMonth = timedate_dp.getMonth();

        mDay = timedate_dp.getDayOfMonth();

    }

    /*调整Fragment大小*/
    private void resizePikcer(FrameLayout fl) {

        List<NumberPicker> npList = findNumberPicker(fl);

        for (NumberPicker np : npList) {

            resizeNumberPicker(np);

        }
    }

    /*调整numberpicker的大小*/
    private void resizeNumberPicker(NumberPicker np) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(120, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(10, 0, 10, 0);

        np.setLayoutParams(params);

    }


    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {

        List<NumberPicker> npList = new ArrayList<NumberPicker>();

        View child = null;

        if (viewGroup != null) {

            for (int i = 0; i < viewGroup.getChildCount(); i++) {

                child = viewGroup.getChildAt(i);

                if (child instanceof NumberPicker) {

                    npList.add((NumberPicker) child);

                } else if (child instanceof LinearLayout) {

                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);

                    if (result.size() > 0) {

                        return result;

                    }

                }

            }

        }

        return npList;
    }

    //返回选择的时间值
    public int getmDay() {
        return mDay;
    }

    public int getmMonth() {
        return mMonth;
    }

    public int getmYear() {
        return mYear;
    }

    public int getmMinute() {
        return mMinute;
    }

    public int getmHour() {
        return mHour;
    }

    //接口
    public interface TimeDatePickerDialogInterface {

        public void positiveListener();

        public void negativeListener();

    }

}
