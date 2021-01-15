package com.example.transaccionfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class listar extends AppCompatActivity {

    RecyclerView recyclertransaccion;

    adaptertransaccion madapter;
    FirebaseFirestore mfirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        recyclertransaccion = findViewById(R.id.rvlistatransaccion);

        recyclertransaccion.setLayoutManager(new LinearLayoutManager(this));

        mfirestore = FirebaseFirestore.getInstance();

        Query query = mfirestore.collection("transaccion");

        FirestoreRecyclerOptions <transaccion> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder <transaccion> ()

                .setQuery(query,transaccion.class).build();


        madapter = new adaptertransaccion(firestoreRecyclerOptions);

        madapter.notifyDataSetChanged();

        recyclertransaccion.setAdapter(madapter);

    }

    protected void onStart() {  // esto es para que cuando ejecute la app se este escuchando la base de datos en espera de cambios
        super.onStart();
        madapter.startListening();
    }

    @Override
    protected void onStop() {  // cuanso la app se cierre, esto es apra que deje de escuchar la base de datos
        super.onStop();
        madapter.stopListening();
    }

}