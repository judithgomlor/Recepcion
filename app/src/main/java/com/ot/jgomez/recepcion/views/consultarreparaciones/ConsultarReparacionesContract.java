package com.ot.jgomez.recepcion.views.consultarreparaciones;

import android.content.Context;

import com.ot.jgomez.recepcion.items.ConsultaReparacionesPendientes;
import com.ot.jgomez.recepcion.views.BasePresenter;
import com.ot.jgomez.recepcion.views.BaseView;

import java.util.List;

/**
 * Created by jgomez on 22/02/17.
 */

public interface ConsultarReparacionesContract {

    interface View extends BaseView<Presenter> {
        void fillData(List<ConsultaReparacionesPendientes> list);
    }

    interface Presenter extends BasePresenter<Context, View> {
        List<ConsultaReparacionesPendientes> getReparacionesPendientes(String fecha, boolean todas,
                                                                       boolean a_partir);

        List<ConsultaReparacionesPendientes> getReparacionesResueltas(String fecha, boolean todas,
                                                                      boolean a_partir);
    }

}
