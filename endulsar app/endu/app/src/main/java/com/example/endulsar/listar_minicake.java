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
public class listar_minicake extends AppCompatActivity {

    RecyclerView recyclerMinicake;

    adapterMinicake minicakeadpter;
    FirebaseFirestore minicakefirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_minicake);

        recyclerMinicake = findViewById(R.id.rvminicake);

        recyclerMinicake.setLayoutManager(new LinearLayoutManager(this));

        minicakefirestore = FirebaseFirestore.getInstance();

        Query query = minicakefirestore.collection("Minicakes");

        FirestoreRecyclerOptions <Minicake> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder <Minicake> ()
                .setQuery(query,Minicake.class).build();

        minicakeadpter = new adapterMinicake(firestoreRecyclerOptions);

        minicakeadpter.notifyDataSetChanged();

        recyclerMinicake.setAdapter(minicakeadpter);




    }

    protected void onStart() {
        super.onStart();
        minicakeadpter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        minicakeadpter.stopListening();
    }
}

