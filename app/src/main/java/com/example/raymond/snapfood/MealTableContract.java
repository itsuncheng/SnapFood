package com.example.raymond.snapfood;

import android.provider.BaseColumns;

/**
 * Created by Raymond on 3/17/2018.
 */

public class MealTableContract {

    private MealTableContract() {}

    /* Inner class that defines the table contents */
    public static class MealHistory implements BaseColumns {
        public static final String TABLE_NAME = "MealHistory";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";

    }

    public static class MealTable implements BaseColumns {
        public static final String TABLE_NAME = "MealTable";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";

    }

}
