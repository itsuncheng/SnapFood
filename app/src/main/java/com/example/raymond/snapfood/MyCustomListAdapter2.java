package com.example.raymond.snapfood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class MyCustomListAdapter2 extends ArrayAdapter<SuggestedFood>{

    private Context context;
    private List<SuggestedFood> mealList = new ArrayList<>();

    public MyCustomListAdapter2(Context context, ArrayList<SuggestedFood> mealList) {
        super(context, 0);
        this.context = context;
        this.mealList = mealList;
    }

    public static Bitmap bitmap;
    public static Bitmap newBitmap;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.rowlayout2,parent,false);

        final SuggestedFood currentMeal = mealList.get(position);

        ImageView imageView = (ImageView) listItem.findViewById(R.id.imageView);
        TextView name = (TextView) listItem.findViewById(R.id.name);



        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String mImageURLString = currentMeal.getBitmapLink();
        Log.d("mImageURLString", "mImageURLString: " + mImageURLString);

        ImageRequest imageRequest = new ImageRequest(
                mImageURLString, // Image URL
                new Response.Listener<Bitmap>() { // Bitmap listener
                    @Override
                    public void onResponse(Bitmap response) {
                        // Do something with response
                        Log.d("listItem Pressed", "Response returned ");

                        bitmap = response;
                        Matrix matrix = new Matrix();
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        float scaleWidth = ((float) 1080) / width;
                        float scaleHeight = scaleWidth;

                        matrix.postScale(scaleWidth,scaleHeight);
                        newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);

                    }
                },
                0, // Image width
                0, // Image height
                ImageView.ScaleType.CENTER_CROP, // Image scale type
                Bitmap.Config.RGB_565, //Image decode configuration
                new Response.ErrorListener() { // Error listener
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something with error response
                        error.printStackTrace();
                        Log.d("Error","Error");
                        // Snackbar.make(mCLayout,"Error", Snackbar.LENGTH_LONG).show();
                    }
                }
        );
        imageRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        // Add ImageRequest to the RequestQueue
        requestQueue.add(imageRequest);




        imageView.setImageBitmap(newBitmap);
        name.setText(currentMeal.getName());


     /*   listItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("listItem Pressed", "ListItem Pressed ");

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());

                String mImageURLString = currentMeal.getSourceLink();
                Log.d("mImageURLString", "mImageURLString: " + mImageURLString);

                ImageRequest imageRequest = new ImageRequest(
                        mImageURLString, // Image URL
                        new Response.Listener<Bitmap>() { // Bitmap listener
                            @Override
                            public void onResponse(Bitmap response) {
                                // Do something with response
                                Log.d("listItem Pressed", "Response returned ");

                                bitmap = response;
                                Matrix matrix = new Matrix();
                                int width = bitmap.getWidth();
                                int height = bitmap.getHeight();
                                float scaleWidth = ((float) 1080) / width;
                                float scaleHeight = scaleWidth;

                                matrix.postScale(scaleWidth,scaleHeight);
                                newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);

                            }
                        },
                        0, // Image width
                        0, // Image height
                        ImageView.ScaleType.CENTER_CROP, // Image scale type
                        Bitmap.Config.RGB_565, //Image decode configuration
                        new Response.ErrorListener() { // Error listener
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Do something with error response
                                error.printStackTrace();
                                Log.d("Error","Error");
                                // Snackbar.make(mCLayout,"Error", Snackbar.LENGTH_LONG).show();
                            }
                        }
                );
                imageRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                // Add ImageRequest to the RequestQueue
                requestQueue.add(imageRequest);

            }
        });*/


        return listItem;


    }

}
