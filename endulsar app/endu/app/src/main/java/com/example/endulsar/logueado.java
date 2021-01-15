package com.example.endulsar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;

public class logueado extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static final String correo="correo";
    public static final String nombre="nombre";

    ImageView torticas,minipersonal,cupcake2, minicake;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueado);
        torticas=findViewById(R.id.imgtoritcas);
        minipersonal=findViewById(R.id.imgminipersonal);
        minicake = findViewById(R.id.imgminicake);
        cupcake2=findViewById(R.id.imgcupcake);


        torticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),listarTortica.class));
            }
        });


        minipersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),listar_minipersonal.class));
            }
        });

        cupcake2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),listar_cupcakes.class));
            }
        });
        minicake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),listar_minicake.class));

            }
        });

    }
}