package com.starpredicts.student.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.starpredicts.student.R;
import com.starpredicts.student.database.DatabaseHelper;

import java.util.ArrayList;

public class NewAddActivity extends AppCompatActivity {

    EditText editText;
    Button btAdd;
    ListView listView;

    DatabaseHelper databaseHelper;
    ArrayList readValues;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_add);

        editText=findViewById(R.id.et_text);
        btAdd = findViewById(R.id.bt_add_new);
        listView = findViewById(R.id.list_view);

        databaseHelper = new DatabaseHelper(NewAddActivity.this);
        loadValues();

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(!text.isEmpty()){
                    if(databaseHelper.addText(text)){
                        editText.setText("");
                        readValues.clear();
                        readValues = databaseHelper.getAllText();
                        loadValues();
                    }
                }
            }
        });

    }

    private void loadValues() {
        readValues = databaseHelper.getAllText();
        arrayAdapter = new ArrayAdapter(NewAddActivity.this,
                android.R.layout.simple_list_item_1,readValues);

        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
        listView.invalidateViews();
        listView.refreshDrawableState();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected_item= String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(getApplicationContext(),selected_item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
