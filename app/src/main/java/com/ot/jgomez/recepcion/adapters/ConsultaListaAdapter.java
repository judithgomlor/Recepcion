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

public class ConsultaListaAdapter extends RecyclerView.Adapter<ConsultaListaAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mNombreApellidos;
        public TextView mResumen;
        public TextView mDescripcion;
        public TextView mFecha;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mNombreApellidos = (TextView) itemView.findViewById(R.id.txtvw_nombre_apellidos_fragment_resource);
            this.mResumen = (TextView) itemView.findViewById(R.id.txtvw_resumen_fragment_resource);
            this.mDescripcion = (TextView) itemView.findViewById(R.id.txtvw_descripcion_fragment_resource);
            this.mFecha = (TextView) itemView.findViewById(R.id.txtvw_fecha_fragment_resource);
        }
    }

    List<ConsultaReparacionesPendientes> mList;
    Context mContext;
    boolean mHoy;

    public ConsultaListaAdapter(List<ConsultaReparacionesPendientes> mList, Context mContext,
                                boolean mHoy) {
        this.mList = mList;
        this.mContext = mContext;
        this.mHoy = mHoy;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.lista_total_pendientes_resueltas, parent, false);
        ViewHolder vHold = new ViewHolder(view);
        return vHold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String nombre = this.mList.get(position).getmNombre();
        String apellidos = this.mList.get(position).getmPrimerApellido() + " " +
                this.mList.get(position).getmSegundoApellido();
        holder.mNombreApellidos.setText(nombre + " " + apellidos);
        holder.mResumen.setText(this.mList.get(position).getmResumenEntrada());
        holder.mDescripcion.setText(this.mList.get(position).getmDescripcionEntrada());
        if(mHoy) {
            holder.mFecha.setText(this.mList.get(position).getmFechaEntrada());
        } else {
            holder.mFecha.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }
}
