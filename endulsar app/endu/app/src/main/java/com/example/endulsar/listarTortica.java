package com.example.endulsar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class listarTortica extends AppCompatActivity {


    RecyclerView recyclerTortica;

    adapterTortica torticadpter;
    FirebaseFirestore torticafirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tortica);


        recyclerTortica = findViewById(R.id.rvtortica);

        recyclerTortica.setLayoutManager(new LinearLayoutManager(this));

        torticafirestore = FirebaseFirestore.getInstance();

        Query query = torticafirestore.collection("Torticas");

        FirestoreRecyclerOptions <Tortica> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder <Tortica> ()
                .setQuery(query,Tortica.class).build();

        torticadpter = new adapterTortica(firestoreRecyclerOptions);

        torticadpter.notifyDataSetChanged();

        recyclerTortica.setAdapter(torticadpter);

    }

    protected void onStart() {
        super.onStart();
        torticadpter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        torticadpter.stopListening();
    }
}