package com.example.raymond.snapfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setImageBitmap(MyCustomListAdapter.newBitmap);
    }
}
