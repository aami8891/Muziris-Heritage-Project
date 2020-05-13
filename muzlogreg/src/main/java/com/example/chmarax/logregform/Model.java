package com.example.chmarax.logregform;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.engine.Resource;



public class Model {

    private String urlimage;
    private String title;
    private String desc;

    public Model(String urlimage, String title, String desc) {
        this.urlimage = urlimage;
        this.title = title;
        this.desc = desc;
    }

    public String geturlimage() {
        
        return urlimage;
    }

    public void seturlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
