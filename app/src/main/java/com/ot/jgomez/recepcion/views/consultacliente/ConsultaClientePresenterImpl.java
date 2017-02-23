package com.ot.jgomez.recepcion.views.consultacliente;

import android.content.Context;

import com.ot.jgomez.recepcion.database.DBClientes;
import com.ot.jgomez.recepcion.items.ConsultaClientes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jgomez on 16/02/17.
 */

public class ConsultaClientePresenterImpl implements ConsultaClienteContract.Presenter {

    private Context mContext;
    private ConsultaClienteContract.View mView;

    @Override
    public List<ConsultaClientes> getClientes() {
        List<DBClientes> clientes = DBClientes.getAllClientes();
        List<ConsultaClientes> listClientes = new ArrayList<>();
        if(clientes.size() != 0) {
            for(int i = 0; i < clientes.size(); ++i) {
                listClientes.add(new ConsultaClientes(
                        clientes.get(i).getNombreCliente(),
                        clientes.get(i).getPrimerApellidoCliente(),
                        clientes.get(i).getSegundoApellidoCliente(),
                        clientes.get(i).getNombreApellidos(),
                        clientes.get(i).getTelefonoCliente(),
                        clientes.get(i).getMarcaVehiculo(),
                        clientes.get(i).getModeloVehiculo(),
                        clientes.get(i).getMatriculaVehiculo()
                ));
            }
        } else {
            this.mView.showMessage("Error al cargar los detalles del cliente");
        }
        return listClientes;
    }

    @Override
    public void attach(Context context, ConsultaClienteContract.View view) {
        this.mContext = context;
        this.mView = view;
    }
}
