package com.example.wsphpmysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //se invoca el fragment

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frgmlogin, new sesionfragment()).commit(); // la variable frgmlogin se trae del id del layout del xml de la actividad a invocar
    }
}