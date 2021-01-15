package com.example.endulsar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
public class listar_cupcakes extends AppCompatActivity {

    RecyclerView recyclerCupcakes;

    adapterCupcakes cupcakesadpter;
    FirebaseFirestore cupcakesfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cupcakes);

        recyclerCupcakes = findViewById(R.id.rvcupcakes);

        recyclerCupcakes.setLayoutManager(new LinearLayoutManager(this));

        cupcakesfirestore = FirebaseFirestore.getInstance();

        Query query = cupcakesfirestore.collection("Cupcake");

        FirestoreRecyclerOptions <Cupcakes> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder <Cupcakes> ()
                .setQuery(query,Cupcakes.class).build();

        cupcakesadpter = new adapterCupcakes(firestoreRecyclerOptions);

        cupcakesadpter.notifyDataSetChanged();

        recyclerCupcakes.setAdapter(cupcakesadpter);




    }

    protected void onStart() {
        super.onStart();
        cupcakesadpter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cupcakesadpter.stopListening();
    }
}

