package com.example.memulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton tsel = (ImageButton) findViewById(R.id.tbutton);
        ImageButton ssel = (ImageButton) findViewById(R.id.sbutton);

        tsel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, teacherlogin.class);
                // myIntent.putExtra("key", value);
                MainActivity.this.startActivity(myIntent);
            }
        });

        ssel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, studentlogin.class);
                // myIntent.putExtra("key", value);
                MainActivity.this.startActivity(myIntent);
            }
        });

    }
}