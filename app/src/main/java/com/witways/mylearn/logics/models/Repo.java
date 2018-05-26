package com.witways.mylearn.logics.models;

import android.graphics.drawable.Drawable;

public class Repo {

    public int image;
    public Drawable imageDrw;
    public String name;
    public boolean expanded = false;
    public boolean parent = false;

    // flag when item swiped
    public boolean swiped = false;

    public Repo() {
    }

    public Repo(int image, String name) {
        this.image = image;
        this.name = name;
    }
}
