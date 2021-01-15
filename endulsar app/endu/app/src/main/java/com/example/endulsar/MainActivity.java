package com.example.endulsar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText correo,clave;
    TextView registrarse;
    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        correo=findViewById(R.id.etcorreol);
        clave=findViewById(R.id.etcontraseñal);
        registrarse=findViewById(R.id.etregistrarl);
        iniciar=findViewById(R.id.btniniciar);

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!correo.getText().toString().isEmpty() && !clave.getText().toString().isEmpty()){


                    db.collection("usuarios")
                            .whereEqualTo("correo", correo.getText().toString())
                            .whereEqualTo("clave", clave.getText().toString())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (!task.getResult().isEmpty()) {
                                            Usuario usu =new Usuario();
                                            for (QueryDocumentSnapshot document : task.getResult()) {


                                                usu.setClave(document.getString("clave"));
                                                usu.setCorreo(document.getString("correo"));
                                                usu.setDireccion(document.getString("direccion"));
                                                usu.setNombre(document.getString("nombre"));
                                                usu.setNum_tel(document.getString("num_tel"));

                                            }
                                            Intent intencion = new Intent(getApplicationContext(),logueado.class);
                                            intencion.putExtra(logueado.correo,usu.getCorreo());
                                            intencion.putExtra(logueado.nombre,usu.getNombre());
                                            startActivity(intencion);
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(),"No existe el usuario",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });


                }



            }
        });
        
        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),registrou.class));
            }
        });
    }
}