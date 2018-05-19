package com.example.raymond.snapfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.RadioButton;

public class EditGenderActivity extends AppCompatActivity {

    private EditText editName;
    private Toolbar toolbar;
    private static String gender="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

       // editName = (EditText) findViewById(R.id.editname);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gender");

    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton:
                if (checked)
                    gender="Male";
                // Pirates are the best
                break;
            case R.id.radioButton2:
                if (checked)
                    gender="Female";
                // Ninjas rule
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_button_save, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.button_save) {



            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Type","gender");
            intent.putExtra("Value", gender);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
