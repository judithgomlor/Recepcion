package com.ot.jgomez.recepcion.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.modificacliente.ModificaClienteActivity;
import com.ot.jgomez.recepcion.views.modificacliente.buscacliente.BuscaClienteActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgomez on 23/02/17.
 */

public class ListaBuscaClientesDialogAdapters extends RecyclerView.Adapter<
        ListaBuscaClientesDialogAdapters.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNombreApellido;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mNombreApellido = (TextView) itemView.findViewById(
                    R.id.txtvw_nombres_apellidos_busca_dialogo);
        }
    }

    private Context mContext;
    private List<ConsultaClientes> mList;
    private ConsultaClientes mClienteSeleccionado;
    private int mPosition;
    private boolean mBoolSelected = false;
    private List<ConsultaClientes> mListAux;
    private static final String EXTRA_NOMBRE = "nombre";
    private static final String EXTRA_PRIMER_APELLIDO = "primer_apellido";
    private static final String EXTRA_SEGUNDO_APELLIDO = "segundo_apellido";
    private static final String EXTRA_TELEFONO = "telefono";

    public ListaBuscaClientesDialogAdapters(Context mContext, List<ConsultaClientes> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public ListaBuscaClientesDialogAdapters.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.lista_nombres_apellidos,
                parent, false);
        ViewHolder vHold = new ViewHolder(view);
        return vHold;
    }

    @Override
    public void onBindViewHolder(ListaBuscaClientesDialogAdapters.ViewHolder holder, final int position) {
        final String nombre = this.mList.get(position).getmNombre();
        final String primer_apellido = this.mList.get(position).getmPrimerApellido();
        final String segundo_apellido = this.mList.get(position).getmSegundoApellido();
        final String apellidos = primer_apellido + " " +segundo_apellido;
        final String telefono = this.mList.get(position).getmTelefono();

        holder.mNombreApellido.setText(nombre + " " + apellidos);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(EXTRA_NOMBRE, nombre);
                bundle.putString(EXTRA_PRIMER_APELLIDO, primer_apellido);
                bundle.putString(EXTRA_SEGUNDO_APELLIDO, segundo_apellido);
                bundle.putString(EXTRA_TELEFONO, telefono);

                pasaOtraActividad(bundle);
            }
        });
    }

    private void pasaOtraActividad(Bundle bundle) {
        if(this.mContext == null) {
            return;
        } else if (this.mContext instanceof BuscaClienteActivity) {
            BuscaClienteActivity activity = (BuscaClienteActivity) this.mContext;
            activity.pasaOtraActividad(bundle);
        }
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }
}
