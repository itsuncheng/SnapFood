package com.example.raymond.snapfood;


import android.graphics.Bitmap;

public class Meal {

    private String date;
    private String name;
    private String calories;
    private Bitmap bitmap;

    public Meal(String date, String name, Bitmap bitmap, String calories){

        this.date = date;
        this.name = name;
        this.bitmap = bitmap;
        this.calories = calories;

    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Bitmap getBitmap(){
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public String getCalories(){
        return calories;
    }

    public void setCalories(String calories){
        this.calories = calories;
    }

}
