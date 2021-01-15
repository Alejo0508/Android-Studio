package com.example.transaccionfirebase;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class adaptertransaccion  extends FirestoreRecyclerAdapter <transaccion, adaptertransaccion.ViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public adaptertransaccion(@NonNull FirestoreRecyclerOptions <transaccion> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull transaccion model) {

        holder.cuentaorigenr.setText(model.getNrocuentaorigen());
        holder.cuentadestinor.setText(model.getNrocuentadestino());
        holder.fechar.setText(model.getFecha());
        holder.horar.setText(model.getHora());
        holder.valorr.setText(model.getValor());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista,null,false);
        return new ViewHolder(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView cuentaorigenr, cuentadestinor, fechar, valorr , horar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cuentaorigenr = itemView.findViewById(R.id.etnrocuentaorigentrans);
            cuentadestinor = itemView.findViewById(R.id.etnrocuentadestinotrans);
            fechar = itemView.findViewById(R.id.etnfechatrans);
            horar = itemView.findViewById(R.id.etnhoratrans);
            valorr = itemView.findViewById(R.id.etnvalortrans);

        }
    }
}
