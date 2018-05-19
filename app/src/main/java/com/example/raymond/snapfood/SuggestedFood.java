package com.example.raymond.snapfood;

import android.graphics.Bitmap;

/**
 * Created by Raymond on 3/25/2018.
 */

public class SuggestedFood {

    private String name;
    private String bitmapLink;
    private String sourceLink;

    public SuggestedFood(String name, String bitmapLink, String sourceLink){
        this.name = name;
        this.bitmapLink = bitmapLink;
        this.sourceLink = sourceLink;
    }

    public void setName (String name){
        this.name = name;
    }

    public void setBitmapLink (String bitmapLink){
        this.bitmapLink = bitmapLink;
    }

    public void setSourceLink (String sourceLink){
        this.sourceLink = sourceLink;
    }

    public String getName(){
        return name;
    }

    public String getBitmapLink(){
        return bitmapLink;
    }

    public String getSourceLink(){
        return sourceLink;
    }
}
