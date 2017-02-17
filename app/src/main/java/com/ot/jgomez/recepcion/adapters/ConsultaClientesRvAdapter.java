package com.ot.jgomez.recepcion.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.consultacliente.ConsultaClienteActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgomez on 16/02/17.
 */

public class ConsultaClientesRvAdapter extends RecyclerView.Adapter<ConsultaClientesRvAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNombre;
        public TextView mApellidos;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mNombre = (TextView) itemView.findViewById(R.id.txtvw_nombre_lista_clientes);
            this.mApellidos = (TextView) itemView.findViewById(R.id.txtvw_apellidos_lista_clientes);
        }
    }

    List<ConsultaClientes> mListClientes;
    Context mContext;
    private static final String EXTRA_NOMBRE = "nombre";
    private static final String EXTRA_PRIMER_APELLIDO = "primer_apellido";
    private static final String EXTRA_SEGUNDO_APELLIDO = "segundo_apellido";
    private static final String EXTRA_TELEFONO = "telefono";

    public ConsultaClientesRvAdapter(List<ConsultaClientes> mListClientes, Context mContext) {
        this.mListClientes = mListClientes;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.lista_clientes_items, parent,
                false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mNombre.setText(this.mListClientes.get(position).getmNombre() + " ");
        String primerApellido = this.mListClientes.get(position).getmPrimerApellido();
        String segundoApellido = this.mListClientes.get(position).getmSegundoApellido();
        String apellidos = primerApellido + " " +segundoApellido;
        holder.mApellidos.setText(apellidos);

        //cuando seleccionemos un cliente
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, ConsultaClienteActivity.class);
                myIntent.putExtra(EXTRA_NOMBRE, mListClientes.get(position).getmNombre());
                myIntent.putExtra(EXTRA_PRIMER_APELLIDO, mListClientes.get(position).getmPrimerApellido());
                myIntent.putExtra(EXTRA_SEGUNDO_APELLIDO, mListClientes.get(position).getmSegundoApellido());
                myIntent.putExtra(EXTRA_TELEFONO, mListClientes.get(position).getmTelefono());
                mContext.startActivity(myIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mListClientes.size();
    }

}
