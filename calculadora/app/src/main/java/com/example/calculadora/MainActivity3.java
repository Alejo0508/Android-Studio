package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    Button iniciar;   //nombramiento de las variables a usar en el ativity3.java
    TextView usuario, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        iniciar = findViewById(R.id.btiniciar); //instanciamiento de la variable btiniciar del xml a la variable iniciar del activity3.java
        usuario = findViewById(R.id.etusuario);
        pass = findViewById(R.id.etpass);


         iniciar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {  // llamar el metodo onclic del boton iniciar

                 Intent act3 = new Intent(getApplicationContext(),MainActivity.class);   // nombramiento de la variable act3 que es la que almacenara los datos a enviar

                 act3.putExtra("eusuario", usuario.getText().toString());   //se crea la variable eusuario que almacenara lo traido por usuario

                 // etusuario envia el dato a usuario
                 //  usuario le envia el dato a act3
                 // act3 se crea una variable eusuario que almacena el dato y lo envia en el boton enviar para instanciarla en el activity


                 act3.putExtra("epass",pass.getText().toString());  // el proceso es el mismo que el se usuario mencionado arriba

                 if (usuario.getText().toString().equals("sara") && pass.getText().toString().equals("12345"))  /*teniendo encuenta que el sistema lee los datos como un string solo se comparan las variables dentro de los ""*/
                 {
                     startActivity(act3); // es lo que le da inicio al boton de pasar a la otra actividad y manda los datos almacenados en act3
                 }
                 else
                 {
                     Toast.makeText(getApplicationContext(), "Usuario invalido",Toast.LENGTH_SHORT).show();  //muestra mensaje si no se ingresa el usuario valido
                 }

             }
         });



    }
}