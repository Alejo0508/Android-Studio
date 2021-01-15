package com.example.tallerbanco;

        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.Toast;
        import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText nombre, documento, fecha, valor;
    TextView resultado, total;
    RadioButton vivienda, vehiculo, inversion, educacion, uno, dos, tres;
    CheckBox cuota;
    Button enviar, borrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.etnombre);
        documento = findViewById(R.id.etdocumento);
        fecha = findViewById(R.id.etfecha);
        valor = findViewById(R.id.etvalor);
        resultado = findViewById(R.id.tvresultado);
        vivienda = findViewById(R.id.rbvivienda);
        vehiculo = findViewById(R.id.rbvehiculo);
        inversion = findViewById(R.id.rbinversion);
        educacion = findViewById(R.id.rbeducacion);
        uno = findViewById(R.id.rb12meses);
        dos = findViewById(R.id.rb24meses);
        tres = findViewById(R.id.rb36meses);
        cuota = findViewById(R.id.cbcuota);
        enviar = findViewById(R.id.btenviar);
        total = findViewById(R.id.tvtotal);
        borrar = findViewById(R.id.btborrar);



        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            nombre.setText("");
            documento.setText("");
            fecha.setText("");
            valor.setText("");
            resultado.setText("");
            total.setText("");
            vivienda.setChecked(false);
            educacion.setChecked(false);
            inversion.setChecked(false);
            vehiculo.setChecked(false);
            uno.setChecked(false);
            dos.setChecked(false);
            tres.setChecked(false);
            cuota.setChecked(false);

            }
        });

        enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DecimalFormat nro = new DecimalFormat("###,###,###");

        double mvalor = Double.parseDouble(valor.getText().toString());

        double credpres = 0, cmanejo = 0, totcredito, totalcuotas = 0;



        if (vivienda.isChecked())
        {
            credpres = mvalor * 0.01 ;
        }
        else
            if (educacion.isChecked())
            {
                credpres = mvalor * 0.012;
            }
         else
             if (inversion.isChecked())
             {
                 credpres = mvalor * 0.02;
             }
         else
             if (vehiculo.isChecked())
             {
                 credpres = mvalor * 0.015;
             }
         else
             {
                 Toast.makeText(getApplicationContext(), "Debe seleccionar un tipo de prestamo", Toast.LENGTH_SHORT).show();
             }

        totcredito = 0;

        if (uno.isChecked())
        {
            totcredito = mvalor + credpres * 12;
            totalcuotas = totcredito / 12;
        }
        else
            if (dos.isChecked())
            {
                totcredito = mvalor + credpres * 24;
                totalcuotas = totcredito / 24;
            }
         else
             if (tres.isChecked())
             {
                 totcredito = mvalor + credpres * 36;
                 totalcuotas = totcredito / 36;
             }
         else
             {
                 Toast.makeText(getApplicationContext(), "Debe seleccionar el numero de cuotas", Toast.LENGTH_SHORT).show();
             }


        if (cuota.isChecked())
        {
            cmanejo =  totalcuotas + 10000;
            totalcuotas = cmanejo;

        }


        total.setText(nro.format(totalcuotas));
        resultado.setText(nro.format(totcredito));
    }
}
