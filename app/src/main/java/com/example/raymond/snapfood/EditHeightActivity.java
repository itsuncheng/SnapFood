package com.example.raymond.snapfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditHeightActivity extends AppCompatActivity {

    private EditText editName;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_weeks);

        editName = (EditText) findViewById(R.id.editname4);

        toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Height");
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

            String weeks = editName.getText().toString();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Type","height");
            intent.putExtra("Value", weeks);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
