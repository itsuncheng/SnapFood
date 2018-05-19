package com.example.raymond.snapfood;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Type";
    private static final String ARG_PARAM2 = "Value";

    // TODO: Rename and change types of parameters
    private String mParam1 = "";
    private String mParam2 = "";

    public static String gender = "";
    public static String currentWeight = "";
    public static String goalWeight = "";
    public static String height = "";
    public static String age = "";
    public static String days = "";

    private OnFragmentInteractionListener mListener;

    private ListView listview;
//    private ArrayList<String> array;
//    private ArrayAdapter adapter;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParam1 = "";
        mParam2 = "";
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        if(mParam1.equals("gender")){
            gender = mParam2;
        }else if(mParam1.equals("currentWeight")){
            currentWeight = mParam2;
        } else if(mParam1.equals("goalWeight")){
            goalWeight = mParam2;
        } else if(mParam1.equals("height")){
            height = mParam2;
        } else if(mParam1.equals("age")){
            age = mParam2;
        } else if(mParam1.equals("days")){
            days = mParam2;
        }

        Log.d(TAG,"mParam: "+mParam1+" mParam2: "+mParam2);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ArrayList<String> array = new ArrayList<>();
        array.add("Gender: " + gender);
        array.add("Current Weight: " + currentWeight);
        array.add("Goal Weight: " + goalWeight);
        array.add("Height: " + height);
        array.add("Age: " + age);
        array.add("Days: " + days);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.profilelayout, array);
        ListView list = (ListView) view.findViewById(R.id.listviewprofile);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(getActivity(), EditGenderActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getActivity(), EditCurrentWeightActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getActivity(), EditGoalWeightActivity.class);
                    startActivity(intent);
                } else if (position == 3){
                    Intent intent = new Intent(getActivity(), EditHeightActivity.class);
                    startActivity(intent);
                } else if (position == 4){
                    Intent intent = new Intent(getActivity(), EditAgeActivity.class);
                    startActivity(intent);
                } else if (position == 5){
                    Intent intent = new Intent(getActivity(), EditDaysActivity.class);
                    startActivity(intent);
                }
            }
        }); return view;
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




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
