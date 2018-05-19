package com.example.raymond.snapfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

public class EditCurrentWeightActivity extends AppCompatActivity {

    private EditText editName;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_weight);

        editName = (EditText) findViewById(R.id.editname2);

        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Current Weight");
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

            String weight = editName.getText().toString();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Type","currentWeight");
            intent.putExtra("Value", weight);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
