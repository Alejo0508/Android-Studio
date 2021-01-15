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
public class listar_minipersonal extends AppCompatActivity {

    RecyclerView recyclerMinipersonal;

    adapterMinipersonal minipersonaladpter;
    FirebaseFirestore minipersonalfirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_minipersonal);

        recyclerMinipersonal = findViewById(R.id.rvminipersonal);

        recyclerMinipersonal.setLayoutManager(new LinearLayoutManager(this));

        minipersonalfirestore = FirebaseFirestore.getInstance();

        Query query = minipersonalfirestore.collection("Minipersonal");

        FirestoreRecyclerOptions <Minipersonal> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder <Minipersonal> ()
                .setQuery(query,Minipersonal.class).build();

        minipersonaladpter = new adapterMinipersonal(firestoreRecyclerOptions);

        minipersonaladpter.notifyDataSetChanged();

        recyclerMinipersonal.setAdapter(minipersonaladpter);




    }

    protected void onStart() {
        super.onStart();
        minipersonaladpter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        minipersonaladpter.stopListening();
    }
}

