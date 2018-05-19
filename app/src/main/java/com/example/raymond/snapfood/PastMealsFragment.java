package com.example.raymond.snapfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PastMealsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PastMealsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PastMealsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ListView listView;
    private MyCustomListAdapter customAdapter;
    private TextView calories;
    private TextView meal;
    private TextView snack;
    private TextView remaining;

//    private static ArrayList<Meal> mealList = new ArrayList<>();

    public PastMealsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PastMealsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PastMealsFragment newInstance(String param1, String param2) {
        PastMealsFragment fragment = new PastMealsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_past_meals, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private String mImageURLString = "http://api.wolframalpha.com/v1/simple?appid=YWJ42K-2XVQ9J3487&i=chocolate+cake%3F";
    private static GenerateMeal g;
    private static boolean check = false;
    public static double numCalories;
    public static int meals = 3;

    public static double caloriesday;
    public static double caloriesmeal;
    public static double caloriesremaining;

    private static String gender;
    private static double currentWeight;
    private static double goalWeight;
    private static double height;
    private static int age;
    private static int days;
    public static boolean check2 = true;

    public static double BMR;
    public static double n;
    public static double c;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) getView().findViewById(R.id.listview);

        Log.d("BBBBBBBBBBBBBBB", "Activity started: " + check2);

        if(getActivity().getIntent().hasExtra("name")) {

            Bitmap bitmap = CameraActivity.getBitmapList().get(CameraActivity.getBitmapList().size()-1);
            String name = getActivity().getIntent().getStringExtra("name");
            String calories = getActivity().getIntent().getStringExtra("calories");
            numCalories = Double.parseDouble(calories);
            g.createMeal("2018/04/08", name, bitmap, calories + " Cal");
            //mealList.add(0,new Meal("2018/02/02", name, bitmap, "15 Cal"));
            getActivity().getIntent().removeExtra("name");
            getActivity().getIntent().removeExtra("calories");

            check = true;
        }

        customAdapter = new MyCustomListAdapter(getActivity(),g.getMealList());
        listView.setAdapter(customAdapter);

        calories = getView().findViewById(R.id.calories);
        meal = getView().findViewById(R.id.meal);
        remaining = getView().findViewById(R.id.remaining);


    if(true) {
        if (ProfileFragment.gender.equals(""))
            gender = "";
        else
            gender = ProfileFragment.gender;

        if (ProfileFragment.currentWeight.equals(""))
            currentWeight = 0;
        else
            currentWeight = Double.parseDouble(ProfileFragment.currentWeight);

        if (ProfileFragment.goalWeight.equals(""))
            goalWeight = 0;
        else
            goalWeight = Double.parseDouble(ProfileFragment.goalWeight);

        if (ProfileFragment.height.equals(""))
            height = 0;
        else
            height = Double.parseDouble(ProfileFragment.height);

        if (ProfileFragment.age.equals(""))
            age = 0;
        else
            age = Integer.parseInt(ProfileFragment.age);

        if (ProfileFragment.days.equals(""))
            days = 0;
        else
            days = Integer.parseInt(ProfileFragment.days);

        calculateCalories();
        /*        caloriesday = ((currentWeight - goalWeight) / (weeks * 7)) * 1000;
        caloriesmeal = caloriesday / (meals + snacks / 2);
        caloriessnack = caloriesday / (meals * 2 + snacks);
        caloriesremaining = caloriesday;*/


    }



        if(check){
            caloriesremaining -= numCalories;
            meals--;
            caloriesmeal = caloriesremaining / meals;
            check = false;
        }


        Log.i("CCCCCCCCCCCC","caloriesday: " + new Double(caloriesday).intValue());
        calories.setText("Calories/day: " + new Double(caloriesday).intValue());
        meal.setText("Calories/Meal: " + new Double(caloriesmeal).intValue());
        remaining.setText("Remaining Calories today: " + new Double(caloriesremaining).intValue());



    }

    public void calculateCalories(){
        if(check2) {
            if (gender.equals("Male")) {
                BMR = (10 * currentWeight) + (6.25 * height) - (5 * age) + 5;
            } else if (gender.equals("Female")) {
                BMR = (10 * currentWeight) + (6.25 * height) - (5 * age) - 161;
            }

            n = (currentWeight - goalWeight) / days;
            c = n * 7 * 500 / 0.45;
            caloriesday = BMR - c;
            caloriesmeal = (BMR - c) / 3;
            caloriesremaining = BMR - c;

            if(days!=0)
                check2 = false;
            Log.d("BBBBBBBBBBBBBBB", "Activity ended: " + check2);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
