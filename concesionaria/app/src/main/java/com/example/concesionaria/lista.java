package com.example.concesionaria;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class lista extends AppCompatActivity {

    ListView lista;
    ArrayList <String> arraycarro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        lista = findViewById(R.id.lvlista);

        cargarcarro();

    }

    private void cargarcarro() {

        arraycarro = listadocarros();

        ArrayAdapter <String> adapcarros = new ArrayAdapter <String> (this, android.R.layout.simple_expandable_list_item_1, arraycarro);


        lista.setAdapter(adapcarros);
    }

    private ArrayList <String> listadocarros() {

        ArrayList <String> datos = new ArrayList <String>();

        sqlconsecionario osql = new sqlconsecionario(this,"bdconsecionario", null,1);

        SQLiteDatabase bd = osql.getReadableDatabase();

        String sql = "select marca, modelo, placa, valor from carro";

        Cursor curlistacarro = bd.rawQuery(sql, null);

        if (curlistacarro.moveToFirst())
        {
            do
            {

                String registrocarro = curlistacarro.getString(0) + "              " + curlistacarro.getString(1) + "            " + curlistacarro.getString(2) + "               " + curlistacarro.getFloat(3);

                datos.add(registrocarro);

            }
            while (curlistacarro.moveToNext());
        }
        bd.close();
        return datos;
    }


}