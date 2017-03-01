package com.ot.jgomez.recepcion.views.modificacliente;

import android.content.Context;

import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.BasePresenter;
import com.ot.jgomez.recepcion.views.BaseView;

import java.util.List;

/**
 * Created by jgomez on 22/02/17.
 */

public interface ModificaClienteContract {
    interface View extends BaseView<Presenter> {
        void fillData(List<ConsultaClientes> list);
    }

    interface Presenter extends BasePresenter<Context, View> {
        List<ConsultaClientes> getClientes(String search);
        List<ConsultaClientes> getAllClientes();
        List<ConsultaClientes> getClientesSinTratarLista();
    }
}
