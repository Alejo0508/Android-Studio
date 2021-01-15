package com.example.wsphpmysql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class listadousuarios extends AppCompatActivity implements Response.Listener<JSONObject> ,Response.ErrorListener {

    RecyclerView recyclerusuario; // se crea variable para el recycler
    ArrayList <usuario> listaUsuarios;      // se crea el arreglo de nuevo que esta en usuarioadapter.java

    RequestQueue rq;  // hace la peticion de la informacion
    JsonRequest jrq;  //el archivo php devuelve todos os registros en formato json


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadousuarios);

        recyclerusuario = findViewById(R.id.rvlistado); // re serefencia la variable recycler con el id del xml donde este el recycler view
        listaUsuarios = new ArrayList<>();


        recyclerusuario.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));  // decimos que a la variable recyclerusuario se muestre en forma de LinearLayoutManager

        recyclerusuario.setHasFixedSize(true); // esto es apra que cuando el tamaño del arreglo cambie el se modifique

        rq = Volley.newRequestQueue(getApplicationContext());// se llama el volley para que se pueda conectar a la base de datos

        cargarWebService();  // se crea metodo para poder invocar el servicio web

    }

    private void cargarWebService() {

        String url = "http://192.168.1.2//tallerBanco/listartransaccion.php";
        jrq = new JsonObjectRequest(Request.Method.POST,url,null,this,this); // esto busca la base de datos
        rq.add(jrq); // esto añade la informacion a la BD

    }


    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getApplicationContext(),"Error en la conexion con el servidor",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {

        usuario usuario=null;
        //Definición de array de JSON para recibir los datos del archivo php
        JSONArray json=response.optJSONArray("datos");
        //Recorrido del arreglo json
        try {

            for (int i=0;i<json.length();i++){  // crea un objeto usuario por cada registro
                usuario = new usuario();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                usuario.setUsr(jsonObject.optString("usr"));  // se le dice que en ese objeto usuario en metodo setusr y lo asigne al campo usr
                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setCorreo(jsonObject.optString("correo"));
                listaUsuarios.add(usuario);
            }
            //progress.hide();
            usuarioadapter adapter = new usuarioadapter (listaUsuarios); // se crea un adaptador de listausuarios que esta llenod e los datos que ya lleno

            recyclerusuario.setAdapter(adapter); // se le adapta la informacion del adaptador al recyclerusuario

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
        }

    }
}