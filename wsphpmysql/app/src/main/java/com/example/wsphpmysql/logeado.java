package com.example.wsphpmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class logeado extends AppCompatActivity {

    //se ponen las variables del intent que estan resibiendose del sesionfragment

    public static final String nombre = "nombre";
    public static final String correo = "correo";

    TextView nombrel, emaill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeado);

        nombrel = findViewById(R.id.tvnombrel);
        emaill = findViewById(R.id.tvemaill);

        nombrel.setText(getIntent().getStringExtra("nombre"));
        emaill.setText(getIntent().getStringExtra("correo"));

    }
}