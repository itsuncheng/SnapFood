package com.example.raymond.snapfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raymond on 3/17/2018.
 */

public class GenerateMeal {

    private static ArrayList<Meal> mealList = new ArrayList<>();
    private static ArrayList<String> mealHistory;

    public GenerateMeal(Context context){

    }

    public static void createMeal(String date, String name, Bitmap bitmap, String calories){

        Meal meal = new Meal(date,name,bitmap,calories);
        mealList.add(0, meal);

        addtoDatabase(name,calories);
    }

    public static ArrayList<Meal> getMealList(){
        return mealList;
    }

    public static ArrayList<String> getMealHistory() { return mealHistory; }

    public static void setMealList(ArrayList<String> list){
        mealHistory = list;
    }

    public static void addtoDatabase(String name, String calories){

        MealHistoryDbHelper mDbHelper = new MealHistoryDbHelper(MainActivity.getAppContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MealTableContract.MealHistory.COLUMN_NAME_TITLE, name);
        values.put(MealTableContract.MealHistory.COLUMN_NAME_SUBTITLE, calories);
        long newRowId = db.insert(MealTableContract.MealHistory.TABLE_NAME, null, values);

        SQLiteDatabase db2 = mDbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                MealTableContract.MealHistory.COLUMN_NAME_TITLE,
                MealTableContract.MealHistory.COLUMN_NAME_SUBTITLE
        };
        String sortOrder =
                MealTableContract.MealHistory.COLUMN_NAME_SUBTITLE + " DESC";
        Cursor cursor = db2.query(
                MealTableContract.MealHistory.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<String> itemIds = new ArrayList<String>();
        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(MealTableContract.MealHistory.COLUMN_NAME_TITLE));
            itemIds.add(itemId);
        }
        cursor.close();
        String foodName = itemIds.get(itemIds.size()-1);
        mealHistory.add(foodName);
        for(String i : mealHistory){
            Log.d("Hi","Item: " + i);
        }
    }
}
