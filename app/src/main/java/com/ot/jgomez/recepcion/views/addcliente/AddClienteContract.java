package com.ot.jgomez.recepcion.views.addcliente;

import android.content.Context;

import com.ot.jgomez.recepcion.api.entities.Makes;
import com.ot.jgomez.recepcion.api.entities.ModelsForMake;
import com.ot.jgomez.recepcion.views.BasePresenter;
import com.ot.jgomez.recepcion.views.BaseView;
import com.ot.jgomez.recepcion.views.main.MainContract;

import java.util.List;

/**
 * Created by jgomez on 9/02/17.
 */

public interface AddClienteContract {
    interface View extends BaseView<MainContract.Presenter> {
        void fillDataMakes(List<Makes> list);
        void fillDataModelsbyMakes(List<ModelsForMake> list);
    }

    interface Presenter extends BasePresenter<Context, AddClienteContract.View> {
        void getMakes(String search);
        void getModelsbyMakes(int Id);
    }
}
