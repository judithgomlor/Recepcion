package com.ot.jgomez.recepcion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.items.ConsultaReparaciones;

import java.util.List;

/**
 * Created by jgomez on 8/02/17.
 */

public class ConsultaReparacionRvAdapter extends RecyclerView.Adapter<ConsultaReparacionRvAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNombreApellidos;
        public TextView mResumen;
        public TextView mVehiculo;

        public ViewHolder(View itemView) {
            super(itemView);
            this.mNombreApellidos = (TextView) itemView.findViewById(R.id.nombre_apellidos_persona);
            this.mResumen = (TextView) itemView.findViewById(R.id.resumen_reparacion);
            this.mVehiculo = (TextView) itemView.findViewById(R.id.vehiculo_reparacion);
        }
    }

    private List<ConsultaReparaciones> mList;
    private Context mContext;

    public ConsultaReparacionRvAdapter(List<ConsultaReparaciones> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.consulta_reparaciones_item_list,
                parent, false);
        ViewHolder vHold = new ViewHolder(view);
        return vHold;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mNombreApellidos.setText(this.mList.get(position).getNombreApellidos());
        holder.mResumen.setText(this.mList.get(position).getResumen());
        holder.mVehiculo.setText(this.mList.get(position).getVehiculo());
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }
}
