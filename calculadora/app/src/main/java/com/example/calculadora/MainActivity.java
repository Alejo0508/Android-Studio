package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /*instanciar objetos con los ids del archivo xml*/

    EditText valor1,valor2;
    TextView resultado, rusuario;
    Button sumar, restar, multiplicar, dividir, limpiar, enviar;
    RadioButton fsi, fno;
    CheckBox iva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*referenciar los objetos instanciados a los ids del archivo xml*/

        valor1 = findViewById(R.id.etvalor1);
        valor2 = findViewById(R.id.etvalor2);
        resultado = findViewById(R.id.tvresultado);
        sumar = findViewById(R.id.btsumar);
        restar = findViewById(R.id.btrestar);
        multiplicar = findViewById(R.id.btmultiplicar);
        dividir = findViewById(R.id.btdividir);
        limpiar = findViewById(R.id.btlimpiar);
        fsi = findViewById(R.id.rbsi);
        fno = findViewById(R.id.rbno);
        iva = findViewById(R.id.cbiva);
        enviar = findViewById(R.id.btenviar);
        rusuario = findViewById(R.id.tvusuarior);  // lo que este en la variable tvusuario se vera en rusuario

        String miusuario = getIntent().getStringExtra("eusuario");  // se crea variable miusuario que almacena los datos de la variable eusuario de la activity3
        rusuario.setText(miusuario); // se le dice que en la variable rusuario almece lo que ya esta en miusuario

        /*Evento click de cada boton*/

        //sumar.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View v) {
          //     double mresultado = 0;

             //   mresultado = Double.parseDouble(valor1.getText().toString()) + Double.parseDouble(valor2.getText().toString()); /*double.parseDouble es para convertirlo de texto a numero.
          //      getText().toString() es el metodo que trae lo que se digite en las cajas de texto (valor1) */


                /*asignar el contenido de la variable mresultado al textveiw resutlado*/
           /*     resultado.setText(""+mresultado);
            }
        });

        restar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double mresultado = 0;

                mresultado = Double.parseDouble(valor1.getText().toString()) - Double.parseDouble(valor2.getText().toString());

                resultado.setText(""+mresultado);
            }
        });

        multiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double mresultado = 0;

                mresultado = Double.parseDouble(valor1.getText().toString()) * Double.parseDouble(valor2.getText().toString());

                resultado.setText(""+mresultado);
            }
        });

        dividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double mresultado = 0;

                mresultado = Double.parseDouble(valor1.getText().toString()) / Double.parseDouble(valor2.getText().toString());

                resultado.setText(""+mresultado);
            }
        });  */



        // ESTE CODIGO SIGUIENTE HARA LO MISMO QUE EL DE ARRIBA PERO EN MENOS LINEAS

        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        multiplicar.setOnClickListener(this);
        dividir.setOnClickListener(this);


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //llamar o invocar la actividad .calss

               // startActivity(new Intent(getApplicationContext(),MainActivity2.class)); //esto trae la actividad2 y siempre debe ser el .class, siempre y cuando no se pasen parametros

                Intent oact2 = new Intent(getApplicationContext(),MainActivity2.class); // esto es invocrlo cuando se pasen aprametros

                // pasar datos a la actividad 2

                oact2.putExtra("evalor1", valor1.getText().toString());  //el putExtra es la que envia a la MainActivity2 el dato y se le pone cualquier variable
                oact2.putExtra("eresultado",resultado.getText().toString());

                startActivity(oact2); // es la actividad que le envia los datos al MainActivity2 contenidos en la variable oact2

            }
        });

    }
    @Override
    public void onClick(View v) {

        if (!valor1.getText().toString().isEmpty() && !valor2.getText().toString().isEmpty()){

            double mvalor1 = Double.parseDouble(valor1.getText().toString()); /*convierte en numeros lo que se escribio en la casilla valor1*/
            double mvalor2 = Double.parseDouble(valor2.getText().toString());
            double mresultado = 0;

            //validar en cual control (boton) se hizo clic a traves de un switch

            switch (v.getId()) /*analiza el codigo y escucha a donde se dara clic*/
            {
                case R.id.btsumar: /*si da clic en suma hara la siguiente operacion*/
                    mresultado = mvalor1 + mvalor2;
                    break; /*rompe el case en caso de que esta sea la que se cumple*/

                case R.id.btrestar:
                    mresultado = mvalor1 - mvalor2;
                    break;

                case R.id.btmultiplicar:
                    mresultado = mvalor1 * mvalor2;
                    break;

                case R.id.btdividir:
                    mresultado = mvalor1 / mvalor2;
                    break;

            }

            if (iva.isChecked()) //checkbox que verifica si tiene iva o no, se pone antes de radio buttom por si usuario mueve el radio entre y no y poder modificar a tiempo el valor
            {
                mresultado = mresultado * 1.19;
            }

            if (fsi.isChecked()) //esto es para activar los radiobuttom y verificarlo
            {
                //formato para los numeros

                DecimalFormat nro = new DecimalFormat("###,###,###.##"); // DecimalFormat importa la libreria y lo que esta en ### es los decimales que va a tener para mostrar.
                resultado.setText(nro.format(mresultado));
            }
            else
            {
                resultado.setText(""+mresultado);
            }

        }

        else{
            Toast.makeText(getApplicationContext(), "Debe ingresar los dos valores", Toast.LENGTH_SHORT).show();
            /*esto muestra un mensaje en la app
             -Toast.makeText(getApplicationContext() llama la libreria que mostrara el mensaje
             -lo que esta en el "" es el texto que va a mostrar.
             -lo que esta en Toast.LENGTH_SHORT).show() es el tiempo que va a mostrar el mensaje en pantalla*/
        }

        limpiar.setOnClickListener(new View.OnClickListener() { //escucha el boton limpiar
            @Override
            public void onClick(View v) {

                valor1.setText(""); /*limpia lo que esta en valor 1*/
                valor2.setText(""); /*limpia lo que esta en valor 2*/
                resultado.setText(""); /*limpia lo que esta en resultado*/

                valor1.requestFocus(); /*posiciona el cursor en valor 1*/
            }


        });
    }
}