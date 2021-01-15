package com.example.transaccionfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class transferir extends AppCompatActivity {

    public static String usuario = "usuario";
    public static String nombre = "nombre";

    TextView titularcuenta, hora, fecha,cuentaorigen, traercuenta, usuariocuenta, saldo1, cuentadestino2, traercuentadestino, saldodestino2, listaricono;
    EditText cuentadestino, valor;
    Button cerrarsesion, cancelar, transferir;

    Double valor1,valor2;
    String total, total2, iduser, iduserdestino;

    int verificadorsaldo;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferir);

        titularcuenta = findViewById(R.id.tvtitular);
        cuentadestino = findViewById(R.id.etcuentadestino);
        cuentaorigen = findViewById(R.id.tvnrocuenta);

        hora = findViewById(R.id.tvhora);
        fecha = findViewById(R.id.tvfecha);
        saldo1 = findViewById(R.id.tvsaldo);

        traercuenta = findViewById(R.id.tvtraercuenta);
        usuariocuenta = findViewById(R.id.tvusuariocuenta);

        valor = findViewById(R.id.etvalor);

        cerrarsesion = findViewById(R.id.btcerrar);
        cancelar = findViewById(R.id.btcancelar);
        transferir = findViewById(R.id.bttransferir);

        saldodestino2 = findViewById(R.id.tvsaldodestino2);
        traercuentadestino = findViewById(R.id.tvtraercuentadestino);
        cuentadestino2 = findViewById(R.id.tvcuentadestino2);

        listaricono = findViewById(R.id.tvlistaricono);


        usuariocuenta.setText(getIntent().getStringExtra("usuario"));
        titularcuenta.setText(getIntent().getStringExtra("nombre"));

        // hora del dispositivo ----------
        Date hor = new Date();
        int horas = hor.getHours();
        int minutos = hor.getMinutes();
        int segundos = hor.getSeconds();
        String tiempo = horas + ":" + minutos + ":" + segundos;

        hora.setText(tiempo);

        // fecha del dispositivo ------------------------
        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date fecha1 = new Date();

        String fecha2 = formatofecha.format(fecha1);

        fecha.setText(fecha2);


        listaricono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // metodolistar();

                startActivity(new Intent(getApplicationContext(),listar.class));
            }
        });


        transferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nurocuentaorigenm = cuentaorigen.getText().toString();
                String nurocuentadestinom = cuentadestino.getText().toString();
                String valorm = valor.getText().toString();
                String fecham = fecha.getText().toString();
                String horam = hora.getText().toString();


                if (!nurocuentadestinom.isEmpty() && !nurocuentaorigenm.isEmpty() && !valorm.isEmpty() && !fecham.isEmpty() && !horam.isEmpty())
                {

                    verificadorsaldo = (int) (Double.parseDouble(saldo1.getText().toString()) - 10000);

                    if (Double.parseDouble(valor.getText().toString())  > verificadorsaldo)
                    {
                        Toast.makeText(getApplicationContext(), "Saldo insuficiente", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {

                        metodotransferir(nurocuentadestinom, nurocuentaorigenm, valorm, horam, fecham);

                        if (!cuentaorigen.getText().toString().isEmpty())
                        {
                            valor1 = Double.parseDouble(saldo1.getText().toString()) - Double.parseDouble(valor.getText().toString());
                            total = String.format(valor1.toString());

                            metodoactualizarcuentaorigen();

                        }

                        if (!cuentadestino.getText().toString().isEmpty())
                        {
                            valor2 = Double.parseDouble(saldodestino2.getText().toString()) + Double.parseDouble(valor.getText().toString());
                            total2 = String.format(valor2.toString());

                            metodoactualizarcuentadestino();
                        }
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "debe ingresar todos los datos", Toast.LENGTH_SHORT).show();
                }

            }
        });


        traercuentadestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscarcuentadestino();

            }
        });

        traercuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                metodotraercuenta();

            }
        });

        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                metodocerrarsesion();

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cuentadestino.setText("");
                valor.setText("");
            }
        });
    }

    private void metodoactualizarcuentadestino() {

        Map<String, Object> uuser = new HashMap<>();

        uuser.put("usuario", cuentadestino2.getText().toString());
        uuser.put("nrocuenta", cuentadestino.getText().toString());
        uuser.put("fecha", fecha.getText().toString());
        uuser.put("saldo", total2.trim());

        db.collection("cuenta").document(iduserdestino)
                .set(uuser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d("cliente", "DocumentSnapshot successfully written!");
                        Toast.makeText(transferir.this,"Transaccion realziada correctmente...",Toast.LENGTH_SHORT).show();

                        valor.setText("");
                        cuentadestino.setText("");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });


    }

    private void metodoactualizarcuentaorigen() {

        Map<String, Object> uuser = new HashMap<>();

        uuser.put("usuario", usuariocuenta.getText().toString());
        uuser.put("nrocuenta", cuentaorigen.getText().toString());
        uuser.put("fecha", fecha.getText().toString());
        uuser.put("saldo", total.trim());

        db.collection("cuenta").document(iduser)
                .set(uuser)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d("cliente", "DocumentSnapshot successfully written!");
                        Toast.makeText(transferir.this,"Transaccion realziada correctmente...",Toast.LENGTH_SHORT).show();

                        valor.setText("");
                        cuentadestino.setText("");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });

    }

    private void metodotransferir(String nurocuentadestinom, String nurocuentaorigenm, String valorm, String horam, String fecham) {


        Map<String, Object> muser = new HashMap<>();
        muser.put("nrocuentaorigen", cuentaorigen.getText().toString());
        muser.put("nrocuentadestino", cuentadestino.getText().toString());
        muser.put("hora", hora.getText().toString());
        muser.put("fecha", fecha.getText().toString());
        muser.put("valor", valor.getText().toString());

        // para guardar un nueco campo en la base de datos

        db.collection("transaccion")
                .add(muser)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        Toast.makeText(getApplicationContext(),"Usuario agregado correctamente",Toast.LENGTH_LONG).show();

                        cuentadestino.setText("");
                        valor.setText("");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),"Error en la conexion a la base de datos",Toast.LENGTH_LONG).show();

                    }
                });


    }

    private void buscarcuentadestino() {

        db.collection("cuenta")

                .whereEqualTo("nrocuenta", cuentadestino.getText().toString())

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {  /// si ejecuto bien la tarea hara el siguiente if

                            if (!task.getResult().isEmpty()) {   /// si no esta vacio el resultado e sporque lo encontro

                                for (QueryDocumentSnapshot document2 : task.getResult()){

                                    Toast.makeText(getApplicationContext(),"La cuenta si existe",Toast.LENGTH_SHORT).show();

                                    iduserdestino = document2.getId();
                                    cuentadestino2.setText(document2.getString("usuario"));
                                    saldodestino2.setText(document2.getString("saldo"));

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

    private void metodotraercuenta() {

        db.collection("cuenta")

        .whereEqualTo("usuario", usuariocuenta.getText().toString())

        .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {  /// si ejecuto bien la tarea hara el siguiente if

                            if (!task.getResult().isEmpty()) {   /// si no esta vacio el resultado e sporque lo encontro

                                for (QueryDocumentSnapshot document1 : task.getResult()){

                                   iduser = document1.getId();
                                   cuentaorigen.setText(document1.getString("nrocuenta"));
                                   saldo1.setText(document1.getString("saldo"));

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

    private void metodocerrarsesion() {

        startActivity(new Intent(getApplicationContext(), MainActivity.class));

        titularcuenta.setText("");
        cuentadestino.setText("");
        hora.setText("");
        fecha.setText("");
        saldo1.setText("");
        usuariocuenta.setText("");
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inftransac = getMenuInflater();
        inftransac.inflate(R.menu.menu_transaccion, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.menulistar:
                metodolistar();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void metodolistar() {

        startActivity(new Intent(getApplicationContext(),listar.class));
    }*/

}
