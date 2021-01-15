package com.example.appsisacademiafb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class listadousuarios extends AppCompatActivity {

    // se instancia el recyclerview
    RecyclerView recyclerUsuarios;

    //instanciar adaptador de recyclerview
    adapterusers madapter;
    FirebaseFirestore mfirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadousuarios);

        recyclerUsuarios = findViewById(R.id.rvlistausuarios); // buscamos el recyclerviewpara instanciarla

        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this));

        mfirestore = FirebaseFirestore.getInstance(); // trae la instancia del firestore

        Query query = mfirestore.collection("usuario").orderBy("name"); // selecciona la tabla usuario de la bd y las ordena por el nombre

        FirestoreRecyclerOptions <user> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<user>()  //se le dice que con el option que trae la informacion se instancia de la clase user y se guarde

                .setQuery(query,user.class).build(); // el setquery es para que el compile la clase y los pase al firestoreRecyclerOptions

        //pasar datos al adaptador
        madapter = new adapterusers(firestoreRecyclerOptions);

        //cambios en el adapter
        madapter.notifyDataSetChanged(); // esto es para que notifique cuando se hagan cambios

        //Asignar el adaptador al recyclerview
        recyclerUsuarios.setAdapter(madapter);

    }

    @Override
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