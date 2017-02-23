package com.ot.jgomez.recepcion.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ot.jgomez.recepcion.R;
import com.ot.jgomez.recepcion.items.ConsultaClientes;

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

    Context mContext;
    List<ConsultaClientes> mList;

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
    public void onBindViewHolder(ListaBuscaClientesDialogAdapters.ViewHolder holder, int position) {
        String nombre = this.mList.get(position).getmNombre();
        String apellidos = this.mList.get(position).getmPrimerApellido() + " " +
                this.mList.get(position).getmSegundoApellido();

        holder.mNombreApellido.setText(nombre + " " + apellidos);
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }
}
