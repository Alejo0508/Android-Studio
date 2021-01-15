package com.example.appsisacademiafb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class adapterusers extends FirestoreRecyclerAdapter <user, adapterusers.ViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adapterusers(@NonNull FirestoreRecyclerOptions<user> options) {  //el option es el que resive cada uno de los datos de la tabla
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull user model) {

        // se le trae los registros de la base de datos

        holder.user.setText(model.getUsr());
        holder.name.setText(model.getName());
        holder.phone.setText(model.getPhone());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuarios,null,false);
        return new ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // aca instanciamos los objetos del xml item_usuarios
        TextView user, name, phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // aca referenciamos los objetos

            user = itemView.findViewById(R.id.etuseritem);
            name = itemView.findViewById(R.id.etnameitem);
            phone = itemView.findViewById(R.id.etphoneitem);
        }
    }
}
