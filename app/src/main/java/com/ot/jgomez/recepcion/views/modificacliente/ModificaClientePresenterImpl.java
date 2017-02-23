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
                if (entries.getNombreApellidos().contains(search) ||
                        entries.getPrimerApellidoCliente().contains(search)) {
                    list.add(new ConsultaClientes(
                            entries.getNombreCliente(),
                            entries.getPrimerApellidoCliente(),
                            entries.getSegundoApellidoCliente(),
                            entries.getNombreApellidos(),
                            entries.getTelefonoCliente(),
                            entries.getMarcaVehiculo(),
                            entries.getModeloVehiculo(),
                            entries.getMatriculaVehiculo()
                    ));
                }
            }
            list = this.trataList(list);
        }
        return list;
    }

    private List<ConsultaClientes> trataList(List<ConsultaClientes> list) {
        List<ConsultaClientes> aux = new ArrayList<>();
        ConsultaClientes clienteAnterior, clienteActual;
        clienteAnterior = list.get(0);
        aux.add(clienteAnterior);
        if (list.size() > 1) {
            for (int i = 0; i < list.size(); ++i) {
                clienteActual = list.get(i);
                if (!clienteAnterior.getmNombre().equals(clienteActual.getmNombre()) ||
                        !clienteAnterior.getmPrimerApellido().equals(clienteActual.getmPrimerApellido()) ||
                        !clienteAnterior.getmSegundoApellido().equals(clienteActual.getmSegundoApellido())) {
                    aux.add(clienteActual);
                }
                clienteAnterior = clienteActual;
            }
        }
        return aux;
    }

    @Override
    public void attach(Context context, ModificaClienteContract.View view) {
        this.mContext = context;
        this.mView = view;
    }
}
