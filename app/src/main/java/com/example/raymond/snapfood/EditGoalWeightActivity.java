package com.example.raymond.snapfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;

public class EditGoalWeightActivity extends AppCompatActivity {

    private EditText editName;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goal);

        editName = (EditText) findViewById(R.id.editname3);

        toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Goal Weight");
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

            String goal = editName.getText().toString();

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Type","goalWeight");
            intent.putExtra("Value", goal);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
