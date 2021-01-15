package com.example.wsphpmysql;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class usuarioadapter extends RecyclerView.Adapter<usuarioadapter.usuariosviewholder>{

    ArrayList <usuario> listaUsuarios; // se crea arreglo que guarde la informacion de la BD

    public usuarioadapter(ArrayList<usuario> listaUsuarios) { // se crea constructor donde se le dice que resiba los aprametros del arreglo
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public usuarioadapter.usuariosviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuarios, null, false); // esto infla el view holder y lo pasa al adaptador xml item_usuarios
        return new usuariosviewholder(view); // esto devuelve lo que lleno

    }

    @Override
    public void onBindViewHolder(@NonNull usuarioadapter.usuariosviewholder holder, int position) {

        holder.usr.setText(listaUsuarios.get(position).getUsr().toString()); // trae a la posicion la informacion que tiene el arreglo
        holder.nombre.setText(listaUsuarios.get(position).getNombre().toString());
        holder.correo.setText(listaUsuarios.get(position).getCorreo().toString());




    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size(); // se le dice que retorne el tamaño del arreglo, si esto no se le pone esto el hara retornando infinito
    }

    public class usuariosviewholder extends RecyclerView.ViewHolder {

        //  se pone variables que tiene el onBindViewHolder donde se almacena lo del arreglo
        TextView usr, correo, nombre;
        ImageView imagen;

        public usuariosviewholder(@NonNull View itemView) {

            super(itemView);

            usr = itemView.findViewById(R.id.etusr);
            nombre = itemView.findViewById(R.id.etnombre);
            correo = itemView.findViewById(R.id.etcorreo);
            imagen = itemView.findViewById(R.id.etimagen);
        }


    }
}
