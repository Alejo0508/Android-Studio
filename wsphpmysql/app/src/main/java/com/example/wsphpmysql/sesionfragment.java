package com.example.wsphpmysql;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// se le implementa leer el objeto de json porque el archivo de php devuelve la info en jason entonces debemos resibirlo asi
public class sesionfragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    //se instancian objetos para que json pueda hacer la fila de mysql para que use la info

    RequestQueue rq;  // permite crear un objeto para realziar peticion o hacer la fila para solicitar info
    JsonObjectRequest jrq;   // permite resibir los datos en formato json

    EditText correo, clave;
    Button iniciar;
    TextView registrarseaqui;




    // se crea el fragment para uno poderlo usar en otras activity que uno necesite

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       // return inflater.inflate(R.layout.fragment_sesionfragment, container, false);

        /*en el archivo build.gradle (module: app) se pone esto implementation 'com.android.volley:volley:1.1.1' para poder traer la libreria apra conectarse a internet
        *
        * una vez hecho eso le damos la instruccion en el manifest para que la app le permita salir a internet
        *
        * <uses-permission android:name="android.permission.INTERNET"/>
        *  */

        View vista = inflater.inflate(R.layout.fragment_sesionfragment, container, false); //se crea la variable vista que esta apuntando al fragment_sesionfragment.xml para traser los id de esa actividad

        correo = vista.findViewById(R.id.etemail);
        clave = vista.findViewById(R.id.etpass);

        iniciar = vista.findViewById(R.id.btniniciarsesion);
        registrarseaqui = vista.findViewById(R.id.tvregistrar);

        rq = Volley.newRequestQueue(getContext()); // aca se usa el metodo volley para poder conectar esos datos a la base de datos de sql en internet


        registrarseaqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(),regusuario.class));
            }
        });

        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iniciarsesion();

            }
        });

        return vista; // se retorna la variable vista
    }


    private void iniciarsesion() {

        // se le pone la url a la que se va a conectar para poder pedir datos

        // usamos la ip del equipo y la carpeta donde esta almecanada los datos y le decimos que variables va a buscar
        String url = "http://192.168.1.2/ServiciosWebAndroidPHP/buscarusuario.php?correo="+correo.getText().toString()+"&clave="+clave.getText().toString();

        jrq = new JsonObjectRequest(Request.Method.GET,url,null,this,this); // esto busca la base de datos
        rq.add(jrq); // esto añade la informacion a la BD


    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Toast.makeText(getContext(),"usuario no encontrado: ",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {

       // Toast.makeText(getContext(),"Usuario encontrado: ",Toast.LENGTH_SHORT).show();


        usuario usua = new usuario(); // esta es la clase que creamos

        // se crea arreglo json para almacenar datos

        JSONArray jsonarrar = response.optJSONArray("datos");
        JSONObject jsonObject = null;

        try {
            jsonObject = jsonarrar.getJSONObject(0); // esta es la posicion del arreglo

            usua.setUsr(jsonObject.optString("usr")); // se pasa al arreglo en la columna usr lo que tenga usua.setusr
            usua.setClave(jsonObject.optString("clave"));
            usua.setCorreo(jsonObject.optString("correo"));
            usua.setNombre(jsonObject.optString("nombre"));
        }

        catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intencion = new Intent(getContext(),logeado.class);  // se crea el intent pra los datos que se enviaran a la actividad

        intencion.putExtra(logeado.nombre,usua.getNombre());  // en la variable intencion se le envia al archivo logeado bajo la variable usua (que es la que llama la clase usuario) y se trae el get nombre y correo
        intencion.putExtra(logeado.correo,usua.getCorreo());
        startActivity(intencion);


    }
}