package com.example.lenovo.aviewoftori.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.aviewoftori.Other.Diary;
import com.example.lenovo.aviewoftori.R;

import java.util.List;

/**
 * Created by asus on 2018/4/23.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private List<Diary>diaryList;




    public class ViewHolder extends RecyclerView.ViewHolder {

        View diaryView;

        private TextView diary_tv_content;

        private TextView diary_tv_time;

        private ImageView diary_iv_image;

        public ViewHolder(View itemView) {
            super(itemView);

            diary_tv_content = (TextView) itemView.findViewById(R.id.diary_tv_content);

            diary_tv_time = (TextView) itemView.findViewById(R.id.diary_tv_time);

            diary_iv_image = (ImageView) itemView.findViewById(R.id.diary_iv_image);

            diaryView = itemView;

        }
    }
    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary_layout,parent,false);

        final ViewHolder viewHolder = new ViewHolder(view);

        //点击事件
        viewHolder.diaryView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // 点击Item事件
            }
        });

        viewHolder.diary_iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击图片事件
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DiaryAdapter.ViewHolder holder, int position) {

        Diary diary = diaryList.get(position);

        //为控件设置资源
        holder.diary_tv_content.setText(diary.getContent());

        holder.diary_tv_time.setText(diary.getTime());

        holder.diary_iv_image.setImageBitmap(getImageThumbanil(diary.getImage(),150,150));


    }

    //获取缩略图
    private Bitmap getImageThumbanil(String uri,int  width,int height) {

        Bitmap bitmap = null;

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        bitmap = BitmapFactory.decodeFile(uri,options);

        options.inJustDecodeBounds = false;

        int beWidth = options.outWidth / width;

        int beHeight = options.outHeight / height;

        int ratio = 1;

        if(beWidth <beHeight){

            ratio = beWidth;

        }else{

            ratio = beHeight;

        }

        if(ratio <= 0){

            ratio = 1;

        }

        options.inSampleSize = ratio;

        bitmap = BitmapFactory.decodeFile(uri,options);

        bitmap = ThumbnailUtils.extractThumbnail(bitmap,width,height,ThumbnailUtils.OPTIONS_RECYCLE_INPUT);

        return bitmap;
    }


    @Override
    public int getItemCount() {
        return diaryList.size();
    }

    public DiaryAdapter( List<Diary> list){

        this.diaryList = list;

    }
}
