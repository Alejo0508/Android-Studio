package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView rvalor1, rresultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvalor1 = findViewById(R.id.tvvalor1r);
        rresultado = findViewById(R.id.tvresultador);


        //como traer el dato en variables individuales

        String mivalor1 = getIntent().getStringExtra("evalor1"); // la variable que trase el resultado es la que se almacena en el oact2.putExtra

        String miresultado = getIntent().getStringExtra("eresultado"); // el getIntent es lo que trae el resultado y el getStringExtra es lo que toma el resultado.

        rvalor1.setText(mivalor1); //a la variable rvalor1 de esta actividad ponerle lo que se almaceno en mivalor1
        rresultado.setText(miresultado); // la variable miresultado no se pone en comillas porque es un String

    }
}