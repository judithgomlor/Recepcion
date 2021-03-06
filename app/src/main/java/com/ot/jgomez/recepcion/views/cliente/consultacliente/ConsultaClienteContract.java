package com.ot.jgomez.recepcion.views.cliente.consultacliente;

import android.content.Context;

import com.ot.jgomez.recepcion.items.ConsultaClientes;
import com.ot.jgomez.recepcion.views.BasePresenter;
import com.ot.jgomez.recepcion.views.BaseView;

import java.util.List;

/**
 * Created by jgomez on 16/02/17.
 */

public interface ConsultaClienteContract {
    interface View extends BaseView<Presenter> {
        void fillData(List<ConsultaClientes> list);
    }

    interface Presenter extends BasePresenter<Context, View> {
        List<ConsultaClientes> getClientes();
    }
}
