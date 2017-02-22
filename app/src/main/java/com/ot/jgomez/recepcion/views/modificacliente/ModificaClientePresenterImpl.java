package com.ot.jgomez.recepcion.views.modificacliente;

import android.content.Context;

import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.items.ConsultaClientes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgomez on 22/02/17.
 */

public class ModificaClientePresenterImpl implements ModificaClienteContract.Presenter {

    private Context mContext;
    private ModificaClienteContract.View mView;

    @Override
    public List<ConsultaClientes> getClientes(String search) {
        List<ConsultaClientes> list = new ArrayList<>();
        List<DBClientes> clientes = DBClientes.getAllClientes();
        if (clientes.size() > 0) {
            for (DBClientes entries : clientes) {
                if(entries.getNombreCliente().contains(search) ||
                        entries.getPrimerApellidoCliente().contains(search)) {
                    list.add(new ConsultaClientes(
                            entries.getNombreCliente(),
                            entries.getPrimerApellidoCliente(),
                            entries.getSegundoApellidoCliente(),
                            entries.getTelefonoCliente(),
                            entries.getMarcaVehiculo(),
                            entries.getModeloVehiculo(),
                            entries.getMatriculaVehiculo()
                    ));
                }
            }
        }
        return list;
    }

    @Override
    public void attach(Context context, ModificaClienteContract.View view) {
        this.mContext = context;
        this.mView = view;
    }
}
