package com.witways.mylearn.material.model;

public class SectionImage {
    public int image;
    public String title;
    public boolean section = false;

    public SectionImage(int image, String title, boolean section) {
        this.image = image;
        this.title = title;
        this.section = section;
    }
}
