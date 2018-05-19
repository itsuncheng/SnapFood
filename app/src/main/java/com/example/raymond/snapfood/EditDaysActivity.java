package com.example.raymond.snapfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class EditDaysActivity extends AppCompatActivity {

    private EditText editName;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_days);

        editName = (EditText) findViewById(R.id.editname6);

        toolbar = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Days");
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
            intent.putExtra("Type","days");
            intent.putExtra("Value", snacks);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
