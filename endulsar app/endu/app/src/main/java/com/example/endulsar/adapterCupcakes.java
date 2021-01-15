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

public class adapterCupcakes extends FirestoreRecyclerAdapter <Cupcakes, adapterCupcakes.ViewHolder>{

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapterCupcakes(@NonNull FirestoreRecyclerOptions <Cupcakes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Cupcakes model) {


        holder.nombre.setText(model.getNombre());
        holder.preciox6.setText(model.getPreciox6());
        holder.preciox9.setText(model.getPreciox9());
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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listar_cupcakes,null,false);
        return new ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;
        TextView nombre,preciox6, preciox9;
        ImageView imagen;
        Button pedir;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            nombre = itemView.findViewById(R.id.tvnombrecupcakes);
            preciox6 = itemView.findViewById(R.id.tvpreciocupcakex6);
            preciox9 = itemView.findViewById(R.id.tvpreciocupcakex9);
            imagen = itemView.findViewById(R.id.etimagencupcake);
            pedir = itemView.findViewById(R.id.btnpedircupcake);

        }

        public void setOnClickListeners() {
            pedir.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnpedircupcake:
                    context.startActivity(new Intent(context, pedidoCupcakes.class));
                    Intent intent = new Intent(context, pedidoCupcakes.class);
                    intent.putExtra("nombrep", nombre.getText());
                    intent.putExtra("preciop", preciox6.getText());
                    intent.putExtra("preciop", preciox9.getText());

                    context.startActivity(intent);
                    break;
            }
        }
    }



}


