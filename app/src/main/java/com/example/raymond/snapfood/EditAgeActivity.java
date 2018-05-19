package com.example.raymond.snapfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditAgeActivity extends AppCompatActivity {

    private EditText editName;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_snacks);

        editName = (EditText) findViewById(R.id.editname5);

        toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Age");
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

            String snacks = editName.getText().toString();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Type","age");
            intent.putExtra("Value", snacks);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
