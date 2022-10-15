package com.example.memulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class studentform extends AppCompatActivity {

    private Spinner spinner;
    private static final String[] paths = {"Sub1", "Sub2", "sub3"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentform);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(studentform.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        Button register = (Button) findViewById(R.id.rbutton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(studentform.this, studenthome.class);
                // myIntent.putExtra("key", value);
                studentform.this.startActivity(myIntent);
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;

        }
    }


    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}