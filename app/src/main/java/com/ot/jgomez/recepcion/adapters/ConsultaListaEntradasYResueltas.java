package com.ot.jgomez.recepcion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;

import java.util.List;

/**
 * Created by jgomez on 21/02/17.
 */

public class ConsultaListaEntradasYResueltas extends RecyclerView.Adapter<ConsultaListaEntradasYResueltas.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNombreApellido;
        public TextView mResumen;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mNombreApellido = (TextView) itemView.findViewById(R.id.txtvw_nombre_apellidos_lista_total_cuentas);
            this.mResumen = (TextView) itemView.findViewById(R.id.txtvw_resumen_lista_total_cuentas);
        }
    }

    List<ConsultaReparacionesPendientes> mList;
    Context mContext;

    public ConsultaListaEntradasYResueltas(List<ConsultaReparacionesPendientes> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.lista_total_cuentas, parent, false);
        ViewHolder vHold = new ViewHolder(view);
        return vHold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nombre = this.mList.get(position).getmNombre();
        String primer_apellido = this.mList.get(position).getmPrimerApellido();
        String segundo_apellido = this.mList.get(position).getmSegundoApellido();
        holder.mNombreApellido.setText(nombre + " " + primer_apellido + " " +segundo_apellido);
        holder.mResumen.setText(this.mList.get(position).getmResumenEntrada());
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }
}
