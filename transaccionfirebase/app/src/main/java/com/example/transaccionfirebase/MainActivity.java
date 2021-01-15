package com.example.transaccionfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText usuario, clave;

    Button iniciar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.etusuario);
        clave = findViewById(R.id.etclave);

        iniciar = findViewById(R.id.btiniciar);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("cliente") // busca en la base de datos usuario


                    .whereEqualTo("usuario", usuario.getText().toString())///realizamos la busqueda por el usuario
                    .whereEqualTo("clave", clave.getText().toString())


                        .get()  // se pone este metodo porque es la que recupera la informacion

                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {  /// si ejecuto bien la tarea hara el siguiente if

                                    if (!task.getResult().isEmpty()) {   /// si no esta vacio el resultado e sporque lo encontro

                                        cliente client = new cliente();

                                        for (QueryDocumentSnapshot document : task.getResult()){

                                            client.setUsuario(document.getString("usuario"));
                                            client.setNombre(document.getString("nombre"));

                                        }

                                        Intent intentcliente = new Intent(getApplicationContext(), transferir.class);

                                        intentcliente.putExtra(transferir.usuario,client.getUsuario());
                                        intentcliente.putExtra(transferir.nombre,client.getNombre());

                                        startActivity(intentcliente);

                                        usuario.setText("");
                                        clave.setText("");

                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(),"No existe el usuario",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

            }
        });



    }



}