package com.mystery.aviewoftori.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mystery.aviewoftori.R;

/*备忘录list适配器*/
public class MemoListAdapter extends BaseAdapter {

    private Context context;

    private RelativeLayout listView_layout;

    private Cursor cursor;

    /*构造方法*/
    public MemoListAdapter(Context context, Cursor cursor) {

        this.context = context;

        this.cursor = cursor;

    }

    /*布局设置方法*/
    @SuppressLint({"ViewHolder", "InflateParams"})
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        listView_layout = (RelativeLayout) inflater.inflate(R.layout.item_memo_listview, null);

        ImageView lv_iv_pic = (ImageView) listView_layout.findViewById(R.id.memo_listview_item_iv);

        TextView lv_tv_text = (TextView) listView_layout.findViewById(R.id.memo_listview_item_content_tv);

        TextView lv_tv_time = (TextView) listView_layout.findViewById(R.id.memo_listview_item_time_tv);

        cursor.moveToPosition(position);

        String content = cursor.getString(cursor.getColumnIndex("content"));

        String time = cursor.getString(cursor.getColumnIndex("time"));

        String url = cursor.getString(cursor.getColumnIndex("image"));

        lv_iv_pic.setImageBitmap(getImageThumbnail(url, 150, 150));

        lv_tv_text.setText(content);

        lv_tv_time.setText(time);

        return listView_layout;
    }

    /*获取图片缩略图方法*/
    private Bitmap getImageThumbnail(String uri, int width, int height) {

        Bitmap bitmap;       //初始化图片

        BitmapFactory.Options options = new BitmapFactory.Options();   //实例化图片解码

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(uri, options);     //寻找图片地址

        options.inJustDecodeBounds = false;     //是否设置按比例缩放

        /*缩略图合适比例 = 图片实际长宽 / 传入的长宽*/
        int beWidth = options.outWidth / width;

        int beHeight = options.outHeight / height;

        int ratio;

        if (beWidth < beHeight) {

            ratio = beWidth;

        } else {

            ratio = beHeight;
        }
        if (ratio <= 0) {

            ratio = 1;
        }

        options.inSampleSize = ratio;

        bitmap = BitmapFactory.decodeFile(uri, options);

        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        return bitmap;
    }

    public int getCount() {

        return cursor.getCount();

    }

    public Object getItem(int position) {

        return cursor.getPosition();

    }

    public long getItemId(int position) {

        return position;

    }
}
