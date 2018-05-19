package com.example.raymond.snapfood;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.provider.BaseColumns;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PastMealsFragment.OnFragmentInteractionListener,
        SuggestionsFragment.OnFragmentInteractionListener, ProfileFragment.OnFragmentInteractionListener{

/*    private ListView listView;
    private MyCustomListAdapter customAdapter;*/

    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;

    // index to identify current nav menu item
    public static int navItemIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_PASTMEALS = "pastmeals";
    private static final String TAG_SUGGESTIONS = "suggestions";
    private static final String TAG_PROFILE = "profile";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_PASTMEALS;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    private Bundle args;

    private boolean fragmentsetargs;
    private static Context context;
    public static ArrayList<String> mealTable;
    public static ArrayList<Double> mealCalories;

    public static Context getAppContext() {
        return MainActivity.context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.context = getApplicationContext();


        MealTableDbHelper mDbHelper = new MealTableDbHelper(MainActivity.getAppContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();



        /*values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "apple pie");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 308);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "baby back ribs");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 248);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "baklava");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 334);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "beef carpaccio");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 240);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "beef tarfare");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 341);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "beet salad");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 50);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "beignets");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 527);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "bibimbap");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 560);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "bread pudding");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 133);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "breakfast burrito");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 351);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "bruschetta");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 52);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "caesar salad");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 200);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "cannoli");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 248);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "caprese salad");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 180);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "carrot cake");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 120);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "ceviche");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 173);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "cheese plate");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 88);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "cheesecake");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 372);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "chicken curry");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 240);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "chicken quesadilla");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 270);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "chicken wings");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 247);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "chocolate cake");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 295);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "chocolate mousse");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 909);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "churros");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 115);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "clam chowder");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 154);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "club sandwich");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 480);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "crab cakes");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 126);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "crème brulee");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 551);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "croque madame");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 263);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "cup cakes");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 277);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "deviled eggs");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 80);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "donuts");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 224);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "dumplings");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 40);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "edamame");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 110);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "eggs benedict");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 553);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "escargots");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 90);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "falafel");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 283);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "filet mignon");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 276);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "fish and chips");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 448);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "foie gras");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 60);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "french fries");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 288);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "french onion soup");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 91);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "french toast");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 389);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "fried calamari");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 149);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "fried rice");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 228);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "frozen yogurt");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 126);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "garlic bread");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 186);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "gnocchi");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 220);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "greek salad");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 150);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "grilled cheese sandwich");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 497);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "grilled salmon");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 367);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "guacamole");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 40);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "gyoza");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 330);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "hamburger");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 421);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "hot and sour soup");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 91);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "hot dog");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 269);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "huevos rancheros");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 500);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "hummus");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 26);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "ice cream");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 194);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "lasagna");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 310);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "lobster bisque");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 350);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "lobster roll sandwich");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 49);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "macaroni and cheese");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 260);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "macarons");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 130);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "miso soup");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 547);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "mussels");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 172);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "nachos");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 480);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "omelette");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 54);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "onion rings");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 314);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "oysters");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 199);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "pad thai");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 940);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "paella");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 345);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "pancakes");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 255);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "panna cotta");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 42);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "peking duck");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 340);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "pho");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 367);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "pizza");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 384);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "pork shop");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 176);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "poutine");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 535);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "prime rib");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 210);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "pulled pork sandwich");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 140);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "ramen");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 9);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "ravioli");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 339);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "red velvet cake");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 370);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "risotto");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 174);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "samosa");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 308);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "sashimi");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 35);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "scallops");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 100);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "seaweed salad");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 130);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "shrimp and grits");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 234);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "spaghetti bolognese");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 352);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "spaghetti carbonara");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 491);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "spring rolls");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 199);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "steak");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 379);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "strawberry shortcake");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 45);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "sushi");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 61);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "tacos");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 304);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "takoyaki");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 79);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "tiramisu");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 492);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "tuna tartare");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 128);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);
        values.put(MealTableContract.MealTable.COLUMN_NAME_TITLE, "waffles");
        values.put(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE, 100);
        db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);*/


        //long newRowId = db.insert(MealTableContract.MealTable.TABLE_NAME, null, values);

        SQLiteDatabase db3 = mDbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                MealTableContract.MealTable.COLUMN_NAME_TITLE,
                MealTableContract.MealTable.COLUMN_NAME_SUBTITLE
        };
        String sortOrder =
                MealTableContract.MealTable.COLUMN_NAME_SUBTITLE + " DESC";
        Cursor cursor = db3.query(
                MealTableContract.MealTable.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<String> itemIds3 = new ArrayList<String>();
        ArrayList<Double> itemIds4 = new ArrayList<Double>();

        while(cursor.moveToNext()) {
            String itemId = cursor.getString(
                    cursor.getColumnIndexOrThrow(MealTableContract.MealTable.COLUMN_NAME_TITLE));
            itemIds3.add(itemId);

            double itemId2 = cursor.getDouble(
                    cursor.getColumnIndexOrThrow(MealTableContract.MealTable.COLUMN_NAME_SUBTITLE));
            itemIds4.add(itemId2);
        }
        cursor.close();
        MainActivity.mealTable = itemIds3;
        MainActivity.mealCalories = itemIds4;
        /*for(int i=0;i<MainActivity.mealTable.size();i++){
            Log.i("AAAAAAAAAAAAAAA","MealTable: "+ MainActivity.mealTable.get(i) );
        }*/


        MealHistoryDbHelper mDbHelper2 = new MealHistoryDbHelper(MainActivity.getAppContext());
        SQLiteDatabase db2 = mDbHelper2.getReadableDatabase();
        String[] projection2 = {
                BaseColumns._ID,
                MealTableContract.MealHistory.COLUMN_NAME_TITLE,
                MealTableContract.MealHistory.COLUMN_NAME_SUBTITLE
        };
        String sortOrder2 =
                MealTableContract.MealHistory.COLUMN_NAME_SUBTITLE + " DESC";
        Cursor cursor2 = db2.query(
                MealTableContract.MealHistory.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        ArrayList<String> itemIds = new ArrayList<String>();
        while(cursor2.moveToNext()) {
            String itemId = cursor2.getString(
                    cursor2.getColumnIndexOrThrow(MealTableContract.MealHistory.COLUMN_NAME_TITLE));
            itemIds.add(itemId);
        }
        cursor2.close();
        GenerateMeal.setMealList(itemIds);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        fragmentsetargs = false;

        // initializing navigation menu
        setUpNavigationView();

        String j1; String j2;
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            if(extras.getString("Type")!= null) {
                j1 = extras.getString("Type");
                j2 = extras.getString("Value");
                args = new Bundle();
                args.putString("Type", j1);
                args.putString("Value", j2);

                fragmentsetargs = true;

                navItemIndex = 2;
                CURRENT_TAG = TAG_PROFILE;
                loadHomeFragment();

                Log.d(TAG_PASTMEALS, "MainActivity Code Run");
            }else{
                navItemIndex = 0;
                CURRENT_TAG = TAG_PASTMEALS;
                loadHomeFragment();
            }

        } else if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_PASTMEALS;
            loadHomeFragment();
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if (navItemIndex == 0) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
        }else if (navItemIndex == 1) {

        }else if (navItemIndex == 2) {

        }else if (navItemIndex == 3) {

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.camera:
                Intent intent = new Intent(this, CameraActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_pastmeals:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_PASTMEALS;
                        break;
                    case R.id.nav_suggestions:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_SUGGESTIONS;
                        break;
                    case R.id.nav_profile:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_PROFILE;
                        break;
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

        /***
         * Returns respected fragment that user
         * selected from navigation menu
         */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                if(fragmentsetargs){
                    fragment.setArguments(args);
                    fragmentsetargs = false;
                }
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                Fragment pastMealsFragment = new PastMealsFragment();
                return pastMealsFragment;
            case 1:
                Fragment suggestionsFragment = new SuggestionsFragment();
                return suggestionsFragment;
            case 2:
                Fragment profileFragment = new ProfileFragment();
                return profileFragment;

            default:
                return new PastMealsFragment();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_PASTMEALS;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}


