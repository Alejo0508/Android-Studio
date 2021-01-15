package com.example.concesionaria;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class sqlconsecionario extends SQLiteOpenHelper {


    String tblvehiculo = "Create Table carro (marca text, placa text primary key, modelo text, valor float)";


    public sqlconsecionario( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(tblvehiculo);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE  carro ");
        db.execSQL(tblvehiculo);

    }
}
