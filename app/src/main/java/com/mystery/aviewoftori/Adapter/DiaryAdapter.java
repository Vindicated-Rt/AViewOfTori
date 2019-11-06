package com.mystery.aviewoftori.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mystery.aviewoftori.Other.Diary;
import com.mystery.aviewoftori.R;

import java.util.List;

/**
 * Created by asus on 2018/4/23.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private Context mContext;
    private List<Diary> diaryList;
    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }

    public interface OnItemLongClickListener {

        boolean onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener longListener) {
        this.itemLongClickListener = longListener;

    }

    public DiaryAdapter(List<Diary> list, Context context) {

        mContext = context;

        this.diaryList = list;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private OnItemClickListener onItemClickListener;

        private OnItemLongClickListener onItemLongClickListener;

        View diaryView;

        private TextView diary_tv_content;

        private TextView diary_tv_time;

        private ImageView diary_iv_image;

        ViewHolder(View itemView, OnItemClickListener listener, OnItemLongClickListener longListener) {
            super(itemView);

            diary_tv_content = (TextView) itemView.findViewById(R.id.diary_tv_content);

            diary_tv_time = (TextView) itemView.findViewById(R.id.diary_tv_time);

            diary_iv_image = (ImageView) itemView.findViewById(R.id.diary_iv_image);

            diaryView = itemView;

            onItemClickListener = listener;

            onItemLongClickListener = longListener;

            diaryView.setOnClickListener(this);

            diaryView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getPosition());

        }

        public boolean onLongClick(View v) {
            return onItemLongClickListener != null && onItemLongClickListener.onItemLongClick(v, getPosition());
        }
    }

    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary_layout, parent, false);

        return new ViewHolder(view, itemClickListener, itemLongClickListener);
    }

    @Override
    public void onBindViewHolder(DiaryAdapter.ViewHolder holder, int position) {

        Diary diary = diaryList.get(position);

        //为控件设置资源
        holder.diary_tv_content.setText(diary.getContent());

        holder.diary_tv_time.setText(diary.getTime());

        holder.diary_iv_image.setImageBitmap(getImageThumbnail(diary.getImage(), 150));

    }

    //获取缩略图
    private Bitmap getImageThumbnail(String uri, int height) {

        Bitmap bitmap;

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeFile(uri, options);

        options.inJustDecodeBounds = false;

        int beWidth = options.outWidth / 150;

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

        bitmap = ThumbnailUtils.extractThumbnail(bitmap, 150, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        return bitmap;
    }


    @Override
    public int getItemCount() {
        return diaryList.size();
    }


}
