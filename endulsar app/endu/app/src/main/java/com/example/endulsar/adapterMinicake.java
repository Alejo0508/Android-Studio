package com.example.endulsar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.squareup.picasso.Picasso;

public class adapterMinicake extends FirestoreRecyclerAdapter <Minicake, adapterMinicake.ViewHolder>{

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapterMinicake(@NonNull FirestoreRecyclerOptions <Minicake> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Minicake model) {


        holder.nombre.setText(model.getNombre());
        holder.precio.setText(model.getPrecio());

        Picasso
                .get()
                .load(model.getImagen(position))
                .resize(200, 200)
                .placeholder(R.drawable.ic_launcher_background)  // precargado
                .error(R.drawable.ic_launcher_foreground)        // error de carga
                .into(holder.imagen);


        holder.setOnClickListeners();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listar_minicakes,null,false);
        return new ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        TextView nombre,precio;
        ImageView imagen;
        Button pedir;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            nombre = itemView.findViewById(R.id.tvnombreminicake);
            precio = itemView.findViewById(R.id.tvpreciominicake);
            imagen = itemView.findViewById(R.id.etimagenminicake);
            pedir = itemView.findViewById(R.id.btnpedirminicake);

        }

        public void setOnClickListeners() {
            pedir.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnpedirminicake:
                    context.startActivity(new Intent(context, pedido.class));
                    Intent intent = new Intent(context, pedido.class);
                    intent.putExtra("nombrep", nombre.getText());
                    intent.putExtra("preciop", precio.getText());
                    context.startActivity(intent);
                    break;
            }
        }
    }



}

