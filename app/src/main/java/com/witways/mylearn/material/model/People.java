package com.witways.mylearn.material.model;

import android.graphics.drawable.Drawable;

public class People {
    /**
     * 图片资源序号
     */
    public int image;
    /**
     * 图片数据
     */
    public Drawable imageDrw;
    /**
     * 姓名
     */
    public String name;
    /**
     * 邮件
     */
    public String email;
    public boolean section = false;

    public People() {
    }

    public People(String name, boolean section) {
        this.name = name;
        this.section = section;
    }

}
