package com.example.concesionaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {


    EditText marca, modelo, placa, valor;
    Button agregar, actualizar, borrar, listar, buscar, limpiar;

    String placanueva, placavieja;


    sqlconsecionario osql =new sqlconsecionario(this,"bdconsecionario", null,1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        marca = findViewById(R.id.etmarca);
        modelo = findViewById(R.id.etmodelo);
        placa = findViewById(R.id.etplaca);
        valor = findViewById(R.id.etvalor);

        agregar = findViewById(R.id.btagregar);
        borrar = findViewById(R.id.bteliminar);
        listar = findViewById(R.id.btlistar);
        buscar = findViewById(R.id.btbuscar);
        actualizar = findViewById(R.id.btactualizar);
        limpiar = findViewById(R.id.btlimpiar);


        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                metodoagregar();
          }
        });

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                buscaplaca (placa.getText().toString().trim());

            }
        });


        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), lista.class));

            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                modelo.setText(" ");
                marca.setText(" ");
                valor.setText(" ");
                placa.setText(" ");

            }
        });

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                metodoborrar();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                actualizarvehiculo (marca.getText().toString(), modelo.getText().toString(), placa.getText().toString(), Double.parseDouble(valor.getText().toString()));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menucon =getMenuInflater();
        menucon.inflate(R.menu.menu_consecionario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.menuagregar:
                metodoagregar();
                return true;

            case R.id.menuactualizar:
                actualizarvehiculo (marca.getText().toString(), modelo.getText().toString(), placa.getText().toString(), Double.parseDouble(valor.getText().toString()));
                return true;

            case R.id.menubuscar:
                buscaplaca (placa.getText().toString().trim());
                return true;

            case R.id.menulistar:
                startActivity(new Intent(getApplicationContext(), lista.class));
                return true;

            case R.id.menueliminar:
                metodoborrar();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void buscaplaca(String placabuscar) {

        if (!placa.getText().toString().isEmpty())
        {
            SQLiteDatabase bd = osql.getReadableDatabase();

            String sql = "select marca, modelo, valor, placa from carro where placa = '"+ placabuscar +"'";

            Cursor cucarro = bd.rawQuery(sql, null);

            DecimalFormat nro = new DecimalFormat("###,###");

            if (cucarro.moveToFirst())
            {
                marca.setText(cucarro.getString(0));
                modelo.setText(cucarro.getString(1));
                valor.setText(nro.format(cucarro.getString(2)));
                placavieja = cucarro.getString(3);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"La placa no esta registrada",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Ingrese la placa a buscar", Toast.LENGTH_LONG).show();
        }

    }

    private void actualizarvehiculo(String marcaact, String modeloact, String placaact, double valoract) {

        placanueva = placaact.trim();

        SQLiteDatabase bd1 = osql.getWritableDatabase();
        SQLiteDatabase bd = osql.getReadableDatabase();


        if (placanueva.equals(placavieja.trim()))
        {

            bd1.execSQL("UPDATE carro SET  marca = '"+marcaact+"', modelo = '"+modeloact+"', valor = '"+valoract+"' where placa = '"+ placanueva+"' ");

            modelo.setText(" ");
            marca.setText(" ");
            valor.setText(" ");
            placa.setText(" ");

            Toast.makeText(getApplicationContext(),"Datos actualizados", Toast.LENGTH_SHORT).show();

        }
        else
        {
            String sql = "select marca, modelo, placa, valor from carro where placa = '"+ placanueva+"'";
            Cursor cucarro = bd.rawQuery(sql, null);

            if (cucarro.moveToFirst())
            {
                Toast.makeText(getApplicationContext(),"La placa esta asignada a otro vehiculo",Toast.LENGTH_SHORT).show();
            }
            else
            {

                bd1.execSQL("UPDATE carro SET placa = '"+placanueva+"', modelo = '"+modeloact+"', marca = '"+marcaact+"', valor = '"+valoract+"' where placa = '"+placavieja+"' ");

                modelo.setText(" ");
                marca.setText(" ");
                valor.setText(" ");
                placa.setText(" ");

                Toast.makeText(getApplicationContext(),"Datos actualizados", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void metodoborrar() {

        AlertDialog.Builder alertacuadro = new AlertDialog.Builder(MainActivity.this);
        alertacuadro.setMessage("Eliminara el vehiculo");
        alertacuadro.setPositiveButton("Sí",new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {

                SQLiteDatabase bd1 = osql.getWritableDatabase();

                bd1.execSQL("DELETE FROM carro WHERE placa = '"+placa.getText().toString()+"'");

                modelo.setText(" ");
                marca.setText(" ");
                valor.setText(" ");
                placa.setText(" ");

                Toast.makeText(getApplicationContext(),"Vehiculo Eliminado Correctamente",Toast.LENGTH_SHORT).show();
            }
        });

        alertacuadro.setNegativeButton("No",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertacuadro.create();
        alertDialog.show();

    }

    private void metodoagregar() {

        if (!marca.getText().toString().isEmpty() && !modelo.getText().toString().isEmpty() && !placa.getText().toString().isEmpty() && !valor.getText().toString().isEmpty())
        {

            SQLiteDatabase bd = osql.getReadableDatabase();

            String sql = " select placa from carro where placa = ' "+ placa.getText().toString()+" ' ";  // es la que esta haciendo la comparacion


            Cursor cucarro = bd.rawQuery(sql, null);

            if (cucarro.moveToFirst())
            {
                Toast.makeText(getApplicationContext(), "La placa ya esta Registrada", Toast.LENGTH_SHORT).show();
            }
            else
            {

                SQLiteDatabase bd1 = osql.getWritableDatabase();

                try {

                    ContentValues contcarro = new ContentValues();

                    contcarro.put("marca", marca.getText().toString().trim());
                    contcarro.put("modelo", modelo.getText().toString().trim());
                    contcarro.put("placa", placa.getText().toString().trim());
                    contcarro.put("valor", valor.getText().toString().trim());

                    bd1.insert("carro", null, contcarro);
                    bd1.close();

                    modelo.setText(" ");
                    marca.setText(" ");
                    valor.setText(" ");
                    placa.setText(" ");

                    Toast.makeText(getApplicationContext(), "El vehiculo se registro con exito",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "¡Error!"+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Ingrese todos los datos", Toast.LENGTH_SHORT).show();
        }

    }

}