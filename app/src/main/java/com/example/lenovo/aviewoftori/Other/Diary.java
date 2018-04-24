package com.example.lenovo.aviewoftori.Other;

/**
 * Created by asus on 2018/4/23.
 */

public class Diary {

    private String content;

    private String time;

    private int imageId;

    public Diary(String content,String time,int imageId){

        this.content = content;

        this.time = time;

        this.imageId = imageId;

    }

    public String getContent(){

        return content;

    }

    public String getTime(){

        return time;

    }

    public int getImageId(){

        return imageId;

    }
}
