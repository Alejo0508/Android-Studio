package com.example.endulsar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registrou extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText nombre,contra,email,direccion,celular;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrou);

        nombre=findViewById(R.id.etNombre);
        contra=findViewById(R.id.etClave);
        email=findViewById(R.id.etEmail);
        direccion=findViewById(R.id.etDireccion);
        celular=findViewById(R.id.etCelular);
        registrar=findViewById(R.id.btnregistrar);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //----
                Map<String, Object> muser = new HashMap<>();
                muser.put("clave", contra.getText().toString());
                muser.put("correo", email.getText().toString());
                muser.put("direccion",direccion.getText().toString());
                muser.put("nombre", nombre.getText().toString());
                muser.put("num_tel",celular.getText().toString());

                db.collection("usuarios")
                        .add(muser)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"Usuario agregado correctamente!!...",Toast.LENGTH_LONG).show();
                                //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Error al agregar usuario!!...",Toast.LENGTH_LONG).show();
                                //Log.w(TAG, "Error adding document", e);
                            }
                        });

                //----
            }
        });
    }
}