package com.example.raymond.snapfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PredictionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView imageView;

    private static final Logger LOGGER = new Logger();

    //Inception V3
    private static final int INPUT_SIZE = 299;
    private static final int IMAGE_MEAN = 128;
    private static final float IMAGE_STD = 128.0f;
    private static final String INPUT_NAME = "Mul:0";
    private static final String OUTPUT_NAME = "final_result";

    private static final String MODEL_FILE = "file:///android_asset/graph_new.pb";
    private static final String LABEL_FILE = "file:///android_asset/labels_new.txt";

    private Classifier classifier;

    private Bitmap newbitmap;
    private TextView textView;
    private EditText editText;
    private Spinner spinner;

    private Button button;

    private String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        imageView = findViewById(R.id.imageView2);

       // if(getIntent().hasExtra("byteArray")) {
        if(CameraActivity.getBitmapList() != null) {

            Bitmap bitmap1 = CameraActivity.getBitmapList().get(CameraActivity.getBitmapList().size()-1);
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap1, 299, 299, false);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.JPEG, 100, bs);
            byte[] BYTE = bs.toByteArray();

            Bitmap bitmap3 = BitmapFactory.decodeByteArray(
                    BYTE, 0, BYTE.length);

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            newbitmap = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1.getHeight(), matrix, true);
            imageView.setImageBitmap(newbitmap);

            classifier =
                    TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_FILE,
                            LABEL_FILE,
                            INPUT_SIZE,
                            IMAGE_MEAN,
                            IMAGE_STD,
                            INPUT_NAME,
                            OUTPUT_NAME);

            textView = findViewById(R.id.result2);

            final List<Classifier.Recognition> results = classifier.recognizeImage(bitmap3);

            spinner = (Spinner) findViewById(R.id.spinner);
            spinner.setOnItemSelectedListener(this);

            List<String> categories = new ArrayList<String>();

            for(Classifier.Recognition result:results){
                categories.add(result.getTitle());
            }

            if(categories.size() ==0){
                Toast.makeText(getApplicationContext(), "Cannot recognize food. Please go back to recognize again", Toast.LENGTH_LONG).show();
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(dataAdapter);

            textView = findViewById(R.id.result2);


            editText = findViewById(R.id.editText);

            button = findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    double calories = numCalories * Double.parseDouble(editText.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("name","" + item);
                    intent.putExtra("calories",Double.toString(calories));
                    startActivity(intent);

                }
            });
        }
    }
    private Double numCalories;
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();

        for(int i=0; i<MainActivity.mealTable.size();i++){
            if(MainActivity.mealTable.get(i).equals(item)){
                numCalories = MainActivity.mealCalories.get(i);
                textView.setText("Calories/serving: " + Double.toString(numCalories) + " x ");
                break;
            }
        }

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
