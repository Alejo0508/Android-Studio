package com.example.wsphpmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class regusuario extends AppCompatActivity {

    EditText usr, nombre, correo, clave;
    Button registrar, regresar, listar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regusuario);

        usr = findViewById(R.id.etusrr);
        nombre = findViewById(R.id.etnombrer);
        correo = findViewById(R.id.etcorreor);
        clave = findViewById(R.id.etclaver);

        registrar = findViewById(R.id.btregistrarr);
        regresar = findViewById(R.id.btregresarr);
        listar = findViewById(R.id.btlistarr);

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),listadousuarios.class));
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String musr = usr.getText().toString();
                String mnombre = nombre.getText().toString();
                String mcorreo = correo.getText().toString();
                String mclave = clave.getText().toString();

                if (!musr.isEmpty() && !mnombre.isEmpty() && !mcorreo.isEmpty() && !mclave.isEmpty())
                {

                    metodoregistrarusuario(musr,mnombre,mcorreo,mclave);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Debe ingresar todos los datos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void metodoregistrarusuario(final String musr, final String mnombre, String mcorreo, String mclave) {


        String url = "http://192.168.1.2/ServiciosWebAndroidPHP/agregarusuario.php";

            StringRequest postRequest = new StringRequest(Request.Method.POST, url,


                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("1")) {
                                Toast.makeText(getApplicationContext(), "Registro de usuario realizado correctamente!", Toast.LENGTH_LONG).show();
                                usr.setText("");
                                nombre.setText("");
                                correo.setText("");
                                clave.setText("");
                                usr.requestFocus();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Correo Existente, Inténtelo con otro!", Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Registro de usuario incorrecto!", Toast.LENGTH_LONG).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {

                        Map<String, String> params = new HashMap<String, String>();
                        params.put("usr", usr.getText().toString().trim());
                        params.put("nombre", nombre.getText().toString().trim());
                        params.put("correo", correo.getText().toString().trim());
                        params.put("clave", clave.getText().toString().trim());
                        return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(postRequest);

        }
}
