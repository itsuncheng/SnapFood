package com.example.raymond.snapfood;

import java.util.ArrayList;

/**
 * Created by Raymond on 3/21/2018.
 */

public class Suggestion {

    private static ArrayList<Meal> mealList = GenerateMeal.getMealList();

    public Suggestion(){

    }

    public static ArrayList<Meal> getSuggestions(){
        sort();

        ArrayList<Meal> list = new ArrayList<Meal>();

        for(int i=0; i<mealList.size();i++){
            if(list.size() < 3) {
                String s = mealList.get(i).getCalories().substring(0,mealList.get(i).getCalories().indexOf("C")-1);
                if (Double.parseDouble(s) < PastMealsFragment.caloriesmeal && !exists(list,s)) {
                    list.add(mealList.get(i));
                }
            }else{
                break;
            }
        }

        return list;


    }

    public static boolean exists(ArrayList<Meal> list, String s){
        boolean exists = false;
        for(int i=0;i<list.size();i++){
            if(s.equals(list.get(i))){
                exists = true;
                break;
            }
        }
        return exists;
    }

    public static void sort(){
        for(int i=0; i<mealList.size();i++){
            for(int j=0; j<mealList.size()-i-1; j++){
                String s1 = mealList.get(j).getCalories().substring(0,mealList.get(j).getCalories().indexOf("C")-1);
                String s2 = mealList.get(j).getCalories().substring(0,mealList.get(j).getCalories().indexOf("C")-1);
                if(Double.parseDouble(s1) < Double.parseDouble(s2)){
                    Meal m = mealList.get(j);
                    mealList.remove(j);
                    mealList.add(j+1,m);
                }
            }
        }
    }



}
