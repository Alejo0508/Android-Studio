package com.example.appsisacademiafb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText  name, user, phone, password;

    Button guardar, eliminar, buscar, editar, listar;

    String iduser;


    // instancias el firebase en el proyecto, siempre debe hacerse en el main
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.etnamer);
        user = findViewById(R.id.etuserr);
        phone = findViewById(R.id.etphoner);
        password = findViewById(R.id.etpasswordr);

        guardar = findViewById(R.id.btguardarr);
        eliminar = findViewById(R.id.bteliminarr);
        buscar = findViewById(R.id.btbuscarr);
        editar = findViewById(R.id.bteditarr);
        listar = findViewById(R.id.btlistarr);



        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                startActivity(new Intent(getApplicationContext(),listadousuarios.class));
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // se crea nuevo usuario en la base de datos usuario

                Map<String, Object> muser = new HashMap<>();
                muser.put("usr", user.getText().toString());
                muser.put("name", name.getText().toString());
                muser.put("phone", phone.getText().toString());
                muser.put("password", password.getText().toString());

                // para guardar un nueco campo en la base de datos

                db.collection("usuario")
                        .add(muser)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                // si se realizo bien hara

                                Toast.makeText(getApplicationContext(),"Usuario agregado correctamente",Toast.LENGTH_LONG).show();

                                user.setText("");
                                name.setText("");
                                phone.setText("");
                                password.setText("");

                              //  Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {


                                // si la conexion a la base de datos tuvo problemas
                                Toast.makeText(getApplicationContext(),"Error en la conexion a la base de datos",Toast.LENGTH_LONG).show();


                              //  Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        });

        //boton buscar

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("usuario") // busca en la base de datos usuario


                        .whereEqualTo("usr", user.getText().toString())  ///realizamos la busqueda por el usuario

                        .get()  // se pone este metodo porque es la que recupera la informacion

                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {  /// si ejecuto bien la tarea hara el siguiente if

                                    if (!task.getResult().isEmpty()) {   /// si no esta vacio el resultado e sporque lo encontro

                                        for (QueryDocumentSnapshot document : task.getResult()){

                                            name.setText(document.getString("name"));
                                            phone.setText(document.getString("phone"));
                                            password.setText(document.getString("password"));

                                        }

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


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> uuser = new HashMap<>();
                uuser.put("name", name.getText().toString());
                uuser.put("phone", phone.getText().toString());
                uuser.put("password", password.getText().toString());

                db.collection("usuario").document(iduser)
                        .set(uuser)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Log.d("cliente", "DocumentSnapshot successfully written!");
                                Toast.makeText(MainActivity.this,"Usuario actualizado correctmente...",Toast.LENGTH_SHORT).show();

                                user.setText("");
                                name.setText("");
                                phone.setText("");
                                password.setText("");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Log.w("cliente", "Error writing document", e);
                            }
                        });
            }
        });


        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.collection("usuario").document(iduser)
                        .delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //Log.d("cliente", "DocumentSnapshot successfully deleted!");
                                Toast.makeText(MainActivity.this,"Cliente borrado correctamente...",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("cliente", "Error deleting document", e);
                            }
                        });

            }
        });


    }
}