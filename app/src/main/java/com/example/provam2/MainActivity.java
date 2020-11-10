package com.example.provam2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goto_registry(View view) {

        Intent registry = new Intent( this , register_class.class );                         /// a new intent is created to pass the focus foward
        startActivity( registry );

    }

}