package com.example.lenovo.aviewoftori.Adapter;

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

        private TextView diary_tv_content;

        private TextView diary_tv_time;

        private ImageView diary_iv_image;

        public ViewHolder(View itemView) {
            super(itemView);

            diary_tv_content = (TextView) itemView.findViewById(R.id.diary_tv_content);

            diary_tv_time = (TextView) itemView.findViewById(R.id.diary_tv_time);

            diary_iv_image = (ImageView) itemView.findViewById(R.id.diary_iv_image);

        }
    }
    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary_layout,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DiaryAdapter.ViewHolder holder, int position) {

        Diary diary = diaryList.get(position);

        holder.diary_tv_content.setText(diary.getContent());

        holder.diary_tv_time.setText(diary.getTime());

        holder.diary_iv_image.setImageResource(diary.getImageId());

    }

    @Override
    public int getItemCount() {
        return diaryList.size();
    }
}
