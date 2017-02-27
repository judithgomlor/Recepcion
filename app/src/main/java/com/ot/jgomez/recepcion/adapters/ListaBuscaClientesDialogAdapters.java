package com.ot.jgomez.recepcion.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.modificacliente.ModificaClienteActivity;

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

    private static final String EXTRA_NOMBRE = "nombre";
    private static final String EXTRA_APELLIDOS = "apellidos";
    private static final String EXTRA_TELEFONO = "telefono";
    private Context mContext;
    private List<ConsultaClientes> mList;
    private ConsultaClientes mClienteSeleccionado;
    private int mPosition;
    private boolean mBoolSelected = false;
    private List<ConsultaClientes> mListAux;

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
        final String apellidos = this.mList.get(position).getmPrimerApellido() + " " +
                this.mList.get(position).getmSegundoApellido();
        final String telefono = this.mList.get(position).getmTelefono();

        holder.mNombreApellido.setText(nombre + " " + apellidos);
/*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBoolSelected = true;
                mPosition = position;
                igualaListas();
                cogeClienteSeleccionado();
                mList = new ArrayList<>();
                mList.add(mClienteSeleccionado);
                notifyDataSetChanged();
            }
        });*/
    }

    private void igualaListas() {
        this.mListAux = new ArrayList<>();
        this.mListAux = this.mList;
    }

    public boolean ismBoolSelected() {
        return this.mBoolSelected;
    }

    public void refrescaLista() {
        this.mList = new ArrayList<>();
        this.mList = this.mListAux;
        notifyDataSetChanged();
    }

    public ConsultaClientes cogeClienteSeleccionado() {
        this.mClienteSeleccionado = new ConsultaClientes(
                mList.get(this.mPosition).getmNombre(),
                mList.get(this.mPosition).getmPrimerApellido(),
                mList.get(this.mPosition).getmSegundoApellido(),
                mList.get(this.mPosition).getmNombreApellidos(),
                mList.get(this.mPosition).getmTelefono(),
                mList.get(this.mPosition).getmMarcaVehiculo(),
                mList.get(this.mPosition).getmModeloVehiculo(),
                mList.get(this.mPosition).getmMatriculaVehiculo());
        return this.mClienteSeleccionado;
    }


    @Override
    public int getItemCount() {
        return this.mList.size();
    }
}
